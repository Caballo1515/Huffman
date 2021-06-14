public class Nodo {
    private char letra;
    private int frecuencia;
    private Nodo padre;
    private Nodo hijo0;
    private Nodo hijo1;
    private boolean apuntado;

    public Nodo(char letra, int frecuencia) {
        this.letra = letra;
        this.frecuencia = frecuencia;
        this.padre = null;
        this.hijo0 = null;
        this.hijo1 = null;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo getHijo0() {
        return hijo0;
    }

    public void setHijo0(Nodo hijo0) {
        this.hijo0 = hijo0;
    }

    public Nodo getHijo1() {
        return hijo1;
    }

    public void setHijo1(Nodo hijo1) {
        this.hijo1 = hijo1;
    }

    public boolean isApuntado() {
        return apuntado;
    }

    public void setApuntado(boolean apuntado) {
        this.apuntado = apuntado;
    }
}
