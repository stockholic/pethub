package kr.pethub.core.authority;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public class AuthUtil {
	
	public static String getPrincipal() {
		
		
		if(SecurityContextHolder.getContext().getAuthentication() == null){
			return "GUS";
		}
		
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        if (obj instanceof UserDetails) {
            return ((UserDetails) obj).getUsername();
        } else {
            return "GUS";
        }
    }
	
	/**
	 * 유저 정보
	 * @return
	 */
	public static Auth getUser(){
		
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(obj.equals("anonymousUser")){
			return null;
		}else{
			//암호화 전 패스워드
			//((AuthInfo)obj).getUser().setPassword(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString());
			return ((AuthInfo)obj).getUser();
		}
		
	}
	
	
    
}
