package repository;

import entity.EstudianteCarrera;


public interface EstudianteCarreraRepository extends BaseJPARepository<EstudianteCarrera, Integer> {

    /**
     * Dado un id de estudiante y un id de carrera crea un registro en la tabla estudiante_carrera
     * El id de estudiante y el id de carrera deben encontrarse en la tabla estudiante y carrera, respectivamente
     *
     * @param idEstudiante
     * @param idCarrera
     * @return El id del registro creado
     */
    Integer matricularEstudiante(Integer idEstudiante, Integer idCarrera);

    boolean exists(Integer idEstudiante, Integer idCarrera);
}
