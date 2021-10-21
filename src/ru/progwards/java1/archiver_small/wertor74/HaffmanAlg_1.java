package ru.progwards.java1.archiver_small.wertor74;

import ru.progwards.java1.archiver.wertor74.HaffmanAlg;
import ru.progwards.java1.archiver.wertor74.Node;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class HaffmanAlg_1 {
    public Path pathPackFile; // путь к оригинальному файлу
    public Map<Byte,String> mapEncodingTable; // кодировочная таблица
    // запаковываем файл
    public void packFile(String PackFilename) {
        pathPackFile = Paths.get(PackFilename);
        byte [] bytesOriginalFile = readFile(pathPackFile); // читаем оригинальный файл в массив байт
        Map<Byte, Integer> mapFrequencyTable = frequencyTable(bytesOriginalFile); // создаём частотную таблицу
        List<Node_1> listHaffmanWood = haffmanWood(mapFrequencyTable); // строим дерево Хаффмана
        mapEncodingTable = encodingTable(listHaffmanWood); // создаём кодировочную таблицу
        byte [] bytesCodedOriginalFile = codingOriginalFile(bytesOriginalFile, mapEncodingTable); // массив закодированного оригинального файла
        Path pathArchiveFile = archiveFilePath(pathPackFile); // создаём путь для архивного файла
        writeFile(pathArchiveFile, bytesCodedOriginalFile); // создаём архивный файл и записываем в него массив байт
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
    public List <Node_1> haffmanWood (Map<Byte, Integer> mapFT) {
        Comparator<Node_1> comparator = new Comparator<>() { // компаратор по часторе
            @Override
            public int compare(Node_1 o1, Node_1 o2) {
                return o2.frequency.compareTo(o1.frequency);
            }
        };
        List <Node_1> lHW = new ArrayList<>(); // приоритетная очередь для дерева Хаффмана
        for (Map.Entry <Byte, Integer> map : mapFT.entrySet()) { // заполняем очередь элементами частотной таблицы
            lHW.add(new Node_1(map.getKey(), map.getValue()));
        }
        lHW.sort(comparator);
        while (lHW.size() != 1) { // собственно строим дерево Хаффмана
            lHW.add(new Node_1(null, lHW.get(lHW.size() - 1).frequency + lHW.get(lHW.size() - 2).frequency));
            lHW.get(lHW.size() - 1).leftChild = lHW.get(lHW.size() - 3);
            lHW.get(lHW.size() - 1).rightChild = lHW.get(lHW.size() - 2);
            lHW.remove(lHW.size() - 2);
            lHW.remove(lHW.size() - 2);
            lHW.sort(comparator);
        }
        return lHW;
    }
    // кодировочная таблица
    public Map<Byte, String> encodingTable (List<Node_1> lHW) {
        ArrayDeque<Node_1> adEncodingTable = new ArrayDeque<>(); // очередь для кодировочной таблицы
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
        StringBuilder intermidiate = new StringBuilder();
        String compressed = "";
        byte counter = 0;
        for (int i = 0; i < bytesFile.length; i++) { // собственно кодируем оригинальный файл
            intermidiate.append(mapET.get(bytesFile[i]));
        }
        // добавляем в конец нули, чтобы длинна сообщения была кратна 8
        for (int lenght = intermidiate.length(), delta = 8 - lenght % 8; counter < delta; counter++) {
            intermidiate.append("0");
        }
        compressed = String.format("%8s", Integer.toBinaryString(counter & 0xff)).replace(" ", "0") + intermidiate;
        StringBuilder compressedString = new StringBuilder(compressed);
        //System.out.println("compressedString" + compressedString);
        byte [] bytesCOF = new byte[compressedString.length() / 8];
        for (int i = 0; i < bytesCOF.length; i++) {
            bytesCOF[i] = (byte) Integer.parseInt(compressedString.substring(i * 8, (i + 1) * 8), 2);
        }
        return bytesCOF;
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
        byte[] bytesUncodingOriginalFile = uncodingArchive(bytesArchivedFile, mapEncodingTable); // раскодируем архив
        Path pathUncodingArchivedFile = pathUncoding(pathPackFile); // меняем раширение архивного файла
        writeFile(pathUncodingArchivedFile, bytesUncodingOriginalFile); // создаём файл и записываем в него массив байт
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
    public Path pathUncoding (Path pathUnc) {
        Path pathUOF = pathUnc.getParent();
        String fileName = pathUnc.getFileName().toString();
        String expansion = fileName.substring(fileName.indexOf("."));
        String newFileName = fileName.substring(0, fileName.indexOf(".")) + "_unarсhived" + expansion;
        pathUOF = pathUOF.resolve(newFileName);
        return pathUOF;
    }

    public static void main(String[] args) {
        HaffmanAlg_1 haffmanAlg_1 = new HaffmanAlg_1();
        haffmanAlg_1.packFile("c:/Users/wertor/Documents/JAVA/Archiver/infolder/test.xml");
        haffmanAlg_1.unpackFile("c:/Users/wertor/Documents/JAVA/Archiver/infolder/test_archived.arh");
    }
}