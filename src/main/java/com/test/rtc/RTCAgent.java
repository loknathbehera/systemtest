package com.test.rtc;

public class RTCAgent {

	private static RTCAgent instance;

	public static synchronized RTCAgent getInstance(String repoUrl, String userId, String password) {

		if (instance == null) {
			System.out.println("Creating RTCAgent");
			instance = new RTCAgent();
		}
		return instance;

	}

	private RTCAgent() {
	}

	public synchronized String getResult(String uuid) {
		return "Result got for " + uuid;
	}

	public synchronized boolean isCompleted(String uuid) {
		return true;
	}

}
