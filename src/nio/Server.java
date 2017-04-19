package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zx on 2017/4/19.
 */
public class Server {

    static ExecutorService executor = Executors.newCachedThreadPool();

    static CompletionHandler<Integer, ReadResult> readComplete = new CompletionHandler<Integer, ReadResult>() {
        @Override
        public void completed(Integer readCount, ReadResult result) {
            System.out.println("thread " + Thread.currentThread().getName() + " read " + readCount);
            result.readBuffer.flip();
            result.channel.write(result.readBuffer, 3, TimeUnit.SECONDS, result.channel, writeComplete);
        }

        @Override
        public void failed(Throwable exc, ReadResult attachment) {

        }
    };

    static class ReadResult {
        ByteBuffer readBuffer;
        AsynchronousSocketChannel channel;
    }

    static CompletionHandler<Integer, AsynchronousSocketChannel> writeComplete = new CompletionHandler<Integer, AsynchronousSocketChannel>() {
        @Override
        public void completed(Integer writeCount, AsynchronousSocketChannel result) {
            System.out.println("thread " + Thread.currentThread().getName() + " wrote " + writeCount);
            try {
                result.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

        }
    };

    static CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> acceptComplete = new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
        @Override
        public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel serverSocketChannel) {
            try {
                System.out.println("Thread: " + Thread.currentThread().getName() + " accepted " + result.getRemoteAddress().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteBuffer dst = ByteBuffer.allocateDirect(1024);
            ReadResult readResult = new ReadResult();
            readResult.readBuffer = dst;
            readResult.channel = result;
            result.read(dst, 3, TimeUnit.SECONDS, readResult, readComplete);

            // accept another one
            serverSocketChannel.accept(serverSocketChannel, acceptComplete);
        }

        @Override
        public void failed(Throwable exc, AsynchronousServerSocketChannel serverSocketChannel) {
            // accept another one
            if (!(exc instanceof InterruptedException)) {
                serverSocketChannel.accept(serverSocketChannel, acceptComplete);
            }
        }
    };

    static class ShutdownHook extends Thread {
        @Override
        public void run() {
            executor.shutdownNow();
            System.out.println("exit");
        }
    }

    public static void main(String[] args) {
        try {
            Runtime.getRuntime().addShutdownHook(new ShutdownHook());

            AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withCachedThreadPool(executor, 10);
            AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
            InetSocketAddress address = new InetSocketAddress(1234);
            serverSocketChannel.bind(address);
            System.out.println("bound to " + address.getAddress().toString());
            serverSocketChannel.accept(serverSocketChannel, acceptComplete);
            System.out.println("start waiting accept");
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
