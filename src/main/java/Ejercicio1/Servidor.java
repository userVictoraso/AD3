package Ejercicio1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    String mensajeLeido = "";

    public Servidor(){
        try{
            ServerSocket ss = new ServerSocket(Utils.PUERTO);//INICIO DE ESCUCHA
            Socket s = ss.accept();//CREAR SOCKET PARA EL CLIENTE
            Utils.dis = new DataInputStream(s.getInputStream());
            mensajeLeido = Utils.dis.readUTF();
            System.out.println("El cliente te ha dicho: " + mensajeLeido);
            System.out.println("Número de caracteres: " + Utils.numeroCaracteres(mensajeLeido) + " caracteres.");
            s.close();//CERRAR EL SOCKET UNA VEZ HA SIDO LEIDO
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
