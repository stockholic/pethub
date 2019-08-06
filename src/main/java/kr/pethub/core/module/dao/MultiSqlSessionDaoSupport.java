package kr.pethub.core.module.dao;


import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class MultiSqlSessionDaoSupport {	
	
	private SqlSession sqlSession;
	
    /**
     * SqlSessionFactory
     *   - sqlSessionFactory가 추가될 경우에는 상속받는 dao에서 override
     * @param sessionFactory
     */
    @Autowired
    @Qualifier(value = "sqlSessionFactory")
    public void setSessionFactory(SqlSessionFactory sessionFactory) {
	    this.sqlSession = new SqlSessionTemplate(sessionFactory);
    }
    
  	
	/**
	 * mapper의 sql id 생성
	 * @param sqlId  SQL ID
	 * @return
	 */
	private String makeMapperSqlId(String sqlId){
	    StringBuilder sb = new StringBuilder();
	    sb.append(this.getClass().getName()).append(".");
	    sb.append(sqlId);
	    
	    return sb.toString();
	}
	
	/**
	 * SelectOne
	 * @param sqlId
	 * @return int
	 */
	public int getInt(String sqlId) {
		return (Integer) sqlSession.selectOne(makeMapperSqlId(sqlId));
	}

	/**
	 * Select int
	 * @param sqlId
	 * @param object
	 * @return int
	 */
	public int getInt(String sqlId, Object object) {
		return (Integer) sqlSession.selectOne(makeMapperSqlId(sqlId), object );
	}
	
	/**
	 * Select String
	 * @param sqlId
	 * @return String
	 */
	public String getString(String sqlId) {
		return (String) sqlSession.selectOne(makeMapperSqlId(sqlId));
	}

	/**
	 * Select String
	 * @param sqlId
	 * @param object
	 * @return String
	 */
	public String getString(String sqlId,Object object) {
		return (String) sqlSession.selectOne(makeMapperSqlId(sqlId), object);
	}
	
	public <T> T selectOne(String sqlId){
		return sqlSession.selectOne(makeMapperSqlId(sqlId));
	}

	/**
	 * Select Object
	 * @param sqlId
	 * @param object
	 * @return <T> the generic type
	 */
	public <T> T selectOne(String sqlId, Object object){
		return sqlSession.selectOne(makeMapperSqlId(sqlId), object);
	}
	
	/**
	 * Select List
	 * @param sqlId
	 * @return <E> the element type
	 */
	public <E> List<E> selectList(String sqlId) {		
		return sqlSession.selectList(makeMapperSqlId(sqlId));
	}

	/**
	 * Select List 
	 * @param sqlId
	 * @param object
	 * @return <E> the element type
	 */
	public <E> List<E> selectList(String sqlId, Object object) {		
		return sqlSession.selectList(makeMapperSqlId(sqlId), object);		
	}
	
	/**
	 *  Insert 
	 * @param sqlId
	 * @return int
	 */
	public int insert(String sqlId){
		return sqlSession.insert(makeMapperSqlId(sqlId));	
	}
	
	/**
	 *  Insert 
	 * @param sqlId
	 * @param object
	 * @return int
	 */
	public int insert(String sqlId, Object object){
		return sqlSession.insert(makeMapperSqlId(sqlId), object);	
	}

	/**
	 * Update
	 * @param sqlId
	 * @return int
	 */
	public int update(String sqlId){
		return  sqlSession.update(makeMapperSqlId(sqlId));
	}

	/**
	 * Update
	 * @param sqlId
	 * @param object
	 * @return int
	 */
	public int update(String sqlId, Object object){
		return  sqlSession.update(makeMapperSqlId(sqlId), object);
	}
	
	/**
	 * Delete 
	 * @param sqlId
	 * @return int
	 */
	public int delete(String sqlId){
		return sqlSession.delete(makeMapperSqlId(sqlId));
	}

	/**
	 * Delete 
	 * @param sqlId
	 * @param object
	 * @return int
	 */
	public int delete(String sqlId, Object object){
		return sqlSession.delete(makeMapperSqlId(sqlId), object);
	}
}
