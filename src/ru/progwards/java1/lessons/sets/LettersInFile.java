package ru.progwards.java1.lessons.sets;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class LettersInFile {
    public static String process(String fileName) throws IOException {
        String text = ""; // в эту переменную будем считывать файл
        StringBuilder result = new StringBuilder(); // результат
        TreeSet<String> textSet = new TreeSet(); // список для сортировки
        // читаем файл
        FileReader reader = new FileReader(fileName);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
        }
        reader.close();
        // фильтруем буквы и добавляем в сортированный список
        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) textSet.add(String.valueOf(c));
        }
        // преобразуем список в строку
        for (String s : textSet) {
            result.append(s);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String fileName = "filein.txt";
        try {
            System.out.println(process(fileName));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
