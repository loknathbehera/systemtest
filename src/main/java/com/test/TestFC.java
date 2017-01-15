package com.test;

public class TestFC extends TestBase {
	StringBuilder fcReport;

	public TestFC(String[] args) throws Exception {
		super(args);
	}

	public static void main(String[] args) throws Exception {
		new TestFC(args).runFCTest();
	}

	public StringBuilder runFCTest() throws InterruptedException {

		fcReport = new StringBuilder();

		Runnable checkoutFC = () -> {
			fcReport.append(testCheckoutFC());
		};

		Runnable mdgbFC = () -> {
			fcReport.append(testMDGBFC());
		};
		Thread t1 = new Thread(checkoutFC,"checkoutFC");
		Thread t2 = new Thread(mdgbFC,"mdgbFC");
		startAndWaitAllThreadToComplete(t1, t2);

		return fcReport;
	}

	public String testCheckoutFC() {
		System.out.println(rtcAgent.isCompleted(""));

		System.out.println(jenkinsScraper.scrape("http://localhost:8080/job/PipleLineTest/38/wfapi/"));

		return "FC test";

	}

	public String testMDGBFC() {
		System.out.println(jenkinsScraper.scrape("http://localhost:8080/job/PipleLineTest/38/wfapi/"));
		System.out.println(rtcAgent.getResult(""));
		return "FC Pver test";

	}
}
