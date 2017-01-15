package com.test.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.jfrog.artifactory.client.Artifactory;
import org.jfrog.artifactory.client.ArtifactoryClient;

public class JfrogArtifactoryClient {

	private Artifactory artifactory;
	static JfrogArtifactoryClient artifactoryClient;

	public static synchronized JfrogArtifactoryClient getInstance(String artifactoryUrl, String userId,
			String password) {

		if (artifactoryClient == null) {
			System.out.println("Creating RTCAgent");
			Artifactory artifactory = ArtifactoryClient.create(artifactoryUrl, "admin", "password");
			artifactoryClient = new JfrogArtifactoryClient(artifactory);
		}
		return artifactoryClient;

	}

	private JfrogArtifactoryClient(Artifactory artifactory) {
		this.artifactory = artifactory;
	}

	public String downloadFile(String fileLocation) {

		String urlContext = artifactory.getUri() + "/" + artifactory.getContextName() + "/";

		String tmpUrl = fileLocation.replace(urlContext, "");
		String repository = tmpUrl.substring(0, tmpUrl.indexOf("/"));
		String pathToFile = tmpUrl.replaceAll(repository + "/", "");

		InputStream iStream = artifactory.repository(repository).download(pathToFile).doDownload();

		@SuppressWarnings("resource")
		String fileContains = new Scanner(iStream, "UTF-8").useDelimiter("\\A").next();

		return fileContains;

	}

	public static void main(String[] args) throws IOException {
		Artifactory artifactory = ArtifactoryClient.create("http://localhost:8081/artifactory", "admin", "password");

		JfrogArtifactoryClient asd = new JfrogArtifactoryClient(artifactory);

		String ad=asd.downloadFile("http://localhost:8081/artifactory/ext-release-local/path/to/newName.txt");

		System.out.println(ad);

		 ad=asd.downloadFile("http://localhost:8081/artifactory/ext-release-local/path/to/newName.txt");

		System.out.println(ad);

		
		 ad=asd.downloadFile("http://localhost:8081/artifactory/ext-release-local/path/to/newName.txt");

		System.out.println(ad);

		
		 ad=asd.downloadFile("http://localhost:8081/artifactory/ext-release-local/path/to/newName.txt");

		System.out.println(ad);

		// InputStream iStream =
		// artifactory.repository("ext-release-local").download("path/to/newName.txt").doDownload();
		//
		// @SuppressWarnings("resource")
		// String inputStreamString = new Scanner(iStream,
		// "UTF-8").useDelimiter("\\A").next();
		//
		// System.out.println(inputStreamString);

	}
}
