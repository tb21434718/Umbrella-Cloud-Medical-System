package Keys;

import javax.crypto.*;
import java.math.BigInteger;
import java.security.*;
import java.util.Random;
import javax.swing.*;

public class Encrypt {
	public static BigInteger encryptionPlus(Key k, Integer data) {
		BigInteger r = new BigInteger(64, new Random());
		BigInteger m = new BigInteger(data.toString());
		return k.g.modPow(m, k.n.pow(2)).multiply(r.modPow(k.n, k.n.pow(2))).mod(k.n.pow(2));
	}

	public static BigInteger encryptionMulti(Key k, Integer data) {
		BigInteger m = new BigInteger(data.toString());
		return m.modPow(k.e, k.n);
	}

	public static BigInteger decryptionPlus(Key k, BigInteger code) {
		BigInteger nsquare = k.n.pow(2);
		BigInteger u = k.g.modPow(k.lambda, nsquare).subtract(BigInteger.ONE).divide(k.n).modInverse(k.n);
		return code.modPow(k.lambda, nsquare).subtract(BigInteger.ONE).divide(k.n).multiply(u).mod(k.n);
	}

	public static BigInteger decryptionMulti(Key k, BigInteger code) {
		return code.modPow(k.d, k.n);
	}

	public static BigInteger add(BigInteger a, BigInteger b) {
		return a.multiply(b);
	}

	public static BigInteger multi(BigInteger a, BigInteger b) {
		return a.multiply(b);
	}

}
