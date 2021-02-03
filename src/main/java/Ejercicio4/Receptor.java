package Ejercicio4;

import Ejercicio2.Frase;
import Ejercicio2.Respuesta;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Receptor extends Thread {
    static final int PORT_NUM = 9876;
    static final int NUM_VIDAS = 10;
    Socket sCliente;

    static Random randomGenerator = new Random();

    static ArrayList<Integer> winCombination = new ArrayList<Integer>();
    static ArrayList<Integer> clientCombination = new ArrayList<Integer>();

    static String combinacionCliente;
    static String respuesta;

    public Receptor(Socket sCliente) {
        this.sCliente = sCliente;
    }

    public static void main(String args[]) throws Exception {
        //GENERAR NUMERO RANDOM SIN REPETIR
        while (winCombination.size() < 4) {
            int random = randomGenerator.nextInt(10);
            if (!winCombination.contains(random)) {
                winCombination.add(random);
                System.out.print(random);
            }
        }
        //SE INICIA EL SERVIDOR
        ServerSocket skServidor = new ServerSocket(PORT_NUM);

        while (true) {
            //ESPERAMOS A LA CONEXION DEL CLIENTE
            Socket socketCliente = skServidor.accept();
            System.out.println();
            System.out.println("Cliente conectado [IP: " + socketCliente.getInetAddress() + ", PUERTO " + PORT_NUM + "]");
            //SE INICIA EL HILO
            new Receptor(socketCliente).start();
        }
    }//Fin de main

    @Override
    public void run() {
        try {
            //SE INICIAN LOS FLUJOS DE E/S
            DataInputStream flujo_entrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(sCliente.getOutputStream());

            //AÑADIR AL ARRAYLIST DEL CLIENTE
            while(true){
                System.out.print("Esperando combinación del cliente: ");
                combinacionCliente = flujo_entrada.readUTF();
                System.out.println(combinacionCliente);
                stringToArraylist(combinacionCliente);

                //COMPROBAR
                respuesta = "";
                for (int i = 0; i < winCombination.size(); i++) {
                    for (int j = 0; j < winCombination.size(); j++) {
                        if (winCombination.get(i).equals(clientCombination.get(i))) {
                            //IMPRIMIR COINCIDEN EN POSICION
                            respuesta += "X";
                            break;
                        } else if (winCombination.get(i).equals(clientCombination.get(j))) {
                            //IMPRIMIR ▒
                            respuesta += "0";
                            break;
                        }
                    }
                }
                //ENVIAR RESPUESTA
                flujo_salida.writeUTF(respuesta);
                System.out.println("\tRespuesta enviada al cliente " + sCliente.getInetAddress() + ": " + respuesta);//CAMBIAR CODIGO DEL CLIENTE
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stringToArraylist(String combination) {
        for (int i = 0; i < combination.length(); i++) {
            int n = Integer.parseInt(String.valueOf(combination.charAt(i)));
            clientCombination.add(n);
        }
    }
}//Fin de ServidorUDP2