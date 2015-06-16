/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignSoftware;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/*
 *@author Shary Yareli Chuc Ku
 *Versión: 2.0
 *Fecha última modificación: 06-12-14
 */
//Clase que permite dar diseño y formato a los tabs del JTabbedPane usado en la ventana principal.
public class DiseñoPanel {

    private String size = "";
    private String PART1 = "<html><body><div align='justify'><table width='100'><tr><td>";
    private String PART2 = "</td></tr></table></div></body></html>";
    private ImageIcon icon;
    private JTabbedPane panelPuntoVenta;
    private JPanel panelOpcion;
    private String URL = "../Images/Sales/";

//    Funcion que tiene los parametros del JTabbedPane, el panel requerido y los titulos para los cambios
//     y formatos necesarios.
    public DiseñoPanel(JTabbedPane tabPanel, JPanel pane1, String titulo, String img) {
        this.panelPuntoVenta = tabPanel;
        this.panelOpcion = pane1;
        size = PART1 + titulo + PART2;
        icon = new ImageIcon(getClass().getResource(URL + img));
        panelPuntoVenta.addTab(size, icon, panelOpcion);
    }

}
