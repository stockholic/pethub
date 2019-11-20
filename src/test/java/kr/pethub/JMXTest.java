package kr.pethub;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.management.OperatingSystemMXBean;

public class JMXTest extends BaseTestCase{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JMXConnector jmxConnector;

	@Test
	public void 메모리_상태보기() throws IOException {
		
		/** 
		 * https://www.lesstif.com/pages/viewpage.action?pageId=20776824
		 * server.xml : <Listener className="org.apache.catalina.mbeans.JmxRemoteLifecycleListener" rmiRegistryPortPlatform="8800" rmiServerPortPlatform="8810"/>
		 *  -Dcom.sun.management.jmxremote  -Dcom.sun.management.jmxremote.authenticate=false -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.ssl=false -Xms128m -Xmx256m
		 */

		logger.debug("-------------------------------------------------------------------------------> start");
		
		
		try {
			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=Memory");
			
			System.out.println("------------------------------------------------- MemoryMBean 정보 출력 Start");
			MemoryUsage memoryUsage = MemoryUsage.from((CompositeData) mBeanServerConnection.getAttribute(objectName,	"HeapMemoryUsage"));
			System.out.println("MemoryMBean 정보 출력:");
			System.out.println("> HeapMemoryUsage-init = "+ getFileSize(memoryUsage.getInit()));
			System.out.println("> HeapMemoryUsage-max = "+ getFileSize(memoryUsage.getMax()));
			System.out.println("> HeapMemoryUsage-used = "+ getFileSize(memoryUsage.getUsed()));
			System.out.println("> HeapMemoryUsage-committed = "+ getFileSize(memoryUsage.getCommitted()));

			memoryUsage = MemoryUsage.from((CompositeData) mBeanServerConnection.getAttribute(objectName,"NonHeapMemoryUsage"));
			System.out.println("\n> NonHeapMemoryUsage-init = "+ getFileSize(memoryUsage.getInit()));
			System.out.println("> NonHeapMemoryUsage-max = "+ getFileSize(memoryUsage.getMax()));
			System.out.println("> NonHeapMemoryUsage-used = "+ getFileSize(memoryUsage.getUsed()));
			System.out.println("> NonHeapMemoryUsage-committed = "+ getFileSize(memoryUsage.getCommitted()));
			
			System.out.println("------------------------------------------------- MemoryMBean 정보 출력 End");

			// Query
			objectName = new ObjectName("java.lang:type=MemoryPool,*");
			Set<?> pools_ = mBeanServerConnection.queryNames(null, objectName);
			Iterator<?> itr_ = pools_.iterator();

			while (itr_.hasNext()) {
				Object obj = itr_.next();
				ObjectName objName = (ObjectName) obj;

				// Print Memory Pool Usage
				System.out.println("----------------------------------------------");
				System.out.println(mBeanServerConnection.getAttribute(objName, "Name") + " Pool 정보 출력:");
				System.out.println("Memory Type = "+ mBeanServerConnection.getAttribute(objName, "Type"));

				System.out.println("Memory Peak Usage:");
				memoryUsage = MemoryUsage.from((CompositeData) mBeanServerConnection.getAttribute(objName, "PeakUsage"));
				System.out.println("> MemoryUsage-init = "+ memoryUsage.getInit() / (1024*1024) + " M");
				System.out.println("> MemoryUsage-max = "+ memoryUsage.getMax() / (1024*1024) + " M");
				System.out.println("> MemoryUsage-used = "+ memoryUsage.getUsed() / (1024*1024) + " M");
				System.out.println("> MemoryUsage-committed = "+ memoryUsage.getCommitted() / (1024*1024) + " M");

				System.out.println("\nMemory Current Usage:");
				memoryUsage = MemoryUsage.from((CompositeData) mBeanServerConnection.getAttribute(objName, "Usage"));
				System.out.println("> MemoryUsage-init = "+ memoryUsage.getInit() / (1024*1024) + " M");
				System.out.println("> MemoryUsage-max = "+ memoryUsage.getMax() / (1024*1024) + " M");
				System.out.println("> MemoryUsage-used = "+ memoryUsage.getUsed() / (1024*1024) + " M");
				System.out.println("> MemoryUsage-committed = "+ memoryUsage.getCommitted() / (1024*1024) + " M");
			}
		} catch (Exception e) {
			e.printStackTrace();
		 }finally {
	       	jmxConnector.close();
		}
		
		logger.debug("-------------------------------------------------------------------------------> end");
		
	}
	
	public String getFileSize(Long size){	
		 if(size <= 0) return "0";
		    final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
		    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
	@Test
	public void JDBC_상태보기() throws IOException {

		logger.debug("-------------------------------------------------------------------------------> start");
		
	        try {

				MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
				ObjectName objectName = new ObjectName("kr.pethub.core.configuration.beans:type=DBCP");
				
				
				System.out.println("MaxTotal : " + mBeanServerConnection.getAttribute(objectName,	"MaxTotal"));
				System.out.println("NumActive : " +mBeanServerConnection.getAttribute(objectName,	"NumActive"));
				System.out.println("NumIdle : " +mBeanServerConnection.getAttribute(objectName,	"NumIdle"));
				
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }finally {
	        	jmxConnector.close();
			}

		
		logger.debug("-------------------------------------------------------------------------------> end");
		
	}
	
	@Test
	public void CPU_상태보기1() throws IOException {

		logger.debug("-------------------------------------------------------------------------------> start");
		
		try {

			MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
			ObjectName objectName = new ObjectName("java.lang:type=OperatingSystem");

			
			System.out.println("ProcessCpuLoad : " + mBeanServerConnection.getAttribute(objectName,	"ProcessCpuLoad"));
			
			Double value = (Double) mBeanServerConnection.getAttribute(objectName,	"ProcessCpuLoad");
//			System.out.println(((int) (value * 1000) / 10.0));
			System.out.println(  Math.round(value*10)/10.0 );
			
			
			/*
			
			AttributeList list = mBeanServerConnection.getAttributes(objectName, new String[] { "ProcessCpuLoad" });
			
			

			if (list.isEmpty()) System.out.println(Double.NaN);

			Attribute att = (Attribute) list.get(0);
			Double value = (Double) att.getValue();

			// usually takes a couple of seconds before we get real values
			if (value == -1.0) System.out.println(Double.NaN);
			
			// returns a percentage value with 1 decimal point precision
			System.out.println("value : " + value);
			System.out.println(((int) (value * 1000) / 10.0));
		*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jmxConnector.close();
		}

		
		logger.debug("-------------------------------------------------------------------------------> end");
		
		
		
	}
	
	// initiate and prepare MXBean interfaces proxy connections
	@SuppressWarnings("restriction")
	@Test
	public void CPU_상태보기2() throws IOException {
		
		logger.debug("-------------------------------------------------------------------------------> start");
    	
    	 long previousJvmProcessCpuTime = 0;
         long previousJvmUptime = 0;
    	
    	try {
    		
	    	MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
	    	
	    	OperatingSystemMXBean peOperatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
	                mBeanServerConnection,
	                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,		//java.lang:type=OperatingSystem
	                com.sun.management.OperatingSystemMXBean.class
	        );
	    	OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.newPlatformMXBeanProxy(
	                mBeanServerConnection,
	                ManagementFactory.OPERATING_SYSTEM_MXBEAN_NAME,		//java.lang:type=OperatingSystem
	                OperatingSystemMXBean.class
	        );
	    	RuntimeMXBean runtimeMXBean = ManagementFactory.newPlatformMXBeanProxy(
	                mBeanServerConnection,
	                ManagementFactory.RUNTIME_MXBEAN_NAME,						//java.lang:type=Runtime
	                RuntimeMXBean.class
	        );
	    	
	    	
	    	 // elapsed process time is in nanoseconds
	        long elapsedProcessCpuTime = peOperatingSystemMXBean.getProcessCpuTime() - previousJvmProcessCpuTime;
	        // elapsed uptime is in milliseconds
	        long elapsedJvmUptime = runtimeMXBean.getUptime() - previousJvmUptime;

	        // total jvm uptime on all the available processors
	        long totalElapsedJvmUptime = elapsedJvmUptime * operatingSystemMXBean.getAvailableProcessors();

	        // calculate cpu usage as a percentage value
	        // to convert nanoseconds to milliseconds divide it by 1000000 and to get a percentage multiply it by 100
	        float cpuUsage = elapsedProcessCpuTime / (totalElapsedJvmUptime * 10000F);

	        // set old timestamp values
	        previousJvmProcessCpuTime = peOperatingSystemMXBean.getProcessCpuTime();
	        previousJvmUptime = runtimeMXBean.getUptime();
	        
	        System.out.println(">>>>>>>>>>>>> " + cpuUsage);
	    	
    	} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jmxConnector.close();
		}
    	
    	logger.debug("-------------------------------------------------------------------------------> end");
    }
	
	@Test
	public void THREADS_상태보기() throws IOException {

		logger.debug("-------------------------------------------------------------------------------> start");
		
	        try {

				MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();
				ObjectName objectName = new ObjectName("java.lang:type=Threading");
				
				
				System.out.println("PeakThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"PeakThreadCount"));
				System.out.println("ThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"ThreadCount"));
				System.out.println("TotalStartedThreadCount : " + mBeanServerConnection.getAttribute(objectName,	"TotalStartedThreadCount"));
				
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }finally {
	        	jmxConnector.close();
			}

		
		logger.debug("-------------------------------------------------------------------------------> end");
		
	}
	

	
			
          
	
}
