package ru.progwards.java1.lessons.maps;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SalesInfo {
    static List<String> loadFile = new ArrayList<>(); // коллекция для загрузок из файла
    public static int loadOrders(String fileName) {
        int lo = 0; // количество загруженных строк
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String nl = scanner.nextLine(); // переменная для строки из файла
                String [] nlArr = nl.split(", "); // передаём строку в массив
                if (nlArr.length == 4) { // проверяем 4 поля в строке, или нет
                    try { // проверяем парсится ли кол-во и цена в числа
                        Integer.parseInt(nlArr[2]);
                        Double.parseDouble(nlArr[3]);
                    } catch (NumberFormatException e) {
                        continue; // если нет, переходим к следующей строке
                    }
                    loadFile.add(nl); // если да, загружаем строку в коллекцию
                    lo++; // и увеличиваем кол-во загруженных строк
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return lo;
    }
    public static Map<String, Double> getGoods() {
        Map<String, Double> productAmount = new TreeMap<>(); // словарь для товара и суммы
        for (int i = 0; i < loadFile.size(); i++) {
            String product = loadFile.get(i).split(", ")[1];
            Double amount = Double.parseDouble(loadFile.get(i).split(", ")[3]);
            if (!productAmount.containsKey(product)) {
                productAmount.put(product, amount);
            } else {
                productAmount.put(product, productAmount.get(product) + amount);
            }
        }
        loadFile.clear();
        return productAmount;
    }
    public static Map<String, AbstractMap.SimpleEntry<Double, Integer>> getCustomers() {
        Map<String, AbstractMap.SimpleEntry<Double, Integer>> nameAmount = new TreeMap<>();
        for (int i = 0; i < loadFile.size(); i++) {
            String name = loadFile.get(i).split(", ")[0];
            Double amount = Double.parseDouble(loadFile.get(i).split(", ")[3]);
            Integer quantity = Integer.parseInt(loadFile.get(i).split(", ")[2]);
            if (!nameAmount.containsKey(name)) {
                nameAmount.put(name, new AbstractMap.SimpleEntry(amount, quantity));
            } else {
                nameAmount.put(name, new AbstractMap.SimpleEntry(nameAmount.get(name).getKey() + amount, nameAmount.get(name).getValue() + quantity));
            }
        }
        loadFile.clear();
        return nameAmount;
    }

    public static void main(String[] args) {
        System.out.println(loadOrders("salesinfo.csv"));
        System.out.println(getGoods());
        //System.out.println(getCustomers());
    }
}