package Keys;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class Util {
	private static final int ERR_VAL = 10;

public static String BytesToString(byte[] bytes) {
		
		String result ="";
		for(int i=0;i<bytes.length;i++)
		{
			int temp = bytes[i] & 0xff;
			String tempHex = Integer.toHexString(temp);
			
			if(tempHex.length()<2)
				result += "0"+tempHex;
			
			else result += tempHex;
		}
		
		
		return result;
		
		
	}
	
	public static byte[] StringToBytes(String hexString) {  
	    if (hexString == null || hexString.equals("")) {  
	        return null;  
	    }  
	    hexString = hexString.toUpperCase();  
	    int length = hexString.length() / 2;  
	    char[] hexChars = hexString.toCharArray();  
	    byte[] d = new byte[length];  
	    for (int i = 0; i < length; i++) {  
	        int pos = i * 2;  
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
	    }  
	    return d;  
	} 
	
	private static byte charToByte(char c) {  
	    return (byte) "0123456789ABCDEF".indexOf(c);  
	}
	
	
	public static BigInteger GCD(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO))
			return a;
		else
			return GCD(b, a.mod(b));
	}

	public static BigInteger LCM(BigInteger a, BigInteger b) {
		return a.multiply(b).divide(GCD(a, b));
	}

	public static BigInteger PrimeGenerate(int num) {
		BigInteger start = BigInteger.probablePrime(num, new Random());
		while (!start.isProbablePrime(ERR_VAL)) { // Miller-Rabin Algorithm
			start = BigInteger.probablePrime(num, new Random());

		}
		return start;
	}

}
