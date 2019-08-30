package kr.pethub.webapp.admin.board.dao;


import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.dao.MultiSqlSessionDaoSupport;
import kr.pethub.core.module.model.FileInfo;


@Repository
public class FileDao extends MultiSqlSessionDaoSupport{	
	
	public int insertFile(FileInfo fileInfo){
		return  insert("insertFile",fileInfo);
	}

	public List<FileInfo> selectFileList(Map<String,Object> map){
		return  selectList("selectFileList",map);
	}

	public FileInfo selectFile(Integer fileSrl){
		return  selectOne("selectFile",fileSrl);
	}

	public int deleteFile(Integer fileSrl){
		return  delete("deleteFile",fileSrl);
	}
}
