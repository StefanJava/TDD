package com.stefan.mockito;

import com.stefan.mockito.dao.DemoDao;
import com.stefan.mockito.service.DemoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class HelloWorldTest {

    @Test
    public void helloWorldTest() {

        // mock DemoDao instance
        DemoDao mockDemoDao = Mockito.mock(DemoDao.class);

        // 使用 mockito 对 getDemoStatus 方法打桩
        Mockito.when(mockDemoDao.getDemoStatus()).thenReturn(1);

        // 调用 mock 对象的 getDemoStatus 方法, 结果永远是 1
        Assert.assertEquals(1, mockDemoDao.getDemoStatus());

        // mock DemoService
        DemoService mockDemoService = new DemoService(mockDemoDao);
        Assert.assertEquals(1, mockDemoService.getDemoStatus());
    }

}
