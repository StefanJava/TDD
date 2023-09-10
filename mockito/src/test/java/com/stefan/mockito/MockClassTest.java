package com.stefan.mockito;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockClassTest {

    @Test
    public void mockClassTest() {

        Random mockRandom = mock(Random.class);  // jdk8

        // 默认值：mock 对象的方法的返回值默认都是返回类型的默认值
        System.out.println(mockRandom.nextBoolean());
        System.out.println(mockRandom.nextInt());
        System.out.println(mockRandom.nextDouble());

        // mock: 指定调用 nextInt 方法时，永远返回 100
        when(mockRandom.nextInt()).thenReturn(100);
        assertEquals(100, mockRandom.nextInt());
        assertEquals(100, mockRandom.nextInt());
    }

    @Test
    public void mockInterfaceTest() {

        List mockList = mock(List.class);

        // 接口的默认值: 和类方法一致, 都是默认返回值
        assertEquals(0, mockList.size());
        assertEquals(null, mockList.get(0));

        // 注意： 调用 mock 对象的写方法, 是没有效果的
        mockList.add("a");
        assertEquals(0, mockList.size());
        assertEquals(null, mockList.get(0));

        // mock 值测试
        when(mockList.get(0)).thenReturn("a");  // 指定 get(0) 时返回 "a"
        assertEquals(0, mockList.size());  // 没有指定 size() 方法的返回值, 这里结果是默认值
        assertEquals("a", mockList.get(0));
        assertEquals(null, mockList.get(1));
    }

}
