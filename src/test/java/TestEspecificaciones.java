
import com.amc_prac2.Algoritmos.Ruta;
import com.amc_prac2.Algoritmos.Voraz;
import com.amc_prac2.GestorTSP.EscritorTSP;
import com.amc_prac2.GestorTSP.LectorTSP;
import com.amc_prac2.Punto.Punto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import org.math.plot.*;
import org.math.plot.plotObjects.BaseLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author javi
 */
public class TestEspecificaciones {

    /**
     * Main para probar las especificaciones pedidas en el enunciado
     *
     * @param args
     */
    public static void main(String[] args) {

        int opcionMenu;

        boolean peorCaso = false;
        boolean salir = false;
        ArrayList<Punto> puntos = new ArrayList<>();
        String nombreArchivoLeido = null;

        do {
            System.out.println("""
                               
                               
                               -------------------------------------------------------
                                                                        Peor Caso:""" + (peorCaso == true ? " ON" : " OFF"));
            System.out.println(nombreArchivoLeido != null ? ("                                         Archivo leido: " + nombreArchivoLeido) : " ");
            System.out.println("1. Generar Array aleatorio");
            System.out.println("2. Leer DataSet de memoria");
            System.out.println("3. NO IMPLEMENTADO AQUI Comprobar estrategias con dataset cargado");
            System.out.println("4. Comparar todas las estrategias (con distintas tallas)");
            System.out.println("5. NO IMPLEMENTADO AQUI Estudiar dos estrategias");
            System.out.println("6. Vaciar Array cargado (no elimina el archivo)");
            System.out.println("7. Mostrar Array cargado");
            System.out.println("8. Activar/Desactivar peor caso (Todos los puntos en la misma coordenada X)");
            System.out.println("9. Elegir estrategia con dataset cargado");
            System.out.println("10. Calculadora de distancias");
            System.out.println("11. Prueba plot");
            System.out.println("0. Salir");

            Scanner entrada = new Scanner(System.in);
            opcionMenu = entrada.nextInt();
            try {
                switch (opcionMenu) {

                    case 1 -> {

                        //Antes de empezar a añadir elementos a la lista, la vacíamos
                        Punto.vaciaPuntos(puntos);
                        System.out.print("Introduce la talla del archivo a generar: ");
                        int talla = entrada.nextInt();
                        System.out.println("Creando nuevo dataset de puntos...");
                        Punto.rellenarPuntos(puntos, talla, peorCaso, 10);
                        System.out.println("Dataset cargado en memoria.");
                        System.out.println("Guardando dataset en disco...");
                        nombreArchivoLeido = "dataset" + puntos.size();
                        EscritorTSP.guardarEnArchivo(puntos, nombreArchivoLeido);

                    }
                    case 2 -> {
                        entrada.nextLine(); // Limpiamos el buffer antes de leer el nombre del archivo
                        System.out.print("Introduce el nombre del archivo a cargar (por ejemplo, berlin52, sin extension de archivo): ");
                        String nombreArchivo = entrada.nextLine();
                        Punto.vaciaPuntos(puntos);
                        puntos = LectorTSP.selectorArchivo(nombreArchivo);
                        nombreArchivoLeido = nombreArchivo;
                    }
                    case 3 -> {
                        //Muestra por pantalla el resultado de aplicar las 4 estrategias 
                        //al dataset cargado en memoria
                      

                    }
                    case 4 -> {
                        int inicial = Punto.puntoInicial(puntos);
                        System.out.println("Inicial: " + inicial);
                        Ruta rutaUni = Voraz.UnidireccionalExhaustiva(puntos, inicial);
                        Ruta rutaUniPoda = Voraz.UnidireccionalExhaustivaPoda(new ArrayList<Punto>(puntos), inicial);
                        Ruta rutaBi = Voraz.BidireccionalExhaustiva(puntos, inicial);
                        Ruta rutaBiPoda = Voraz.BidireccionalExhaustivaPoda(new ArrayList<Punto>(puntos) , inicial);
                        System.out.println("Unidireccional sin poda"
                                + "\n-------------------------");
                        //System.out.println(rutaUni.getRuta());
                        System.out.println("Distancia: " + rutaUni.getDistanciaTotal());
                        System.out.println("Coste: " + rutaUni.getCosteTotal());
                        System.out.println("Bidireccional sin poda"
                                + "\n-------------------------");
                        System.out.println("Distancia: " + rutaBi.getDistanciaTotal());
                        System.out.println("Coste: " + rutaBi.getCosteTotal());
                        // System.out.println(rutaUniPoda.getRuta());
                        System.out.println("Unidireccional con poda"
                                + "\n-------------------------");
                        System.out.println("Distancia: " + rutaUniPoda.getDistanciaTotal());
                        System.out.println("Coste: " + rutaUniPoda.getCosteTotal());
                       // System.out.println(rutaBi.getRuta());
                        System.out.println("Bidireccional con poda"
                                + "\n-------------------------");
                        System.out.println("Distancia: " + rutaBiPoda.getDistanciaTotal());
                        System.out.println("Coste: " + rutaBiPoda.getCosteTotal());
                        //System.out.println(rutaBiPoda.getRuta());
                    }
                    case 5 -> {

                    }
                    case 6 -> {
                        Punto.vaciaPuntos(puntos);
                        nombreArchivoLeido = null;
                    }
                    case 7 -> {
                        for (Punto i : puntos) {
                            System.out.println("Punto " + i);
                            //System.out.println("Punto " + i.getId() + ": " + i.getX() + ", " + i.getY());
                        }
                    }
                    case 8 -> {
                        peorCaso = (peorCaso != true);
                    }
                    case 9 -> {

                        // No hay puntos cargados
                        if (puntos.size() == 0) {
                            System.out.println("Primero elija o cree un dataset");
                            sleep(2000);
                            break;
                        }

                        Ruta ruta = null;
                       
                        System.out.println("1. Voraz unidireccional sin poda");
                        System.out.println("2. Voraz bidireccional sin poda");
                        System.out.println("3. Voraz unidireccional con poda");
                        System.out.println("4. Voraz bidireccional con poda");

                        int opcion = entrada.nextInt();
                        Random rnd = new Random(System.currentTimeMillis());
                        //int inicial = rnd.nextInt(puntos.size());
                        int inicial = 9;
                        switch (opcion) {
                            case 1 -> {
                                ruta = Voraz.UnidireccionalExhaustiva(puntos, inicial);
                                
                            }
                            case 2 -> {
                                ruta = Voraz.BidireccionalExhaustiva(puntos, inicial);
                                
                            }
                            case 3 -> {
                                ruta = Voraz.UnidireccionalExhaustivaPoda(puntos, inicial);
                                
                            }
                            case 4 -> {
                                ruta = Voraz.BidireccionalExhaustivaPoda(puntos, inicial);
                                
                            }

                            default -> {
                                System.out.println("opcion incorrecta");
                                sleep(2000);
                            }
                        }

                        if (ruta == null) {
                            break;
                        }

                        System.out.println("solucion"
                                + "\n-------------------------");
                        System.out.println(ruta);
                        
                        //coordenadas[0] -> array de coord eje X
                        //coordenadas[1] -> array de coord eje Y
                        double[][] coordenadas = new double[2][];

                        try {
                            ArrayList<Punto> aux = ruta.getRuta();
                            Punto.separarCordenadas(ruta.getRuta(), coordenadas);
                            Plot2DPanel ploteando = new Plot2DPanel();
                            ploteando.addScatterPlot("pruebaPlot", Color.BLUE, coordenadas[0], coordenadas[1]);
                            ploteando.addLinePlot("Unidireccional", Color.RED, coordenadas[0], coordenadas[1]);

                            Punto punAux;
                            // Añadir las etiquetas manualmente
                            BaseLabel et = new BaseLabel("hola", Color.GREEN, 1.0);
                            et.setCoord(100, 100);
                            ploteando.addPlotable(et);
                            for (int i = 0; i < ruta.getRuta().size(); i++) {
                                punAux = ruta.getRuta().get(i);

                                BaseLabel etiqueta = new BaseLabel(String.valueOf(punAux.getId()), Color.BLUE, 0.5, 0.5);
                                etiqueta.setFont(new Font("Arial", Font.BOLD, 12));
                                etiqueta.setCoord(punAux.getX(), punAux.getY());
                                ploteando.addPlotable(etiqueta);
                            }

                            JFrame frame = new JFrame("panel pal plot");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setContentPane(ploteando);
                            frame.setSize(new Dimension(800, 800));
                            frame.setVisible(true);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    }
                    case 10 -> {
                        do {
                            // Como las id empiezan por 1 y no por 0 -> el punto con id 1 será el que tenga indice 0
                            System.out.println("*********** -99 para salir de calculadora ***********");
                            System.out.println("punto 1");
                            int opcion = entrada.nextInt();
                            Punto p1 = puntos.get(opcion-1);
                            System.out.println("punto 2");
                            opcion = entrada.nextInt();
                            Punto p2 = puntos.get(opcion-1);
                            System.out.println("Distancia:   " + p1.distancia(p2));
                        } while (entrada.nextInt() != -99);

                    }
                    case 11 -> {
                        double[] x = {12.3, 1, 3, 4};
                        double[] y = {34.3, 30, 50, 40};
                        Plot2DPanel ploteando = new Plot2DPanel();
                        ploteando.addScatterPlot("pruebaPlot", Color.red, x, y);

                        JFrame frame = new JFrame("panel pal plot");
                        frame.setContentPane(ploteando);
                        frame.setVisible(true);

                    }
                    case 0 -> {
                        salir = true;
                    }
                    default -> {
                        System.out.println("Elección no válida");
                        sleep(1000);
                    }
                }

            } catch (Exception e) {
                System.err.println("Error " + e.getMessage());
            }
            

        } while (!salir);
    }

}
