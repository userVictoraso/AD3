package Ejercicio4;

import Ejercicio2.Frase;
import Ejercicio2.Respuesta;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Receptor {
    static final int PORT_NUM = 9876;

    static DatagramPacket paqRecibido = null;
    static DatagramPacket paqEnviado = null;

    static ArrayList<Integer> winCombination = new ArrayList<Integer>();
    static ArrayList<Integer> clientCombination = new ArrayList<Integer>();

    static byte[] recibidos = new byte[1024];
    static byte[] enviados = new byte[1024];

    static String combinacionCliente;

    public static void main(String args[]) throws Exception {
        //GENERAR NUMERO RANDOM SIN REPETIR
        Random randomGenerator = new Random();
        while (winCombination.size() < 4) {

            int random = randomGenerator.nextInt(10);
            if (!winCombination.contains(random)) {
                winCombination.add(random);
                System.out.println(random);
            }
        }

        DatagramSocket serverSocket = new DatagramSocket(PORT_NUM);

        while (true) {
            System.out.println("Esperando combinación de algún cliente");
            //RECIBO DATAGRAMA
            paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(paqRecibido);
            combinacionCliente = new String(paqRecibido.getData(), paqRecibido.getOffset(), paqRecibido.getLength());

            //DIRECCION ORIGEN/ASOCIAR COMBINACION CON CLIENTE, CARGAR HILO
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();
            System.out.println("Cliente " + 1 + ": [IP ->" + IPOrigen.toString() + "]"); //CAMBIAR EL CODIGO DEL CLIENTE
            System.out.println("\tCombinacion del cliente: " + combinacionCliente);

            //PASAR DE STRING A ARRAYLIST
            stringToArraylist(combinacionCliente);

            //COMPROBAR
            String respuesta = "";
            for (int i = 0; i < winCombination.size(); i++) {
                for (int j = 0; j < winCombination.size(); j++) {
                    if (winCombination.get(i) == clientCombination.get(i)) {
                        //IMPRIMIR COINCIDEN EN POSICION
                        respuesta += "";
                        break;
                    } else if (winCombination.get(i) == clientCombination.get(j)) {
                        //IMPRIMIR ▒
                        respuesta += "";
                        break;
                    }
                }
            }

            enviados = respuesta.getBytes();
            paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            serverSocket.send(paqEnviado);
            System.out.println("\tRespuesta enviada al cliente " + 1 + ": " + respuesta);//CAMBIAR CODIGO DEL CLIENTE

            //Para terminar
            if (combinacionCliente.trim().equals("*")) break;
        }//Fin de while
        serverSocket.close();
        System.out.println("Socket cerrado...");

    }//Fin de main

    public static void stringToArraylist(String combination) {
        for (int i = 0; i < winCombination.size(); i++) {
            clientCombination.add((int) combination.charAt(i));
        }
    }
}//Fin de ServidorUDP2