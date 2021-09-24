package ru.progwards.java1.archiver.wertor74;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HaffmanAlg {
    // запаковываем файл
    public void packFile(String PackFilename) {
        Path pathPackFile = Paths.get(PackFilename); // путь к оригинальному файлу
        byte [] bytesOriginalFile = readFile(pathPackFile); // читаем оригинальный файл в массив байт
        Map <Byte, Integer> mapFrequencyTable = frequencyTable(bytesOriginalFile); // создаём частотную таблицу
        List <Node> listHaffmanWood = haffmanWood(mapFrequencyTable); // строим дерево Хаффмана
        Map<Byte, String> mapEncodingTable = encodingTable(listHaffmanWood); // создаём кодировочную таблицу
        byte [] bytesCodedOriginalFile = codingOriginalFile(bytesOriginalFile, mapEncodingTable); // массив закодированного оригинального файла
        byte [] bytesExpansion = codingOriginalFileExpansion(pathPackFile); // массив байт расширения оригинального файла
        byte [] bytesEncodingTable = codingEncodingTable(mapEncodingTable); // массив байт кодировочной таблицы
        byte [] bytesFinalMassive = finalBytesMassive(bytesExpansion, bytesEncodingTable, bytesCodedOriginalFile); // собираем конечный массив байт для записи в врхивный файл
        Path pathArchiveFile = archiveFilePath(pathPackFile); // создаём путь для архивного файла
        writeFile(pathArchiveFile, bytesFinalMassive); // создаём архивный файл и записываем в него массив байт
    }
    // читаем файл и возвращаем массив бвйт
    public byte [] readFile (Path path) {
        byte [] bytesFile = {};
        try {
            bytesFile = Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println(e);
        }
        return bytesFile;
    }
    // создаём частотную таблицу
    public Map<Byte, Integer> frequencyTable (byte [] bytes) {
        Map <Byte, Integer> mapFT = new HashMap<>(); // словарь частотной таблицы
        for (Byte b : bytes) {
            if (mapFT.containsKey(b)) {
                mapFT.put(b, mapFT.get(b) + 1);
            } else {
                mapFT.put(b, 1);
            }
        }
        return mapFT;
    }
    // строим дерево Хаффмана
    public List <Node> haffmanWood (Map<Byte, Integer> mapFT) {
        Comparator<Node> comparator = new Comparator<>() { // компаратор по часторе
            @Override
            public int compare(Node o1, Node o2) {
                return o2.frequency.compareTo(o1.frequency);
            }
        };
        List <Node> lHW = new ArrayList<>(); // приоритетная очередь для дерева Хаффмана
        for (Map.Entry <Byte, Integer> map : mapFT.entrySet()) { // заполняем очередь элементами частотной таблицы
            lHW.add(new Node(map.getKey(), map.getValue()));
        }
        lHW.sort(comparator);
        while (lHW.size() != 1) { // собственно строим дерево Хаффмана
            lHW.add(new Node(null, lHW.get(lHW.size() - 1).frequency + lHW.get(lHW.size() - 2).frequency));
            lHW.get(lHW.size() - 1).leftChild = lHW.get(lHW.size() - 3);
            lHW.get(lHW.size() - 1).rightChild = lHW.get(lHW.size() - 2);
            lHW.remove(lHW.size() - 2);
            lHW.remove(lHW.size() - 2);
            lHW.sort(comparator);
        }
        return lHW;
    }
    // кодировочная таблица
    public Map<Byte, String> encodingTable (List<Node> lHW) {
        ArrayDeque<Node> adEncodingTable = new ArrayDeque<>(); // очередь для кодировочной таблицы
        Map<Byte, String> mapET = new HashMap<>(); // кодировочная таблица
        adEncodingTable.offer(lHW.get(0)); // добавляем дерево Хаффмана в очередь
        while (!adEncodingTable.isEmpty()) { // кодируем элементы проходя по дереву
            if (!adEncodingTable.peek().isLeaf()) {
                adEncodingTable.peek().leftChild.code = adEncodingTable.peek().code + "0";
                adEncodingTable.peek().rightChild.code = adEncodingTable.peek().code + "1";
                adEncodingTable.offer(adEncodingTable.peek().leftChild);
                adEncodingTable.offer(adEncodingTable.poll().rightChild);
            } else {
                mapET.put(adEncodingTable.peek().name, adEncodingTable.poll().code);
            }
        }
        return mapET;
    }
    // кодируем оригинальный файл и записываем в массив байт
    public byte [] codingOriginalFile (byte [] bytesFile, Map <Byte, String> mapET) {
        String intermidiate = "";
        String compressed = "";
        byte counter = 0;
        for (int i = 0; i < bytesFile.length; i++) { // собственно кодируем оригинальный файл
            intermidiate += mapET.get(bytesFile[i]);
        }
        // добавляем в конец нули, чтобы длинна сообщения была кратна 8
        for (int lenght = intermidiate.length(), delta = 8 - lenght % 8; counter < delta; counter++) {
            intermidiate += "0";
        }
        compressed = String.format("%8s", Integer.toBinaryString(counter & 0xff)).replace(" ", "0") + intermidiate;
        StringBuilder compressedString = new StringBuilder(compressed);
        byte [] bytesCOF = new byte[compressedString.length() / 8];
        for (int i = 0; i < bytesCOF.length; i++) {
            bytesCOF[i] = (byte) Integer.parseInt(compressedString.substring(i * 8, (i + 1) * 8), 2);
        }
        return bytesCOF;
    }
    // кодируем расширение оригинального файла в массив байт
    public byte [] codingOriginalFileExpansion (Path pathPF) {
        String expansion; // расширение оригинального файла
        expansion = pathPF.getFileName().toString();
        expansion = expansion.substring(expansion.indexOf("."), expansion.length());
        byte [] bytesExp = new byte[expansion.length()]; // массив байт расширения оригинального файла
        bytesExp = expansion.getBytes();
        return bytesExp;
    }
    // кодируем кодировочную таблицу в массив байт
    public byte [] codingEncodingTable (Map <Byte, String> mapET) {
        String stringET = ""; // стринг кодировочной таблицы
        for (Map.Entry <Byte, String> entry : mapET.entrySet()) {
            stringET += entry.getKey() + " " + entry.getValue() + " ";
        }
        byte [] bytesET = new byte[stringET.length()]; // массив байт кодировочной таблицы
        bytesET = stringET.getBytes();
        return bytesET;
    }
    // собираем конечный массив байт для записи в архивный файл
    public byte [] finalBytesMassive (byte [] bytesExp, byte [] bytesET, byte [] bytesCOF) {
        List <Byte> listBytesFinal = new ArrayList<>(); // конечный список бвйт
        // переводим длинны массивов расширения, кодировочной таблицы и закодированного оригинального файла в байты, разделяя их запятыми
        // символ & является точкой начала самих массивов расширения оригинального файла, кодировочной таблицы и архива
        String lenghtsMassives = String.valueOf(bytesExp.length) + "," + String.valueOf(bytesET.length) + "," + String.valueOf(bytesCOF.length) + "&";
        byte [] bytesMassivesLenght = {};
        bytesMassivesLenght = lenghtsMassives.getBytes();
        // добавляем в конечный список байт длинны массивов расширения, кодировочной таблицы и закодированного оригинального файла
        for (int i = 0; i < bytesMassivesLenght.length; i++) {
            listBytesFinal.add(bytesMassivesLenght[i]);
        }
        // добавляем поочерёдно сами массивы
        for (int i = 0; i < bytesExp.length; i++) {
            listBytesFinal.add(bytesExp[i]);
        }
        for (int i = 0; i < bytesET.length; i++) {
            listBytesFinal.add(bytesET[i]);
        }
        for (int i = 0; i < bytesCOF.length; i++) {
            listBytesFinal.add(bytesCOF[i]);
        }
        byte [] finalBM = new byte[listBytesFinal.size()]; // финальный массив байт для записи в архивный файл
        for (int i = 0; i < finalBM.length; i++) {
            finalBM[i] = listBytesFinal.get(i);
        }
        return finalBM;
    }
    // меняем расширение для архивного файла
    public Path archiveFilePath (Path pathPF) {
        String fileName = pathPF.getFileName().toString();
        fileName = fileName.substring(0, fileName.indexOf(".")) + "_archived.arh";
        pathPF = pathPF.getParent().resolve(fileName);
        return pathPF;
    }
    // записываем массив байт в файл
    public void writeFile (Path pathFile, byte [] bytesFile) {
        try {
            Files.createFile(pathFile);
            Files.write(pathFile, bytesFile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    // распаковываем архивный файл
    public void unpackFile(String unpackFilename) {
        Path pathUnpackFile = Paths.get(unpackFilename); // путь к архивному файлу
        byte [] bytesArchivedFile = readFile(pathUnpackFile); // читаем архивный файл
        int[] lenghtMassivesPlusSP = lenghtMassives(bytesArchivedFile); // получаем длинны массивов плюс точка начала массивов
        String originalFileExpansion = encodingOriginalFileExpansion(bytesArchivedFile, lenghtMassivesPlusSP); // получаем расширение оригинального файла
        Map<Byte, String> encodingTable = uncodingEncodingTable(bytesArchivedFile, lenghtMassivesPlusSP); // получаем кодировочную таблицу
        byte[] bytesOriginalFileArchive = bytesArchive(bytesArchivedFile,lenghtMassivesPlusSP); // получаем массив байт архива
        byte[] bytesUncodingOriginalFile = uncodingArchive(bytesOriginalFileArchive,encodingTable); // раскодируем архив
        Path pathUncodingArchivedFile = pathUncoding(pathUnpackFile, originalFileExpansion); // меняем раширение архивного файла
        writeFile(pathUncodingArchivedFile, bytesUncodingOriginalFile); // создаём файл и записываем в него массив байт
    }
    // получаем длинны массивов расширения оригинального файла, кодировочной талбицы и архива
    public int [] lenghtMassives (byte[] bytesAF) {
        int startingPoint = 0; // точка начала массивов расширения оригинального файла, кодировочной таблицы и самого архива
        List <Byte> listLenghtMassives = new ArrayList<>();
        for (int i = 0; i < bytesAF.length; i++) {
            if (bytesAF[i] != 38) { // находим точку начала самих массивов
                listLenghtMassives.add(bytesAF[i]); // добавляем в список байт длины массивов разделённые запятыми
            } else {
                startingPoint = i;
                break;
            }
        }
        byte [] bytesLenghtMassives = new byte[listLenghtMassives.size()]; // делаем массив для записи в string
        for (int i = 0; i < bytesLenghtMassives.length; i++) {
            bytesLenghtMassives [i] = listLenghtMassives.get(i);
        }
        String strLenghtMassives = "";
        try {
            strLenghtMassives += new String(bytesLenghtMassives, "UTF-8"); // записываем в string
        } catch (Exception e) {
            System.out.println(e);
        }
        String [] strLenghtMassivesArr = strLenghtMassives.split(","); // записываем в массив разделяя по запятым
        int[] intLenghtMassives = new int[strLenghtMassivesArr.length + 1]; // массив int длинн массивов, плюс в конец добавляем начальную точку
        for (int i = 0; i < strLenghtMassivesArr.length; i++) {
            intLenghtMassives[i] = Integer.parseInt(strLenghtMassivesArr[i]);
        }
        intLenghtMassives[intLenghtMassives.length - 1] = startingPoint;
        return intLenghtMassives;
    }
    // получаем расширение оригинального файла
    public String encodingOriginalFileExpansion (byte[] bytesAF, int[] lenghtMass) {
        String strOFE = ""; // расширение оригинального файла
        int start = lenghtMass[lenghtMass.length - 1] + 1; // начальная точка
        int lenght = lenghtMass[0]; // длинна массива расширения оригинального файла
        byte[] bytesOFE = new byte[lenght];
        for (int i = start; i < start + lenght; i++) {
            bytesOFE[i - start] = bytesAF[i];
        }
        try {
            strOFE += new String(bytesOFE, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return strOFE;
    }
    // получаем кодировочную таблицу
    public Map <Byte, String> uncodingEncodingTable (byte[] bytesAF, int[] lenghtMass) {
        Map <Byte, String> mapUncodingET = new HashMap<>(); // кодировочная таблица
        int start = lenghtMass[lenghtMass.length - 1] + 1 + lenghtMass[0]; // начальная точка массива кодировочной таблицы
        int lenght = lenghtMass[1]; // длинна массива кодировочной таблицы
        String [] strUncodingETArr = new String[lenght]; // массив стринг кодировочной таблицыы
        String strUncodingET = ""; // стринг кодировочной таблицы
        byte[] bytesUncodingET = new byte[lenght];
        for (int i = start; i < start + lenght; i++) {
            bytesUncodingET[i - start] = bytesAF[i];
        }
        try {
            strUncodingET += new String(bytesUncodingET, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        strUncodingETArr = strUncodingET.split(" ");
        for (int i = 0; i < strUncodingETArr.length - 1; i = i + 2) {
            mapUncodingET.put(Byte.valueOf(strUncodingETArr[i]), strUncodingETArr[i + 1]);
       }
        return mapUncodingET;
    }
    // получаем массив байт закодированного оригинального файла
    public byte [] bytesArchive (byte[] bytesAF, int[] lenghtNass) {
        int start = lenghtNass[lenghtNass.length - 1] + 1 + lenghtNass[0] + lenghtNass[1]; // начальная точка массива архива
        int lenght = lenghtNass[2]; // длинна массива архива
        byte[] bytesArch = new byte[lenght];
        for (int i = start; i < start + lenght; i++) {
            bytesArch[i - start] = bytesAF[i];
        }
        return bytesArch;
    }
    // раскодируем архив
    public byte[] uncodingArchive (byte[] bytesArch, Map<Byte, String> mapET) {
        StringBuilder sbUA = new StringBuilder();
        for (Byte b : bytesArch) { // переводим байты в биты
            sbUA.append(String.format("%8s", Integer.toBinaryString(b & 0xff)).replace(" ", "0"));
        }
        String delta = ""; // первый байт - количество добавленных нулей
        for (int i = 0; i < 8; i++) {
            delta += sbUA.charAt(i);
        }
        int ADDDED_ZEROES = Integer.parseInt(delta, 2); // количество добавленных нулей
        String current = "";
        List<Byte> byteUncodedUnpackFile = new ArrayList<>();
        for (int i = 8; i < sbUA.length() - ADDDED_ZEROES; i++) { // раскодируем
            current += sbUA.charAt(i);
            for (Map.Entry<Byte, String> m : mapET.entrySet()) {
                if (current.equals(m.getValue())) {
                    byteUncodedUnpackFile.add(m.getKey());
                    current = "";
                }
            }
        }
        byte[] bytesUA = new byte[byteUncodedUnpackFile.size()]; // массив байт для записи в файл
        for (int i = 0; i <bytesUA.length ; i++) {
            bytesUA[i] = byteUncodedUnpackFile.get(i);
        }
        return bytesUA;
    }
    // меняем расширение архивного файла на оригинальное
    public Path pathUncoding (Path pathUnc, String strOFE) {
        Path pathUOF = pathUnc.getParent();
        String fileName = pathUnc.getFileName().toString();
        String newFileName = fileName.substring(0, fileName.indexOf(".")) + "_unarсhived" + strOFE;
        pathUOF = pathUOF.resolve(newFileName);
        return pathUOF;
    }

    public static void main(String[] args) {
        HaffmanAlg haffmanAlg = new HaffmanAlg();
        haffmanAlg.packFile("c:/Users/wertor/Documents/JAVA/Archiver/infolder/Text_3.txt");
        haffmanAlg.unpackFile("c:/Users/wertor/Documents/JAVA/Archiver/infolder/Text_3_archived.arh");
    }
}
