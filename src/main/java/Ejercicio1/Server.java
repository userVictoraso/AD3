package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    String message = "";
    Socket s = null;
    public Server(){
        try{
            ServerSocket ss = new ServerSocket(Utils.PORT);//INICIO DE ESCUCHA
            s = ss.accept();//CREAR SOCKET PARA EL CLIENTE
            Utils.dis = new DataInputStream(s.getInputStream());
            Utils.dos = new DataOutputStream(s.getOutputStream());
            while (true){
                System.out.println("Esperando mensaje del cliente: ");
                message = Utils.dis.readUTF();
                System.out.println("El cliente te ha dicho: " + message);
                Utils.dos.writeUTF("Número de caracteres: " + Utils.countCharacter(message) + " caracteres.");
                System.out.println("Mensaje enviado.");
                if (message.trim().equals("*")) break;
            }
            s.close();//CERRAR EL SOCKET UNA VEZ HA SIDO LEIDO
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
