package com.test;

public class TestPver extends TestBase {
	StringBuilder pverReport;

	public TestPver(String[] args) {
		super(args);
	}

	public StringBuilder runPverTest() throws InterruptedException {

		pverReport = new StringBuilder();

		Runnable checkoutPver = () -> {
			pverReport.append(testCheckoutPver());
		};

		Runnable mdgbPver = () -> {
			pverReport.append(testMDGBPver());
		};
		Thread t1 = new Thread(checkoutPver);
		Thread t2 = new Thread(mdgbPver);

		startAndWaitAllThreadToComplete(t1, t2);
		return pverReport;
	}

	public String testCheckoutPver() {
		System.out.println(rtcAgent.getResult(""));
		return "Pver test";

	}

	public String testMDGBPver() {
		System.out.println(rtcAgent.getResult(""));

		return "MDGB Pver test";

	}
}
