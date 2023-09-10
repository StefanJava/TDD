package com.stefan.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExampleService3.class)
public class PowerMockTest {

    @Test
    public void test() {

        PowerMockito.mockStatic(ExampleService3.class);

        when(ExampleService3.add(1, 3)).thenReturn(100);

        assertEquals(100, ExampleService3.add(1, 3));
        assertEquals(100, ExampleService3.add(1, 3));
    }

}

class ExampleService3 {

    public static int add(int a, int b) {
        return a + b;
    }
}
