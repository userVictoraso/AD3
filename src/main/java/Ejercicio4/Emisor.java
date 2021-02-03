package Ejercicio4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Emisor {

    static DatagramPacket envio = null;

    static final int PORT_NUM = 9876;

    static  byte[] enviados;
    static byte[] recibidos = new byte[1024];
    static String nuevaFrase = "";

    public static void main(String args[]) throws Exception {
        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        DatagramSocket clientSocket = new DatagramSocket();//socket cliente

        // DATOS DEL SERVIDOR
        InetAddress IPServidor = InetAddress.getLocalHost();// localhost
        int puerto = 9876; // puerto por el que escucha

        // INTRODUCIR NUMERO
        System.out.println("Introduce tu combinación: ");
        String cadena = scanner.nextLine();
        enviados = cadena.getBytes();

        //ENVIANDO DATAGRAMA AL SERVIDOR
        envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
        clientSocket.send(envio);

        // RECIBIENDO DATAGRAMÄ DEL SERVIDOR
        DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        clientSocket.receive(recibo);
        String respuesta = new String(recibo.getData(), recibo.getOffset(), recibo.getLength());

        clientSocket.close();
    }
}