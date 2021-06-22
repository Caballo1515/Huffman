public class Huffman {
    private static Nodo[] nodosOrdenados = null;
    private  static  String[][] tabla = null;
    private static Nodo pdi = null, heap = null;
    public static int coste_computacional_arbol, coste_computacional_codificacion, coste_computacional_descodificacion, coste_computacional_tabla;
    public static int mida_arbol;

    public static String getTabla() {
        StringBuilder tabla_string = new StringBuilder();
        for (int i =0; i!=tabla.length; i++){
            tabla_string.append("[<").append(tabla[i][0]).append("> <").append(tabla[i][1]).append(">]").append("\n");
        }
        return tabla_string.toString();
    }

    public static void arbol(char[] caracteres, int[] frecuencias){
        //entra por parametro la tabla de caracteres desordenada con las frecuencias respectivas
        coste_computacional_arbol = 1;

        Nodo[] nodos_ordenados = new Nodo[caracteres.length];
        nodosOrdenados = new Nodo[caracteres.length];
        int i;
        //Ordenamos los caracteres segun sus frecuencias(de menos frecuencia a mas)
        for (i=0;i!= caracteres.length;i++ ){
            int posicion=0, minimo=1000;
            for (int x = 0; x != caracteres.length; x++){
                coste_computacional_arbol++;
                if(minimo > frecuencias[x] && frecuencias[x] != -1){
                    minimo = frecuencias[x];
                    posicion = x;
                }
            }
            nodos_ordenados[i] = new Nodo(caracteres[posicion], frecuencias[posicion]);
            frecuencias[posicion] = -1;
        }
        //Creamos un nuevo array de los mismos nodos ordenados(no se referencia al de nodos referenciados porque esa memoria sera modificada)
        for (i=0; i!= nodos_ordenados.length; i++){
            coste_computacional_arbol++;
            nodosOrdenados[i] = new Nodo(nodos_ordenados[i].getLetra(), nodos_ordenados[i].getFrecuencia());
        }
        //Declaramos la mida del arbol como la cantidad de nodos existentes de manera inicial
        mida_arbol = nodos_ordenados.length;
        //Creacion del arbol
        while (nodos_ordenados.length!=1){
            i=0;
            Nodo hijo0 = nodos_ordenados[i];
            i++;
            Nodo hijo1 = nodos_ordenados[i];
            Nodo enlace = new Nodo('\u0000', hijo0.getFrecuencia()+hijo1.getFrecuencia());
            //Cada vez que creamos un nodo de enlace incrementamos en 1 la mida del arbol
            mida_arbol++;
            enlace.setHijo0(hijo0);
            enlace.setHijo1(hijo1);
            hijo0.setPadre(enlace);
            hijo1.setPadre(enlace);
            //Creo un nuevo array de nodos y cambio los dos nodos que utilize para enlazarse por el nodo de enlace con su respectiva frecuencia
            Nodo[] nodos = new Nodo[nodos_ordenados.length-1];
            nodos[0] = enlace;
            for (int x=1; x!=nodos.length;x++ ){
                coste_computacional_arbol++;
                nodos[x] = nodos_ordenados[x+1];
            }
            nodos_ordenados = nodos;
            nodos = new Nodo[nodos.length];
            //Ordeno los nodos por si el enlace tiene una frecuencia mas pesada
            for (i=0;i!= nodos_ordenados.length;i++ ){
                int posicion=0, minimo=1000;
                for (int x = 0; x != nodos_ordenados.length; x++){
                    coste_computacional_arbol++;
                    if(minimo > nodos_ordenados[x].getFrecuencia() && nodos_ordenados[x].getFrecuencia() != -1){
                        minimo = nodos_ordenados[x].getFrecuencia();
                        posicion = x;
                    }
                }
                nodos[i] = nodos_ordenados[posicion];
                nodos_ordenados[posicion]= new Nodo('\u0000', -1);
            }
            nodos_ordenados = nodos;
        }
        heap = nodos_ordenados[0];
        //Llamo a la funcion tabla() para generar la tabla de codificacion
        tabla();
    }

    public static void tabla(){
        coste_computacional_tabla = 1;
        //Genero la tabla de cidificacion de longitud de cantidad de caracteres y dos posiciones por cada fila, el caracter y el codigo
        tabla = new String[nodosOrdenados.length][2];
        int i;
        //Introduzco los caracteres de manera ordenada
        for (i = 0; i!= nodosOrdenados.length; i++){
            coste_computacional_tabla++;
            tabla[i][0] = String.valueOf(nodosOrdenados[i].getLetra());
        }
        int contador = 0;
        pdi = heap;
        int aux = 0;
        //inicializamos el String del codigo con String vacio
        String codigo ="";
        //Recorremos toda la tabla con el pdi hasta llegar a todos los nodos de carracteres
        while (contador!=nodosOrdenados.length){
            coste_computacional_tabla++;
            if (pdi.getHijo0()!=null && !pdi.getHijo0().isApuntado() && aux ==0){
                codigo = codigo+0;
                pdi = pdi.getHijo0();
            }else if(aux == 1 && pdi.getHijo0()!=null && !pdi.getHijo1().isApuntado()){
                codigo = codigo+1;
                pdi = pdi.getHijo1();
                aux=0;
            }else if(pdi.getHijo0()!=null && pdi.getHijo1()!=null && pdi.getHijo0().isApuntado()&& pdi.getHijo1().isApuntado() ){
                pdi.setApuntado(true);
                pdi = pdi.getPadre();
                codigo = codigo.substring(0,codigo.length()-1);
                aux = 1;
            } else {
                //caso de llegar a nodo con caracter y no apuntado
                i=0;
                while (!pdi.isApuntado()){
                    coste_computacional_tabla++;
                    if(tabla[i][0].equals(String.valueOf(pdi.getLetra()))){
                        pdi.setApuntado(true);
                        tabla[i][1] = codigo;
                        pdi = pdi.getPadre();
                        aux = 1;

                        contador++;
                        codigo = codigo.substring(0,codigo.length()-1);
                        break;
                    }else{
                        i++;
                    }
                }
            }
        }
    }

    public static String codificar(String mesaje){
        coste_computacional_codificacion = 1;
        StringBuilder codificado = new StringBuilder();
        //Recorro el mensaje que entra por parametro
        for (int i =0; i!= mesaje.length(); i++){
            coste_computacional_codificacion++;
            int x =0;
            //Recorro la tabla para ver el codigo de cada carracter que sale
            while (!tabla[x][0].equals(String.valueOf(mesaje.charAt(i)))){
                coste_computacional_codificacion++;
                x++;
            }
            codificado.append(tabla[x][1]);
        }
        return codificado.toString();
    }

    public static String descodificar(String mensaje_codificado){
        coste_computacional_descodificacion = 1;
        pdi = heap;
        StringBuilder mensaje_descodificado = new StringBuilder();
        //Recorro el mensaje codificado
        for(int i=0; i!=(mensaje_codificado.length()); i++){
            coste_computacional_descodificacion++;
            if(mensaje_codificado.charAt(i) == '1' && pdi.getHijo1()!=null ){
                pdi = pdi.getHijo1();
            }else if(mensaje_codificado.charAt(i) == '0' && pdi.getHijo0()!=null){
                pdi = pdi.getHijo0();
            } else {
                //Si encuentro un carracter lo escribo y vuelvo al heap
                mensaje_descodificado.append(pdi.getLetra());
                pdi =heap;
                i--;
            }
        }
        //Añado el ultimo caracter
        mensaje_descodificado.append(pdi.getLetra());
        return mensaje_descodificado.toString();
    }
}


