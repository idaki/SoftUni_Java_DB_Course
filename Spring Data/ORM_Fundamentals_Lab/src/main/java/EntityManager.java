import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;

public class EntityManager implements DbContext {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Retention(RetentionPolicy.RUNTIME)
    public interface id {

    }


    @Override
    public boolean persit(Object entity) throws IllegalAccessException {

        Field primaryKey = getId(entity.getClass());
        primaryKey.setAccessible(true);
        Object value = primaryKey.get(entity);
        if (value == null || (long) value < 0) {
            return doInsert = (entity,primaryKey);
        }

        return doUpdate(enity, primaryKey);
    }

    private Field getId(Class<?> entity) {
        Arrays.stream(entity.getDeclaredFields()).
                filter(x -> x.equals(x.isAnnotationPresent((Class<? extends Annotation>) id.class)))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Enity does not have primary key"));
    }


    @Override
    public Iterable find(Class table) {
        return null;
    }

    @Override
    public Iterable find(Class table, String where) {
        return null;
    }

    @Override
    public Object findFirst(Class table) {
        return null;
    }

    @Override
    public Object findFirst(Class table, String where) {
        return null;
    }


}
