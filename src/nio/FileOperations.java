package nio;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Created by zx on 2017/4/19.
 */
public class FileOperations {
    public static void main(String[] args) {
        traversePath();
        //writeAndCopyFile();
    }

    public static void writeAndCopyFile() {
        try {
            Files.createDirectories(Paths.get("d:\\temp"));
            Files.deleteIfExists(Paths.get("d:\\temp\\from.txt"));
            Path fromFile = Files.createFile(Paths.get("d:\\temp\\from.txt"));
            BufferedWriter writer = Files.newBufferedWriter(fromFile);
            for (int i = 0; i < 10000; i++) {
                writer.write("wtf ");
                writer.write(Integer.toString(i));
                writer.newLine();
            }
            writer.flush();
            writer.close();

            Path toFile = Files.copy(fromFile, Paths.get("d:\\temp\\to.txt"), REPLACE_EXISTING);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            long readByteCount = Files.copy(toFile, stream);
            System.out.println("readByteCount: " + readByteCount);
            System.out.println("toFile exists: " + Files.exists(toFile));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class TimedRunner {
        TimedRunner(Runnable runnable) {
            long startTime = System.nanoTime();
            try {
                runnable.run();
            } finally {
                long operationTime = System.nanoTime() - startTime;
                System.out.println(String.format(Locale.US, "operation takes %d ms", operationTime / 1000 / 1000));
            }
        }
    }

    public static void traversePath() {
        new TimedRunner(() -> {
            Path rootPath = Paths.get("d:\\Baidu");
            System.out.println("start visit " + rootPath);
            try {
                Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Objects.requireNonNull(file);

                        System.out.println(file.toString());

                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
