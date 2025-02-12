/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.GestorTSP;
import com.amc_prac2.Algoritmos.Ruta;
import com.amc_prac2.Punto.Punto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Gestiona el proceso de guardar la info/datasets en archivos
 * @author javi
 */
public class EscritorTSP {
    
    /**
     * <b>IMPORTANTE</b> Si el archivo indicado existe, lo sobreescribe
     * <p>Indicar unicamente el nombre del archivo. El metodo se engarga de aniadirle
     * la extension ".tsp" y lo guarda en la carpeta datasets ubicada en la raiz del 
     * proyecto
     * @param puntos lista de puntos a escribir en archivo
     * @param nombreArchivo nombre del archivo a guardar
     */
    public static void guardarEnArchivo(ArrayList<Punto> puntos, String nombreArchivo) {
        
                                //Si el archivo ya existe, lo sobrescribe 
        
        //al usar try-with-resources writer se cierra automáticamente se produzca o no una excepción
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("datasets/" + nombreArchivo + ".tsp"))){
            // Escribir la cabecera
            writer.write("NAME: dataset" + puntos.size() + "\n");
            writer.write("TYPE: TSP\n");
            writer.write("COMMENT: " + puntos.size() + " random locations\n");
            writer.write("DIMENSION: " + puntos.size() + "\n");
            writer.write("EDGE_WEIGHT_TYPE: EUC_2D\n");
            writer.write("NODE_COORD_SECTION\n");
            
            // Escribir los puntos
            for (Punto punto : puntos) {
                writer.write(punto.getId() + " " + punto.getX() + " " + punto.getY() + "\n");
            }
            
            //Escribir EOF al final del archivo
            writer.write("EOF\n");
        }catch(IOException e){
            System.out.println("Error de escritura: " + e.getMessage() + "\nPor favor, elimine el archivo generado");
        }
    }
    
     /**
     * <b>IMPORTANTE</b> Si el archivo indicado existe, lo sobreescribe
     * <p>Indicar unicamente el nombre del archivo. El metodo se engarga de aniadirle
     * la extension ".tsp" y lo guarda en la carpeta datasets ubicada en la raiz del 
     * proyecto
     * @param ruta contenedor de datos con:<p>
     * - lista de puntos a escribir en archivo <p>
     * - distancia total<p>
     * - numero de distancias euclideas calculadas
     * @param nombreArchivo nombre del archivo a guardar
     */
    public static void guardarEnArchivoTour(Ruta ruta, String nombreArchivo) {
        
                                //Si el archivo ya existe, lo sobrescribe 
        
        //al usar try-with-resources writer se cierra automáticamente se produzca o no una excepción
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("tours/" + nombreArchivo + ".tour"))){
            // Escribir la cabecera
            writer.write("NAME: " + nombreArchivo + ".tour\n");
            writer.write("TYPE: TOUR\n");
            // Como punto inicial y final son el mismo, restar 1 punto a la dimension total
            writer.write("DIMENSION: " + (ruta.getRuta().size()-1) + "\n");
            writer.write("SOLUTION: " + ruta.getDistanciaTotal() + "\n");
            writer.write("TOUR_SECTION\n");
            
            // Escribo camino
            ArrayList<Punto> rutaAux = new ArrayList<>(ruta.getRuta());
            
            writer.write("" + rutaAux.get(0).getId());
            for(int i = 1; i < ruta.getRuta().size(); i++){
                writer.write(" - " + rutaAux.get(i).getId());
            }
            
            
            // Escribir los puntos
            for (int i = 0; i<ruta.getRuta().size()-1; i++) {
                // Escribimos la distancia entre los dos puntos
                writer.write("\n" + rutaAux.get(i).distancia( rutaAux.get(i+1) ) + " - ");
                writer.write( rutaAux.get(i).getId() + ", "+ rutaAux.get(i+1).getId());
            }
            
            //Escribir EOF al final del archivo
            writer.write("\nEOF\n");
        }catch(IOException e){
            System.out.println("Error de escritura: " + e.getMessage() + "\nPor favor, elimine el archivo generado");
        }
    }
    
    
}
