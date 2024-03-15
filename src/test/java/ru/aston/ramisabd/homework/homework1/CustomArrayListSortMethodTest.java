package ru.aston.ramisabd.homework.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class CustomArrayListSortMethodTest {

    public CustomArrayList<String> customArrayList;
    String test = "";

    @BeforeEach
    public void createNewList() {
        customArrayList = new CustomArrayList<>();

        for (int i = 0; i < 10; i++) {
            test += i;
            customArrayList.add(i, test);
        }
    }

    @Test
    public void sortNaturalOrder() {

        customArrayList.sort(Comparator.naturalOrder());

        test = "";

        for (int i = 0; i < customArrayList.size(); i++) {
            test += i;
            Assertions.assertEquals(test, customArrayList.get(i), "При сортировки по натуральному порядку, " +
                    "элементы должны находиться в отсортированном порядке");
        }

    }

    @Test
    public void sortReverseOrder() {
        customArrayList.sort(Comparator.reverseOrder());

        test = "";

        for (int i = customArrayList.size() - 1; i >= 0; i--) {
            test += customArrayList.size() - 1 - i;
            Assertions.assertEquals(test, customArrayList.get(i), "При сортировки в обратном порядке, " +
                    "элементы должны находиться в отсортированном порядке");
        }
    }
}
