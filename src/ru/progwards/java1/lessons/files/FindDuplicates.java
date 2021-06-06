package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FindDuplicates {
    public List<List<String>> findDuplicates(String startPath) {
        List<List<String>> result = new ArrayList<>();
        ArrayList<Path> fileCollection = new ArrayList<>();// общий список всех файлов в дереве
        try {
            // отсеиваем вложенные каталоги и записываем файлы в общий список
            PathMatcher pathMatcher = FileSystems. getDefault().getPathMatcher("glob:**/*.*");
            Files. walkFileTree(Paths.get(startPath), new SimpleFileVisitor<Path>() {
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
            // сравниваем файлы
            for (int i = 0; i < fileCollection.size() - 1; i++) {
                List<String> fileDuplicates = new ArrayList<>(); // список дубликатов
                for (int j = i + 1; j < fileCollection.size(); j++) {
                    // сравниваем имена
                    if (fileCollection.get(i).getFileName().equals(fileCollection.get(j).getFileName())) {
                        // сравниваем атрибуты
                        if (Files.getAttribute(fileCollection.get(i), "lastModifiedTime").equals(Files.getAttribute(fileCollection.get(j), "lastModifiedTime"))
                                && Files.getAttribute(fileCollection.get(i), "size").equals(Files.getAttribute(fileCollection.get(j), "size"))) {
                            // сравниваем содержимое файлов
                            if (Files.readString(fileCollection.get(i)).equals(Files.readString(fileCollection.get(j)))) {
                                // и записываем в список дубликатов
                                if (!fileDuplicates.contains(fileCollection.get(i).toString())) fileDuplicates.add(fileCollection.get(i).toString());
                                if (!fileDuplicates.contains(fileCollection.get(j).toString())) fileDuplicates.add(fileCollection.get(j).toString());
                            }
                        }
                    }
                }
                if (result.isEmpty()) { // проверяем списки на совпадение и записываем в result
                    if (!fileDuplicates.isEmpty()) result.add(fileDuplicates);
                } else {
                    if (!result.get(result.size() - 1).containsAll(fileDuplicates)) result.add(fileDuplicates);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new FindDuplicates().findDuplicates("c:/Users/wertor/IdeaProjects"));
    }
}