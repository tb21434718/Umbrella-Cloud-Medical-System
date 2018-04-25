package Keys;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class DecryptData {
	// 乘法解密
	public static String DecryptMulti(Key k, String t) {
		BigInteger data = new BigInteger(t);
		return Encrypt.decryptionMulti(k, data).toString();
	}

	// 加法解密
	public static String DecryptPlus(Key k, String t) {
		BigInteger data = new BigInteger(t);
		return Encrypt.decryptionPlus(k, data).toString();
	}

	// AES解密
	public static String DecryptAes(SecretKeySpec k, String t) throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance("AES");
		// 初始化为解密模式的密码器
		cipher.init(cipher.DECRYPT_MODE, k);
		byte[] bytes = Util.StringToBytes(t);
		byte[] result = cipher.doFinal(bytes);

		String string = new String(result);

		return string;
	}



}
