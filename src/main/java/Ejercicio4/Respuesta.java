package Ejercicio4;

public class Respuesta {

    int nVidas;
    String respuesta;

    public Respuesta(int nVidas, String respuesta) {
        this.nVidas = nVidas;
        this.respuesta = respuesta;
    }

    public int getnVidas() {
        return nVidas;
    }

    public void setnVidas(int nVidas) {
        this.nVidas = nVidas;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

}
