/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.Algoritmos;

import com.amc_prac2.Punto.Punto;
import java.util.ArrayList;

/**
 * Algoritmo de ordenacion de puntos (2 dimensiones) en base a sus coordenadas
 *
 * @author javi
 */
public class Quicksort {

    /**
     * Ordena el array de puntos por la coordenada X de manera creciente No es
     * necesario indicar el limite / tamanio del array
     *
     * @param puntos lista de objetos tipo Punto
     */
    public static void quickSort(ArrayList<Punto> puntos) {
        quickSort(puntos, 0, puntos.size() - 1);
    }

    /**
     * Ordena el array de puntos por la coordenada Y de manera creciente <p>
     * No es necesario indicar el limite / tamanio del array
     *
     * @param puntos lista de objetos tipo Punto
     */
    public static void quickSortY(ArrayList<Punto> puntos) {
        quickSortY(puntos, 0, puntos.size() - 1);
    }

    /**
     * Ordena el array de puntos por la coordenada X de manera creciente
     *
     * @param puntos lista de objetos tipo Punto
     * @param izquierda limite izquierdo / inicio del array
     * @param derecha limite derecho / fin del array
     */
    // Función principal para ordenar el arreglo usando QuickSort
    public static void quickSort(ArrayList<Punto> puntos, int izquierda, int derecha) {

        if (izquierda < derecha) {
            Punto pivote = puntos.get(izquierda);
            int i = izquierda;
            int j = derecha;

            while (i <= j) {
                while (puntos.get(i).getX() < pivote.getX()) {
                    i++;
                }
                while (puntos.get(j).getX() > pivote.getX()) {
                    j--;
                }
                if (i <= j) {
                    Punto temp = puntos.get(i);
                    puntos.set(i, puntos.get(j));
                    puntos.set(j, temp);
                    i++;
                    j--;
                }
            }

            if (izquierda < j) {
                quickSort(puntos, izquierda, j);
            }
            if (i < derecha) {
                quickSort(puntos, i, derecha);
            }
        }
    }

    /**
     * Ordena el array de puntos por la coordenada Y de manera creciente
     *
     * @param puntos lista de objetos tipo Punto
     * @param izquierda limite izquierdo / inicio del array
     * @param derecha limite derecho / fin del array
     */
    public static void quickSortY(ArrayList<Punto> puntos, int izquierda, int derecha) {

        if (izquierda < derecha) {
            Punto pivote = puntos.get(izquierda);
            int i = izquierda;
            int j = derecha;

            while (i <= j) {
                while (puntos.get(i).getY() < pivote.getY()) {
                    i++;
                }
                while (puntos.get(j).getY() > pivote.getY()) {
                    j--;
                }
                if (i <= j) {
                    Punto temp = puntos.get(i);
                    puntos.set(i, puntos.get(j));
                    puntos.set(j, temp);
                    i++;
                    j--;
                }
            }

            if (izquierda < j) {
                quickSortY(puntos, izquierda, j);
            }
            if (i < derecha) {
                quickSortY(puntos, i, derecha);
            }
        }
    }
}
