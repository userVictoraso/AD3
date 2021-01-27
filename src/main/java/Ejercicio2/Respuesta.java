package Ejercicio2;

public class Respuesta {
    boolean existe;
    String texto;

    public Respuesta() {
    }

    public Respuesta(boolean existe, String texto) {
        this.existe = existe;
        this.texto = texto;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
