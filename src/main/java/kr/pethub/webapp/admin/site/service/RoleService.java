package kr.pethub.webapp.admin.site.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.webapp.admin.site.dao.RoleDao;
import kr.pethub.webapp.admin.site.model.Role;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;

	/**
	 * 권한 수
	 * @param role
	 * @return
	 */
	public int selectRoleCount(Role role) {
		return roleDao.selectRoleCount(role);
	}

	/**
	 * 권한 목록
	 * @param role
	 * @return
	 */
	public List<Role> selectRoleList(Role role) {
		return roleDao.selectRoleList(role);
	}

	/**
	 * 권한 조회
	 * @param seqNo
	 * @return
	 */
	public Role selectRole(String roleSrl) {
		return roleDao.selectRole(roleSrl);
	}

	/**
	 * 권한 등록
	 * @param role
	 */
	public void insertRole(Role role) {
		roleDao.insertRole(role);
	}

	/**
	 * 권한 수정
	 * @param role
	 */
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	/**
	 * 권한 사용여부 수정
	 * @param role
	 */
	public void updateStatus(Role role) {
		

		for(String roleSrl : role.getArrRoleSrl()){
			Map<String,String> map = new HashMap<String,String>();
			map.put("useYn", role.getUseYn());
			map.put("roleSrl", roleSrl);		
			roleDao.updateStatus(map);
		}
	}

	/**
	 * 권한 삭제
	 * @param role
	 */
	public void deleteRole(Role role) {
		
		for(String roleSrl : role.getArrRoleSrl()){
			roleDao.deleteRole(roleSrl);
		}
		
	}
}
