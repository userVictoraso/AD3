package Ejercicio1;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    String mensaje = "";

    public Cliente(){
        try {
            Socket s = new Socket(Utils.HOST, Utils.PUERTO);
            Utils.dos = new DataOutputStream(s.getOutputStream());
            System.out.println(Utils.pregunta);
            mensaje = Utils.scan.nextLine();
            Utils.dos.writeUTF(mensaje);
            Utils.dos.flush();
            s.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
