package com.stefan.mockito;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThrowTest {

    /**
     * 例子1: thenThrow 用来让函数调用抛出异常
     */
    @Test
    public void throwTest1() {

        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenThrow(new RuntimeException("异常"));

        try {
            mockRandom.nextInt();
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
            assertEquals("异常", ex.getMessage());
        }
    }

    /**
     * 例子2: thenThrow 中可以指定多个异常, 在调用时异常依次出现, 若调用次数超过异常的数量, 再次调用时抛出最后一个异常
     */
    @Test
    public void throwTest2() {

        Random mockRandom = mock(Random.class);
        when(mockRandom.nextInt()).thenThrow(new RuntimeException("异常1"), new RuntimeException("异常2"));

        try {
            mockRandom.nextInt();
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
            assertEquals("异常1", ex.getMessage());
        }

        try {
            mockRandom.nextInt();
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
            assertEquals("异常2", ex.getMessage());
        }

        try {
            mockRandom.nextInt();
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
            assertEquals("异常2", ex.getMessage());
        }
    }

}
