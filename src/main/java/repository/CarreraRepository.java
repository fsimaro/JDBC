package repository;

import dto.CarreraDto;
import dto.ReporteCarreraDto;
import entity.Carrera;
import util.ConnectionFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface CarreraRepository extends BaseJPARepository<Carrera, Integer> {

    /**
     * Devuelve un listado con las carreras ordenadas de forma desciendente por cantidad de inscriptos
     * @return lista de {@code CarreraDto}
     */
    List<CarreraDto> getCarrerasOrdenadasPorInscriptos();

    /**
     * Devuelve el reporte solicitado en el inciso c
     * @return lista de {@code ReporteCarreraDto}
     */
    List<ReporteCarreraDto> getReporteCarreras();

}
