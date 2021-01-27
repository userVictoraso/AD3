package Ejercicio2;

public class Frase {
    String texto;
    String autor;

    public Frase(String texto, String autor) {
        this.texto = texto;
        this.autor = autor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Frase{" +
                "texto='" + texto + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
