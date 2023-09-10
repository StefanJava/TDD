package com.stefan.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class DoThrowTest {

    static class ExampleService {
        public void hello() {
            System.out.println("Hello");
        }
    }

    @Mock
    private ExampleService exampleService;

    @Test
    public void test() {

        // 这种写法可以达到效果
        doThrow(new RuntimeException("异常")).when(exampleService).hello();

        try {
            exampleService.hello();
            fail();
        } catch (Exception ex) {
            assertTrue(ex instanceof RuntimeException);
            assertEquals("异常", ex.getMessage());
        }
    }

}
