/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignSoftware;

import com.sun.awt.AWTUtilities;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que redondea las esquinas de los formularios.
public class FormDesign {

    private JFrame form;

//    Constructor, con el parametro Jframe necesario para moldear.
    public FormDesign(JFrame formulario) {
        form = formulario;
        design();
    }

//    Metodo que redondeo el formulario 
    public final void design() {
        Shape forma;
        forma = new RoundRectangle2D.Double(0, 0, form.getBounds().width,
                form.getBounds().height, 50, 50);
        AWTUtilities.setWindowShape(form, forma);
    }
}
