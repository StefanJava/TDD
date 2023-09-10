package com.stefan.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ParameterTest {

    @Mock
    public List<String> testList;

    @Test
    public void test01() {

        // 精确匹配 0
        when(testList.get(0)).thenReturn("a");
        assertEquals("a", testList.get(0));

        // 精确匹配 0
        when(testList.get(0)).thenReturn("b");
        assertEquals("b", testList.get(0));

        // 模糊匹配
        when(testList.get(anyInt())).thenReturn("c");
        assertEquals("c", testList.get(0));
        assertEquals("c", testList.get(1));
    }

}
