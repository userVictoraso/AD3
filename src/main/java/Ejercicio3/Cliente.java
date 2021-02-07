package Ejercicio3;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente extends Thread {

    static DatagramPacket envio = null;
    static Scanner scanner = new Scanner(System.in);
    static int puerto = 4444; // puerto por el que escucha

    public static void main(String[] args) {
        new Cliente().run();
    }

    @Override
    public void run() {
        // FLUJO PARA ENTRADA ESTANDAR
        try {
            System.out.println("Iniciado el hilo.");
            DatagramSocket clientSocket = new DatagramSocket(6777);//socket cliente
            byte[] enviados;
            byte[] recibidos = new byte[1024];
            // DATOS DEL SERVIDOR
            InetAddress IPServidor = InetAddress.getLocalHost();// localhost
            while (true) {
                // INTRODUCIR DATOS POR TECLADO
                System.out.println("Escribe tu frase: ");
                String cadena = scanner.nextLine();
                enviados = cadena.getBytes();

                if (cadena.trim().equals("*")) break;
                // ENVIANDO DATAGRAMA AL SERVIDOR
                System.out.println("\tEnviando frase al servidor.");
                envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
                clientSocket.send(envio);

                // RECIBIENDO DATAGRAMÄ DEL SERVIDOR
                DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
                clientSocket.receive(recibo);
                String respuesta = new String(recibo.getData(), recibo.getOffset(), recibo.getLength());
                System.out.println("Frase recibida: " + respuesta);
            }
            clientSocket.close();
        } catch (SocketException | UnknownHostException se) {
            se.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Hilo acabado");
    }

}