package componentScan;

import annotation.Component;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComponentScanManager {

    public static Map<Class, Object> run(Class startClass) {
        Reflections reflections = new Reflections(startClass.getPackageName(), new TypeAnnotationsScanner());

        Map<Class, Object> beans = new HashMap<>();
        reflections.getTypesAnnotatedWith(Component.class, true).stream().forEach(it -> {
                beans.put(it, null);
        });
        return beans;
    }

}
