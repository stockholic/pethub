package kr.pethub;

import org.junit.AfterClass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.pethub.configuration.AnnotationConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AnnotationConfiguration.class)
public class BaseTestCase extends Assert {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ApplicationContext context;
	
	@AfterClass
	public static void testDown() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void dummy() {
	}
}
