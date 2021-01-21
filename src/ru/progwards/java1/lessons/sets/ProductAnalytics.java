package ru.progwards.java1.lessons.sets;

import java.util.*;

public class ProductAnalytics {
    private List<Shop> shops;
    private List<Product> products;
    public ProductAnalytics(List<Product> products, List<Shop> shops) {
        this.shops = shops;
        this.products = products;
    }
    public Set<Product> existInAll() {
        Set<Product> inAll = new HashSet(existAtListInOne());
        for (Shop shop : shops) {
            inAll.retainAll(shop.getProducts());
        }
        return inAll;
    }
    public Set<Product> existAtListInOne() {
        Set<Product> listIn = new HashSet();
        for (Shop shop : shops) {
            listIn.addAll(shop.getProducts());
        }
        return listIn;
    }
    public Set<Product> notExistInShops() {
        Set<Product> notIn = new HashSet(products);
        for (Shop shop : shops) {
            notIn.removeAll(shop.getProducts());
        }
        return notIn;
    }
    public Set<Product> existOnlyInOne() {
        Set<Product> onlyIn = new HashSet();
        for (Product product : products) {
            int count = 0;
            for (Shop shop : shops) {
                if (shop.getProducts().contains(product)) {
                    count ++;

                }
            }
            if (count == 1) onlyIn.add(product);
        }
        return onlyIn;
    }

    public static void main(String[] args) {
        Product product_1 = new Product("Сотовый");
        Product product_2 = new Product("Планшет");
        Product product_3 = new Product("Ноутбук");
        Product product_4 = new Product("Компьютер");
        Product product_5 = new Product("Телевизор");
        Product product_6 = new Product("PS");
        Product product_7 = new Product("Пылесос");
        Product product_8 = new Product("Микроволновка");
        Product product_9 = new Product("Чайник");
        Product product_10 = new Product("Холодильник");
        List<Product> allProducts = new ArrayList();
        List<Product> products_1 = new ArrayList();
        List<Product> products_2 = new ArrayList();
        List<Product> products_3 = new ArrayList();
        allProducts.addAll(List.of(product_1, product_2, product_3, product_4, product_5, product_6, product_7, product_8, product_9, product_10));
        products_1.addAll(List.of(product_1, product_3, product_5, product_7));
        products_2.addAll(List.of(product_1, product_2, product_4, product_6, product_8));
        products_3.addAll(List.of(product_9, product_8, product_5, product_4, product_1));
        Shop magaz_1 = new Shop(products_1);
        Shop magaz_2 = new Shop(products_2);
        Shop magaz_3 = new Shop(products_3);
        List<Shop> allShops = new ArrayList();
        allShops.addAll(List.of(magaz_1, magaz_2, magaz_3));
        ProductAnalytics productAnalytics = new ProductAnalytics(allProducts, allShops);
        System.out.println(productAnalytics.existOnlyInOne());
    }
}
