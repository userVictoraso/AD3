package Ejercicio2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Emisor {

    static DatagramPacket envio = null;
    static DatagramPacket envioDos = null;

    static String nuevaFrase = "";

    public static void main(String args[]) throws Exception {
        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        DatagramSocket clientSocket = new DatagramSocket();//socket cliente
        byte[] enviados;
        byte[] recibidos = new byte[1024];

        // DATOS DEL SERVIDOR
        InetAddress IPServidor = InetAddress.getLocalHost();// localhost
        int puerto = 9876; // puerto por el que escucha

        // INTRODUCIR DATOS POR TECLADO
        System.out.println("¿Qué autor buscas?");
        String cadena = scanner.nextLine();
        enviados = cadena.getBytes();

        // ENVIANDO DATAGRAMA AL SERVIDOR
        System.out.println("\tEnviando el autor " + cadena + " al servidor.");
        envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
        clientSocket.send(envio);

        // RECIBIENDO DATAGRAMÄ DEL SERVIDOR
        DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
        clientSocket.receive(recibo);
        String respuesta = new String(recibo.getData(), recibo.getOffset(), recibo.getLength());

        if (respuesta.trim().equals("No hay texto de este autor")) {
            System.out.println("\tIngresa una frase para el autor");
            nuevaFrase = scanner.nextLine();
            enviados = nuevaFrase.getBytes();
            envioDos = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
            clientSocket.send(envioDos);
            System.out.println("\t\tFrase añadida: " + nuevaFrase.trim());
        } else {
            System.out.println("\t\tLa frase de " + cadena + " es: " + respuesta.trim());
        }
        clientSocket.close();
    }
}