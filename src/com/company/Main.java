package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] field = input.nextLine().split(" ");
        int numOfRoots = Integer.parseInt(input.nextLine());
        int quantity = 0;
        for (int i = 0; i < numOfRoots; i++) {

            String[] temp = input.nextLine().split(" ");
            int rootStart = Integer.parseInt(temp[0]);
            int rootStep = Integer.parseInt(temp[1]);

            int tempQuantity = getResources(field, rootStep, rootStart);

            if(tempQuantity>quantity){
                quantity = tempQuantity;
            }
        }
        System.out.println(quantity);
    }

    private static int getResources(String[] field, int rootStep, int rootStart) {
        HashMap<String, Integer> collected = new HashMap<>();
        int position = rootStart;
        while (true) {
            while(position >= field.length){
                position -= field.length;
            }

            String[] resourceTemp = field[position].split("_");
            String name = resourceTemp[0];
            int value = 0;

            if (resourceTemp.length < 2) {
                value = 1;
            } else {
                value = Integer.parseInt(resourceTemp[1]);
            }

            if (collected.containsKey(name)) {
                break;
            } else if (name.equals("stone") || name.equals("gold")
                    || name.equals("wood") || name.equals("food")) {
                collected.put(name, value);
            }
            position += rootStep;
        }
        return collected.values().stream().reduce(0, Integer::sum);
    }
}
