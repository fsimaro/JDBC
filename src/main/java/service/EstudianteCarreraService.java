package service;

import repository.EstudianteCarreraRepository;
import repository.EstudianteCarreraRepositoryImpl;
import repository.RepositoryFactory;

/**
 * Services creados unicamente para seguir una arquitectura por capas
 */
public class EstudianteCarreraService {

    private final EstudianteCarreraRepository estudianteCarreraRepository;

    public EstudianteCarreraService(String persistenceUnitName){
        this.estudianteCarreraRepository = RepositoryFactory.getInstance().getRepository(EstudianteCarreraRepositoryImpl.class, persistenceUnitName);
    }

    /**
     *
     * @param idEstudiante
     * @param idCarrera
     */
    public void matricularEstudiante(Integer idEstudiante, Integer idCarrera){
        System.out.printf("Matriculando estudiante con id %d en carrera con id %d%n", idEstudiante, idCarrera);
        if (!estudianteCarreraRepository.exists(idEstudiante, idCarrera))
            estudianteCarreraRepository.matricularEstudiante(idEstudiante, idCarrera);
        System.out.println("Matriculación existosa");
    }

}
