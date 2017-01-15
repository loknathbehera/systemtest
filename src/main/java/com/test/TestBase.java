package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.client.JfrogArtifactoryClient;
import com.test.jenkins.JenkinsScraper;
import com.test.rtc.RTCAgent;
import com.test.util.Credential;

public class TestBase {

	static Logger logger = LoggerFactory.getLogger(TestBase.class);

	protected RTCAgent rtcAgent;
	protected JenkinsScraper jenkinsScraper;
	protected JfrogArtifactoryClient artifactoryClient;
	protected StringBuilder finalReport;
	protected StringBuilder pverReport;
	protected StringBuilder fcReport;
	protected StringBuilder pfamReport;
	Map<String, TemplateClass> cachedData;
	Properties properties;
	FileInputStream inStream;

	public TestBase() {
		finalReport = new StringBuilder();
		pfamReport = new StringBuilder();
		pverReport = new StringBuilder();
		fcReport = new StringBuilder();
	}

	public TestBase(String[] args) throws Exception {

		properties = new Properties();

		try {
			inStream = new FileInputStream(args[0]);
			properties.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String userName = properties.getProperty("userId");
		String password = Credential.decrypt(properties.getProperty("password"));
		String rtcUrl = properties.getProperty("rtcUrl");
		String artifactoryUrl = properties.getProperty("artifactoryUrl");

		if (userName == null || password == null || rtcUrl == null) {
			System.out.println("Create the connfig.properties file \n java -cp systemtest-1.0-jar-with-dependencies.jar com.util.GenerateConfig");
			throw new Exception("Configure the the config file");
		}

		rtcAgent = RTCAgent.getInstance(rtcUrl, userName, password);
		jenkinsScraper = new JenkinsScraper(userName, password);
		artifactoryClient=JfrogArtifactoryClient.getInstance(artifactoryUrl, userName, password);
	}

	public void startThreads(Thread... threads) {
		for (Thread thread : threads) {
			logger.info("{} Start......", thread.getName());
			thread.start();
		}
	}

	public void waitAllThreadToComplete(Thread... threads) {
		for (Thread thread : threads) {
			try {
				logger.info("{} waiting to complete......", thread.getName());
				thread.join();
			} catch (InterruptedException e) {
				logger.error("ERROR :{}" + e);
			}
		}
	}

	public void startAndWaitAllThreadToComplete(Thread... threads) {
		startThreads(threads);
		waitAllThreadToComplete(threads);
	}
}
