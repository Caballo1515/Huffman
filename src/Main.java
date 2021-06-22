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
            throw new RuntimeException("\nmedida de mensaje inadecuada");
        }
        crearArbol(frase);
        System.out.println(Huffman.getTabla().toString());
        String mensaje_codificado = Huffman.codificar(frase);
        float byts_aux = (float) mensaje_codificado.length() / 8;
        int byts_mensaje = (int) Math.ceil(byts_aux);
        System.out.println(mensaje_codificado + " mida mensaje:" + byts_mensaje +"byte "+ "Coste computacional codificacion:  "+Huffman.coste_computacional_codificacion+"\nmida arbol:" + Huffman.mida_arbol + " nodos(char 2byte, int 4byte puntero 2*3byte, boolean 1byte = 13byte) que equivalen a " + Huffman.mida_arbol*13 + " bytes" + " Coste computacional arbol:  "+Huffman.coste_computacional_arbol);
        System.out.println("El codigo descodificado: "+Huffman.descodificar(mensaje_codificado) + " Coste computacional descodificacion: " + Huffman.coste_computacional_descodificacion);
        System.out.println("El coste computacional de la creacion de la tabla ha sido: " + Huffman.coste_computacional_tabla);
    }

    public static void crearArbol(String frase){
        char[] lletras = new char[frase.length()];
        int[] frequencias = new int[frase.length()];
        int aux, contador;
        //Creo un bucle que recorre toda la frase introducida para crear los dos arrays
        for (int i=0;i<frase.length();i++){
            aux=0;
            //'\u0000' valor por defecto de un array de char
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
        //Si existen frases con caracteres n!=0 caracteres con frecuencia 1 etrará en el catch
        try {
            while (lletras[aux]!='\u0000'){
                aux++;
                contador++;
            }
        } catch (Exception e){
            contador = lletras.length;
            aux = lletras.length;
        }
        //Redimensiono los arrays para que sean de longitud de cantidad de caracteres que tiene la frase
        char[] caracteres = new char[contador];
        int[] frecuencia = new int[contador];
        for(aux = 0 ; aux< contador; aux++){
            caracteres[aux]= lletras[aux];
            frecuencia[aux]=frequencias[aux];
        }
        int[] frecuencias_ordenadas = frecuencia;
        char[] caracteres_ordenados = new char[contador];
        //en el caso que solo se utilice un caracter
        if(caracteres_ordenados.length==1){
            throw new RuntimeException("\ntienes que usar mas de un caracter");
        }
        Huffman.arbol(caracteres, frecuencia);
    }


}
