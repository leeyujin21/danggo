package com.example.danggo.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class SqlBaseDAO {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private SqlSessionTemplate sqlSession;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    /*
    * 입력 처리 SQL mapping
    * @param - queryId 입력 처리 SQL mapping 쿼리 ID
    * @return - insert 적용 결과 count
    * */
    public int insert(String queryId) {
        LOGGER.debug("queryId = " + queryId);
        return sqlSession.insert(queryId);
    }

    /*
     * 입력 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @param paramObj - 입력처리 SQL mapping 입력 데이터를 세팅한 파라미터 객체
     * @return - insert 적용 결과 count
     * */
    public int insert(String queryId, Object paramObj) {
        LOGGER.debug("queryId = " + queryId);
        return sqlSession.insert(queryId, paramObj);
    }

    /*
     * 수정 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @return - update 적용 결과 count
     * */
    public int update(String queryId) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.update(queryId);
    }

    /*
     * 수정 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @param paramObj - 입력처리 SQL mapping 입력 데이터를 세팅한 파라미터 객체
     * @return - update 적용 결과 count
     * */
    public int update(String queryId, Object paramObj) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.update(queryId, paramObj);
    }

    /*
     * 삭제 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @return - delete 적용 결과 count
     * */
    public int delete(String queryId) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.delete(queryId);
    }

    /*
     * 삭제 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @return - delete 적용 결과 count
     * */
    public int delete(String queryId, Object paramObj) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.delete(queryId, paramObj);
    }

    /*
     * 단건 조회 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @return - select 적용 결과 Result Type
     * */
    public <T> T selectOne(String queryId) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.selectOne(queryId);
    }

    /*
     * 단건 조회 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @param paramObj - 입력처리 SQL mapping 입력 데이터를 세팅한 파라미터 객체
     * @return - select 적용 결과 Result Type
     * */
    public <T> T selectOne(String queryId, Object paramObj) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.selectOne(queryId);
    }

    /*
     * 다건 조회 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @return - select 적용 결과 List
     * */
    public <E> List<E> selectList(String queryId) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.selectOne(queryId);
    }

    /*
     * 다건 조회 처리 SQL mapping
     * @param queryId - id 입력 처리 SQL mapping 쿼리 ID
     * @param paramObj - 입력처리 SQL mapping 입력 데이터를 세팅한 파라미터 객체
     * @return - select 적용 결과 List
     * */
    public <E> List<E> selectList(String queryId, Object paramObj) {
        LOGGER.debug("queryId = "+queryId);
        return sqlSession.selectOne(queryId, paramObj);
    }
}
