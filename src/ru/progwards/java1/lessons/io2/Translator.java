package ru.progwards.java1.lessons.io2;

import java.util.Arrays;
import java.util.Locale;

public class Translator {
    private String[] inLang, outLang;
    public Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }
    public String translate(String sentence) {
        String result = "";
        // разбиваем строку на составные части: слова, пробелы и знаки препинания
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = sentence.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                stringBuilder.append(chars[i]);
                if (i <= chars.length - 2) {
                    if (!Character.isAlphabetic(chars[i + 1])) stringBuilder.append('@');
                }
            } else {
                stringBuilder.append(chars[i]);
                stringBuilder.append('@');
            }
        }
        // передаём строку в массив
        String[] sentenceArr = stringBuilder.toString().split("@");
        // сравниваем
        for (int i = 0; i < sentenceArr.length; i++) {
            int count = 0; // счётчик если совпало
            for (int j = 0; j < inLang.length; j++) {
                if (sentenceArr[i].compareToIgnoreCase(inLang[j]) == 0 && outLang.length > j) {
                    count ++;
                    if (sentenceArr[i].compareTo(inLang[j]) != 0) {
                        result += outLang[j].substring(0, 1).toUpperCase() + outLang[j].substring(1);
                    } else {
                        result += outLang[j];
                    }
                }
            }
            // если не совпало, то выводим оригинал
            if (count == 0) result += sentenceArr[i];
        }
        return result;
    }

    public static void main(String[] args) {
        String[] inLang = {"привет", "мир"};
        String[] outLang = {"hello", "world"};
        String sentence = "Привет Мир!";
        Translator translator = new Translator(inLang, outLang);
        System.out.println(translator.translate(sentence));
    }
}