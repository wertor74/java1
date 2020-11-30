package ru.progwards.java1.lessons.io1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Arrays;

public class Coder {
    public static void codeFile(String inFileName, String outFileName, char[] code, String logName) {
        byte[] simbol;
        try {
            FileInputStream byteFile = new FileInputStream(inFileName);
            try {
                simbol = byteFile.readAllBytes();
            } finally {
                byteFile.close();
            }
            FileWriter fileWriter = new FileWriter(outFileName);
            try {
                for (int i = 0; i < simbol.length; i++) {
                    fileWriter.write(code[(int) simbol[i]]);
                }
            } finally {
                fileWriter.close();
            }
        } catch (Exception e) {
            try {
                FileWriter logWriter = new FileWriter(logName, true);
                try {
                    logWriter.write(e.getMessage() + "\n");
                } finally {
                    logWriter.close();
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        String inFileName = "filein.txt";
        String outFileName = "fileout.txt";
        String logName = "filelog.txt";
        char[] code = {'!', '@', '#', '$', '%', '^', '&', '*', '-', '+'};
        byte[] simbolout = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        try {
            FileOutputStream byteFile = new FileOutputStream(inFileName);
            try {
                byteFile.write(simbolout);
            } finally {
                byteFile.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        codeFile(inFileName, outFileName, code, logName);
    }
}
