package Ejercicio4;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Emisor {
    static final int PORT_NUM = 9876;
    static DataInputStream flujo_entrada;
    static DataOutputStream flujo_salida;

    public static void main(String args[]) throws Exception {
        // FLUJO PARA ENTRADA ESTANDAR
        Scanner scanner = new Scanner(System.in);
        Socket skCliente = new Socket("localhost", PORT_NUM);

        flujo_entrada = new DataInputStream(skCliente.getInputStream());
        flujo_salida = new DataOutputStream(skCliente.getOutputStream());

        while (true){
            // INTRODUCIR NUMERO
            System.out.println("Introduce tu combinación: ");
            String cadena = scanner.nextLine();
            flujo_salida.writeUTF(cadena);

            //PARA SALIR
            if (cadena.trim().equals("*")) break;

            // RECIBIENDO DATAGRAMÄ DEL SERVIDOR
            String respuesta = flujo_entrada.readUTF();
            System.out.println("El resultado es: " + respuesta);

        }

    }
}