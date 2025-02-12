/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.Algoritmos;

import java.util.ArrayList;

import com.amc_prac2.Punto.Punto;


/**
 * Algoritmo Voraz con las cuatro aproximaciones propuestas
 *
 * @author javi
 */
public class Voraz {

    /**
     *
     * Primera aproximacion de la estrategia voraz:
     * <p>
     * Busqueda voraz unidireccional exhaustiva: no hace poda y unicamente busca
     * el proximo Punto mas cercano desde el ultimo aniadido
     *
     * @param puntos Lista de los Puntos
     * @param ciudadInicial Punto inicial desde el que iniciar la busqueda /
     * ruta. El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de
     * la lista
     * @return devuelve objeto de tipo Ruta -> contiene array con la solucion,
     * la distancia total y el numero de veces que ha tenido que calcular la
     * distancia euclidea
     */
    public static Ruta UnidireccionalExhaustiva(ArrayList<Punto> puntos, int ciudadInicial) {
        int numCiudades = puntos.size();

        // Todos los valores boolean toman por defecto el valor false
        boolean[] visitado = new boolean[numCiudades];  // En java los array se inicializan automaticamente
        ArrayList<Punto> ruta = new ArrayList<>();
        double distanciaTotal = 0.0;
        int costeTotal = 0;

        // Aniadimos ciudad inicial a la ruta
        Punto ciudadActual = puntos.get(ciudadInicial);
        ruta.add(ciudadActual);
        visitado[ciudadInicial] = true;

        // Como la ciudad esta aniadida a visitados 
        // al comparar con la siguiente ciudad,
        // no hay que preocuparse de que calcule la distancia consigo misma
        for (int i = 0; i < puntos.size(); i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto siguienteCiudad = null;
            int indiceSiguiente = 0;

            // Buscar la ciudad mas cercana no visitada
            // Como hay que recorrer todos los puntos, empezamos desde el inicio
            for (int j = 0; j < numCiudades; j++) {
                if (!visitado[j]) {
                    double distancia = ciudadActual.distancia(puntos.get(j));
                    costeTotal++;
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteCiudad = puntos.get(j);
                        indiceSiguiente = j;
                    }
                }
            }

            if (siguienteCiudad == null) {
                continue;
            }

            // Añadir la siguiente ciudad a la ruta
            ruta.add(siguienteCiudad);
            visitado[indiceSiguiente] = true;
            distanciaTotal += distanciaMinima;
            ciudadActual = siguienteCiudad;

        }

        // Regresar a la ciudad inicial
        distanciaTotal += ciudadActual.distancia(puntos.get(ciudadInicial));
        costeTotal++;
        ruta.add(puntos.get(ciudadInicial));

        // Crear y devolver el objeto Ruta
        return new Ruta(ruta, distanciaTotal, costeTotal);
    }

    /**
     *
     * Segunda aproximacion de la estrategia voraz:
     * <p>
     * Busqueda voraz bidireccional exhaustiva: no hace poda, busca el proximo
     * Punto mas cercano desde el primer y ultimo punto
     *
     * @param puntos Lista de los Puntos
     * @param ciudadInicial Punto inicial desde el que iniciar la busqueda /
     * ruta. El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de
     * la lista
     * @return devuelve objeto de tipo Ruta -> contiene array con la solucion,
     * la distancia total y el numero de veces que ha tenido que calcular la
     * distancia euclidea
     */
    public static Ruta BidireccionalExhaustiva(ArrayList<Punto> puntos, int ciudadInicial) {
        int numCiudades = puntos.size();

        // Todos los valores boolean toman por defecto el valor false
        boolean[] visitado = new boolean[numCiudades];  // En java los array se inicializan automaticamente
        ArrayList<Punto> ruta = new ArrayList<>();
        ArrayList<Punto> rutaAux = new ArrayList<>(); // Tener en cuenta que el orden hay que invertirlo
        double distanciaTotal = 0.0;
        int costeTotal = 0;

        // Aniadimos ciudad inicial a las rutas
        Punto ciudadActual = puntos.get(ciudadInicial);
        Punto ciudadActualAux = puntos.get(ciudadInicial);
        ruta.add(ciudadActual);
        rutaAux.add(ciudadActualAux);
        visitado[ciudadInicial] = true;

        // Como la ciudad esta aniadida a visitados 
        // al comparar con la siguiente ciudad,
        // no hay que preocuparse de que calcule la distancia consigo misma
        for (int i = 0; i < puntos.size(); i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto siguienteCiudad = null;
            int indiceSiguiente = 0;

            //Indica si el punto debe añadirse al inicio o al final de la cola / ruta
            boolean puntoCabeza = true;

            // Buscar la ciudad mas cercana no visitada por la cabeza de la cola
            // Como hay que recorrer todos los puntos, empezamos desde el inicio
            for (int j = 0; j < numCiudades; j++) {
                if (!visitado[j]) {
                    double distancia = ciudadActual.distancia(puntos.get(j));
                    costeTotal++;
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteCiudad = puntos.get(j);
                        indiceSiguiente = j;
                    }

                    //Repito comprobacion con el primer punto del array
                }
            }

            // Buscar la ciudad mas cercana no visitada por la cola de la cola
            // Como hay que recorrer todos los puntos, empezamos desde el inicio
            // Evitamos iterar dos veces sobre el punto inicial
            if (ciudadActualAux.getId() != ciudadActual.getId()) {
                for (int j = 0; j < numCiudades; j++) {
                    if (!visitado[j]) {
                        double distancia = ciudadActualAux.distancia(puntos.get(j));
                        costeTotal++;
                        if (distancia < distanciaMinima) {
                            distanciaMinima = distancia;
                            siguienteCiudad = puntos.get(j);
                            indiceSiguiente = j;
                            puntoCabeza = false;
                        }

                        //Repito comprobacion con el primer punto del array
                    }
                }
            }

            //En teoria no deberia llegar a pasar
            if (siguienteCiudad == null) {
                continue;
            }

            // Añadir la siguiente ciudad a la ruta
            if (puntoCabeza) {
                ruta.add(siguienteCiudad);
                ciudadActual = siguienteCiudad;
            } else {
                rutaAux.add(siguienteCiudad);
                ciudadActualAux = siguienteCiudad;
            }

            visitado[indiceSiguiente] = true;
            distanciaTotal += distanciaMinima;

        }

        // Calculamos la distancia de los puntos que quedan en los extremos de la ruta
        distanciaTotal += ciudadActual.distancia(ciudadActualAux);
        costeTotal++;

        // Aniadimos los puntos de la ruta auxiliar a la ruta completa con su orden correcto
        // ciudad inicial ya estaba en rutaAux
        for (int i = rutaAux.size() - 1; i >= 0; i--) {
            ruta.addLast(rutaAux.get(i));
        }

        // Crear y devolver el objeto Ruta
        return new Ruta(ruta, distanciaTotal, costeTotal);
    }

    /**
     *
     * Tercera aproximacion de la estrategia voraz:
     * <p>
     * Busqueda voraz unidireccional exhaustiva con poda: Ordena el array por la
     * coordenada X y unicamente busca el proximo Punto mas cercano desde el
     * ultimo aniadido
     *
     * @param puntos Lista de los Puntos
     * @param ciudadInicial Punto inicial desde el que iniciar la busqueda /
     * ruta. El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de
     * la lista
     * @return devuelve objeto de tipo Ruta -> contiene array con la solucion,
     * la distancia total y el numero de veces que ha tenido que calcular la
     * distancia euclidea
     */
    public static Ruta UnidireccionalExhaustivaPoda(ArrayList<Punto> puntos, int ciudadInicial){

        // La poda hay que realizarla en el segundo bucle, cuando la distancia en el eje ordenado por 
        // quicksort sea mayor o igual a la distancia euclidea menor actual
        
        // Al ordenar los puntos la ciudad inicial dejara de estar en la posicion que estaba.
        // Debemos buscarla para empezar por el mismo punto en todos los algoritmos
        Punto puntoInicial = puntos.get(ciudadInicial);
        
        // Primero ordenamos el array haciendo uso de quicksort
        Quicksort.quickSort(puntos);
        
        ciudadInicial = puntos.indexOf(puntoInicial);
        
        int numCiudades = puntos.size();
        // Todos los valores boolean toman por defecto el valor false
        boolean[] visitado = new boolean[numCiudades];  // En java los array se inicializan automaticamente
        ArrayList<Punto> ruta = new ArrayList<>();
        double distanciaTotal = 0.0;
        int costeTotal = 0;

        // Aniadimos ciudad inicial a la ruta
        Punto ciudadActual = puntos.get(ciudadInicial);
        ruta.add(ciudadActual);
        visitado[ciudadInicial] = true;

        // Como la ciudad esta aniadida a visitados 
        // al comparar con la siguiente ciudad,
        // no hay que preocuparse de que calcule la distancia consigo misma
        for (int i = 0; i < puntos.size(); i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto siguienteCiudad = null;
            int indiceSiguiente = 0;

            // Buscar la ciudad mas cercana no visitada
            // Como hay que podar, empezamos desde el punto siguiente al inicial (el mas cercano por eje)
            for (int j = 0; j < numCiudades; j++) {
                int acotar = (j + ciudadInicial) % puntos.size();
                // Poda
                double distanciaEje = Math.abs(ciudadActual.getX() - puntos.get(acotar).getX());         // Es mejor hacer la poda antes de comprobar si
                if(distanciaEje >= distanciaMinima)                                                 // el punto ha sido visitado? o despues
                    break;
                
                if (!visitado[acotar]) {
                    double distancia = ciudadActual.distancia(puntos.get(acotar));
                    costeTotal++;
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteCiudad = puntos.get(acotar);
                        indiceSiguiente = acotar;
                    }
                }
            }

            if (siguienteCiudad == null) {
                continue;
            }

            // Añadir la siguiente ciudad a la ruta
            ruta.add(siguienteCiudad);
            visitado[indiceSiguiente] = true;
            distanciaTotal += distanciaMinima;
            ciudadActual = siguienteCiudad;

        }

        // Regresar a la ciudad inicial
        distanciaTotal += ciudadActual.distancia(puntos.get(ciudadInicial));
        costeTotal++;
        ruta.add(puntos.get(ciudadInicial));

        // Crear y devolver el objeto Ruta
        return new Ruta(ruta, distanciaTotal, costeTotal);
    }
    
    
    /**
     *
     * Cuarta aproximacion de la estrategia voraz:
     * <p>
     * Busqueda voraz bidireccional exhaustiva con poda: busca el proximo
     * Punto mas cercano, desde el primer y ultimo punto, podando cuando
     * la distancia entre los puntos del eje ordenado por quicksort sea mayor
     * o igual a la distancia minima actual
     *
     * @param puntos Lista de los Puntos
     * @param ciudadInicial Punto inicial desde el que iniciar la busqueda /
     * ruta. El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de
     * la lista
     * @return devuelve objeto de tipo Ruta -> contiene array con la solucion,
     * la distancia total y el numero de veces que ha tenido que calcular la
     * distancia euclidea
     */
    public static Ruta BidireccionalExhaustivaPoda(ArrayList<Punto> puntos, int ciudadInicial) {
        
        // La poda hay que realizarla en el segundo bucle, cuando la distancia en el eje ordenado por 
        // quicksort sea mayor o igual a la distancia euclidea menor actual
        
        // Al ordenar los puntos la ciudad inicial dejara de estar en la posicion que estaba.
        // Debemos buscarla para empezar por el mismo punto en todos los algoritmos
        Punto puntoInicial = puntos.get(ciudadInicial);
        
        // Primero ordenamos el array haciendo uso de quicksort
        Quicksort.quickSort(puntos);
        
        ciudadInicial = puntos.indexOf(puntoInicial);
        
        int numCiudades = puntos.size();

        // Todos los valores boolean toman por defecto el valor false
        boolean[] visitado = new boolean[numCiudades];  // En java los array se inicializan automaticamente
        ArrayList<Punto> ruta = new ArrayList<>();
        ArrayList<Punto> rutaAux = new ArrayList<>(); // Tener en cuenta que el orden hay que invertirlo
        double distanciaTotal = 0.0;
        int costeTotal = 0;

        // Aniadimos ciudad inicial a las rutas
        Punto ciudadActual = puntos.get(ciudadInicial);
        Punto ciudadActualAux = puntos.get(ciudadInicial);
        ruta.add(ciudadActual);
        rutaAux.add(ciudadActualAux);
        visitado[ciudadInicial] = true;

        // Como la ciudad esta aniadida a visitados 
        // al comparar con la siguiente ciudad,
        // no hay que preocuparse de que calcule la distancia consigo misma
        for (int i = 0; i < puntos.size(); i++) {
            double distanciaMinima = Double.MAX_VALUE;
            Punto siguienteCiudad = null;
            int indiceSiguiente = 0;

            //Indica si el punto debe añadirse al inicio o al final de la cola / ruta
            boolean puntoCabeza = true;

            // Buscar la ciudad mas cercana no visitada por la cabeza de la cola
            // empezamos desde el inicio
            for (int j = 0; j < numCiudades; j++) {
                
                int acotar = j;//(j + ciudadInicial) % puntos.size();
                // Poda
                double distanciaEje = Math.abs(ciudadActual.getX() - puntos.get(acotar).getX());         // Es mejor hacer la poda antes de comprobar si
                if(distanciaEje >= distanciaMinima)                                                 // el punto ha sido visitado? o despues
                    break;
                
                if (!visitado[acotar]) {
                    double distancia = ciudadActual.distancia(puntos.get(acotar));
                    costeTotal++;
                    if (distancia < distanciaMinima) {
                        distanciaMinima = distancia;
                        siguienteCiudad = puntos.get(acotar);
                        indiceSiguiente = acotar;
                    }

                    //Repito comprobacion con el primer punto del array
                }
            }
            
            double distanciaMinimaIzda = Double.MAX_VALUE;                 //Reseteo valor de distancia minima
            // Buscar la ciudad mas cercana no visitada por la cola de la cola
            // Como hay que recorrer todos los puntos, empezamos desde el inicio
            // Evitamos iterar dos veces sobre el punto inicial
            if (ciudadActualAux.getId() != ciudadActual.getId()) {
                for (int j = 0; j < numCiudades; j++) {
                    
                    int acotar = j;//(j + ciudadInicial) % puntos.size();
                    // Poda
                    double distanciaEje = Math.abs(ciudadActualAux.getX() - puntos.get(acotar).getX());         // Es mejor hacer la poda antes de comprobar si
                    if(distanciaEje >= distanciaMinimaIzda)                                                 // el punto ha sido visitado? o despues
                        break;
                    
                    if (!visitado[acotar]) {
                        double distancia = ciudadActualAux.distancia(puntos.get(acotar));
                        costeTotal++;
                        
                        if (distancia < distanciaMinimaIzda)                                                // solo lo uso para podar
                            distanciaMinimaIzda = distancia;
                        
                        if (distancia < distanciaMinima) {
                            distanciaMinima = distancia;
                            siguienteCiudad = puntos.get(acotar);
                            indiceSiguiente = acotar;
                            puntoCabeza = false;
                        }

                        //Repito comprobacion con el primer punto del array
                    }
                }
            }

            //En teoria no deberia llegar a pasar
            if (siguienteCiudad == null) {
                continue;
            }

            // Añadir la siguiente ciudad a la ruta
            if (puntoCabeza) {
                ruta.add(siguienteCiudad);
                ciudadActual = siguienteCiudad;
            } else {
                rutaAux.add(siguienteCiudad);
                ciudadActualAux = siguienteCiudad;
            }

            visitado[indiceSiguiente] = true;
            distanciaTotal += distanciaMinima;

        }

        // Calculamos la distancia de los puntos que quedan en los extremos de la ruta
        distanciaTotal += ciudadActual.distancia(ciudadActualAux);
        costeTotal++;

        // Aniadimos los puntos de la ruta auxiliar a la ruta completa con su orden correcto
        // ciudad inicial ya estaba en rutaAux
        for (int i = rutaAux.size() - 1; i >= 0; i--) {
            ruta.addLast(rutaAux.get(i));
        }

        // Crear y devolver el objeto Ruta
        return new Ruta(ruta, distanciaTotal, costeTotal);
    }

}
