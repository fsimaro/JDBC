package util;

import entity.Estudiante;
import entity.enums.EGenero;
import main.Main;
import service.CarreraService;
import service.EstudianteCarreraService;
import service.EstudianteService;

import java.time.LocalDate;

public class CasosDePrueba {


    private final EstudianteService estudianteService;
    private final CarreraService carreraService;
    private final EstudianteCarreraService estudianteCarreraService;

    public CasosDePrueba(String persistenceUnitName){
        this.estudianteService = new EstudianteService(persistenceUnitName);
        this.carreraService = new CarreraService(persistenceUnitName);
        this.estudianteCarreraService = new EstudianteCarreraService(persistenceUnitName);
    }

    /**
     * Casos de prueba solicitados
     */
    public void ejecutar(){
        //Dar de alta un estudiante
        Integer id = estudianteService.findByLibretaUniversitaria("248360").map(Estudiante::getId).orElse(null);
        if (id == null)
            id = estudianteService.save(new Estudiante(null, "Federico", "Simaro", LocalDate.of(1994, 1, 28), EGenero.M.name(), "248360", "38129576", "Tandil")).getId();
        //Matricular un estudiante en una carrera
        estudianteCarreraService.matricularEstudiante(id, 1);
        //recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
        estudianteService.findAllOrderBy("numeroDocumento ASC");
        System.out.println(Main.SEPARATOR);
        //recuperar un estudiante, en base a su número de libreta universitaria.
        estudianteService.findByLibretaUniversitaria("248360");
        System.out.println(Main.SEPARATOR);
        //recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
        carreraService.getCarrerasOrdenadasPorInscriptos();
        System.out.println(Main.SEPARATOR);
        //recuperar todos los estudiantes, en base a su género.
        estudianteService.findAllByGenero(EGenero.F);
        System.out.println(Main.SEPARATOR);
        //recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
        estudianteService.findByIdCarreraAndCiudadOrigen(1, "Tandil");
        System.out.println(Main.SEPARATOR);
        //Generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica.
        carreraService.getReporteCarreras();
        System.out.println(Main.SEPARATOR);
    }


}
