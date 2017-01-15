package com.test.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Credential {
	public static void main(String[] args) throws Exception {
		String plainText = "Hello world!";

		String cipherText = encrypt(plainText);
		String decryptedCipherText = decrypt(cipherText);

		System.out.println(plainText);
		System.out.println(cipherText);
		System.out.println(decryptedCipherText);
	}

	public static String encrypt(String plainText) {
		Cipher cipher;
		byte[] encryptedBytes = null;
		try {
			cipher = getCipher(Cipher.ENCRYPT_MODE);
			encryptedBytes = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Base64.encodeBase64String(encryptedBytes);
	}

	public static String decrypt(String encrypted) {
		Cipher cipher;
		byte[] plainBytes = null;
		try {
			cipher = getCipher(Cipher.DECRYPT_MODE);
			plainBytes = cipher.doFinal(Base64.decodeBase64(encrypted));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new String(plainBytes);
	}

	private static Cipher getCipher(int cipherMode) throws Exception

	{
		String encryptionKey = "MZygpewJsCpRrfOr";
		String encryptionAlgorithm = "AES";
		SecretKeySpec keySpecification = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), encryptionAlgorithm);
		Cipher cipher = Cipher.getInstance(encryptionAlgorithm);
		cipher.init(cipherMode, keySpecification);

		return cipher;
	}
}
