/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebap2_archivos;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class AnalizadorLogica {
   

    
    
    
    public ResultadoBusqueda procesarDir(File DirRaiz, String TxtBusqueda){
        ResultadoBusqueda resul= new ResultadoBusqueda();
        
        String txtminus = TxtBusqueda.toLowerCase();
         recorerSistemaArchivos(DirRaiz, txtminus, resul);
        return resul;
    }
    
    
    
    private void recorerSistemaArchivos(File dir, String txtBusqueda,ResultadoBusqueda res ){
        File[] subElementos = dir.listFiles();
        
        if(subElementos== null)return;
        
        for(File elemento : subElementos){
            if(elemento.isDirectory()){
                recorerSistemaArchivos(elemento, txtBusqueda, res);
              
                } else if (elemento.isFile()) {
             
                clasificarExtension(elemento.getName(), res);
                
                if (elemento.getName().toLowerCase().contains(txtBusqueda)) {
                    res.rutasEncontradas.add(elemento.getAbsolutePath());
                }
            
                
            }
        }
        
        
        
    }
    
    private void clasificarExtension(String nombreArchivo, ResultadoBusqueda res) {
        String nombreMinuscula = nombreArchivo.toLowerCase();
        if (nombreMinuscula.endsWith(".txt")) {
            res.txt++;
        } else if (nombreMinuscula.endsWith(".java")) {
            res.java++;
        } else if (nombreMinuscula.endsWith(".pdf")) {
            res.pdf++;
        } else {
            res.otros++;
        }
    }
    
    
}
