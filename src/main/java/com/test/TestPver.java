package com.test;

public class TestPver extends TestBase {
	StringBuilder pverReport;

	public TestPver(String[] args) throws Exception {
		super(args);
	}

	public static void main(String[] args) throws Exception {
		new TestPver(args).runPverTest();
	}

	public StringBuilder runPverTest() throws InterruptedException {

		pverReport = new StringBuilder();

		Runnable checkoutPver = () -> {
			pverReport.append(testCheckoutPver());
		};

		Runnable mdgbPver = () -> {
			pverReport.append(testMDGBPver());
		};
		Thread t1 = new Thread(checkoutPver,"checkoutPver");
		Thread t2 = new Thread(mdgbPver,"mdgbPver");

		startAndWaitAllThreadToComplete(t1, t2);
		return pverReport;
	}

	public String testCheckoutPver() {
		System.out.println(rtcAgent.getResult(""));
		return "Pver test";

	}

	public String testMDGBPver() {
		System.out.println(rtcAgent.getResult(""));

		System.out.println(artifactoryClient.downloadFile("http://localhost:8081/artifactory/ext-release-local/path/to/newName.txt"));
		
		return "MDGB Pver test";

	}
}
