package main;

import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;
import repository.*;

import java.io.IOException;
import java.util.List;

/**
 * Clase auxiliar para imprimir los registros en base de datos
 */
public class ImprimirTablas {

    /**
     * Imprime el contenido de las tablas Carrera, Estudiante y EstudianteCarrera
     * @param persistenceUnitName
     * @throws IOException
     */
    public static void ejecutar(String persistenceUnitName) throws IOException {
        System.out.println(Main.SEPARATOR);
        imprimirEstudiantes(persistenceUnitName);
        System.out.println(Main.SEPARATOR);
        imprimirCarreras(persistenceUnitName);
        System.out.println(Main.SEPARATOR);
        imprimirRelaciones(persistenceUnitName);
        System.out.println(Main.SEPARATOR);
    }

    private static void imprimirEstudiantes(String persistenceUnitName) {
        System.out.println("Listado de estudiantes:");
        EstudianteRepository estudianteRepository = RepositoryFactory.getInstance().getRepository(EstudianteRepositoryImpl.class, persistenceUnitName);
        List<Estudiante> result = estudianteRepository.findAll();
        result.forEach(estudiante -> System.out.println(estudiante.toString()));
    }

    private static void imprimirCarreras(String persistenceUnitName) throws IOException {
        System.out.println("Listado de carreras");
        CarreraRepository carreraRepository = RepositoryFactory.getInstance().getRepository(CarreraRepositoryImpl.class, persistenceUnitName);
        List<Carrera> result = carreraRepository.findAll();
        result.forEach(carrera -> System.out.println(carrera.toString()));
    }

    private static void imprimirRelaciones(String persistenceUnitName) throws IOException {
        System.out.println("Listado de relaciones");
        EstudianteCarreraRepository estudianteCarreraRepository = RepositoryFactory.getInstance().getRepository(EstudianteCarreraRepositoryImpl.class, persistenceUnitName);
        List<EstudianteCarrera> result = estudianteCarreraRepository.findAll();
        result.forEach(estudianteCarrera -> System.out.println(estudianteCarrera.toString()));
    }

}
