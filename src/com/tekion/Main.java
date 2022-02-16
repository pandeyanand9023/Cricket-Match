package com.tekion;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String activeMatch = "1";
        do {
            MatchController matchController = new MatchController(br);
            matchController.startMatch();
            System.out.println("Want to play another match?\n1. Press 1 for Yes\n2. Press 2 for No");
            activeMatch = br.readLine();
        }
        while (activeMatch.equals("1"));
    }
}
