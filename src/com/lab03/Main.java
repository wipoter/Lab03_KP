package com.lab03;

import com.lab03.Product.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //map 1
        Map<String, List<Product>> map1 = new HashMap<>();
        setMap(map1, "input1.txt");
        System.out.println("First map:");
        printMap(map1, 3);

        //map 2
        Map<String, List<Product>> map2 = new HashMap<>();
        setMap(map2, "input2.txt");
        System.out.println("Second map:");
        printMap(map2, 3);

        //map comp
        Map<String, List<Product>> map = new HashMap<>();
        commonMap(map, map1, map2);
        System.out.println("Common map:");
        printMap(map, 4);

        //sorted map
        System.out.println("Sorted map:");
        sortMap(map);
        printMap(map, 4);

        //map of names
        Map<String, List<Product>> mapOFNamesOfProduct = new HashMap<>();
        for(Map.Entry<String, List<Product>> tmp: map.entrySet()) {
            tmp.getValue().forEach((n) -> {
                String name = n.getName();
                if (!mapOFNamesOfProduct.containsKey(n.getName())) {
                    mapOFNamesOfProduct.put(name, new ArrayList<>());
                    mapOFNamesOfProduct.get(name).add(n);
                }
                else
                    mapOFNamesOfProduct.get(name).add(n);
            });
        }

        //count of  names
        System.out.println("Count of names: ");
        amountByName(mapOFNamesOfProduct);

        //Amount
        System.out.println("Amount of products: " + amountOfProducts(map));

        //full cost
        System.out.println("Full cost: " + costOfAll(map));
    }

    public static void printMap(Map<String, List<Product>> map, int num) {
        for (Map.Entry<String, List<Product>> tmp : map.entrySet()) {
            for (int i = 0; i < num && i < tmp.getValue().size(); i++)
                System.out.println(tmp.getValue().get(i).toString());
            System.out.println();
        }
    }

    public  static void setMap(Map<String, List<Product>> map, String path) throws IOException {
        FileReader read = new FileReader(path);
        Scanner scan = new Scanner(read);

        while (scan.hasNext()){
            String[] lines = scan.nextLine().split(" ");
            if(!map.containsKey(lines[2])){
                map.put(lines[2], new ArrayList<>());
                map.get(lines[2]).add(new Product(lines[0], lines[1], lines[2], Integer. parseInt(lines[3]), Integer. parseInt(lines[4])));
            }
            else
                map.get(lines[2]).add(new Product(lines[0], lines[1], lines[2], Integer. parseInt(lines[3]), Integer. parseInt(lines[4])));
        }
        read.close();
    }

    public static void commonMap(Map<String, List<Product>> map, Map<String, List<Product>> map1, Map<String, List<Product>> map2){
        map.putAll(map1);
        for(Map.Entry<String, List<Product>> tmp: map2.entrySet()){
            if(!map.containsKey(tmp.getKey()))
                map.put(tmp.getKey(), tmp.getValue());
            else{
                tmp.getValue().forEach((n) -> {
                    map.get(tmp.getKey()).add(n);
                });
            }
        }
    }

    public static void sortMap(Map<String, List<Product>> map){
        for(Map.Entry<String, List<Product>> tmp: map.entrySet()){
            Collections.sort(tmp.getValue(), new Comparator<Product>(){
                public int compare(Product p1, Product p2){
                    if(p1.getCost() > p2.getCost()) return -1;
                    if(p1.getCost() < p2.getCost()) return 1;
                    return 0;
                }
            });
        }
    }

    public static void amountByName(Map<String, List<Product>> map){
        for(Map.Entry<String, List<Product>> tmp: map.entrySet()){
            System.out.println(tmp.getKey() + ": " + tmp.getValue().size());
        }
    }

    public static int amountOfProducts(Map<String, List<Product>> map){
        int amount = 0;
        for(Map.Entry<String, List<Product>> tmp: map.entrySet()){
            amount += tmp.getValue().size();
        }
        return amount;
    }

    public static int costOfAll(Map<String, List<Product>> map) {
        int cost = 0;
        for (Map.Entry<String, List<Product>> tmp : map.entrySet()) {
            cost += tmp.getValue().stream().mapToInt(Product::getCost).sum();
        }
        return cost;
    }
}
