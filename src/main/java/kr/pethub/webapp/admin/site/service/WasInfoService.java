package kr.pethub.webapp.admin.site.service;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("restriction")
public class WasInfoService {
	
	
	@Autowired
	private JMXConnector jmxConnector;
	
	public String getCpuInfo() {
		
		String value = "0";
		
		try {

			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=OperatingSystem");
//			System.out.println("ProcessCpuLoad : " + mBeanServerConnection.getAttribute(objectName,	"ProcessCpuLoad"));
			value = String.format("%.1f", (Double)mBeanServerConnection.getAttribute(objectName,"ProcessCpuLoad") * 100 );
		} catch (Exception e) {
			e.printStackTrace();
		}
	     
	     return value;
	}
	
}
