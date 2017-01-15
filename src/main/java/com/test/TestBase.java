package com.test;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.test.jenkins.JenkinsScraper;
import com.test.rtc.RTCAgent;

public class TestBase {

	static Logger logger = LoggerFactory.getLogger(TestBase.class);

	protected RTCAgent rtcAgent;
	protected JenkinsScraper jenkinsScraper;
	protected StringBuilder finalReport;
	protected StringBuilder pverReport;
	protected StringBuilder fcReport;
	protected StringBuilder pfamReport;
	Map<String, TemplateClass> cachedData;

	public TestBase() {
		finalReport = new StringBuilder();
		pfamReport = new StringBuilder();
		pverReport = new StringBuilder();
		fcReport = new StringBuilder();
	}

	public TestBase(String[] args) {
		String userName = args[0];
		String password = args[1];
		String rtcUrl = args[2];

		rtcAgent = RTCAgent.getInstance(rtcUrl, userName, password);
		jenkinsScraper = new JenkinsScraper(userName, password);
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
