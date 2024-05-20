package start;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Reflection {
    public static List<Object> retrieveProperties(Object object){
        List<Object> objects = new ArrayList<Object>();
        for(Field field : object.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object value;
            try{
                value = field.get(object);
                objects.add(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return objects;
    }
}
