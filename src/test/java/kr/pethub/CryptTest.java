package kr.pethub;

import java.util.UUID;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptTest extends BaseTestCase{
	
	
	@Test
	public void bCrypt() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		String password = "1111";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		logger.debug(hashedPassword);
		logger.debug("UUID : {}",UUID.randomUUID());
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
}
