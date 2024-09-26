package repository;

import dto.CarreraDto;
import dto.ReporteCarreraDto;
import entity.Carrera;
import entity.EstudianteCarrera;
import util.ConnectionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del Repository asociado a la entidad Carrera
 */
public class CarreraRepositoryImpl implements CarreraRepository {

    private final EntityManager entityManager;

    public CarreraRepositoryImpl(String persistenceUnitName){
        this.entityManager = ConnectionFactory.getInstance().getEntityManager(persistenceUnitName);
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public Optional<Carrera> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Carrera.class, id));
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public List<Carrera> findAll() {
        return entityManager.createQuery("SELECT c FROM Carrera c", Carrera.class).getResultList();
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public Carrera save(Carrera entity) {
        entityManager.getTransaction().begin();
        if (entity.getId() == null)
            entityManager.persist(entity);
        else
            entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public void delete(Carrera entity) {
        entityManager.remove(entity);
    }

    /**
     * @see CarreraRepository
     */
    @Override
    public List<CarreraDto> getCarrerasOrdenadasPorInscriptos() {
        String sqlQuery = "SELECT c.nombre, SUM(CASE WHEN ec.graduado = false THEN 1 ELSE 0 END) as inscriptos " +
                "FROM Carrera c " +
                "JOIN EstudianteCarrera ec ON (ec.idCarrera = c.id)" +
                "GROUP BY c.nombre " +
                "ORDER BY inscriptos DESC";

        List<Object[]> allRecords = entityManager.createQuery(sqlQuery, Object[].class).getResultList();

        return allRecords
                .stream()
                .map(record -> new CarreraDto((String) record[0], (Integer) record[1]))
                .collect(Collectors.toList());
    }

    /**
     * @see CarreraRepository
     */
    @Override
    public List<ReporteCarreraDto> getReporteCarreras() {
        String sqlQuery = "SELECT c.nombre, ec.anioInscripcion, COUNT(ec.idEstudiante) as inscriptos, SUM(CASE WHEN ec.graduado = FALSE THEN 0 ELSE 1 END) as graduados " +
                "FROM Carrera c " +
                "JOIN EstudianteCarrera ec ON (ec.idCarrera = c.id) " +
                "GROUP BY c.nombre, ec.anioInscripcion " +
                "ORDER BY c.nombre ASC, ec.anioInscripcion ASC";

        List<Object[]> allRecords = entityManager.createQuery(sqlQuery, Object[].class).getResultList();

        List<ReporteCarreraDto> result = new ArrayList<>();

        allRecords.forEach(record -> {
            ReporteCarreraDto reporteCarreraDto = new ReporteCarreraDto((String) record[0]);
            if (!result.contains(reporteCarreraDto)){
                result.add(reporteCarreraDto);
            }
            result.get(result.indexOf(reporteCarreraDto)).getDetalleAnual().add(new ReporteCarreraDto.DetalleCarreraAnual((Integer) record[1], (Long) record[2], (Integer) record[3]));
        });
        return result;
    }

}
