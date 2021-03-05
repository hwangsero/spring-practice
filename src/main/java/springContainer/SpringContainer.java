package springContainer;


import annotation.Autowired;
import org.reflections.Reflections;
import org.reflections.Store;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SpringContainer {

    private static SpringContainer instance;
    private Map<Class, Object> beans;
    private SpringContainer() {};

    private SpringContainer(Map<Class, Object> beans, Class startClass) {
        this.beans = beans;
        beans = Collections.unmodifiableMap(beans);
        dependencyInjection(startClass);
    }

    public static SpringContainer getInstance(Map<Class, Object> beans, Class startClass) {
        if (null == SpringContainer.instance) {
            synchronized (SpringContainer.class) {
                if (instance == null)
                    SpringContainer.instance = new SpringContainer(beans, startClass);
            }
        }
        return instance;
    }

    private void dependencyInjection(Class startClass) {

        Reflections reflections = new Reflections((startClass.getPackageName()), new FieldAnnotationsScanner());

//        Reflections reflections = new Reflections("");
//
//        Set<Class<?>> subTypesOf = reflections.getSubTypesOf(Object.class);
//        subTypesOf.stream().forEach(c -> {
//            Arrays.stream(c.getDeclaredFields()).forEach(field -> {
//                Object instance = createInstance(c);
//                field.setAccessible(true);
//                Object obj = beans.get(field.getType());
//                if(obj == null) {
//                    obj = createInstance(field.getType());
//                    beans.put(field.getType(), obj);
//                }
//                try {
//                    field.set(instance, obj);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            });
//        });

        reflections.getFieldsAnnotatedWith(Autowired.class).stream().forEach(it -> {
            Object instance = createInstance(it.getDeclaringClass());
            it.setAccessible(true);
            Object obj = beans.get(it.getType());
            if(obj == null) {
                obj = createInstance(it.getType());
                beans.put(it.getType(), obj);
            }
            try {

                it.set(instance, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    private static <T> T createInstance(final Class<T> classType) {
        try {

            return classType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
