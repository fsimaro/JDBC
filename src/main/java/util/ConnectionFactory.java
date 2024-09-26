package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory de conexiones o EntityManagers
 */
public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();
    // Mapa que asocia nombres de unidades de persistencia con sus EntityManagerFactory correspondientes
    private static Map<String, EntityManagerFactory> entityManagerFactories = new HashMap<>();

    // Constructor privado para patrón Singleton
    private ConnectionFactory() {
    }

    public static synchronized ConnectionFactory getInstance() {
        return instance;
    }

    /**
     * Devuelve un EntityManager de la unidad de persistencia indicada
     * @param persistenceUnitName
     * @return Un {@code EntityManager} del tipo solicitado
     */
        public EntityManager getEntityManager(String persistenceUnitName) {
        if (!entityManagerFactories.containsKey(persistenceUnitName)) {
            entityManagerFactories.put(persistenceUnitName, Persistence.createEntityManagerFactory(persistenceUnitName));
        }
        return entityManagerFactories.get(persistenceUnitName).createEntityManager();
    }

    /**
     * Cierra la instancia de EntityManagerFactory correspondiente a la unidad indicada
     * @param unitName
     */
    public void closeEntityManagerFactory(String unitName) {
        EntityManagerFactory emf = entityManagerFactories.get(unitName);
        if (emf != null && emf.isOpen()) {
            emf.close();
            entityManagerFactories.remove(unitName);
        }
    }

    /**
     * Cierra todas las instancias de EntityManagerFactory
     */
    public void closeAll() {
        for (EntityManagerFactory emf : entityManagerFactories.values()) {
            if (emf.isOpen()) {
                emf.close();
            }
        }
    }

}
