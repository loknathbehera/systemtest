package com.test;

public class TestPFAM extends TestBase {
	StringBuilder pfamReport;

	public TestPFAM(String[] args) throws Exception {
		super(args);
	}

	public static void main(String[] args) throws Exception {
		logger.info("PFAM Test Start ");
		new TestPFAM(args).runPFAMTest();
	}

	public StringBuilder runPFAMTest() throws InterruptedException {

		pfamReport = new StringBuilder();

		Runnable checkoutPFAM = () -> {
			pfamReport.append(testCheckoutPFAM());
		};

		Runnable mdgbPFAM = () -> {
			pfamReport.append(testMDGBPFAM());
		};
		Thread t1 = new Thread(checkoutPFAM,"checkoutPFAM");
		Thread t2 = new Thread(mdgbPFAM,"mdgbPFAM");
		startAndWaitAllThreadToComplete(t1, t2);

		return pfamReport;
	}

	public String testCheckoutPFAM() {
		System.out.println(rtcAgent.getResult(""));
		return "PFAM test";

	}

	public String testMDGBPFAM() {
		System.out.println(rtcAgent.isCompleted(""));
		System.out.println(jenkinsScraper.scrape("http://localhost:8080/job/PipleLineTest/38/wfapi/"));
		return "PFAM Pver test";

	}
}
