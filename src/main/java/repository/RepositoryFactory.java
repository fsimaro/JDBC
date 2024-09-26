package repository;


import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {

    private static RepositoryFactory instance;
    // Mapa que asocia nombres de clases que implementan repositorios con su implementación correspondiente
    private static Map<String, Object> repositories = new HashMap<>();

    // Constructor privado para patrón Singleton
    private RepositoryFactory() {}

    public static synchronized RepositoryFactory getInstance() {
        if (instance == null) {
            instance = new RepositoryFactory();
        }
        return instance;
    }

    /** Método genérico para obtener un repositorio asociado a una unidad de persistencia determinada
     * @param repositoryClass
     * @param persistenceUnitName
     * @return Implementación del repositorio solicitado
     * @param <T>
     */
    public <T> T getRepository(Class<T> repositoryClass, String persistenceUnitName) {
        String key = repositoryClass.getSimpleName() + " " + persistenceUnitName;
        // Verificar si ya existe una instancia del repositorio
        if (!repositories.containsKey(key)) {
            try {
                // Crear nueva instancia del repositorio si no existe
                Constructor<T> constructor = repositoryClass.getDeclaredConstructor(String.class);
                repositories.put(key, constructor.newInstance(persistenceUnitName));
            } catch (Exception e) {
                throw new RuntimeException("Error instanciando repositorio: " + repositoryClass.getName(), e);
            }
        }
        // Devolver la instancia existente o recién creada
        return (T) repositories.get(key);
    }

}
