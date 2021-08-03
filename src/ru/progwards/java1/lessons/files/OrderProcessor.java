package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class OrderProcessor {
    public Path pathOrders;
    public int countOrders = 0;
    public List<Order> orderList = new ArrayList<>(); // список ордеров
    public OrderProcessor(String startPath) {
        this.pathOrders = Paths.get(startPath);
    }
    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        ArrayList<Path> pathList = new ArrayList<>(); // список файлов с заказами
        try {
            PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???-??????-????.csv");
            Files.walkFileTree(pathOrders, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    if (pathMatcher.matches(path)) {
                        List<String> stringOrderItemList = Files.readAllLines(path);
                        for (int i = 0; i < stringOrderItemList.size(); i++) {
                            String [] linesOrderItemArr = stringOrderItemList.get(i).split(",");
                            if (linesOrderItemArr.length == 3) {
                                pathList.add(path);
                            } else {
                                countOrders ++;
                            }
                        }
                    } else {
                        countOrders ++;
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException e) {
                    return FileVisitResult.CONTINUE;
                }
            });
            for (Path path : pathList) {
                LocalDateTime orderDate = ZonedDateTime.parse(Files.getAttribute(path, "lastModifiedTime").toString()).toLocalDateTime();
                if (start == null) start = LocalDate.EPOCH;
                if (finish == null) finish = LocalDate.MAX;
                String [] fileNameArr = path.getFileName().toString().replace(".csv", "").split("-");
                if (orderDate.toLocalDate().compareTo(start) > -1 && orderDate.toLocalDate().compareTo(finish) < 1) {
                    if (shopId == null || fileNameArr[0].equals(shopId)) {
                        orderList.add(new Order());
                        orderList.get(orderList.size() - 1).shopId = fileNameArr[0];
                        orderList.get(orderList.size() - 1).orderId = fileNameArr[1];
                        orderList.get(orderList.size() - 1).customerId = fileNameArr[2];
                        orderList.get(orderList.size() - 1).datetime = orderDate;
                        List<String> stringOrderItemList = Files.readAllLines(path);
                        List<OrderItem> orderItemList = new ArrayList<>(); // список позиций в ордере
                        double sumOrderItems = 0.0; // сумма стоимости всех позиций в заказе
                        for (int i = 0; i < stringOrderItemList.size(); i++) {
                            orderItemList.add(new OrderItem());
                            String [] linesOrderItemArr = stringOrderItemList.get(i).split(",");
                            orderItemList.get(orderItemList.size() - 1).googsName = linesOrderItemArr[0].trim();
                            orderItemList.get(orderItemList.size() - 1).count = Integer.valueOf(linesOrderItemArr[1].trim());
                            orderItemList.get(orderItemList.size() - 1).price = Double.valueOf(linesOrderItemArr[2].trim());
                            sumOrderItems = sumOrderItems + orderItemList.get(orderItemList.size() - 1).count * orderItemList.get(orderItemList.size() - 1).price;
                        }
                        orderItemList.sort(new Comparator<>() {
                            @Override
                            public int compare(OrderItem o1, OrderItem o2) {
                                return o1.googsName.compareTo(o2.googsName);
                            }
                        });
                        orderList.get(orderList.size() - 1).items = orderItemList;
                        orderList.get(orderList.size() - 1).sum = sumOrderItems;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return countOrders;
    }
    public List<Order> process(String shopId) {
        loadOrders(null, null, shopId);
        orderList.sort(new Comparator<>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        });
        return orderList;
    }
    public Map<String, Double> statisticsByShop() {
        Map<String, Double> sbs = new TreeMap<>();
        process(null);
        for (Order order : orderList) {
            if (sbs.containsKey(order.shopId)) {
                sbs.put(order.shopId, sbs.get(order.shopId) + order.sum);
            } else {
                sbs.put(order.shopId, order.sum);
            }
        }
        return sbs;
    }
    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> sbg = new TreeMap<>();
        process(null);
        for (Order order : orderList) {
            for (OrderItem oI : order.items) {
                if (sbg.containsKey(oI.googsName)) {
                    sbg.put(oI.googsName, sbg.get(oI.googsName) + oI.count * oI.price);
                } else {
                    sbg.put(oI.googsName, oI.count * oI.price);
                }
            }
        }
        return sbg;
    }
    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> sbd = new TreeMap<>();
        process(null);
        for (Order order : orderList) {
            if (sbd.containsKey(order.datetime.toLocalDate())) {
                sbd.put(order.datetime.toLocalDate(), sbd.get(order.datetime.toLocalDate()) + order.sum);
            } else {
                sbd.put(order.datetime.toLocalDate(), order.sum);
            }
        }
        return sbd;
    }

    public static void main(String[] args) {
        OrderProcessor op = new OrderProcessor("c:/Users/wertor/Documents/JAVA/OrderProcessor");
        System.out.println(op.loadOrders(LocalDate.of(2021, 06, 01), LocalDate.of(2021, 06, 10), "S01"));
        //System.out.println(op.process("S01"));
        //System.out.println(op.statisticsByShop());
        //System.out.println(op.statisticsByGoods());
        //System.out.println(op.statisticsByDay());
    }
}