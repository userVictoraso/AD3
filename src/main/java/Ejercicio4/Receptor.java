package Ejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Receptor extends Thread {
    Socket cSocket;

    static ArrayList<Integer> winCombinationArraylist = new ArrayList<Integer>();
    static ArrayList<Integer> clientCombinationArraylist = new ArrayList<Integer>();

    static String clientCombinationString;
    static String responseBuilder;

    public Receptor(Socket cSocket) {
        this.cSocket = cSocket;
    }

    public static void main(String args[]) throws Exception {
        //GENERAR NUMERO RANDOM SIN REPETIR
        while (winCombinationArraylist.size() < 4) {
            int random = Utils.randomGenerator.nextInt(10);
            if (!winCombinationArraylist.contains(random)) {
                winCombinationArraylist.add(random);
                System.out.print(random);
            }
        }
        //SE INICIA EL SERVIDOR
        ServerSocket serverSocket = new ServerSocket(Utils.PORT_NUM);

        while (true) {
            //ESPERAMOS A LA CONEXION DEL CLIENTE
            Socket clientSocket = serverSocket.accept();
            System.out.println();
            System.out.println("Cliente conectado [IP: " + clientSocket.getInetAddress() + ", PUERTO " + Utils.PORT_NUM + "]");
            //SE INICIA EL HILO
            new Receptor(clientSocket).start();
        }
    }//Fin de main

    public void run() {
        try {
            //SE INICIAN LOS FLUJOS DE E/S
            DataInputStream inputData = new DataInputStream(cSocket.getInputStream());
            DataOutputStream outputData = new DataOutputStream(cSocket.getOutputStream());
            //INICIALIZAR LA RESPUESTA
            Respuesta res = new Respuesta(Utils.NUM_VIDAS, null);
            //AÑADIR AL ARRAYLIST DEL CLIENTE
            while (true) {
                System.out.print("Esperando combinación del cliente: ");
                clientCombinationString = inputData.readUTF();
                System.out.println(clientCombinationString);

                Utils.stringToArraylist(clientCombinationString, clientCombinationArraylist);
                Utils.arrayListToString(winCombinationArraylist);

                //COMPROBAR
                responseBuilder = "";
                for (int i = 0; i < winCombinationArraylist.size(); i++) {
                    for (int j = 0; j < winCombinationArraylist.size(); j++) {
                        if (clientCombinationString.equals(Utils.arrayListToString(winCombinationArraylist))) {
                            //SI GANAS
                            responseBuilder = "Enhorabuena, has ganado";
                        } else if (winCombinationArraylist.get(i).equals(clientCombinationArraylist.get(i))) {
                            //IMPRIMIR COINCIDEN EN POSICION
                            responseBuilder += "X";
                            break;
                        } else if (winCombinationArraylist.get(i).equals(clientCombinationArraylist.get(j))) {
                            //IMPRIMIR ▒
                            responseBuilder += "0";
                            break;
                        }
                    }
                }
                //CARGAR EL OBJETO RESPUESTA
                res.setRespuesta(responseBuilder);
                res.setnVidas(res.getnVidas() - 1);
                if (res.getnVidas() < 1) {
                    res.setRespuesta("Has perdido todos los intentos.");
                }
                //ENVIAR RESPUESTA
                outputData.writeUTF(res.getRespuesta() + " [NºVIDAS: " + res.getnVidas() + "]");
                System.out.println("\tRespuesta enviada al cliente " + cSocket.getInetAddress() + ": " + res.getRespuesta() + " [NºVIDAS: " + res.getnVidas() + "]");//CAMBIAR CODIGO DEL CLIENTE
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//Fin de ServidorUDP2