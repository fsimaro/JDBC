package main;

import repository.enums.DBType;
import util.CasosDePrueba;

import java.io.IOException;

/**
 * @author Federico Simaro Bonastia
 */
public class Main {

    public static final String SEPARATOR = "\n\n";

    /**
     * Método main, se ejecuta la carga de tablas, la impresión luego de la carga
     * y los casos de prueba solicitados
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Casos de prueba para DERBY
        System.out.println("Trabajo Práctico N°2: Persistencia");
        CargarTablas.ejecutar(DBType.DERBY.toString());
        ImprimirTablas.ejecutar(DBType.DERBY.toString());
        CasosDePrueba casosDePrueba = new CasosDePrueba(DBType.DERBY.toString());
        casosDePrueba.ejecutar();
    }

}