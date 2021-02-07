package Ejercicio4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Utils {
    static final int PORT_NUM = 9876;
    static final int NUM_VIDAS = 10;

    static final String winMessage = "Enhorabuena, has ganado";

    static Pattern pattern = Pattern.compile("(\\d{4})");
    static Random randomGenerator = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void stringToArraylist(String combination, ArrayList clientCombination) {
        for (int i = 0; i < combination.length(); i++) {
            int n = Integer.parseInt(String.valueOf(combination.charAt(i)));
            clientCombination.add(n);
        }
    }

    public static String arrayListToString(ArrayList arraylist) {
        String combinacionGanador = "";
        for (int i = 0; i < arraylist.size(); i++) {
            combinacionGanador += arraylist.get(i);
        }
        return combinacionGanador;
    }
}
