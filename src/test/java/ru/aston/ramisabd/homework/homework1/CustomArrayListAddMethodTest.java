package ru.aston.ramisabd.homework.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CustomArrayListAddMethodTest {

    public CustomArrayList<String> customArrayList;

    @BeforeEach
    public void createNewList() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void addToCustomArrayListByIndex() {
        String testElement = "testElement";

        customArrayList.add(0, testElement);
        Assertions.assertEquals(1, customArrayList.size(), "При добалении одного элемента" +
                " в список, длинна должна быть равна 1");
        Assertions.assertEquals(testElement, customArrayList.get(0), "При добалении одного элемента" +
                " в список, елемент не был найден по тому же индексу");
    }

    @Test
    public void addFirstToCustomArrayListByIndex() {
        String testElement = "t";
        int count = 2;
        while (count-- > 0) {
            testElement += "t";
            customArrayList.add(0, testElement);
        }

        Assertions.assertEquals(2, customArrayList.size(), "При добалении элементов" +
                " в начало список, длинна списка должна быть равна 2");

        testElement = "t";

        for (int i = customArrayList.size() - 1; i >= 0; i--) {
            testElement += "t";
            Assertions.assertEquals(testElement, customArrayList.get(i), "При добалении 2 элементов" +
                    " в начало список, каждый элемент должен быть на своем месте");
        }
    }

    @Test
    public void addToCustomArrayListByWrongIndex() {
        String testElement = "testElement";

        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> customArrayList.add(1, testElement));

        Assertions.assertEquals("Выход за границу списка", thrown.getMessage());
    }

    @Test
    public void addToCustomArrayListByNegativeIndex() {
        String testElement = "testElement";

        IndexOutOfBoundsException thrown = Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> customArrayList.add(-1, testElement));

        Assertions.assertEquals("Выход за границу списка", thrown.getMessage());
    }

    @Test
    public void addMostThan10Element() {
        String testElement = "t";

        for (int i = 0; i < 11; i++) {
            testElement += "t";
            customArrayList.add(i, testElement);
        }

        Assertions.assertEquals(11, customArrayList.size(), "При добалении 11 элементов" +
                " в список, длинна списка должна быть равна 11");

        testElement = "t";
        for (int i = 0; i < 11; i++) {
            testElement += "t";
            Assertions.assertEquals(testElement, customArrayList.get(i), "При добалении 11 элементов" +
                    " в список, каждый элемент должен быть на своем месте");
        }
    }


}
