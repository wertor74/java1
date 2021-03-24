package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class UsageFrequency {
    public static List<String> text; // коллекция для содержимого файла
    public static String fileName;
    public static void processFile(String fileName) {
        text = new ArrayList<>();
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNext()) {
                text.add(scanner.next());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static Map<Character, Integer> getLetters() {
        processFile(fileName);
        List<Character> symbol = new ArrayList<>(); // коллекция для символов из файла
        Map<Character, Integer> letters = new HashMap<>(); // словарь для символов и количества
        // добавляем символы в коллекцию и убираем не нужные
        for (int i = 0; i < text.size(); i++) {
            for (char c : text.get(i).toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    symbol.add(c);
                }
            }
        }
        // считаем частоту появления символа в тексте и добавляем в словарь
        for (char c : symbol) {
            if (!letters.containsKey(c)) {
                int count = Collections.frequency(symbol, c);
                letters.put(c, count);
            }
        }
        return letters;
    }
    public static Map<String, Integer> getWords() {
        processFile(fileName);
        List<String> word = new ArrayList<>(); // коллекция для слов в тексте
        Map<String, Integer> words = new HashMap<>(); // словарь для слов и колличества
        // убираем ненужные символы из слов и добавляем в коллекцию
        for (int i = 0; i < text.size(); i++) {
            String wordText = "";
            for (char c : text.get(i).toCharArray()) {
                if (Character.isLetterOrDigit(c)) {
                    wordText += String.valueOf(c);
                }
            }
            word.add(wordText);
        }
        // считаем частоту употребления слов и добавляем в словарь
        for (int i = 0; i < word.size(); i++) {
            if (!word.get(i).isEmpty()) {
                if (words.containsKey(word.get(i))) {
                    words.put(word.get(i), words.get(word.get(i)) + 1);
                } else {
                    words.put(word.get(i), 1);
                }
            }
        }
        return words;
    }

/*    public static void main(String[] args) {
        fileName = "wiki.test.tokens";
        processFile(fileName);
        //getLetters();
        System.out.println(getWords());
    }*/
}
