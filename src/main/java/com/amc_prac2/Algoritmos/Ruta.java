/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.Algoritmos;

import com.amc_prac2.Punto.Punto;
import java.util.ArrayList;

/**
 * Clase para guardar la ruta de puntos con el total de 
 * veces que se ha calculado la distancia euclidea
 * @author javi
 */
public class Ruta {

    private final ArrayList<Punto> ruta;
    private final int costeTotal;
    private final double distanciaTotal;

    /**
     * Objeto para gestionar la ruta obtenida con las distintas estrategias
     * de busqueda y el numero de calculos necesarios para ello
     * @param ruta ArrayList de objetos tipo Punto en el que almacenar la ruta
     * @param distanciaTotal suma de las distancias del recorrido
     * @param costeTotal numero de veces que se ha tenido que calcular la distancia
     * euclidea para obtener la ruta actual
     */
    public Ruta(ArrayList<Punto> ruta, double distanciaTotal, int costeTotal) {
        this.ruta = ruta;
        this.distanciaTotal = distanciaTotal;
        this.costeTotal = costeTotal;
    }
    
    /**
     * Objeto para gestionar la ruta obtenida con las distintas estrategias
     * de busqueda y el numero de calculos necesarios para ello
     * <p> Inicializa costeTotal a 0
     * @param ruta ArrayList de objetos tipo Punto en el que almacenar la ruta
     */
    public Ruta(ArrayList<Punto> ruta) {
        this.ruta = ruta;
        this.costeTotal = 0;
        this.distanciaTotal = 0;
    }
    
    /**
     *
     * @return ArrayList de los Puntos que forman la ruta
     */
    public ArrayList<Punto> getRuta() {
        return ruta;
    }

    /**
     *
     * @return Numero de veces que se ha calculado la distancia
     * euclidea en la ruta actual
     */
    public double getCosteTotal() {
        return costeTotal;
    }
    
    /**
     *
     * @return suma de las distancias entre los puntos de la ruta
     */
    public double getDistanciaTotal(){
        return distanciaTotal;
    }
    
}
