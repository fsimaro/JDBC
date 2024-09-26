package repository;

import entity.Estudiante;
import entity.EstudianteCarrera;
import entity.enums.EGenero;
import util.ConnectionFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Implementación del Repository asociado a la entidad Estudiante
 */
public class EstudianteRepositoryImpl implements EstudianteRepository {

    private final EntityManager entityManager;

    public EstudianteRepositoryImpl(String persistenceUnitName){
        this.entityManager = ConnectionFactory.getInstance().getEntityManager(persistenceUnitName);
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public Optional<Estudiante> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Estudiante.class, id));
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public List<Estudiante> findAll() {
        return entityManager.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
    }

    /**
     * @see BaseJPARepository
     */
    @Override
    public Estudiante save(Estudiante entity) {
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
    public void delete(Estudiante entity) {
        entityManager.remove(entity);
    }

    /**
     * @see EstudianteRepository
     */
    @Override
    public Optional<Estudiante> findByLibretaUniversitaria(String libretaUniversitaria){
        String sqlQuery = "SELECT e FROM Estudiante e WHERE e.libretaUniversitaria = \'" + libretaUniversitaria + '\'';
        return Optional.ofNullable(entityManager.createQuery(sqlQuery, Estudiante.class).getSingleResult());
    }

    /**
    * @see EstudianteRepository
     */
    @Override
    public List<Estudiante> findAllByGenero(EGenero genero){
        String sqlQuery = "SELECT e FROM Estudiante e WHERE e.genero = '" + genero.toString() + "'";
        return entityManager.createQuery(sqlQuery, Estudiante.class).getResultList();
    }

    /**
     * @see EstudianteRepository
     */
    @Override
    public List<Estudiante> findAllOrderBy(String property){
        String sqlQuery = "SELECT e FROM Estudiante e ORDER BY " + property;
        return entityManager.createQuery(sqlQuery, Estudiante.class).getResultList();
    }

    /**
     * @see EstudianteRepository
     */
    @Override
    public List<Estudiante> findByIdCarreraAndCiudadOrigen(Integer idCarrera, String ciudadOrigen) {

        String sqlQuery = "SELECT DISTINCT e FROM EstudianteCarrera ec " +
                "JOIN Estudiante e ON (e.id = ec.idEstudiante) " +
                "WHERE ec.idCarrera = " + idCarrera + " " +
                "AND e.ciudadOrigen = '" + ciudadOrigen + "'";

        return entityManager.createQuery(sqlQuery, Estudiante.class).getResultList();
    }

}
