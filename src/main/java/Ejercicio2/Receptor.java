package Ejercicio2;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Receptor {
    static List<Frase> frases = new ArrayList<>();
    static Respuesta respuesta = new Respuesta(true, null);

    static DatagramPacket paqRecibido = null;
    static DatagramPacket paqEnviado = null;

    public static void main(String args[]) throws Exception {
        frases.add(new Frase("Es sibarítico", "Dali"));
        frases.add(new Frase("Pienso luego existo", "Aristoteles"));

        //Puerto por el que escucha el servidor: 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] recibidos = new byte[1024];
        byte[] enviados;
        String autor;
        String frase;

        while (true) {
            System.out.println("Esperando datagrama.....");

            //RECIBO DATAGRAMA
            paqRecibido = new DatagramPacket(recibidos, recibidos.length);
            serverSocket.receive(paqRecibido);
            autor = new String(paqRecibido.getData(), paqRecibido.getOffset(), paqRecibido.getLength());
            System.out.println("\tAutor " + autor + " recibido");

            //DIRECCION ORIGEN
            InetAddress IPOrigen = paqRecibido.getAddress();
            int puerto = paqRecibido.getPort();

            boolean autorEncontrado = false;
            for (Frase f : frases) {
                if (autor.contains(f.getAutor())) {
                    System.out.println("\t\tAutor encontrado");
                    respuesta.setExiste(true);
                    respuesta.setTexto(f.getTexto());
                    autorEncontrado = true;
                }
            }
            if (!autorEncontrado) {
                System.out.println("\tAutor no encontrado");
                respuesta.setExiste(false);
                respuesta.setTexto("No hay texto de este autor");
            }
            enviados = respuesta.getTexto().getBytes();
            paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
            serverSocket.send(paqEnviado);
            System.out.println("\tMensaje enviado: " + respuesta.getTexto());

            if (respuesta.isExiste() == false) {
                System.out.println("Esperando la frase del autor " + autor);
                paqRecibido = new DatagramPacket(recibidos, recibidos.length);
                serverSocket.receive(paqRecibido);
                frase = new String(paqRecibido.getData(), paqRecibido.getOffset(), paqRecibido.getLength());
                frases.add(new Frase(frase, autor));
                System.out.println("Autor *" + autor + "* con frase *" + frase + "* añadido.");
            }

            //Para terminar
            if (autor.trim().equals("*")) break;

        }//Fin de while

        serverSocket.close();
        System.out.println("Socket cerrado...");

    }//Fin de main
}//Fin de ServidorUDP2