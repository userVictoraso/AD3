package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class Utils {

    //CONSTANTES
    final static String HOST = "localhost";
    final static int PUERTO = 2000;
    final static String pregunta = "¿Que le quieres decir al servidor?";

    //UTILIDADES
    static Scanner scan = new Scanner(System.in);
    static DataOutputStream dos = null;
    static DataInputStream dis = null;


    public static int numeroCaracteres(String string){
        int numero = 0;
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) != ' '){
                numero++;
            }
        }
        return numero;
    }
}
