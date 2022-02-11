package com.tekion;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int activeMatch = 1;
        do {
            MatchController matchController = new MatchController();
            matchController.startMatch();
            System.out.println("Want to play another match?\n1. Press 1 for Yes\n2. Press 2 for No");
            activeMatch = Integer.parseInt(br.readLine());
        }
        while (activeMatch == 1);
    }
}
