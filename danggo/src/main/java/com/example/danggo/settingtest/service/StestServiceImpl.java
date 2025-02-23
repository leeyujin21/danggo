package com.example.danggo.settingtest.service;

import com.example.danggo.settingtest.dao.StestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StestServiceImpl implements StestService {

    @Autowired
    private StestDAO stestDAO;

    @Override
    public void insertTest(String note) {
        stestDAO.insertTest(note);
    }
}
