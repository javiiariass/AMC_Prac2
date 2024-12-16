/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.Algoritmos;

import com.amc_prac2.Punto.Punto;
import java.util.ArrayList;



/**
 * Algoritmo Voraz con las cuatro aproximaciones propuestas
 *
 * @author javi
 */
public class Voraz {
    
    /**
     * 
     * Primera aproximacion de la estrategia voraz:
     * <p>Busqueda voraz unidireccional exhaustiva: no hace poda y unicamente 
     * busca el proximo Punto mas cercano desde el ultimo aniadido
     * @param puntos Lista de los Puntos
     * @param ciudadInicial Punto inicial desde que iniciar la busqueda / ruta.
     * El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de la lista
     * @return
     */
    public static Ruta EstrategiaUnidireccionalExhaustiva(ArrayList<Punto> puntos, int ciudadInicial) {
        int numCiudades = puntos.size();
        boolean[] visitado = new boolean[numCiudades];
        ArrayList<Punto> ruta = new ArrayList<>();
        double costeTotal = 0.0;

        // Aniadimos ciudad inicial a la ruta
        Punto ciudadActual = puntos.get(ciudadInicial);
        ruta.add(ciudadActual);
        visitado[ciudadInicial] = true;
        
        // Como la ciudad esta aniadida a visitados 
        // al comparar con la siguiente ciudad,
        // no hay que preocuparse de que calcule la distancia consigo misma
        for (int i = ciudadInicial; i < puntos.size(); i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto siguienteCiudad = null;
            int indiceSiguiente = 0;

            // Buscar la ciudad mas cercana no visitada
            // Como hay que recorrer todos los puntos, empezamos desde el inicio
            for (int j = 0; j < numCiudades; j++) {
                if (!visitado[j]) {
                    double distancia = ciudadActual.distancia(puntos.get(j));
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteCiudad = puntos.get(j);
                        indiceSiguiente = j;
                    }
                }
            }

            // Añadir la siguiente ciudad a la ruta
            if (siguienteCiudad != null) {
                ruta.add(siguienteCiudad);
                visitado[indiceSiguiente] = true;
                costeTotal += distanciaMinima;
                ciudadActual = siguienteCiudad;
            }
        }

        // Regresar a la ciudad inicial
        costeTotal += ciudadActual.distancia(puntos.get(ciudadInicial));
        ruta.add(puntos.get(ciudadInicial));

        // Crear y devolver el objeto Ruta
        return new Ruta(ruta,costeTotal, 0);
    }
    /**
     * 
     * Primera aproximacion de la estrategia voraz:
     * <p>Busqueda voraz unidireccional exhaustiva: no hace poda y unicamente 
     * busca el proximo Punto mas cercano desde el ultimo aniadido
     * @param puntos Lista de los Puntos
     * @param ciudadInicial Punto inicial desde que iniciar la busqueda / ruta.
     * El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de la lista
     * @return
     */
    public static Ruta UnidireccionalExhaustiva(ArrayList<Punto> puntos, int ciudadInicial) {
        int numCiudades = puntos.size();
        
        // Todos los valores boolean toman por defecto el valor false
        boolean[] visitado = new boolean[numCiudades];  // En java los array se inicializan automaticamente
        ArrayList<Punto> ruta = new ArrayList<>();
        double costeTotal = 0.0;

        // Aniadimos ciudad inicial a la ruta
        Punto ciudadActual = puntos.get(ciudadInicial);
        ruta.add(ciudadActual);
        visitado[ciudadInicial] = true;
        
        // Como la ciudad esta aniadida a visitados 
        // al comparar con la siguiente ciudad,
        // no hay que preocuparse de que calcule la distancia consigo misma
        for (int i = ciudadInicial; i < puntos.size(); i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto siguienteCiudad = null;
            int indiceSiguiente = 0;

            // Buscar la ciudad mas cercana no visitada
            // Como hay que recorrer todos los puntos, empezamos desde el inicio
            for (int j = 0; j < numCiudades; j++) {
                if (!visitado[j]) {
                    double distancia = ciudadActual.distancia(puntos.get(j));
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteCiudad = puntos.get(j);
                        indiceSiguiente = j;
                    }
                }
            }

            // Añadir la siguiente ciudad a la ruta
            if (siguienteCiudad != null) {
                ruta.add(siguienteCiudad);
                visitado[indiceSiguiente] = true;
                costeTotal += distanciaMinima;
                ciudadActual = siguienteCiudad;
            }
        }

        // Regresar a la ciudad inicial
        costeTotal += ciudadActual.distancia(puntos.get(ciudadInicial));
        ruta.add(puntos.get(ciudadInicial));

        // Crear y devolver el objeto Ruta
        return new Ruta(ruta, costeTotal);
    }
    
    
}




