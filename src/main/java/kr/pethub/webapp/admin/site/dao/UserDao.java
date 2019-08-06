package kr.pethub.webapp.admin.site.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.webapp.admin.site.model.User;

@Repository
public class UserDao extends MultiSqlSessionDaoSupport{	
	
	public int selectUserCount(User user){
		return  getInt("selectUserCount",user);
	}

	public List<User> selectUserList(User user){
		return  selectList("selectUserList",user);
	}

	public User selectUser(Integer usrSrl){
		return  selectOne("selectUser",usrSrl);
	}

	public int insertUser(User user){
		return  insert("insertUser",user);
	}

	public int insertUserRole(User user){
		return  insert("insertUserRole",user);
	}

	public int updateUser(User user){
		return  update("updateUser",user);
	}

	public int updateUseRole(User user){
		return  update("updateUseRole",user);
	}
}
