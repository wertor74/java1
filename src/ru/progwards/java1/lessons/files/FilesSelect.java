package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilesSelect {
    public void selectFiles(String inFolder, String outFolder, List<String> keys) {
        ArrayList<Path> fileCollection = new ArrayList<>(); // список всех файлов
        // ищем файлы и заносим в список
        try {
            PathMatcher pathMatcher = FileSystems. getDefault().getPathMatcher("glob:**/*.txt");
            Files. walkFileTree(Paths.get(inFolder), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                    if (pathMatcher.matches(path)) fileCollection.add(path);
                    return FileVisitResult. CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult. CONTINUE;
                }
            });
            // сравниваем содержимое файла с keys
            for (Path path : fileCollection) {
                String fileContent = Files.readString(path); // записываем содержимое файла в строку
                for (int i = 0; i < keys.size(); i++) {
                    if (fileContent.contains(keys.get(i))) { // сравниваем
                        Path newPath = Paths.get(outFolder).resolve(keys.get(i));
                        Files.createDirectory(newPath); // создаём новую папку
                        Files.copy(path, newPath.resolve(path.getFileName())); // копируем файл
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(fileCollection);
    }

    public static void main(String[] args) {
        List<String> keys = new ArrayList<>();
        keys.add("arbuz");
        keys.add("banan");
        keys.add("vinograd");
        new FilesSelect().selectFiles("c:/Users/wertor/IdeaProjects", "c:/Users/wertor/Documents", keys);
    }
}