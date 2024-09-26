package service;

import entity.Estudiante;
import entity.enums.EGenero;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;
import repository.RepositoryFactory;

import java.util.List;
import java.util.Optional;

/**
 * Services creados unicamente para seguir una arquitectura por capas
 */
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(String persistenceUnitName){
        this.estudianteRepository = RepositoryFactory.getInstance().getRepository(EstudianteRepositoryImpl.class, persistenceUnitName);
    }

    /**
     * @see repository.BaseJPARepository
     */
    public Estudiante save(Estudiante entidad){
        System.out.println("Guardando el estudiante: " + entidad.toString());
        entidad = estudianteRepository.save(entidad);
        System.out.println("Estudiante guardado con id " + entidad.getId());
        return entidad;
    }

    /**
     * Imprime el resultado de llamar al método findAllByGenero de {@code EstudianteRepository}
     */
    public List<Estudiante> findAllByGenero(EGenero genero){
        System.out.println("Obteniendo estudiantes con genero: " + genero);
        List<Estudiante> estudiantes = estudianteRepository.findAllByGenero(genero);
        for(Estudiante e : estudiantes) System.out.println(e.toString());
        return estudiantes;
    }

    /**
     * Imprime el resultado de llamar al método findAllOrderBY de {@code EstudianteRepository}
     */
    public List<Estudiante> findAllOrderBy(String property){
        System.out.println("Obteniendo estudiantes ordenados por:  " + property);
        List<Estudiante> estudiantes = estudianteRepository.findAllOrderBy(property);
        for(Estudiante e : estudiantes) System.out.println(e.toString());
        return estudiantes;
    }

    /**
     * Imprime el resultado de llamar al método findByLibretaUniversitaria de {@code EstudianteRepository}
     */
    public Optional<Estudiante> findByLibretaUniversitaria(String libretaUniversitaria){
        System.out.println("Buscando estudiante con libreta universitaria: " + libretaUniversitaria);
        Optional<Estudiante> estudiante = estudianteRepository.findByLibretaUniversitaria(libretaUniversitaria);
        if (estudiante.isPresent()) {
            System.out.println(estudiante.get().toString());
        } else {
            System.out.println("No se ha encontrado un estudiante con libreta " + libretaUniversitaria);
        }
        return estudiante;
    }

    /**
     * Imprime el resultado de llamar al método findByIdCarreraAndCiudadOrigen de {@code EstudianteRepository}
     */
    public List<Estudiante> findByIdCarreraAndCiudadOrigen(Integer idCarrera, String ciudadOrigen){
        System.out.println("Obteniendo los estudiantes inscriptos en la carrera con id :" + idCarrera + " y que su ciudad de origen sea: " + ciudadOrigen);
        List<Estudiante> estudiantes = estudianteRepository.findByIdCarreraAndCiudadOrigen(idCarrera, ciudadOrigen);
        for(Estudiante e : estudiantes) System.out.println(e.toString());
        return estudiantes;
    };

}
