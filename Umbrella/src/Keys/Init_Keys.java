package Keys;



import java.io.Serializable;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Init_Keys implements Serializable{

//	private static final long serialVersionUID= -4912521145290965808l;
	private  int userID;
	private  int userPwd;
	private  Key key1;
	private  SecretKeySpec key2;


	public Init_Keys(int Pwd) throws Exception {

		 userID = 0;//身份鉴别码
		 userPwd = Pwd;//用户识别码



		// 全同态加密算法密钥生成
		long seed = (long) (Math.random() * 1000000000);
		 key1 = new Key(64);

		// 创建AES的Key
		KeyGenerator kGenerator = KeyGenerator.getInstance("AES");
		// 128位的key
		kGenerator.init(128);
		// 生成一个密钥
		SecretKey secretKey = kGenerator.generateKey();
		// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
		byte[] enCodeFormat = secretKey.getEncoded();
		// 转换为AES专用密钥
		 key2 = new SecretKeySpec(enCodeFormat, "AES");

		// 创建密码器
		Cipher cipher = Cipher.getInstance("AES");

		// 初始化为加密模式的密码器
		cipher.init(Cipher.ENCRYPT_MODE, key2);
	}

	public  int getID() {
		return userID;
	}

	public  int getPwd() {
		return userPwd;
	}

	public  Key getKey() {
		return key1;
	}

	public  SecretKeySpec getKeyAes() {
		return key2;
	}

}
