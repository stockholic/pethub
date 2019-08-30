package kr.pethub.webapp.admin.board.service;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.pethub.core.module.model.FileInfo;
import kr.pethub.core.utils.FileManager;
import kr.pethub.webapp.admin.board.dao.FileDao;


@Service
public class FileService{
	
	@Value("${board.file.path}") 
	private String boardFilePath;
	
	@Value("${stock.file.path}") 
	private String stockFilePath;
	
	@Autowired
	FileDao fileDao;
	
	public List<FileInfo> multiFileUpload(List<MultipartFile> file, String flag) throws Exception{
		return  FileManager.upload(file, boardFilePath + "/" + flag);
	}

	public FileInfo singleFileUpload(MultipartFile file, String fileTp) throws Exception{
		return  FileManager.upload(file, getFilePath(fileTp));
	}

	public FileInfo singleFileUpload(MultipartFile file, String fileTp, String flag) throws Exception{
		return  FileManager.upload(file, getFilePath(fileTp) + "/" + flag);
	}
	
	public String getFilePath(String fileTp){
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("board", boardFilePath);
		map.put("stock", stockFilePath);
		
		return map.get(fileTp);
	}
	
	public List<FileInfo> selectFileList(Map<String,Object> map){ 
		return  fileDao.selectFileList(map);
	}

	public FileInfo selectFile(Integer fileSrl){ 
		return  fileDao.selectFile(fileSrl);
	}
	
}
