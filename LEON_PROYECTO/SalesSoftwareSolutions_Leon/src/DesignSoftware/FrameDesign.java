/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignSoftware;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que permite escalar las imagenes en el tamaño de los Jlabels y Jtextfields
//utilizados en la aplicación.
public class FrameDesign {

    private String url;
    private JLabel label;
    private JButton button;

//    Constructor: Tiene la ruta de la imagen a ensamblar en los Jlabels y Jtextfields.
    public FrameDesign(String ruta) {
        this.url = ruta;
    }

//    Metodo que agrega a los Jlabels la imagen ya escalada al tamaño del mismo.
    public void addLabel(JLabel icon) {
        ImageIcon imgIcon = new ImageIcon(getClass().getResource(url));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(icon.getWidth(),
                icon.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        icon.setIcon(iconoEscalado);
    }

//    Metodo que agrega a los Jtextfields la imagen ya escalada al tamaño del mismo
    public void addButton(JButton icon) {
        ImageIcon imgIcon = new ImageIcon(getClass().getResource(url));
        Image imgEscalada = imgIcon.getImage().getScaledInstance(icon.getWidth(),
                icon.getHeight(), Image.SCALE_SMOOTH);
        Icon iconoEscalado = new ImageIcon(imgEscalada);
        icon.setIcon(iconoEscalado);
    }
}
