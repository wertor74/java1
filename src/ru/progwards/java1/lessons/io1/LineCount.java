package ru.progwards.java1.lessons.io1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LineCount {
    public static int calcEmpty(String fileName) {
        int i = 0;
        try {
            FileReader reader = new FileReader(fileName);
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().isEmpty()) i++;
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e + ", ");
            return -1;
        }
        return i;
    }

    public static void main (String[] args) throws IOException {
        String fileName = "file1.txt";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write("\n");
        //fileWriter.write("Строка 1.\n");
        //fileWriter.write("\n");
        //fileWriter.write("Строка 2.\n");
        //fileWriter.write("\n");
        //fileWriter.write("Строка 3.\n");
        //fileWriter.write("\n");
        //fileWriter.write("Строка 4.\n");
        fileWriter.close();
        System.out.println(calcEmpty("file1.txt"));
    }
}
