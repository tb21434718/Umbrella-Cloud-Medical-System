package Keys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;


public class CloudCompute {
	public static int Count(File file) throws Exception {


		ArrayList<String> dataList = new ArrayList<String>(10000);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String s = null;
		while ((s = bf.readLine()) != null)
			dataList.add(s);
		bf.close();

		return dataList.size() / 2;
	}

	public static BigInteger Add(File file) throws Exception {

		ArrayList<String> dataList = new ArrayList<String>(10000);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String s = null;
		while ((s = bf.readLine()) != null)
			dataList.add(s);
		bf.close();
		ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();
		for (int i = 0; i < dataList.size(); i = i + 2) {
			String t = dataList.get(i);
			BigInteger data = new BigInteger(t);
			numbers.add(data);
		}

		BigInteger sum = numbers.get(0);
		for (int i = 1; i < numbers.size(); i++) {
			BigInteger data1 = numbers.get(i);
			sum = Encrypt.add(sum, data1);
		}
		return sum;
	}

	public static BigInteger Multi(File file) throws Exception {

		ArrayList<String> dataList = new ArrayList<String>(10000);
		BufferedReader bf = new BufferedReader(new FileReader(file));
		String s = null;
		while ((s = bf.readLine()) != null)
			dataList.add(s);
		bf.close();
		ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();
		for (int i = 1; i < dataList.size(); i = i + 2) {
			String t = dataList.get(i);
			BigInteger data = new BigInteger(t);
			numbers.add(data);
		}

		BigInteger multi = numbers.get(0);
		for (int i = 1; i < numbers.size(); i++) {

			BigInteger data1 = numbers.get(i);
			multi = Encrypt.multi(multi, data1);
		}
		return multi;
	}

}
