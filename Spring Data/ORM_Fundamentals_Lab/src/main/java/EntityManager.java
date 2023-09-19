import annotations.Column;
import annotations.Entity;
import annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;


public class EntityManager implements DbContext {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persit(Object entity) throws IllegalAccessException, SQLException {
        Field idField = getIdField(entity.getClass());
        idField.setAccessible(true);
        Object idValue = idField.get(entity);
        if (idValue == null || (long) idValue == 0) {
            return insertId(entity);
        }

        return ++++++++updateEntity(entity);
        ;
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

    private Field getIdField(Class<?> entityClass) {
        return Arrays.stream(entityClass.getDeclaredFields()).
                filter(x -> x.isAnnotationPresent(Id.class)).
                findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));
    }

    private boolean insertId(Object entity) throws SQLException {
        String INSERT_QUERY = "INSERT INTO %s(%s) VALUES(%s);";
        String tableName = getTableName(entity.getClass());
        String fieldNamesWithoutID = getFieldNamesWithoutID(entity.getClass());
        String fieldValuesWithoutID = getFieldValuesWithoutID(entity);
        String query = String.format(INSERT_QUERY, tableName, fieldNamesWithoutID, fieldValuesWithoutID);

        PreparedStatement statement = this.connection.prepareStatement(query);
        return statement.executeUpdate() == 1;
    }




    private String getTableName(Class<?> entityClass) {
        Entity annotation = entityClass.getAnnotation(Entity.class);
        if (annotation == null) {
            throw new UnsupportedOperationException("Entity must have Entity annotation");
        }
        return annotation.name();
    }

    private String getFieldNamesWithoutID(Class<?> entityClass) {
        Field idField = getIdField(entityClass);
        return Arrays.stream(entityClass.getDeclaredFields()).
                filter(f->!f.getName().equals(idField.getName())).
                filter(f->f.isAnnotationPresent(Column.class)).
                map(f->f.getAnnotation(Column.class).name()).
                filter(name -> !name.equals(idField.getName())).
                collect(Collectors.joining(","));
    }

    private String getFieldValuesWithoutID(Object entity) {
       Class<?> entityClass= entity.getClass();
        Field  idField = getIdField(entityClass);

        return Arrays.stream(entityClass.getDeclaredFields()).
                filter(f->!f.getName().equals(idField.getName())).
                filter(f->f.isAnnotationPresent(Column.class)).
                map(f->{
                    f.setAccessible(true);
                    try{
                       Object value = f.get(entity);
                     return String.format("''%s",value.toString());
                    }catch (IllegalAccessException e){
                        throw new RuntimeException(e);
                    }
                }).
                collect(Collectors.joining(","));
    }

}
