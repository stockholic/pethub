package kr.pethub.webapp.admin.site.service;

import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.core.utils.SysUtil;

@Service
@SuppressWarnings("restriction")
public class WasInfoService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private JMXConnector jmxConnector;
	
	/**
	 * CPU 사용량
	 * @return
	 */
	public Map<String,Object>  getCpuInfo() {
		
		String value = "0";
		Map<String,Object> map = new HashMap<String, Object>();
		
		
		try {

			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=OperatingSystem");
			
//			logger.info("ProcessCpuLoad : " + mBeanServerConnection.getAttribute(objectName,	"ProcessCpuLoad"));
			
			value = String.format("%.1f", (Double)mBeanServerConnection.getAttribute(objectName,"ProcessCpuLoad") * 100 );
			
			map.put("cpu",value);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	     
	     return map;
	}
	 
	/**
	 * 메모리 사용량
	 * @return
	 */
	public List<Map<String,Object>> getMemIfo() {
		
		List<Map<String,Object>> list = new  ArrayList<Map<String,Object>>();
		
		try {
			
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=Memory");
			MemoryUsage memoryUsage = MemoryUsage.from((CompositeData) mBeanServerConnection.getAttribute(objectName,	"HeapMemoryUsage"));
			
//			logger.info("HeapMemoryUsage-init : \t"+ SysUtil.getFileSize(memoryUsage.getInit()));
//			logger.info("HeapMemoryUsage-max : \t"+ SysUtil.getFileSize(memoryUsage.getMax()));
//			logger.info("HeapMemoryUsage-used : \t"+ SysUtil.getFileSize(memoryUsage.getUsed()));
//			logger.info("HeapMemoryUsage-committed = "+ SysUtil.getFileSize(memoryUsage.getCommitted()));
			
			Map<String,Object> map1 = new HashMap<String, Object>();
			map1.put("Init", memoryUsage.getInit());
			map1.put("Max", memoryUsage.getMax());
			map1.put("Used", memoryUsage.getUsed());
			map1.put("Committed", memoryUsage.getCommitted());
			list.add(map1);
			
			Map<String,Object> map2 = new HashMap<String, Object>();
			map2.put("Init", SysUtil.getFileSize(memoryUsage.getInit()));
			map2.put("Max", SysUtil.getFileSize(memoryUsage.getMax()));
			map2.put("Used", SysUtil.getFileSize(memoryUsage.getUsed()));
			map2.put("Committed", SysUtil.getFileSize(memoryUsage.getCommitted()));
			list.add(map2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	     
	}
	
	/**
	 * JDBC 커넥션
	 * @return
	 */
	public Map<String,Object> getJdbcPool() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("kr.pethub.core.configuration.beans:type=DBCP");
//			
//			logger.info("MaxTotal : " + mBeanServerConnection.getAttribute(objectName,	"MaxTotal"));
//			logger.info("NumActive : " +mBeanServerConnection.getAttribute(objectName,	"NumActive"));
//			logger.info("NumIdle : " +mBeanServerConnection.getAttribute(objectName,	"NumIdle"));
			
			map.put("MaxTotal", mBeanServerConnection.getAttribute(objectName,"MaxTotal"));
			map.put("NumActive", mBeanServerConnection.getAttribute(objectName,"NumActive"));
			map.put("NumIdle", mBeanServerConnection.getAttribute(objectName,	"NumIdle"));


		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	     
	}
	
	/**
	 * 쓰레드
	 * @return
	 */
	public Map<String,Object> getThread() {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=Threading");
			
			
//			logger.info("PeakThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"PeakThreadCount"));
//			logger.info("ThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"ThreadCount"));
//			logger.info("TotalStartedThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"TotalStartedThreadCount"));
			
			
			map.put("PeakThreadCount", mBeanServerConnection.getAttribute(objectName,"PeakThreadCount"));
			map.put("ThreadCount", mBeanServerConnection.getAttribute(objectName,"ThreadCount"));
			map.put("TotalStartedThreadCount", mBeanServerConnection.getAttribute(objectName,	"TotalStartedThreadCount"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
		
	}
	
}
