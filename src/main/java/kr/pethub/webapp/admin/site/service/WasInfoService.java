package kr.pethub.webapp.admin.site.service;

import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.core.utils.SysUtil;

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
	
	public Map<String,Object> getMemIfo() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=Memory");
			MemoryUsage memoryUsage = MemoryUsage.from((CompositeData) mBeanServerConnection.getAttribute(objectName,	"HeapMemoryUsage"));
			
//			System.out.println("HeapMemoryUsage-init : \t"+ SysUtil.getFileSize(memoryUsage.getInit()));
//			System.out.println("HeapMemoryUsage-max : \t"+ SysUtil.getFileSize(memoryUsage.getMax()));
//			System.out.println("HeapMemoryUsage-used : \t"+ SysUtil.getFileSize(memoryUsage.getUsed()));
//			System.out.println("HeapMemoryUsage-committed = "+ SysUtil.getFileSize(memoryUsage.getCommitted()));
			
			map.put("INIT", SysUtil.getFileSize(memoryUsage.getInit()));
			map.put("MAX", SysUtil.getFileSize(memoryUsage.getMax()));
			map.put("USED", SysUtil.getFileSize(memoryUsage.getUsed()));
			map.put("COMMITTED", SysUtil.getFileSize(memoryUsage.getCommitted()));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	     
	}
	
	
	public Map<String,Object> getJdbcPool() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("kr.pethub.core.configuration.beans:type=DBCP");
//			
//			System.out.println("MaxTotal : " + mBeanServerConnection.getAttribute(objectName,	"MaxTotal"));
//			System.out.println("NumActive : " +mBeanServerConnection.getAttribute(objectName,	"NumActive"));
//			System.out.println("NumIdle : " +mBeanServerConnection.getAttribute(objectName,	"NumIdle"));
			
			map.put("MaxTotal", mBeanServerConnection.getAttribute(objectName,"MaxTotal"));
			map.put("NumActive", mBeanServerConnection.getAttribute(objectName,"NumActive"));
			map.put("NumIdle", mBeanServerConnection.getAttribute(objectName,	"NumIdle"));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	     
	}
	
	public Map<String,Object> getThread() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=Threading");
			
			
//			System.out.println("PeakThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"PeakThreadCount"));
//			System.out.println("ThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"ThreadCount"));
//			System.out.println("TotalStartedThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"TotalStartedThreadCount"));
			
			
			map.put("PeakThreadCount", mBeanServerConnection.getAttribute(objectName,"PeakThreadCount"));
			map.put("ThreadCount", mBeanServerConnection.getAttribute(objectName,"ThreadCount"));
			map.put("TotalStartedThreadCount", mBeanServerConnection.getAttribute(objectName,	"TotalStartedThreadCount"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
		
	}
	
}
