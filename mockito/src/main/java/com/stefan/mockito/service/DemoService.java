package com.stefan.mockito.service;

import com.stefan.mockito.dao.DemoDao;

public class DemoService {
    private DemoDao demoDao;

    public DemoService(DemoDao demoDao) {
        this.demoDao = demoDao;
    }

    public int getDemoStatus() {
        return demoDao.getDemoStatus();
    }
}
