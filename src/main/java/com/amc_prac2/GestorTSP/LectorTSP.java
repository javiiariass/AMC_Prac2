/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.GestorTSP;

import com.amc_prac2.Punto.Punto;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Gestiona el proceso de leer datasets en formato ".tsp"
 *
 * @author javi
 */
public class LectorTSP {

    /**
     *
     * @param rutaArchivo introducir nombre del archivo.
     * <p>
     * Usa ruta relativa desde la carpeta raíz del proyecto
     * ->"dataset/nombreDelDataset.tsp".
     * <p>
     * Introducir UNICAMENTE NOMBRE de archivo ubicado en carpeta datasets del
     * proyecto. El programa se encarga de ubicarlo y añadirle la extensión .tsp
     * @return Devuelve una lista de los puntos leidos del fichero o una lista
     * vacía en su defecto //@throws IOException
     */
    public static ArrayList<Punto> selectorArchivo(String rutaArchivo) {//throws IOException{
        File fichero = new File("datasets/" + rutaArchivo + ".tsp");
        return lectorArchivo(fichero);
    }
    
    
    
    
    public static ArrayList<Punto> lectorArchivo(File fichero){
       int dimension = 0;
        ArrayList<Punto> puntos = new ArrayList<>();
        boolean empiezanCoordenadas = false;
        //Con try-with-resources nos aseguramos de que los recursos se cierren, ocurra o no una excepción
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {

            String linea;
            //leemos mientras que la linea leida no devuelva nulo
            while ((linea = br.readLine()) != null) {
                //Si la linea empieza por dimension, la dividimos en dos desde los :
                if (linea.startsWith("DIMENSION")) {
                    String[] valores = linea.split(":");
                    //Una vez dividida, tomamos la parte derecha, 
                    //le quitamos los espacios en blanco y lo pasamos a entero
                    dimension = Integer.parseInt(valores[1].trim());

                    //Después de leer está línea, empiezan las coordenadas
                } else if (linea.equals("NODE_COORD_SECTION")) {
                    empiezanCoordenadas = true;

                    //Si empiezan las coordenadas y no estamos en final de fichero
                } else if (empiezanCoordenadas && !linea.equals("EOF")) {
                    //Guadamos en un array de Strings el num de la cordenada y
                    //los dos puntos de la cordenada -> deberían ser 3
                    //Dividimos por el patrón \\s+ -> uno o más carácteres de espacio en blanco
                    String[] coordenadas = linea.trim().split("\\s+");
                    if (coordenadas.length == 3) {
                        //id -> num de la coordenada en la dimension total
                        int id = Integer.parseInt(coordenadas[0]);
                        //coordenada eje X
                        double x = Double.parseDouble(coordenadas[1]);
                        //coordenada eje y
                        double y = Double.parseDouble(coordenadas[2]);
                        puntos.add(new Punto(id, x, y));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
            return null;
        }

        if (puntos.size() != dimension) {
            System.out.println("Advertencia! El número de puntos leídos no coincide con la dimensión especificada");
            return null;
        }

        return puntos;
    }

    

}
