package ru.progwards.java1.lessons.io1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CharFilter {
    public static void filterFile(String inFileName, String outFileName, String filter) throws Exception {
        byte[] byteFilter = {};
        byte[] byteText = {};
            // читаем данные из входного файла в байтовый массив
            FileInputStream byteFile = new FileInputStream(inFileName);
            // записываем строку filter в файл, чтобы преобразовать её к байтам
            FileWriter fileWriter = new FileWriter("filefilter.txt");
                byteText = byteFile.readAllBytes();
                fileWriter.write(filter);
                byteFile.close();
                fileWriter.close();
            // читаем filter из файла в байтовый массив
            FileInputStream byteFilterFile = new FileInputStream("filefilter.txt");
                byteFilter = byteFilterFile.readAllBytes();
                byteFilterFile.close();
        // собственно фильтруем
        for (int i = 0; i < byteText.length; i++) {
            for (int j = 0; j < byteFilter.length; j++) {
                if (byteText[i] == byteFilter[j]) {
                    byteText[i] = 0; // обнуляем символ, равный символу из filter
                }
            }
        }
        // записываем в файл
        FileOutputStream byteFile1 = new FileOutputStream(outFileName);
        for (int i = 0; i < byteText.length; i++) {
            if (byteText[i] != 0) {
                byteFile1.write(byteText[i]);
            }
        }
        byteFile1.close();
    }

    public static void main(String[] args) throws Exception{
        String inFileName = "filein.txt";
        String outFileName = "fileout.txt";
        String filter = " -,.()";
        filterFile(inFileName, outFileName, filter);
    }
}
