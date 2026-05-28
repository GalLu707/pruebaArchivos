/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebap2_archivos;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class claseGui extends JFrame{
    
    private JTextField txt;
    private JTextField txtBusqueda;
    private JButton btnSeleccionar;
    private JButton btnAnalizar;        
            
    private JLabel lblTxt, lblJava, lblPdf, lblOtros;       
    private JTextArea areaResultados;

    private AnalizadorLogica logica;
     
     
            
           public claseGui(){
               
           }
           
           private void Ventana(){
               setTitle("contador de archivos");
               setSize(800,800);
               setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               setLocationRelativeTo(null);
               setLayout(new BorderLayout(10, 10));

               
           }
           
           
           
           
}
