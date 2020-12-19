package ru.progwards.java1.lessons.io2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Censor {
    public static class CensorException extends RuntimeException {
        public String inoutFileName = "";
        public CensorException(String inoutFileName) {
            this.inoutFileName = inoutFileName;
        }
        @Override
        public String getMessage() {
            return inoutFileName;
        }
    }
    public static void censorFile(String inoutFileName, String[] obscene) {
        StringBuilder text = new StringBuilder();
        // читаем файл в строку
        try (FileReader fr = new FileReader(inoutFileName)) {
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                text.append(sc.nextLine());
            }
        } catch (Exception e) {
            throw new CensorException(e.getMessage());
        }
        // ищем совпадения и меняем на *
        for (int i = 0; i < obscene.length; i++) {
            int iO = text.indexOf(obscene[i]);
            if (iO != -1) {
                StringBuilder obs = new StringBuilder("");
                for (char c : obscene[i].toCharArray()) {
                    c = '*';
                    obs.append(c);
                }
                text.replace(iO, iO + obscene[i].length(), obs.toString());
            }
        }
        // записываем в файл
        try (FileWriter fw = new FileWriter(inoutFileName)) {
            fw.write(text.toString());
        } catch (Exception e) {
            throw new CensorException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inoutFileName = "aaaaaaa.txt";
        String[] obscene = {"day", "two", "count", "storey", "write"};
        try {
            censorFile(inoutFileName, obscene);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}