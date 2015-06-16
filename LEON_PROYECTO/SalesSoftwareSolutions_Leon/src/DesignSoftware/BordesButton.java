/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignSoftware;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que permite colorear los bordes de los botones requeridos.
public class BordesButton {

    private JButton boton;

//    Constructor: obtiene el boton a colorear.
    public BordesButton(JButton boton) {
        this.boton = boton;
    }

//    Coloca borde en linea y del color especificado al boton.
    public void encender() {
        Border resaltarBorde = new LineBorder(new Color(91, 137, 193), 2);
        boton.setBorder(resaltarBorde);
    }
//    Elimina el borde en linea y del color especificado al boton.

    public void apagar() {
        boton.setBorder(null);
    }

}
