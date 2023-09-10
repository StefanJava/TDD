package com.stefan.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockAnnotationTest {

    /*@Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }*/

    @Mock
    private Random random;

    @Test
    public void test() {
        when(random.nextInt()).thenReturn(99);
        assertEquals(99, random.nextInt());
        assertEquals(99, random.nextInt());
    }

}
