package ru.aston.ramisabd.homework.homework1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class CustomArrayListAddAllMethodTest {
    public CustomArrayList<String> customArrayList;

    @BeforeEach
    public void createNewList() {
        customArrayList = new CustomArrayList<>();
    }

    @Test
    public void addAllCollectionsToCustomArrayList() {
        Set<String> collections = new HashSet<>();

        String testElement = "t";

        collections.add(testElement);

        customArrayList.addAll(collections);

        Assertions.assertEquals(collections.size(), customArrayList.size(), "При добалении коллекции с одним элементом" +
                " в список, длинна должна быть равна 1");
        Assertions.assertEquals(testElement, customArrayList.get(0), "При добалении коллекции с одним элементом" +
                " в список, елементы должны совпадать");
    }

    @Test
    public void addMostThan10Element() {

        Set<String> collections = new HashSet<>();

        String testElement = "t";

        for (int i = 0; i < 11; i++) {
            testElement += "t";
            collections.add(testElement);
        }

        customArrayList.addAll(collections);

        Assertions.assertEquals(collections.size(), customArrayList.size(), "При добалении коллекции с одним элементом" +
                " в список, длинна должна быть равна");

    }
}
