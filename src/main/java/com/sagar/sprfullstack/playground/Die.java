package com.sagar.sprfullstack.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Die {
    public static void main(String args[]) {
        Random random = new Random();
        List<Integer> rolls = new ArrayList<>();
        for(int i = 0; i<=99; i++){
            Integer roll = random.nextInt((6-1)+1)+1;
            rolls.add(roll);
        }
        rolls.forEach(roll -> System.out.print(roll + " | "));

        HashMap<Integer, Integer> dieCount = new HashMap<>();
        rolls.forEach(roll -> {
            if(!dieCount.containsKey(roll))
                dieCount.put(roll, 1);
            else if(dieCount.containsKey(roll))
                dieCount.put(roll, (dieCount.get(roll) + 1));
        });

        System.out.println(dieCount);
    }
}
