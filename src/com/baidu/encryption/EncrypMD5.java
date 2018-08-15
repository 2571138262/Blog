package com.baidu.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypMD5 {

	public static byte[] eccrypt(String info) throws NoSuchAlgorithmException {
		// ����MD5�㷨����MessageDigest����
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		// ʹ��srcBytes����ժҪ
		md5.update(srcBytes);
		// ��ɹ�ϣ���㣬�õ�result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	public static void main(String args[]) throws NoSuchAlgorithmException {
		String msg = "��XX-��Ʒ��������";
		EncrypMD5 md5 = new EncrypMD5();
		byte[] resultBytes = md5.eccrypt(msg);

		System.out.println("�����ǣ�" + new String(resultBytes));
		System.out.println("�����ǣ�" + msg);
	}

}
