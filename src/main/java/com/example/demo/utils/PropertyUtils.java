package com.example.demo.utils;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * @author lm
 */
public class PropertyUtils {
    public static <T> ObjectProperty<T> createProperty(T value) {
        SimpleObjectProperty<T> property = new SimpleObjectProperty<>();
        property.set(value);
        return property;
    }
}