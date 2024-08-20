package tv.on;


import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static Set<Class> findAllScrapperProviderClasses(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return reflections.getSubTypesOf(Object.class)
                .stream()
                .filter(clazz -> clazz.getName().endsWith("ScrapperProvider"))
                .collect(Collectors.toSet());
    }
}
