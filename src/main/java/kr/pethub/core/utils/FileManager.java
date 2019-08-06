package kr.pethub.core.utils;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import kr.pethub.core.module.model.FileInfo;



public class FileManager {

	/**
	 * 파일업로드
	 * @param files
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static List< FileInfo> upload(List<MultipartFile> files, String filePath) throws Exception{

		List< FileInfo> list = new ArrayList< FileInfo>();

		if (null != files && files.size() > 0) {

			File dir = new File(filePath);
			if(!dir.exists())	dir.mkdirs();

			for (MultipartFile multipartFile : files) {

				if(!multipartFile.isEmpty()){
					String fileRealNm = multipartFile.getOriginalFilename();

					String fileExt = fileRealNm.substring(fileRealNm.lastIndexOf(".") + 1,fileRealNm.length()).toLowerCase();
					if(fileExt.equals("jsp")) fileExt = "txt";
					String fileSysNm = UUID.randomUUID().toString().replace("-", "") + "." + fileExt;
					long fileSize = multipartFile.getSize();

					File f = new File(filePath + "/" + fileSysNm);
//					if(f.exists()) f.delete();

					multipartFile.transferTo(f);

					FileInfo file = new FileInfo();
					file.setFilePath(filePath);
					file.setFileSysNm(fileSysNm);
					file.setFileRealNm(fileRealNm);
					file.setFileSize(fileSize);

					list.add(file);

				}
			}

		}

		return list;
	}

	/**
	 * 파일업로드
	 * @param mf
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static  FileInfo upload(MultipartFile mf, String filePath) throws Exception{

		 FileInfo file = new  FileInfo();

		if (null != mf) {
			File dir = new File(filePath);
			if(!dir.exists())	dir.mkdirs();

			if(!mf.isEmpty()){
				String fileRealNm = mf.getOriginalFilename();

				String fileExt = fileRealNm.substring(fileRealNm.lastIndexOf(".") + 1,fileRealNm.length()).toLowerCase();
				if(fileExt.equals("jsp")) fileExt = "txt";
				String fileSysNm = UUID.randomUUID().toString().replace("-", "") + "." + fileExt;
				long fileSize = mf.getSize();

				File f = new File(filePath + "/" + fileSysNm);
//				if(f.exists()) f.delete();

				mf.transferTo(f);

				file.setFilePath(filePath);
				file.setFileSysNm(fileSysNm);
				file.setFileRealNm(fileRealNm);
				file.setFileSize(fileSize);
			}
		}
		return file;
	}

	
	/**
	 * 파일 다운로드
	 * @param request
	 * @param response
	 * @param file
	 * @throws UnsupportedEncodingException
	 */
	public static void fileDownload(HttpServletResponse response,  FileInfo file) throws UnsupportedEncodingException {
		
		if(file == null) return;
		if(file.getFilePath().indexOf("..") != -1) return;
		if(file.getFileSysNm().indexOf("..") != -1) return;
		if(file.getFileRealNm().indexOf("..") != -1) return;
		
		String filePath = file.getFilePath();
		String fileSysNm = file.getFileSysNm();
		String fileNm =  URLEncoder.encode(file.getFileRealNm(),"UTF-8");
		
		try{ 
			File f =  new File(filePath,fileSysNm); 
			
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=" +  fileNm);
	        response.setHeader("Content-Length", "" + f.length() );
	        
	        InputStream in = new FileInputStream(f);
	        OutputStream out =  response.getOutputStream();        
	        
	        BufferedInputStream bin = null;
	        BufferedOutputStream bos = null; 
	        
	        try {
	            bin = new BufferedInputStream( in );
	            bos = new BufferedOutputStream( out ); 
	        
	            byte[] buf = new byte[2048]; //buffer size 2K.
	            int read = 0;
	            while ((read = bin.read(buf)) != -1) {
	                bos.write(buf,0,read);
	            }
	        } finally {
	        	try {if(bos != null) bos.close();} catch(Exception e){};
	        	try {if(bin != null) bin.close();} catch(Exception e){};
	        }        

		}catch(Exception e){
			e.getStackTrace();
		}
		
	}
	
	/**
	 * 파일삭제
	 * @param fileNm
	 */
	public static void fileDelete(String fileNm) {
		File f = new File(fileNm);
		if(f.exists()) f.delete();
	}

}
