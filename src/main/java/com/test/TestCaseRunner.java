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
					logger.error("ERROR :{}" + e);
				}
			};

			Runnable pverTest = () -> {

				try {
					finalReport.append(new TestPver(args).runPverTest());
				} catch (Exception e) {
					logger.error("ERROR :{}" + e);
				}
			};

			Runnable fcTest = () -> {
				try {
					finalReport.append(new TestFC(args).runFCTest());
				} catch (Exception e) {
					logger.error("ERROR :{}" + e);
				}
			};

			Thread t1 = new Thread(pfamTest,"PFAM TEST");
			Thread t2 = new Thread(pverTest,"PVER TEST");
			Thread t3 = new Thread(fcTest,"FC TEST");

			startAndWaitAllThreadToComplete(t1, t2, t3);

		} catch (Exception e2) {
			// TODO: handle exception
		} finally {
			logger.info("ALL TEST COMPLETED ");
			logger.info("REPORT GENERRATE : {}", finalReport);
			System.out.println("###FINAL REPORT#####");
			System.out.println(finalReport);
			System.out.println("###FINAL REPORT END#####");
		}

	}

}
