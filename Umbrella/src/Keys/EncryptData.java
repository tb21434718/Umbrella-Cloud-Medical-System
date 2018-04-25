package Keys;

import java.math.BigInteger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class EncryptData {

	// 乘法加密
	public static String EncryptMulti(Key k, String t) {
		Integer data = Integer.valueOf(t);
		return Encrypt.encryptionMulti(k, data).toString();
	}

	// 加法加密
	public static String EncryptPlus(Key k, String t) {
		Integer data = Integer.valueOf(t);
		return Encrypt.encryptionPlus(k, data).toString();
	}

	// AES加密
	public static String EncryptAes(SecretKeySpec k, String t) throws Exception {
		// 创建密码器
		Cipher cipher = Cipher.getInstance("AES");
		// 初始化为加密模式的密码器
		cipher.init(Cipher.ENCRYPT_MODE, k);
		byte[] result = cipher.doFinal(t.getBytes());
		String string = Util.BytesToString(result);

		return string;
	}

}
