package com.example.danggo.settingtest.dao;

import com.example.danggo.common.dao.SqlBaseDAO;
import org.springframework.stereotype.Repository;

@Repository("StestDAO")
public class StestDAOImpl extends SqlBaseDAO implements StestDAO {
    @Override
    public void insertTest(String note) {
        insert("StestSql.test", note);
    }
}
