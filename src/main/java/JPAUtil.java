import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Crea un EntityManagerFactory con patrón Singleton y Thread-Safe. La clase debe tener un método estático que devuelva una instancia de EntityManagerFactory. La clase debe tener un método para cerrar la factoría.
 * Además, debe tener un método que devuelva un EntityManager recogiendo el nombre de la unidad de persistencia.
 *
 * Mejora: el EntityManager debe ser creado con el método createEntityManager() de la factoría y debe ser único para cada unidad de persistencia.
 */
public class JPAUtil {

    public static final String LEXISLACIONMARIADB = "lexislacionmariadb";
    public static final String VIDEOCLUBPOSTGRESQL = "VideoClub";

    private static final Map<String, EntityManagerFactory> emFactories = new HashMap<>() ;

    private JPAUtil() {
    }

    private static boolean isEntityManagerFactoryClosed(String unidadPersistencia) {
        return !emFactories.containsKey(unidadPersistencia) || emFactories.get(unidadPersistencia)== null ||
                !emFactories.get(unidadPersistencia).isOpen();
    }

    public static EntityManagerFactory getEmFactory(String unidadPersistencia) {
        if (isEntityManagerFactoryClosed(unidadPersistencia)) {
            synchronized (JPAUtil.class) {
                if (isEntityManagerFactoryClosed(unidadPersistencia)) {
                    try {
                        emFactories.put(unidadPersistencia, Persistence.createEntityManagerFactory(unidadPersistencia));
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("e.getMessage() = " + e.getMessage());
                    }
                }
            }
        }
        return emFactories.get(unidadPersistencia);
    }

    public static EntityManager getEntityManager(String persistenceUnitName) {
        return getEmFactory(persistenceUnitName).createEntityManager();

    }


    public static void close(String persistenceUnitName) {
        if (emFactories.containsKey(persistenceUnitName)) {
            emFactories.get(persistenceUnitName).close();
        }
    }
}
