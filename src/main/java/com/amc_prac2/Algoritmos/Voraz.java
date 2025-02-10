/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.amc_prac2.Algoritmos;

import java.util.ArrayList;

import com.amc_prac2.Punto.Punto;
import java.util.Arrays;
import java.util.List;

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
     * @param ciudadInicial Punto inicial desde que iniciar la busqueda / ruta.
     * El primer punto es el 0 -> si ciudadInicial == 3 -> 4o elemento de la
     * lista
     * @return
     */
//    public static Ruta EstrategiaUnidireccionalExhaustiva(ArrayList<Punto> puntos, int ciudadInicial) {
//        int numCiudades = puntos.size();
//        boolean[] visitado = new boolean[numCiudades];
//        ArrayList<Punto> ruta = new ArrayList<>();
//        double costeTotal = 0.0;
//
//        // Aniadimos ciudad inicial a la ruta
//        Punto ciudadActual = puntos.get(ciudadInicial);
//        ruta.add(ciudadActual);
//        visitado[ciudadInicial] = true;
//        
//        // Como la ciudad esta aniadida a visitados 
//        // al comparar con la siguiente ciudad,
//        // no hay que preocuparse de que calcule la distancia consigo misma
//        for (int i = ciudadInicial; i < puntos.size(); i++) {
//            double distanciaMinima = Double.MAX_VALUE;
//            Punto siguienteCiudad = null;
//            int indiceSiguiente = 0;
//
//            // Buscar la ciudad mas cercana no visitada
//            // Como hay que recorrer todos los puntos, empezamos desde el inicio
//            for (int j = 0; j < numCiudades; j++) {
//                if (!visitado[j]) {
//                    double distancia = ciudadActual.distancia(puntos.get(j));
//                    if (distancia < distanciaMinima) {
//                        distanciaMinima = distancia;
//                        siguienteCiudad = puntos.get(j);
//                        indiceSiguiente = j;
//                    }
//                }
//            }
//
//            // Añadir la siguiente ciudad a la ruta
//            if (siguienteCiudad != null) {
//                ruta.add(siguienteCiudad);
//                visitado[indiceSiguiente] = true;
//                costeTotal += distanciaMinima;
//                ciudadActual = siguienteCiudad;
//            }
//        }
//
//        // Regresar a la ciudad inicial
//        costeTotal += ciudadActual.distancia(puntos.get(ciudadInicial));
//        ruta.add(puntos.get(ciudadInicial));
//
//        // Crear y devolver el objeto Ruta
//        return new Ruta(ruta,costeTotal, 0);
//    }
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
     * @throws java.lang.InterruptedException
     */
    public static Ruta UnidireccionalExhaustivaPoda(ArrayList<Punto> puntos, int ciudadInicial) throws InterruptedException {

        // La poda hay que realizarla en el segundo bucle, cuando la distancia en el eje ordenado por 
        // quicksort sea mayor o igual a la distancia euclidea menor actual
        System.out.println("Posicion inicial punto: " + ciudadInicial);
        System.out.println("Punto: " + puntos.get(ciudadInicial));
        
        // Al ordenar los puntos la ciudad inicial dejara de estar en la posicion que estaba.
        // Debemos buscarla para empezar por el mismo punto en todos los algoritmos
        Punto aux = puntos.get(ciudadInicial);
        
        
        // Primero ordenamos el array haciendo uso de quicksort
        Quicksort.quickSort(puntos);
        
        ciudadInicial = puntos.indexOf(aux);
        
        System.out.println("Posicion punto nueva : " + ciudadInicial);
        System.out.println("Punto: " + puntos.get(ciudadInicial));
        
        Thread.sleep(3000);
        
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
        
        // Primero ordenamos el array haciendo uso de quicksort
        Quicksort.quickSort(puntos);
        
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

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------    
//}
    private static int cont = 0;
    private static double solucion = 0.0;
    private static ArrayList<Double> rayas = new ArrayList<>();

    public static void setSolucion() {
        Voraz.solucion = 0;
    }

    public static double getSolucion() {
        return solucion;
    }

    public static void setCont() {
        Voraz.cont = 0;
    }

    public static int getCont() {
        return cont;
    }

    private static int ciudadMasCercana(Punto origen, ArrayList<Punto> ciudades, boolean[] visitadas) {
        int posicion = -1;
        double minima = Double.MAX_VALUE;
        for (int i = 0; i < ciudades.size(); i++) {
            if (!visitadas[i]) {
                double distancia = distancia(origen, ciudades.get(i));
                cont++;

                if (distancia < minima) {
                    minima = distancia;
                    posicion = i;
                }
            }
        }
        return posicion;
    }

    public static double distancia(Punto a, Punto b) {
        double x = Math.abs(a.getX() - b.getX());
        double y = Math.abs(a.getY() - b.getY());

        return (double) Math.sqrt((x * x) + (y * y));
    }

    private static int ciudadMasCercanaPoda(Punto origen, ArrayList<Punto> ciudades, boolean[] visitadas) {
        int posicion = -1;
        double minima = Double.MAX_VALUE;

        for (int i = 0; i < ciudades.size(); i++) {
            if (!visitadas[i]) {
                // Poda respecto al eje X
                if (Math.abs(origen.getX() - ciudades.get(i).getX()) < minima) {
                    // Calcula la distancia solo si pasa la poda
                    double distancia = distancia(origen, ciudades.get(i));
                    cont++;

                    if (distancia < minima) {
                        minima = distancia;
                        posicion = i;
                    }
                }
            }
        }

        return posicion;
    }

    public static ArrayList<Punto> vorazUnidireccional(ArrayList<Punto> ciudades, int ini) {
        setCont();
        setSolucion();
        rayas.clear();
        ArrayList<Punto> ruta = new ArrayList<>();
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);

        Punto ciudadActual = ciudades.get(ini);
        visitadas[ini] = true;
        ruta.add(ciudadActual);

        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercana(ciudadActual, ciudades, visitadas);
            double dist = distancia(ciudadActual, ciudades.get(posicion));
            cont++;
            solucion += dist;
            rayas.add(dist);
            visitadas[posicion] = true;
            ruta.add(ciudades.get(posicion));
            ciudadActual = ciudades.get(posicion);
        }
        double distanciafinal = distancia(ciudadActual, ciudades.get(ini));
        cont++;
        solucion += distanciafinal;
        rayas.add(distanciafinal);
        ruta.add(ciudades.get(ini));

        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccional(ArrayList<Punto> ciudades, int ini) {
        rayas.clear();
        ArrayList<Punto> ruta = new ArrayList<>();
        setCont();
        setSolucion();
        ArrayList<Punto> rutaIzquierda = new ArrayList<>();
        ArrayList<Punto> rutaDerecha = new ArrayList<>();
        double distanciaIzquierda = 0, distanciaDerecha = 0, distanciaFinal = 0;
        Punto inicio = ciudades.get(ini);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ini] = true;

        rutaIzquierda.add(inicio);
        rutaDerecha.add(inicio);
        Punto extremoIzq = inicio;
        Punto extremoDer = inicio;

        int cercanaIzquierda = -1;
        int cercanaDerecha = -1;

        while (rutaIzquierda.size() + rutaDerecha.size() <= ciudades.size()) {
            if (cercanaIzquierda == -1 || visitadas[cercanaIzquierda]) {
                cercanaIzquierda = ciudadMasCercana(extremoIzq, ciudades, visitadas);
                distanciaIzquierda = distancia(extremoIzq, ciudades.get(cercanaIzquierda));
                cont++;
            }

            if (cercanaDerecha == -1 || visitadas[cercanaDerecha]) {
                cercanaDerecha = ciudadMasCercana(extremoDer, ciudades, visitadas);
                distanciaDerecha = distancia(extremoDer, ciudades.get(cercanaDerecha));
                cont++;
            }

            if (distanciaIzquierda <= distanciaDerecha) {
                Punto izquierda = ciudades.get(cercanaIzquierda);
                rutaIzquierda.add(izquierda);
                visitadas[cercanaIzquierda] = true;
                extremoIzq = izquierda;
                solucion += distanciaIzquierda;
                rayas.add(distanciaIzquierda);

                cercanaIzquierda = -1;
            } else {
                Punto derecha = ciudades.get(cercanaDerecha);
                rutaDerecha.add(derecha);
                visitadas[cercanaDerecha] = true;
                extremoDer = derecha;
                solucion += distanciaDerecha;
                rayas.add(distanciaDerecha);

                // Reinicia el cálculo para el siguiente más cercano a la derecha
                cercanaDerecha = -1;
            }
        }

        // Calcula la distancia final desde el último extremo al inicio
        if (rutaIzquierda.size() > rutaDerecha.size()) {
            distanciaFinal = distancia(inicio, extremoIzq);
        } else {
            distanciaFinal = distancia(inicio, extremoDer);
        }

        rayas.add(distanciaFinal);
        solucion += distanciaFinal;

        // Construye la ruta final
        ruta.addAll(rutaIzquierda);
        for (int i = rutaDerecha.size() - 1; i >= 0; i--) {
            ruta.add(rutaDerecha.get(i));
        }

        return ruta;
    }

    public static ArrayList<Punto> vorazUnidireccionalPoda(ArrayList<Punto> ciudades, int ini) {
        ArrayList<Punto> ruta = new ArrayList<>();
        setCont();
        setSolucion();
        rayas.clear();
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);

        Punto puntoIni = ciudades.get(ini);
        quicksort2(ciudades, 0, ciudades.size() - 1);

        int ini2 = ciudades.indexOf(puntoIni);

        Punto ciudadActual = ciudades.get(ini2);
        visitadas[ini2] = true;
        ruta.add(ciudadActual);
        while (ruta.size() < ciudades.size()) {
            int posicion = ciudadMasCercanaPoda(ciudadActual, ciudades, visitadas);
            // Verificar si ciudadMasCercanaPoda encontró una ciudad válida
            if (posicion == -1) {
                throw new RuntimeException("Error: No se encontró una ciudad válida para continuar la ruta.");
            }
            double dist = distancia(ciudadActual, ciudades.get(posicion));
            cont++;
            solucion += dist;
            rayas.add(dist);
            visitadas[posicion] = true;
            ciudadActual = ciudades.get(posicion);
            ruta.add(ciudadActual);
        }
        double distanciafinal = distancia(ciudadActual, ciudades.get(ini2));
        cont++;
        solucion += distanciafinal;
        rayas.add(distanciafinal);
        ruta.add(ciudades.get(ini2));
        // Cerrar el ciclo
        return ruta;
    }

    public static ArrayList<Punto> vorazBidireccionalPoda(ArrayList<Punto> ciudades, int ini) {
        ArrayList<Punto> ruta = new ArrayList<>();
        setCont();
        setSolucion();
        rayas.clear();
        double distanciaIzquierda = 0, distanciaDerecha = 0, distanciaFinal = 0;
        Punto puntoIni = ciudades.get(ini);
        quicksort2(ciudades, 0, ciudades.size() - 1);

        int ini2 = ciudades.indexOf(puntoIni);

        ArrayList<Punto> rutaIzquierda = new ArrayList<>();
        ArrayList<Punto> rutaDerecha = new ArrayList<>();

        Punto inicio = ciudades.get(ini2);
        boolean[] visitadas = new boolean[ciudades.size()];
        Arrays.fill(visitadas, false);
        visitadas[ini2] = true;

        rutaIzquierda.add(inicio);
        rutaDerecha.add(inicio);
        Punto extremoIzq = inicio;
        Punto extremoDer = inicio;

        // Variables para almacenar las ciudades más cercanas calculadas
        int cercanaIzquierda = -1;
        int cercanaDerecha = -1;

        while (rutaIzquierda.size() + rutaDerecha.size() <= ciudades.size()) {
            if (cercanaIzquierda == -1 || visitadas[cercanaIzquierda]) {
                cercanaIzquierda = ciudadMasCercanaPoda(extremoIzq, ciudades, visitadas);
                distanciaIzquierda = distancia(extremoIzq, ciudades.get(cercanaIzquierda));
                cont++;
            }

            if (cercanaDerecha == -1 || visitadas[cercanaDerecha]) {
                cercanaDerecha = ciudadMasCercanaPoda(extremoDer, ciudades, visitadas);
                distanciaDerecha = distancia(extremoDer, ciudades.get(cercanaDerecha));
                cont++;
            }

            if (distanciaIzquierda <= distanciaDerecha) {
                // Añadir al inicio
                Punto izquierda = ciudades.get(cercanaIzquierda);
                rutaIzquierda.add(izquierda);
                visitadas[cercanaIzquierda] = true;
                extremoIzq = izquierda;
                solucion += distanciaIzquierda;
                rayas.add(distanciaIzquierda);

                // Reinicia el cálculo para la siguiente iteración
                cercanaIzquierda = -1;
            } else {
                // Añadir al final
                Punto derecha = ciudades.get(cercanaDerecha);
                rutaDerecha.add(derecha);
                visitadas[cercanaDerecha] = true;
                extremoDer = derecha;
                solucion += distanciaDerecha;
                rayas.add(distanciaDerecha);

                // Reinicia el cálculo para la siguiente iteración
                cercanaDerecha = -1;
            }
        }

        // Calcula la distancia final desde el último extremo al inicio
        if (rutaIzquierda.size() > rutaDerecha.size()) {
            distanciaFinal = distancia(inicio, extremoIzq);
        } else {
            distanciaFinal = distancia(inicio, extremoDer);
        }
        cont++;
        rayas.add(distanciaFinal);
        solucion += distanciaFinal;

        // Construye la ruta final
        ruta.addAll(rutaIzquierda);
        for (int i = rutaDerecha.size() - 1; i >= 0; i--) {
            ruta.add(rutaDerecha.get(i));
        }

        return ruta;
    }

    public static void quicksort2(ArrayList<Punto> puntos, int izq, int der) {

        // tomamos primer elemento como pivote
        Punto pivote = puntos.get(izq);
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        Punto aux;

        while (i < j) {

            while ((puntos.get(i).getX() <= pivote.getX()) && (i < j)) {

                i++;// busca elemento mayor que pivote

            }

            while (puntos.get(j).getX() > pivote.getX()) {
                j--;// busca elemento menor que pivote

            }

            if (i < j) {                        // si no se han cruzado                      
                aux = puntos.get(i);                      // los intercambia
                puntos.set(i, puntos.get(j));
                puntos.set(j, aux);

            }

        }

        puntos.set(izq, puntos.get(j));      // se coloca el pivote en su lugar de forma que tendremos                                    
        puntos.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1) {
            quicksort2(puntos, izq, j - 1);
        }

        if (j + 1 < der) {
            quicksort2(puntos, j + 1, der);
        }
    }
}
