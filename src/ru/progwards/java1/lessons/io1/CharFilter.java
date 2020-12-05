package ru.progwards.java1.lessons.io1;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws Exception {
        String text = "";
        // читаем данные из входного файла в байтовый массив
        FileReader reader = new FileReader(inFileName);
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            text = scanner.nextLine();
        }
        reader.close();
        // представляем текст из входного файла в виде массива символов
        char[] chrText = text.toCharArray();
        // представляем filter в виде массива символов
        char[] chrFilter = filter.toCharArray();
        // собственно фильтруем
        for (int i = 0; i < chrText.length; i++) {
            for (int j = 0; j < chrFilter.length; j++) {
                if (chrText[i] == chrFilter[j]) {
                    chrText[i] = '\u0000'; // обнуляем символ, равный символу из filter
                }
            }
        }
        // записываем в файл
        FileWriter writer = new FileWriter(outFileName);
        for (int i = 0; i < chrText.length; i++) {
            if (chrText[i] != '\u0000') {
                writer.write(chrText[i]);
            }
        }
        writer.close();
    }

    public static void main(String[] args) throws Exception{
        String inFileName = "filein.txt";
        String outFileName = "fileout.txt";
        String filter = " -,.()р";
        filterFile(inFileName, outFileName, filter);
    }
}