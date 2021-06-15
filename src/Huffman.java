public class Huffman {
    private static Nodo[] nodosOrdenados = null;
    private  static  String[][] tabla = null;
    private static Nodo pdi0 = null, heap = null, pdi1 = null;
    private static int coste_computacional;
    public static int mida_arbol;

    public static String getTabla() {
        String tabla_string = "";
        for (int i =0; i!=tabla.length; i++){
            tabla_string = tabla_string + "[<" + tabla[i][0] + "> <" + tabla[i][1] + ">]" + "\n";
        }
        return tabla_string;
    }

    public static Nodo arbol(char[] caracteres, int[] frecuencias){

        Nodo[] nodos_ordenados = new Nodo[caracteres.length];
        nodosOrdenados = new Nodo[caracteres.length];
        int i;
        for (i=0;i!= caracteres.length;i++ ){
            int posicion=0, minimo=1000;
            for (int x = 0; x != caracteres.length; x++){
                if(minimo > frecuencias[x] && frecuencias[x] != -1){
                    minimo = frecuencias[x];
                    posicion = x;
                }
            }
            nodos_ordenados[i] = new Nodo(caracteres[posicion], frecuencias[posicion]);;
            frecuencias[posicion] = -1;
        }
        for (i=0; i!= nodos_ordenados.length; i++){
            nodosOrdenados[i] = new Nodo(nodos_ordenados[i].getLetra(), nodos_ordenados[i].getFrecuencia());
        }
        mida_arbol = nodos_ordenados.length;
        i=0;
        while (nodos_ordenados.length!=1){
            i=0;
            Nodo hijo0 = nodos_ordenados[i];
            i++;
            Nodo hijo1 = nodos_ordenados[i];
            Nodo enlace = new Nodo('\u0000', hijo0.getFrecuencia()+hijo1.getFrecuencia());
            mida_arbol++;
            enlace.setHijo0(hijo0);
            enlace.setHijo1(hijo1);
            hijo0.setPadre(enlace);
            hijo1.setPadre(enlace);
            Nodo[] nodos = new Nodo[nodos_ordenados.length-1];
            nodos[0] = enlace;
            for (int x=1; x!=nodos.length;x++ ){
                nodos[x] = nodos_ordenados[x+1];
            }
            nodos_ordenados = new Nodo[nodos.length];
            nodos_ordenados = nodos;
            nodos = new Nodo[nodos.length];

            for (i=0;i!= nodos_ordenados.length;i++ ){
                int posicion=0, minimo=1000;
                for (int x = 0; x != nodos_ordenados.length; x++){
                    if(minimo > nodos_ordenados[x].getFrecuencia() && nodos_ordenados[x].getFrecuencia() != -1){
                        minimo = nodos_ordenados[x].getFrecuencia();
                        posicion = x;
                    }
                }
                nodos[i] = nodos_ordenados[posicion];
                nodos_ordenados[posicion]= new Nodo('\u0000', -1);
            }
            nodos_ordenados = nodos;
            i=0;
        }
        heap = nodos_ordenados[0];
        tabla();
        return null;
    }

    public static void tabla(){
        tabla = new String[nodosOrdenados.length][2];
        int i;
        for (i = 0; i!= nodosOrdenados.length; i++){
            tabla[i][0] = String.valueOf(nodosOrdenados[i].getLetra());
        }
        int contador = 0;
        pdi0 = heap;
        int aux = 0;
        String codigo ="";
        while (contador!=nodosOrdenados.length){
            if (pdi0.getHijo0()!=null && !pdi0.getHijo0().isApuntado() && aux ==0){
                codigo = codigo+0;
                pdi0 = pdi0.getHijo0();
            }else if(aux == 1 && pdi0.getHijo0()!=null && !pdi0.getHijo1().isApuntado()){
                codigo = codigo+1;
                pdi0 = pdi0.getHijo1();
                aux=0;
            }else if(pdi0.getHijo0()!=null && pdi0.getHijo1()!=null && pdi0.getHijo0().isApuntado()&& pdi0.getHijo1().isApuntado() ){
                pdi0.setApuntado(true);
                pdi0 = pdi0.getPadre();
                codigo = codigo.substring(0,codigo.length()-1);
                aux = 1;
            } else {
                i=0;
                while (!pdi0.isApuntado()){
                    if(tabla[i][0].equals(String.valueOf(pdi0.getLetra()))){
                        pdi0.setApuntado(true);
                        tabla[i][1] = codigo;
                        pdi0 = pdi0.getPadre();
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
        String codificado = "";
        for (int i =0; i!= mesaje.length(); i++){
            int x =0;
            while (!tabla[x][0].equals(String.valueOf(mesaje.charAt(i)))){
                x++;
            }
            codificado = codificado + tabla[x][1];
        }
        return codificado;
    }

    public static String descodificar(String mensaje_codificado){
        pdi0 = heap;
        String mensaje_descodificado = "";
        for(int i=0; i!=(mensaje_codificado.length()); i++){
            if(mensaje_codificado.charAt(i) == '1' && pdi0.getHijo1()!=null ){
                pdi0=pdi0.getHijo1();
            }else if(mensaje_codificado.charAt(i) == '0' && pdi0.getHijo0()!=null){
                pdi0 = pdi0.getHijo0();
            } else {
                mensaje_descodificado = mensaje_descodificado + pdi0.getLetra();
                pdi0=heap;
                i--;
            }
        }
        mensaje_descodificado = mensaje_descodificado + pdi0.getLetra();
        return mensaje_descodificado;
    }
}


