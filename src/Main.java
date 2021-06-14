import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        System.out.println("escriba la secuencia que desea, que sea mas larga de 1 caracter");
        String frase = input.nextLine();
        crearArbol(frase);
        System.out.println(Huffman.getTabla().toString());
        String mensaje_codificado = Huffman.codificar(frase);
        System.out.println(mensaje_codificado);
        System.out.println(Huffman.descodificar(mensaje_codificado));
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
        while (lletras[aux]!='\u0000'){
            aux++;
            contador++;
        }
        char[] caracteres = new char[contador];
        int[] frecuencia = new int[contador];
        for(aux = 0 ; aux< contador; aux++){
            caracteres[aux]= lletras[aux];
            frecuencia[aux]=frequencias[aux];
        }
        int[] frecuencias_ordenadas = frecuencia;
        char[] caracteres_ordenados = new char[contador];
        Huffman.arbol(caracteres, frecuencia);
    }


}
