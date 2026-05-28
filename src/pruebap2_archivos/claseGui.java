/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebap2_archivos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author USER
 */
public class claseGui extends JFrame{
    
    private JTextField txtruta;
    private JTextField txtBusqueda;
    private JButton btnSeleccionar;
    private JButton btnAnalizar;        
            
    private JLabel lblTxt, lblJava, lblPdf, lblOtros;       
    private JTextArea areaResultados;

    private AnalizadorLogica logica;
     
     
            
           public claseGui(){
               logica = new AnalizadorLogica();
               Ventana();
               componentes();
           }
           
           private void Ventana(){
               setTitle("contador de archivos");
               setSize(600,600);
               setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               setLocationRelativeTo(null);
               setLayout(new BorderLayout(10, 10));

               
           }
           
          private void componentes(){
         
              JPanel panelEntrada = new JPanel(new GridLayout(3, 1, 5, 5));
              panelEntrada.setBorder(BorderFactory.createTitledBorder("Configuración de Búsqueda"));

              JPanel filaRuta = new JPanel(new BorderLayout(5, 5));
              filaRuta.add(new JLabel(" Directorio Raíz: "), BorderLayout.WEST);
              txtruta = new JTextField();
              filaRuta.add(txtruta, BorderLayout.CENTER);
              btnSeleccionar = new JButton("Buscar carpeta...");
              filaRuta.add(btnSeleccionar, BorderLayout.EAST);
              JPanel filaBusqueda = new JPanel(new BorderLayout(5, 5));
              filaBusqueda.add(new JLabel(" Texto a buscar: "), BorderLayout.WEST);
              txtBusqueda = new JTextField();
              filaBusqueda.add(txtBusqueda, BorderLayout.CENTER);

              btnAnalizar = new JButton("Iniciar Análisis y Búsqueda");
              btnAnalizar.setFont(new Font("Comic Sans Ms", Font.BOLD, 13));

              panelEntrada.add(filaRuta);
              panelEntrada.add(filaBusqueda);
              panelEntrada.add(btnAnalizar);
              
               JPanel panelResultados = new JPanel(new BorderLayout(10, 10));

              JPanel panelContadores = new JPanel(new GridLayout(1, 4, 10, 10));
              panelContadores.setBorder(BorderFactory.createTitledBorder("Contador de Archivos por Extensión"));

              lblTxt = new JLabel("TXT: 0", SwingConstants.CENTER);
              lblJava = new JLabel("JAVA: 0", SwingConstants.CENTER);
              lblPdf = new JLabel("PDF: 0", SwingConstants.CENTER);
              lblOtros = new JLabel("OTROS: 0", SwingConstants.CENTER);

              Font fuenteLabels = new Font("Comic Sans Ms", Font.BOLD, 12);
              lblTxt.setFont(fuenteLabels);
              lblJava.setFont(fuenteLabels);
              lblPdf.setFont(fuenteLabels);
              lblOtros.setFont(fuenteLabels);

              panelContadores.add(lblTxt);
              panelContadores.add(lblJava);
              panelContadores.add(lblPdf);
              panelContadores.add(lblOtros);

              JPanel panelRutas = new JPanel(new BorderLayout());
              panelRutas.setBorder(BorderFactory.createTitledBorder("Rutas de Archivos Coincidentes"));
              areaResultados = new JTextArea();
              areaResultados.setEditable(false);
              JScrollPane scrollPane = new JScrollPane(areaResultados);
              panelRutas.add(scrollPane, BorderLayout.CENTER);

              panelResultados.add(panelContadores, BorderLayout.NORTH);
              panelResultados.add(panelRutas, BorderLayout.CENTER);
              add(panelEntrada, BorderLayout.NORTH);
              add(panelResultados, BorderLayout.CENTER);


              
              btnSeleccionar.addActionListener(new ActionListener() {
                 
                  @Override
                  public void actionPerformed(ActionEvent e){
                      
                      JFileChooser selector = new JFileChooser();
                selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Solo carpetas
                int opcion = selector.showOpenDialog(claseGui.this);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    txtruta.setText(selector.getSelectedFile().getAbsolutePath());
                      
                      
                  }
                  
                  }
                  
              });
              
              btnAnalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAnalisis();
            }
        });
              
              

     }

    private void ejecutarAnalisis() {
        String rutaIntroducida = txtruta.getText().trim();
        String textoBusqueda = txtBusqueda.getText().trim();

        
         if (rutaIntroducida.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce o selecciona una ruta.", "Campo vacío", JOptionPane.WARNING_MESSAGE);
            return;
        }

        File directorio = new File(rutaIntroducida);

        if (!directorio.exists()) {
            JOptionPane.showMessageDialog(this, "La ruta introducida no existe en el sistema.", "Error de Ruta", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!directorio.isDirectory()) {
            JOptionPane.showMessageDialog(this, "La ruta corresponde a un archivo individual. Debe seleccionar un Directorio (Carpeta).", "Error de Tipo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        ResultadoBusqueda resul = logica.procesarDir(directorio, textoBusqueda);
        
        lblTxt.setText("TXT: " + resul.txt + " archivos");
        lblJava.setText("JAVA: " + resul.java + " archivos");
        lblPdf.setText("PDF: " + resul.pdf + " archivos");
        lblOtros.setText("OTROS: " + resul.otros + " archivos");


      areaResultados.setText("");
        if (resul.rutasEncontradas.isEmpty()) {
            areaResultados.setText("No se encontraron archivos que coincidan con los criterios.");
        } else {
            for (String ruta : resul.rutasEncontradas) {
                areaResultados.append(ruta + "\n");
            }
        }  
        
        
        
        
    }

       
           
}
