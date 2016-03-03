package superbad.idea.builder;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MetaBuilder<T> {

    private T beanToBuild;

    private Map<String, Object> mapSet = new HashMap<>();

    public static <T> MetaBuilder<T> getBuilder(Class<T> classBean) {
        return new MetaBuilder<>(classBean);
    }

    public static <T> MetaBuilder<T> getBuilder(T bean) {
        return new MetaBuilder<>(bean);
    }

    private static void makeAccessible(AccessibleObject obj) {
        if (!obj.isAccessible()) {
            obj.setAccessible(true);
        }
    }

    private MetaBuilder(Class<T> classBean) {
        try {
            Constructor<T> constructor = classBean.getDeclaredConstructor();
            makeAccessible(constructor);
            this.beanToBuild = constructor.newInstance();
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException("Cannot instantiate this object", e);
        }
    }

    private MetaBuilder(T bean) {
        this.beanToBuild = bean;
    }

    public MetaBuilder<T> set(String propertyName, Object property) {
        mapSet.put(propertyName, property);
        return this;
    }

    private void setFieldProperty(String propertyName, Object property) {
        try {
            Field field = beanToBuild.getClass()
                    .getDeclaredField(propertyName);
            makeAccessible(field);
            field.set(beanToBuild, property);
        } catch (Exception e) {
            // nothing to catch here
        }
    }

    public T build() {
        mapSet.entrySet()
            .stream()
            .forEach(entry -> setFieldProperty(entry.getKey(), entry.getValue()));
        return beanToBuild;
    }

}
