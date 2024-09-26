package repository;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz parametrizable con los métodos comunes a todos los repositorios.
 * Proporciona las operaciones básicas de CRUD (crear, leer, actualizar y eliminar)
 * para cualquier entidad gestionada por un repositorio.
 *
 * @param <T> Clase relativa a la entidad a representar.
 * @param <I> Tipo del identificador de la entidad.
 */
public interface BaseJPARepository<T, I> {

    /**
     * Devuelve un opcional con la entidad asociada al id si esta existe, o vacío en caso contrario.
     *
     * @param id Identificador de la entidad que se desea buscar.
     * @return Un {@code Optional} con la entidad encontrada o {@code Optional.empty()} si no existe.
     */
    Optional<T> findById(I id);

    /**
     * Devuelve una lista con todas las entidades de este repositorio.
     *
     * @return Lista de todas las entidades gestionadas por el repositorio.
     */
    List<T> findAll();

    /**
     * Guarda o actualiza una entidad en el repositorio.
     * Si la entidad ya existe, se actualiza; si no, se inserta una nueva.
     *
     * @param entity La entidad que se va a guardar o actualizar.
     * @return La entidad guardada o actualizada.
     */
    T save(T entity);

    /**
     * Elimina la entidad especificada del repositorio.
     *
     * @param entity La entidad que se desea eliminar.
     */
    void delete(T entity);
}
