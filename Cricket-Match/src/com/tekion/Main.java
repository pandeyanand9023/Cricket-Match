package com.tekion;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int active_match = 1;
        do {
            MatchController m = new MatchController();
            m.startMatch();
            System.out.println("Want to play another match?\n1. Press 1 for Yes\n2. Press 2 for No");
            active_match = Integer.parseInt(br.readLine());
        }
        while (active_match == 1);
    }
}
