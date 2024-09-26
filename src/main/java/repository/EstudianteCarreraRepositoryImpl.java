package repository;

import dto.CarreraDto;
import dto.ReporteCarreraDto;
import entity.Estudiante;
import entity.EstudianteCarrera;
import util.ConnectionFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del Repository asociado a la entidad EstudianteCarrera
 */
public class EstudianteCarreraRepositoryImpl implements EstudianteCarreraRepository {

    private final EntityManager entityManager;

    public EstudianteCarreraRepositoryImpl(String persistenceUnitName) {
        this.entityManager = ConnectionFactory.getInstance().getEntityManager(persistenceUnitName);
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public Optional<EstudianteCarrera> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(EstudianteCarrera.class, id));
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public List<EstudianteCarrera> findAll() {
        return entityManager.createQuery("SELECT ec FROM EstudianteCarrera ec", EstudianteCarrera.class).getResultList();
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public EstudianteCarrera save(EstudianteCarrera entity) {
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
    public void delete(EstudianteCarrera entity) {
        entityManager.remove(entity);
    }

    /**
     * @see EstudianteCarreraRepository
     */
    @Override
    public Integer matricularEstudiante(Integer idEstudiante, Integer idCarrera) {
        entityManager.getTransaction().begin();
        EstudianteCarrera entityToSave = new EstudianteCarrera(idEstudiante, idCarrera);
        entityManager.persist(entityToSave);
        entityManager.getTransaction().commit();
        return entityToSave.getId();
    }

    @Override
    public boolean exists(Integer idEstudiante, Integer idCarrera) {
        String sqlQuery = "SELECT COUNT(ec) FROM EstudianteCarrera ec " +
                "WHERE ec.idCarrera = " + idCarrera + " " +
                "AND ec.idEstudiante = " + idEstudiante + " ";

        Long count = entityManager.createQuery(sqlQuery, Long.class).getSingleResult();
        return count > 0;
    }

}

