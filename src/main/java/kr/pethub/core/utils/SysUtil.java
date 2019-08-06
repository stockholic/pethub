package kr.pethub.core.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.SequenceInputStream;
import java.io.Writer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

public class SysUtil {
	
	/***
	 * base64Encoder <p>
	 * @param sOriginStr
	 * @return
	 */
	public static String  base64Encoder(String sOriginStr){
		String sEncoderStr = "";
		try{
			Base64 encoder = new Base64();
	        byte[] byStr=sOriginStr.getBytes();;
	        sEncoderStr = encoder.encode(byStr).toString();
		}catch(Exception e){}
		
		return sEncoderStr;
	}

	/***
	 * base64Decoder <p>
	 * @param sEncoderStr
	 * @return
	 */
	public static String  base64Decoder(String sEncoderStr){
		String sDecoderStr = "";
		try{
			Base64 decoder = new Base64();
			byte[] byStr = (byte[]) decoder.decode(sEncoderStr);
			sDecoderStr = new String(byStr);
		}catch(Exception e){}
		
		return sDecoderStr ;
	}
	
	
	/**
	 * 콘솔 명령 결과 출력
	 * @param cmd
	 * @return String
	 */
	public static String getStream(String cmd){
		
		StringBuffer dataList = new StringBuffer();

		Runtime rt = Runtime.getRuntime();
		Process ps = null;
		
		try{
			ps = rt.exec(cmd);

			BufferedReader br = new BufferedReader(
			new InputStreamReader(
			new SequenceInputStream(ps.getInputStream(), ps.getErrorStream())));

			while((br.readLine()) != null){
				dataList.append(br.readLine() + "\n");
			}
			br.close();

		}catch(IOException e){}
		
		return dataList.toString();
	}
	

	/**
	 * 이미지 리사이징
	 * @param originalFile
	 * @param thumbnailFile
	 * @param thumbWidth
	 * @param thumbHeight
	 * @param quality
	 * @throws Exception
	 */
	public static void makeThumbNail(String originalFile, String thumbnailFile, int thumbWidth, int thumbHeight) throws Exception{
		
		Image image = javax.imageio.ImageIO.read(new File(originalFile));

		double thumbRatio = (double)thumbWidth / (double)thumbHeight;
		int imageWidth    = image.getWidth(null);
		int imageHeight   = image.getHeight(null);
		
		double imageRatio = (double)imageWidth / (double)imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int)(thumbWidth / imageRatio);
		} else {
			thumbWidth = (int)(thumbHeight * imageRatio);
		}
		   
		if(imageWidth < thumbWidth && imageHeight < thumbHeight) {
			thumbWidth = imageWidth;
			thumbHeight = imageHeight;
		} else if(imageWidth < thumbWidth){
			thumbWidth = imageWidth;
		}else if(imageHeight < thumbHeight){
			thumbHeight = imageHeight;
		}
		
		BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = thumbImage.createGraphics();
		graphics2D.setBackground(Color.WHITE);
		graphics2D.setPaint(Color.WHITE); 
		graphics2D.fillRect(0, 0, thumbWidth, thumbHeight);
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
   
		 javax.imageio.ImageIO.write(thumbImage, "JPG", new File(thumbnailFile));
		       
	}
	
	/***
	 * 파일 사이즈 <p>
	 * @param file
	 * @return String
	 */
	public static  String getFileSize(Long file){	

		String size = "0";

		if(file < 1024){
			size = file + " B";
		}else if(file >= 1024 && file < 1024 * 1024){
			size = String.format("%.2f", (double)file / 1024 ) + " KB";
		}else{
			size = String.format("%.2f", (double)file / 1024 ) + " MB";
		}

		return size;
	}
	
	/***
	 * 파일읽기
	 * @param path : 파일경로<br>
	 * @param filename : 파일명
	 * @return
	 */
	public static String getFileRead(String path, String filename) {
		
		StringBuffer dataList = new StringBuffer();
		BufferedReader in = null;
		try{
			File oTmpFile = new File(path, filename);
			if (!oTmpFile.exists()) 	dataList.append("");

			in = new java.io.BufferedReader(new FileReader(oTmpFile));
			while(in.ready()) {
				dataList.append(in.readLine()+ "\n");
			}

		}catch (Exception e){
			
		}finally{
			if(in != null)	try{in.close();} catch (IOException e) {}
		}

		return dataList.toString();
	}
		
	/***
	 * 파일쓰기
	 * @param str : 파일내용
	 * @param filename : 파일명(경로포함)
	 */
	public static void setFileWrite(String str, String filename, String charset) { 
		FileOutputStream fos = null;
		Writer out = null;
		
		try  {
			fos = new FileOutputStream(filename); 
			out = new OutputStreamWriter(fos, charset); 
			out.write(str); 
			
		} catch (IOException e) {
			
		}finally{
			if(out != null)	try{out.close();} catch (IOException e) {}
			if(fos != null)	try{fos.close();} catch (IOException e) {}
		}
	}
	
	/***
	 * 쿠키값 가져오기 <p>
	 * @param request <br>
	 * @param sName : 쿠키명 <br>
	 * @return String
	 * @throws Exception
	 */
	public static String getCookie(HttpServletRequest request, String sName) throws Exception {
		Cookie [] cookies = request.getCookies();
		if (cookies==null) return "";
		String value = "";
		for(int i=0;i<cookies.length;i++) {
			if(sName.equals(cookies[i].getName())) {
				value = cookies[i].getValue();				
				break;
			}
		}
		return value;
	}
	
	/***
	 * 쿠키값 생성 <p>
	 * @param response <br>
	 * @param sName : 쿠키 명 <br>
	 * @param cValue : 쿠키 값 <br>
	 * @param nTime : 쿠키 우효시간 ( -1 브라우저 생성시만 존재)<br>
	 * @param sDomain : 유효 도메인 ex) www.merong.net , .merong.net
	 */
	public static void setCookie(HttpServletResponse response, String sName, String cValue, int nTime, String sDomain) {
		
		Cookie cookie = new Cookie(sName, cValue.trim());
		cookie.setPath("/");
		if(nTime > 0) nTime = 	3600 * nTime;
		cookie.setMaxAge(nTime);		
		
		if(sDomain != null && !sDomain.equals("")) cookie.setDomain(sDomain);
		
		response.addCookie(cookie);		
	}
	
	/*** 세션 ID 가져오기<p>
	 * @param request <br>
	 * @param name
	 * @return
	 */
	public static String getSessionID(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
	    return session.getId();
	} 
	
	public static int getSessionTime(HttpServletRequest request) {
		HttpSession session   = request.getSession(true);
	    return session.getMaxInactiveInterval();
	} 
	
	/***
	 * 세션 가져오기<p>
	 * @param request <br>
	 * @param name
	 * @return
	 */
	
/*	public static LoginDto getSession(HttpServletRequest request, String sName) {
		HttpSession session = request.getSession(true);
		return (LoginDto)session.getAttribute(sName);
	} */ 
	
	 /***
     * 세션 생성<p>
     * @param request <br>
     * @param sName <br>
     * @param sValue
     */
/*    public static void setSession(HttpServletRequest request, String sName, LoginDto sValue) {
      HttpSession session   = request.getSession(true);
      session.setAttribute(sName, sValue);
    } */
    
    /***
     * 세션 종료<p>
     * @param request <br>
     * @param nMaxTime : 분단위
     */
    public static void setSessionOut(HttpServletRequest request) {
		HttpSession session   = request.getSession(true);
	    session.invalidate();
	} 
    
    /***
     * 세션 시간 설정<p>
     * @param request <br>
     * @param nMaxTime : 분단위
     */
    public static void setSessionTime(HttpServletRequest request, int nMinute) {
		HttpSession session   = request.getSession(true);
		int  nMaxTime = 60 * nMinute; 
	    session.setMaxInactiveInterval(nMaxTime);
	} 
	
    
    /**
     * Remote IP
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
    	    	
    	String ip = null;
		String [] headers = {"X-FORWARDED-FOR","Proxy-Client-IP","WL-Proxy-Client-IP","HTTP_CLIENT_IP","HTTP_X_FORWARDED_FOR"};
		
		for( String header : headers ) {
			ip = request.getHeader(header);
			if(ip != null) return ip; 
		}

		if (ip == null)  ip = request.getRemoteAddr();
		
		return ip;

	}
    


	
}