package com.stefan.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoDemo {

    @Mock
    private ExampleService2 spyExampleService2;

    @Test
    public void test01() {

        System.out.println("---call test01---");

        System.out.println("打桩前: " + spyExampleService2.add(1, 2));

        when(spyExampleService2.add(1, 2)).thenReturn(4);

        System.out.println("打桩后: " + spyExampleService2.add(1, 2));
    }

    @Test
    public void test02() {

        System.out.println("---call test02---");

        System.out.println("打桩前: " + spyExampleService2.add(1, 2));

        when(spyExampleService2.add(1, 2)).thenReturn(4);

        System.out.println("打桩后: " + spyExampleService2.add(1, 2));
    }

    @Test
    public void test03() {

        System.out.println("---call test03---");

        when(ExampleService2.sub(2, 1)).thenReturn(100);
    }

}

class ExampleService2 {

    public int add(int a, int b) {
        return a + b;
    }

    public static int sub(int a, int b) {
        return a - b;
    }

}
