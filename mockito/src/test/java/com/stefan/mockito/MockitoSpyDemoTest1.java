package com.stefan.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
public class MockitoSpyDemoTest1 {

    @Spy
    private ExampleService1 exampleService1 = new ExampleService1(1);

    @Test
    public void test_spy() {

        MockitoAnnotations.initMocks(this);

        Assert.assertEquals(3, exampleService1.add(2));

        when(exampleService1.add(2)).thenReturn(4);
        assertEquals(4, exampleService1.add(2));
    }

}

class ExampleService1 {

    private int a;

    public ExampleService1(int a) {
        this.a = a;
    }

    public int add(int b) {

        return this.a + b;
    }

}
