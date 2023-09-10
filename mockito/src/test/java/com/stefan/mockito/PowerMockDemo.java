package com.stefan.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
public class PowerMockDemo {

    @Mock
    private Random random;

    @Test
    public void test() {

        when(random.nextInt()).thenReturn(100);

        assertEquals(100, random.nextInt());
    }

}
