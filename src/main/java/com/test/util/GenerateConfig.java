package com.test.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;

public class GenerateConfig {
	public static void main(String[] args) {

		Properties prop = new Properties();
		OutputStream output = null;
		Scanner sc = new Scanner(System.in);

		try {

			System.out.println("Enter the UserId :");
			String userId = sc.next();

			System.out.println("Enter the password :");
			String password = sc.next();

			System.out.println("Enter the RTC Url :");
			String rtcUrl = sc.next();

			System.out.println("Enter the Jfrog Repository Url :");
			String artifactoryUrl = sc.next();

			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("userId", userId);
			prop.setProperty("password", Credential.encrypt(password));
			prop.setProperty("rtcUrl", rtcUrl);
			prop.setProperty("artifactoryUrl", artifactoryUrl);

			// save properties to project root folder
			prop.store(output, null);

			System.out.println("Config file generated in sucessfully");

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}