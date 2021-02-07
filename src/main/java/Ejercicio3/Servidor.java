package Ejercicio3;

import Ejercicio2.Frase;
import Ejercicio2.Respuesta;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    static DatagramPacket paqRecibido = null;
    static DatagramPacket paqEnviado = null;

    public static void main(String args[]) throws Exception {

        //Puerto por el que escucha el servidor: 9876
        DatagramSocket serverSocket = new DatagramSocket(4444);
        byte[] recibidos = new byte[1024];
        byte[] enviados;
        String fraseRecibida;

        while (true) {
            System.out.println("Esperando datagrama.....");
            //RECIBO DATAGRAMA
            paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(paqRecibido);
            fraseRecibida = new String(paqRecibido.getData(), paqRecibido.getOffset(), paqRecibido.getLength());
            System.out.println("\tFrase recibida");

            //DIRECCION ORIGEN
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();

            String fraseConvertida = StringToUpperCase.mayuscula(fraseRecibida);

            enviados = fraseConvertida.getBytes();
            paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            serverSocket.send(paqEnviado);
            System.out.println("\tFrase convertida: " + fraseConvertida);
            System.out.println("\tIP: " + IPOrigen + "; PUERTO: " + puerto);

            //Para terminar
            if (fraseRecibida.trim().equals("*")) break;
        }//Fin de while
        serverSocket.close();
        System.out.println("Socket cerrado...");
    }//Fin de main
}//Fin de ServidorUDP2