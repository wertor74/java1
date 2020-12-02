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
        try {
            // читаем данные из входного файла в байтовый массив
            FileInputStream byteFile = new FileInputStream(inFileName);
            // записываем строку filter в файл, чтобы преобразовать её к байтам
            FileWriter fileWriter = new FileWriter("filefilter.txt");
            try {
                byteText = byteFile.readAllBytes();
                fileWriter.write(filter);
            } finally {
                byteFile.close();
                fileWriter.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            // читаем filter из файла в байтовый массив
            FileInputStream byteFilterFile = new FileInputStream("filefilter.txt");
            try {
                byteFilter = byteFilterFile.readAllBytes();
            } finally {
                byteFilterFile.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // собственно фильтруем
        for (int i = 0; i < byteText.length; i++) {
            for (int j = 0; j < byteFilter.length; j++) {
                if (byteText[i] == byteFilter[j]) {
                    byteText[i] = 0; // обнуляем символ, равный символу из filter
                }
            }
        }
        // записываем в файл
        try {
            FileOutputStream byteFile = new FileOutputStream(outFileName);
            try {
                for (int i = 0; i < byteText.length; i++) {
                    if (byteText[i] != 0) {
                        byteFile.write(byteText[i]);
                    }
                }
            } finally {
                byteFile.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception{
        String inFileName = "filein.txt";
        String outFileName = "fileout.txt";
        String filter = " -,.()";
        filterFile(inFileName, outFileName, filter);
    }
}
