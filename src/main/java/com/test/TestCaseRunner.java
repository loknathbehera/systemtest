package com.test;

public class TestCaseRunner extends TestBase {

	public static void main(String[] args) throws Exception {

		logger.info("Start of system test");

		new TestCaseRunner().runTestClasses(args);
	}

	private void runTestClasses(String[] args) {
		try {

			Runnable pfamTest = () -> {
				try {
					finalReport.append(new TestPFAM(args).runPFAMTest());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

			Runnable pverTest = () -> {

				try {
					finalReport.append(new TestPver(args).runPverTest());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

			Runnable fcTest = () -> {
				try {
					finalReport.append(new TestFC(args).runFCTest());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

			Thread t1 = new Thread(pfamTest);
			Thread t2 = new Thread(pverTest);
			Thread t3 = new Thread(fcTest);

			startAndWaitAllThreadToComplete(t1, t2, t3);

		} catch (Exception e2) {
			// TODO: handle exception
		} finally {
			System.out.println("Finally Executed");
			System.out.println(finalReport);
		}

	}

}
