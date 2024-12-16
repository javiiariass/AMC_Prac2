
import com.amc_prac2.GestorTSP.EscritorTSP;
import com.amc_prac2.GestorTSP.LectorTSP;
import com.amc_prac2.Punto.Punto;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
            System.out.println("1. Generar Array aleatorio");
            System.out.println("2. Leer DataSet de memoria");
            System.out.println("3. Comprobar estrategias con dataset cargado");
            System.out.println("4. Comparar todas las estrategias");
            System.out.println("5. Estudiar dos estrategias");
            System.out.println("6. ");
            System.out.println("7. Mostrar Array cargado");
            System.out.println("8. Activar/Desactivar peor caso (Todos los puntos en la misma coordenada X)");
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
                        System.out.print("Introduce el nombre del archivo a cargar (por ejemplo, berlin52): ");
                        String nombreArchivo = entrada.nextLine();
                        Punto.vaciaPuntos(puntos);
                        puntos = LectorTSP.lecturaArchivo(nombreArchivo);
                        nombreArchivoLeido = nombreArchivo;
                    }
                    case 3 -> {
                        //Muestra por pantalla el resultado de aplicar las 4 estrategias 
                        //al dataset cargado en memoria

                    }
                    case 4 -> {
                        boolean[] visitado = new boolean[3];
                        visitado[2] = true;
                        System.out.println(visitado[0]!=true);
                        Random rand = new Random();
                        int inicial = rand.nextInt(40); 
                        for (int i = 0; i < 40 ; i++){
                            System.out.println(inicial);
                            inicial=(inicial+1)%40;
                            
                        }
                    }
                    case 5 -> {

                    }
                    case 6 -> {

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
