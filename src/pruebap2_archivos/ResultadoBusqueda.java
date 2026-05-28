/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebap2_archivos;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ResultadoBusqueda {
   public int txt;
    public int java;
    public int pdf;
    public int otros;
    public ArrayList<String> rutasEncontradas;

    public ResultadoBusqueda() {
        this.txt = 0;
        this.java = 0;
        this.pdf = 0;
        this.otros = 0;
        this.rutasEncontradas = new ArrayList<>();
    }  
}
