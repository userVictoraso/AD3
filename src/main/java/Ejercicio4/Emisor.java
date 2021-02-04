package Ejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Emisor {
    static DataInputStream inputData;
    static DataOutputStream outputData;

    public static void main(String args[]) throws Exception {
        // FLUJO PARA ENTRADA ESTANDAR
        Socket clientSocket = new Socket("localhost", Utils.PORT_NUM);

        inputData = new DataInputStream(clientSocket.getInputStream());
        outputData = new DataOutputStream(clientSocket.getOutputStream());

        while (true) {
            // INTRODUCIR NUMERO
            System.out.println("Introduce tu combinación: ");
            String clientTry = Utils.scanner.nextLine();

            //PARA SALIR
            if (clientTry.trim().equals("*")) break;

            //COMPROBAR SI LA CADENA TIENE EL PATRON CORRECTO
            while (!clientTry.matches("(\\d{4})")){
                System.out.println("Introduce una combinación válida (4 números).");
                clientTry = Utils.scanner.nextLine();
            }

            outputData.writeUTF(clientTry);

            // RECIBIENDO DATAGRAMÄ DEL SERVIDOR
            String response = inputData.readUTF();
            System.out.println("El resultado es: " + response);

        }

    }
}