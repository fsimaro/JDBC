package main;

import entity.Carrera;
import entity.Estudiante;
import entity.EstudianteCarrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import repository.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

/**
 * Clase auxiliar para precargar las tablas con datos desde archivos csv
 */
public class CargarTablas {


    /**
     * Dada una unidad de persistencia inserta registros leídos desde archivos csv en las tablas
     * Carrera, Estudiante y EstudianteCarrera, si estas están vacías.
     * @param persistenceUnitName
     * @throws IOException
     */
    public static void ejecutar(String persistenceUnitName) throws IOException{
        System.out.println("Ejecutando cargado de tablas");
        System.out.println(Main.SEPARATOR);
        try {
            cargarEstudiantes(persistenceUnitName);
            cargarCarreras(persistenceUnitName);
            cargarRelaciones(persistenceUnitName);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Cargado de tablas finalizado");
        System.out.println(Main.SEPARATOR);
    }

    private static void cargarEstudiantes(String persistenceUnitName) throws IOException {
        EstudianteRepository estudianteRepository = RepositoryFactory.getInstance().getRepository(EstudianteRepositoryImpl.class, persistenceUnitName);
        if (!estudianteRepository.findAll().isEmpty()) return;
        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setHeader()  // Automáticamente utiliza la primera fila como encabezado
                .setSkipHeaderRecord(true)  // Omitir la primera fila del CSV en el resultado
                .build()
                .parse(new FileReader("src/main/resources/csv-files/estudiantes.csv"));
        for (CSVRecord row: parser){
            Estudiante entity =
                    new Estudiante(null, row.get("nombre"), row.get("apellido"),
                            Date.valueOf(row.get("fecha_nacimiento")).toLocalDate(), row.get("genero"), row.get("libreta_universitaria"),
                            row.get("numero_documento"), row.get("ciudad_origen"));
            estudianteRepository.save(entity);
        }
    }

    private static void cargarCarreras(String persistenceUnitName) throws IOException {
        CarreraRepository carreraRepository = RepositoryFactory.getInstance().getRepository(CarreraRepositoryImpl.class, persistenceUnitName);
        if (!carreraRepository.findAll().isEmpty()) return;
        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setHeader()  // Automáticamente utiliza la primera fila como encabezado
                .setSkipHeaderRecord(true)  // Omitir la primera fila del CSV en el resultado
                .build()
                .parse(new FileReader("src/main/resources/csv-files/carreras.csv"));
        for (CSVRecord row: parser){
            Carrera entity = new Carrera(null, row.get("nombre"));
            carreraRepository.save(entity);
        }
    }

    private static void cargarRelaciones(String persistenceUnitName) throws IOException {
        EstudianteCarreraRepository estudianteCarreraRepository = RepositoryFactory.getInstance().getRepository(EstudianteCarreraRepositoryImpl.class, persistenceUnitName);
        if (!estudianteCarreraRepository.findAll().isEmpty()) return;
        CSVParser parser = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(new FileReader("src/main/resources/csv-files/relaciones.csv"));
        for (CSVRecord row: parser){
            EstudianteCarrera entity = new EstudianteCarrera(null,
                    Integer.valueOf(row.get("id_estudiante")),
                    Integer.valueOf(row.get("id_carrera")),
                    Integer.valueOf(row.get("anio_inscripcion")),
                    Boolean.parseBoolean(row.get("graduado")));
            estudianteCarreraRepository.save(entity);
        }
    }

}
