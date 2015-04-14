/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionVentas;

import ModelDB.conexion;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Shary
 */
public class ReducirArticulos {

    private String[] cantidades;
    private String[] articulos;
    public boolean respuesta;
    private conexion con = new conexion();
    int maximo = 0, restar = 0;
    

    public void verificarCantidad(String cantidad, String articulo) {
        System.out.println("Entro a verificar");
        try {
            con.busqueda("articulos", "nombre", "existencia", articulo);
            if (Integer.parseInt(con.registro_busqueda)
                    < Integer.parseInt(cantidad)) {
                JOptionPane.showMessageDialog(null, "Solo existe en inventario\n"
                        + Integer.parseInt(con.registro_busqueda) + " productos " + articulo);
               
                respuesta=false;
            }
            else
            {
                respuesta=true;
            }
        } catch (Exception e) {
        }
    }

    public void actualizarBD(String cantidad, String nombres) {
        try {
            System.out.println("Entro a Actualizar");
            this.cantidades = cantidad.split("[|]");
            this.articulos = nombres.split("[|]");
            maximo = cantidades.length;

            for (int i = 0; i < maximo; i++) {
                System.out.println("\n" + articulos[i] + "VUELTA " + i);
                con.busqueda("articulos", "nombre", "existencia", articulos[i]);
                restar = (Integer.parseInt(con.registro_busqueda)) - (Integer.parseInt(cantidades[i]));
                System.out.println(restar);
                con.actualizar("articulos", "existencia", String.valueOf(restar), "nombre='" + articulos[i] + "';");
            }
        } catch (Exception e) {
        }
    }

}
