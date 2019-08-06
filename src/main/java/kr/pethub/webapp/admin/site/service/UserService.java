package kr.pethub.webapp.admin.site.service;

import java.text.ParseException;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.pethub.webapp.admin.site.dao.UserDao;
import kr.pethub.webapp.admin.site.model.User;


@Service
public class UserService{
	
	@Autowired
	private UserDao userDao;
		
	/**
	 * 사용자 수
	 * @param user
	 * @return
	 */
	public int selectUserCount(User user){
		return  userDao.selectUserCount(user);
	}
	
	/**
	 * 사용자 목록
	 * @param user
	 * @return
	 */
	public List<User> selectUserList(User user){
		user.setTotalRow(selectUserCount(user));
		return  userDao.selectUserList(user);
	}

	/**
	 * 사용자 조회
	 * @param usrSrl
	 * @return
	 */
	public User selectUser(Integer usrSrl){
		return  userDao.selectUser(usrSrl);
	}
	
	/**
	 * 사용자 등록
	 * @param user
	 */
	@Transactional
	public void  insertUser(User user){
		
		if(StringUtils.isNotEmpty(user.getPassword())) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		//사용자 등록
		userDao.insertUser(user);
		
		//사용자 권한 등록
		userDao.insertUserRole(user);
		
	}

	/**
	 * 사용자 수정
	 * @param user
	 * @throws ParseException 
	 */
	@Transactional
	public void  updateUser(User user) throws ParseException{
		
		if(StringUtils.isNotEmpty(user.getPassword())) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		//사용자 수정
		userDao.updateUser(user);

		//사용자 권한 수정
		userDao.updateUseRole(user);
	}
	
}
