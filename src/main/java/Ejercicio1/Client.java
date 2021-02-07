package Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    String message = "";
    String messageFromServer = "";
    Socket s = null;

    public Client(){
        try {
            s = new Socket(Utils.HOST, Utils.PORT);
            Utils.dis = new DataInputStream(s.getInputStream());
            Utils.dos = new DataOutputStream(s.getOutputStream());
            while (true){
                System.out.println(Utils.question);
                message = Utils.scan.nextLine();
                Utils.dos.writeUTF(message);
                Utils.dos.flush();
                messageFromServer = Utils.dis.readUTF();
                System.out.println(messageFromServer);
                if (message.trim().equals("*")) break;
            }
            s.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
