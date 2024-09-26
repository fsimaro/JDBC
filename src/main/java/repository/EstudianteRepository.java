package repository;

import entity.Estudiante;
import entity.EstudianteCarrera;
import entity.enums.EGenero;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends BaseJPARepository<Estudiante, Integer> {

    /**
     * Dado un numero de libreta universitaria devuelve un opcional con el registro del estudiante si existe,
     * o vacío en caso contrario
     * @param libretaUniversitaria
     * @return Un {@code Optional} con la entidad encontrada o {@code Optional.empty()} si no existe.
     */
    Optional<Estudiante> findByLibretaUniversitaria(String libretaUniversitaria);

    /**
     * Devuelve los estudiantes de un genero
     * @param genero
     * @return Listado de {@code Estudiante}
     */
    List<Estudiante> findAllByGenero(EGenero genero);

    /**
     * Devuelve un listado de estudiantes ordenado por un criterio determinado
     * @param property
     * @return Listado de {@code Estudiante}
     */
    List<Estudiante> findAllOrderBy(String property);

    /**
     * Devuelve un listado de estudiantes inscriptos en determinada carrera y de una ciudad de origen determinada
     * @param idCarrera
     * @param ciudadOrigen
     * @return Listado de {@code Estudiante}
     */
    List<Estudiante> findByIdCarreraAndCiudadOrigen(Integer idCarrera, String ciudadOrigen);
}
