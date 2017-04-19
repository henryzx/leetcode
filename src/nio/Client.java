package nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Created by zx on 2017/4/19.
 */
public class Client {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 100; i++) {

                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(new InetSocketAddress(InetAddress.getLoopbackAddress().getHostAddress(), 1234));
                ByteBuffer stringBuf = ByteBuffer.wrap("你好!😂".getBytes(StandardCharsets.UTF_16));
                System.out.println("wrote " + socketChannel.write(stringBuf));
                ByteBuffer buff = ByteBuffer.allocateDirect(1024);
                socketChannel.read(buff);
                buff.flip();

                System.out.println("from server: " + StandardCharsets.UTF_16.decode(buff).toString());

                socketChannel.close();

                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
