import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("escriba la secuencia que desea, que sea mas larga de 1 caracter y utilizar al menos dos caracteres distintos");
        String frase = input.nextLine();
        if(frase.length()<2){
            throw new RuntimeException("medida de mensaje inadecuada");
        }
        crearArbol(frase);
        System.out.println(Huffman.getTabla().toString());
        String mensaje_codificado = Huffman.codificar(frase);
        int byts_mensaje = (int) Math.ceil((double) (mensaje_codificado.length() / 8));
        System.out.println(mensaje_codificado + " mida mensaje:" + byts_mensaje +"byte"+ "\nmida arbol:" + Huffman.mida_arbol + " nodos(char 2byte, int 4byte puntero 2*3byte, boolean 1byte = 13byte) que equivalen a " + Huffman.mida_arbol*13 + " bytes");
        System.out.println("El codigo descodificado: "+Huffman.descodificar(mensaje_codificado));
    }

    public static void crearArbol(String frase){
        char[] lletras = new char[frase.length()];
        int[] frequencias = new int[frase.length()];
        int aux, contador;
        for (int i=0;i<frase.length();i++){
            aux=0;
            while (lletras[aux]!= '\u0000'  && lletras[aux] != frase.charAt(i)){
                aux++;
            }
            if(lletras[aux] == '\u0000'){
                lletras[aux]=frase.charAt(i);
                frequencias[aux] = frequencias[aux] + 1;
            }else {
                frequencias[aux] = frequencias[aux] + 1;
            }
        }
        aux=0;
        contador=0;
        try {
            while (lletras[aux]!='\u0000'){
                aux++;
                contador++;
            }
        } catch (Exception e){
            contador = lletras.length;
            aux = lletras.length;
        }

        char[] caracteres = new char[contador];
        int[] frecuencia = new int[contador];
        for(aux = 0 ; aux< contador; aux++){
            caracteres[aux]= lletras[aux];
            frecuencia[aux]=frequencias[aux];
        }
        int[] frecuencias_ordenadas = frecuencia;
        char[] caracteres_ordenados = new char[contador];
        if(caracteres_ordenados.length==1){
            throw new RuntimeException("tienes que usar mas de un caracter");
        }
        Huffman.arbol(caracteres, frecuencia);
    }


}
