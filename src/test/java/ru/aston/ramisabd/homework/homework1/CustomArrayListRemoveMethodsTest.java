package ru.aston.ramisabd.homework.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class CustomArrayListRemoveMethodsTest {
    public CustomArrayList<String> customArrayList;
    String testElement1 = "testElement";
    String testElement2 = "testElement2";

    @BeforeEach
    public void createNewList() {
        customArrayList = new CustomArrayList<>();
        customArrayList.add(0, testElement1);
        customArrayList.add(0, testElement2);
    }

    @Test
    public void removeFromCustomArrayListByIndex() {
        customArrayList.remove(0);

        Assertions.assertEquals(1, customArrayList.size(), "При удаление элемента" +
                " из списка длинной 2, длинна должна быть равна 1");

        try {
            Field field = customArrayList.getClass().getDeclaredField("arr");
            field.setAccessible(true);
            Object[] arr = (Object[]) field.get(customArrayList);

            Assertions.assertEquals(testElement1, arr[0], "При удаление элемента" +
                    " из списка,элемент следующий по индексу должны сместиться на один влево");
            Assertions.assertNull(arr[1], "При удаление элемента" +
                    " из списка,элемент должны быть удалены, а значение последнего элемента пепеписаться на null");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Ошибка рефлексии");
        }
    }

    @Test
    public void removeFromCustomArrayListByElement() {
        customArrayList.remove(testElement2);

        Assertions.assertEquals(1, customArrayList.size(), "При удаление элемента" +
                " из списка длинной 2, длинна должна быть равна 1");

        try {
            Field field = customArrayList.getClass().getDeclaredField("arr");
            field.setAccessible(true);
            Object[] arr = (Object[]) field.get(customArrayList);

            Assertions.assertEquals(testElement1, arr[0], "При удаление элемента" +
                    " из списка,элемент следующий по индексу должны сместиться на один влево");
            Assertions.assertNull(arr[1], "При удаление элемента" +
                    " из списка,элемент должны быть удалены, а значение последнего элемента пепеписаться на null");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Ошибка рефлексии");
        }
    }


}
