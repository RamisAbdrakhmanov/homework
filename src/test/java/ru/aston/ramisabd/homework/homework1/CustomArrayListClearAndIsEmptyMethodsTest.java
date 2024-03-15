package ru.aston.ramisabd.homework.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class CustomArrayListClearAndIsEmptyMethodsTest {

    public CustomArrayList<String> customArrayList;

    @BeforeEach
    public void createNewList() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void addToCustomArrayListByIndex() {
        String testElement = "testElement";

        customArrayList.add(0, testElement);
        customArrayList.clear();
        Assertions.assertEquals(0, customArrayList.size(), "При удаление элементов" +
                " из списка, длинна должна быть равна 0");
        Assertions.assertTrue(customArrayList.isEmpty(), "При удаление элементов" +
                " из списка, коллекция должна быть пустой");

        try {
            Field field = customArrayList.getClass().getDeclaredField("arr");
            field.setAccessible(true);
            Object[] arr = (Object[]) field.get(customArrayList);

            Assertions.assertNull(arr[0], "При удаление элементов" +
                    " из списка, все элементы должны быть так же удалены");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Ошибка рефлексии");
        }

    }
}
