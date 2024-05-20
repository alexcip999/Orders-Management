package dataAccess;

import conection.ConnectionFactory;
import start.Reflection;

import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbstractDAO<T> {
    protected Class<T> entityClass;
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type) {
        this.type = type;
    }
    private String createSelectQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " =?");
        return stringBuilder.toString();
    }
    private String createInsertQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(field);
        stringBuilder.append(" (");

        Field[] fields = type.getDeclaredFields();
        for(int i = 1; i < fields.length; i++){
            stringBuilder.append(fields[i].getName());
            if(i < fields.length - 1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(") VALUES (");
        for(int i = 0; i < fields.length - 1; i++){
            stringBuilder.append("?");
            if(i < fields.length - 2){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }
    private String createEditQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE " + field + " SET ");
        Field[] fields = type.getDeclaredFields();
        for(int i = 1; i < fields.length; i++){
            if(i < fields.length - 1){
                stringBuilder.append(fields[i].getName() + " = ?, ");
            }else{
                stringBuilder.append(fields[i].getName() + " = ? ");
            }

        }
        stringBuilder.append("WHERE " + fields[0].getName() + " = ?");
        return stringBuilder.toString();
    }
    private String createDeleteQuery(String field){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM " + field + " WHERE id = ?");
        return stringBuilder.toString();
    }

    private String createDisplayQuery(String field){
        return "SELECT * FROM " + field;
    }

    public  T findById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            //return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T insert(T t, String field){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(field);
        System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            List<Object> objects = Reflection.retrieveProperties(t);
            for(int i = 1; i < objects.size(); i++){
                statement.setObject(i, objects.get(i));
            }
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T edit(T t, String field){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createEditQuery(field);
        System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            List<Object> objects = Reflection.retrieveProperties(t);
            for(int i = 1; i < objects.size(); i++){
                statement.setObject(i, objects.get(i));
            }
            statement.setObject(objects.size(), objects.get(0));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:edit " + e.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    public T delete(int id, String field){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(field);
        System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setObject(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public DefaultTableModel display(String field){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        DefaultTableModel tableModel = null;
        String query = createDisplayQuery(field);
        try{
            tableModel = new DefaultTableModel();
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            for(int i = 1; i <= columnCount; i++){
                tableModel.addColumn(rs.getMetaData().getColumnName(i));
            }
            while(rs.next()){
                Object[] row = new Object[columnCount];
                for(int i = 1; i <= columnCount; i++){
                    row[i - 1] = rs.getObject(i);
                }
                tableModel.addRow(row);
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return tableModel;
    }

    private List<T> createObjects(ResultSet resultSet){
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;
        for(int i = 0; i < constructors.length; i++){
            constructor = constructors[i];
            if(constructor.getGenericParameterTypes().length == 0){
                break;
            }
        }
        try{
            while(resultSet.next()){
                constructor.setAccessible(true);
                T instance = (T)constructor.newInstance();
                for(Field field : type.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
