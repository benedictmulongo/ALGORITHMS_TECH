package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //(char)(ch1 + Math.random() * (ch2 – ch1 + 1))
        int base = 0;
        int bound = 20;
        int [] W = {20,10,1,14,32,5};
        int [] P = {20,6,4,9,1,6};
        int Limit = 50;
        Knapsack_SimAnnea knapsack = new Knapsack_SimAnnea(W,P,Limit,W.length);
        System.out.print("[" );
        for(int e :knapsack.SimulatedAnnealing(5000))
            System.out.print(" " + e);
        System.out.println(" ]" );
    }
}
