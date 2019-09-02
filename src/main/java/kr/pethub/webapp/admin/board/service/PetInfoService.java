package kr.pethub.webapp.admin.board.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.pethub.core.module.model.FileInfo;
import kr.pethub.core.utils.FileManager;
import kr.pethub.webapp.admin.board.dao.FileDao;
import kr.pethub.webapp.admin.board.dao.PetInfoDao;
import kr.pethub.webapp.admin.board.model.PetInfo;


@Service
public class PetInfoService{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PetInfoDao petInfoDao;
		
	@Autowired
	private FileDao fileDao;
	
	@Value("${petInfo.file.url}") 
	private String petInfoFileUrl;
	
	/**
	 * 펫 정보 수
	 * @param petInfo
	 * @return
	 */
	public int selectPetInfoCount(PetInfo petInfo){
		return  petInfoDao.selectPetInfoCount(petInfo);
	}
	
	/**
	 * 펫 정보 목록
	 * @param petInfo
	 * @return
	 */
	public List<PetInfo> selectPetInfoList(PetInfo petInfo){
		petInfo.setTotalRow(selectPetInfoCount(petInfo));
		List<PetInfo> list =  petInfoDao.selectPetInfoList(petInfo);
		
		int num = petInfo.getTotalRow() - petInfo.getRowStart();
		
		for (PetInfo pi : list) {
			pi.setNum(num);
			num--;
		}
		
		return list;
		
		
	}

	/**
	 *  펫 정보 조회
	 * @param petSrl
	 * @return
	 */
	public PetInfo selectPetInfo(Integer petSrl){
		PetInfo petinfo =  petInfoDao.selectPetInfo(petSrl);
		petinfo.setPetImg(petInfoFileUrl);
		
		return  petinfo;
	}
	
	/**
	 * 펫 정보 등록
	 * @param petInfo
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public void  insertPetInfo(PetInfo petInfo) throws JsonParseException, JsonMappingException, IOException{
		
		petInfoDao.insertPetInfo(petInfo);
		
		//첨부파일 등록
		if(StringUtils.isNotEmpty(petInfo.getFileInfo())) {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = mapper.readValue(petInfo.getFileInfo(), Map.class);
			List<Object> fileList = (List<Object>)map.get("insertFile");
			
			for(Object obj : fileList) {
				FileInfo fileInfo = mapper.convertValue(obj, FileInfo.class);
				fileInfo.setFileRefSrl(petInfo.getPetSrl());
				fileInfo.setFileTp("petInfo");
				fileDao.insertFile(fileInfo);
			}
		}
		
	}
	

	/**
	 * 펫 정보 수정
	 * @param petInfo
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public void  updatePetInfo(PetInfo petInfo) throws JsonParseException, JsonMappingException, IOException {
		petInfoDao.updatePetInfo(petInfo);
		
		
		if(StringUtils.isNotEmpty(petInfo.getFileInfo())) {
			ObjectMapper mapper = new ObjectMapper();
			Map<String,Object> map = mapper.readValue(petInfo.getFileInfo(), Map.class);

			//첨부파일 등록
			List<Object> fileList = (List<Object>)map.get("insertFile");
			for(Object obj : fileList) {
				FileInfo fileInfo = mapper.convertValue(obj, FileInfo.class);
				fileInfo.setFileRefSrl(petInfo.getPetSrl());
				fileInfo.setFileTp("petInfo");
				fileDao.insertFile(fileInfo);
			}
			
			//첨부파일 삭제
			List<Object> deleteList = (List<Object>)map.get("deleteFile");
			for(Object petInfoFileSrl : deleteList) {
				
				FileInfo  fileInfo = fileDao.selectFile((Integer)petInfoFileSrl);
				FileManager.fileDelete(fileInfo.getFilePath() + "/" + fileInfo.getFileSysNm());
				
				fileDao.deleteFile((Integer)petInfoFileSrl);
			}
			
		}
	}

	/**
	 * 펫 정보 삭제
	 * @param petInfo
	 */
	@Transactional
	public void  deletePetInfo(PetInfo petInfo) {

		int isDelete = petInfoDao.deletePetInfo(petInfo);
		
		if(isDelete > 0) {
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fileTp", "petInfo");
			map.put("fileRefSrl", petInfo.getPetSrl());
			
			List<FileInfo>  fileList = fileDao.selectFileList(map);
			for(FileInfo fileInfo : fileList) {
				FileManager.fileDelete(fileInfo.getFilePath() + "/" + fileInfo.getFileSysNm());
				fileDao.deleteFile(fileInfo.getFileSrl());
			}
		}
		
	}
	
	
}
