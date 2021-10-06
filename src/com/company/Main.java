package com.company;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        System.out.println("How many sides are there to your dice>");
        int sides = input.nextInt();
        System.out.println("How many times would you like to throw the dice?");
        int repeats = input.nextInt();
        HashMap<Integer, Integer> thrown = new HashMap<Integer, Integer>();
        for (int i = 0; i < sides + 1; i++) {
            thrown.put(i, 0);
        }
        for (int i = 0; i < repeats; i++) {
            int randomNumber = (random.nextInt((sides))) + 1;
            thrown.put(randomNumber, thrown.get(randomNumber) + 1);
        }
        //Creating file and opening it to write too
        PrintWriter file = new PrintWriter("storageFile", "UTF-8");
        // 1) creates a list which holds all the entries of the hashmap 2) starts a comparison which outputs it starting from the biggest (e2)
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(thrown.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) {
                return e2.getValue() - e1.getValue();
            }
        });
        //Prints it and also writes it to a file
        for (Map.Entry<Integer, Integer> e : list) {
            double percentage2 = (((double) (e.getValue() + 1)) / (double) (repeats)) * 100;
            file.println(e.getKey() + " ---> " + e.getValue() + " times. " + String.format("%.2f", percentage2) + "%");
            System.out.println("you rolled a " + e.getKey() + " , " + e.getValue() + " times!!! This made up " + String.format("%.2f", percentage2) + "% of the rolls");
        }
        file.close();

    }
}
