package com.stefan.mockito;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MockitoSpyDemoTest {

    @Test
    public void test_spy() {

        ExampleService spyExampleService = spy(new ExampleService());
        ExampleService spyExampleService1 = mock(ExampleService.class);

        // 默认会走真实方法
        assertEquals(3, spyExampleService.add(1, 2));
        // 默认返回值默认值
        assertEquals(0, spyExampleService1.add(1, 2));

        // 打桩后, 就不会走真实方法了
        when(spyExampleService.add(1, 2)).thenReturn(4);
        assertEquals(4, spyExampleService.add(1, 2));

        // 但是参数不匹配的调用, 依然走真实方法
        assertEquals(3, spyExampleService.add(2, 1));
    }

}

class ExampleService {

    int add(int a, int b) {

        return a + b;
    }

}
