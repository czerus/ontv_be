package tv.on;


import com.neovisionaries.i18n.CountryCode;
import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
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

    @SneakyThrows
    public static Integer getIntFieldValueByReflection(Class<?> clazz, String fieldName) {
        Field field = clazz.getDeclaredField(fieldName);
        return (Integer) field.get(null);
    }

    @SneakyThrows
    public static CountryCode getCountryCodeFieldValueByReflection(Class<?> clazz, String fieldName) {
        Field field = clazz.getDeclaredField(fieldName);
        return (CountryCode) field.get(null);
    }
}
