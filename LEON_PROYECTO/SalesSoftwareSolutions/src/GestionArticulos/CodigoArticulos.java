/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionArticulos;

import ModelDB.conexion;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que permite determinar el numero mayor de los codigos, 
//cada codigo se conforma de 3 letras y el numero. La clase determina el numero mayor.
public class CodigoArticulos {

    private String codigoArticulo = "";
    private String[] codigoLetra;
    private String[] codigo;
    private conexion con;
    public int mayor = 0;

//    Constructor: Tiene como parametros el nombre real de todos los productos de la BD, asi
//    como el codigo de los mismos. Todos en una larga cadena de texto.
    public CodigoArticulos(String nombre, String codigo) {
        separar(nombre, codigo);
    }

//    Metodo que separa toda la cadena de texto (codigo) en unidades unicas para determinar el mayor.
    public void separar(String nombre, String codigo) {
        int i = 0;
        int contador = 0;
        System.out.println("Entrando a la funcion separar");
        this.codigoLetra = codigo.split(",");
        int max = this.codigoLetra.length;
        for (i = 0; i < max; i++) {
            contador = this.codigoLetra[i].length();
            codigoArticulo += this.codigoLetra[i].substring(3, contador) + ",";
        }
        this.codigo = codigoArticulo.split(",");
        int[] results = new int[this.codigo.length];

        for (int j = 0; j < this.codigo.length; j++) {
            try {
                results[j] = Integer.parseInt(this.codigo[j]);
            } catch (NumberFormatException nfe) {
            };
        }
        ordenar(results);
    }

//    Método con la funcion principal de llamar al metodo de ordenamiento quicksort.
//    Tiene como parametro un vector de todos los numero obtenidos al separar el codigo.
    public void ordenar(int[] results) {
        System.out.println("Entrando a la funcion ordenar");
        int izq = 0;
        int der = this.codigo.length - 1;
        quicksort(results, izq, der);
    }

//    Método de ordenamiento de menor a mayor. Los menores a su izquierda y los mayores a su derecha.
//    Va partiendo el vector a la mitad y se sigue sucesivamente para realizar una búsqueda
//    Más rápida y eficiente.
    public void quicksort(int results[], int izq, int der) {
        int pivote = results[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        int aux;
        System.out.println("Entrando a la funcion quicksort");
        while (i < j) {            // mientras no se crucen las búsquedas
            while (results[i] <= pivote && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (results[j] > pivote) {
                j--;         // busca elemento menor que pivote
            }
            if (i < j) {                      // si no se han cruzado                      
                aux = results[i];                  // los intercambia
                results[i] = results[j];
                results[j] = aux;
            }
        }
        results[izq] = results[j]; // se coloca el pivote en su lugar de forma que tendremos
        results[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        mayor = results[j];
        if (izq < j - 1) {
            quicksort(results, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(results, j + 1, der); // ordenamos subarray derecho
        }
    }
}
