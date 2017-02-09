import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import dataGenerator.Constants;

public class Apriori {

	private static boolean mVerboseMode = false;
	private static int mSupportThresholdFirstPass = Constants.SUPPORT_THRESHOLD_FIRST_PASS;
	private static int mNoOfTransactions = Constants.NO_OF_ITEM_SETS;
	private static int mSupportThresholdSecondPass = Constants.SUPPORT_THRESHOLD_SECOND_PASS;
	private static String FileName = Constants.FILENAME;

	public static HashMap<String, Integer> permutation(HashMap<String, Integer> firstPassResult) {

		HashMap<String, Integer> twoStringInput = new HashMap<String, Integer>();
		String belowThreshold = "";

		// checking for below threshold before generating 2 - item itemsets
		for (String s : Constants.elementArray) {

			if (firstPassResult.get(s) > mSupportThresholdFirstPass) {
				// System.out.println(firstPassResult.get(s));
				belowThreshold += s;
			}
		}

		System.out.print("Items Passing the First Pass : ");
		System.out.println(belowThreshold + "");

		for (int i = 0; i < belowThreshold.length(); i++) {
			for (int j = i + 1; j < belowThreshold.length(); j++) {
				// System.out.println(input.charAt(i)+ "" + input.charAt(j));
				twoStringInput.put(belowThreshold.charAt(i) + "" + belowThreshold.charAt(j), 0);

			}
		}

		// System.out.println(twoStringInput);
		return twoStringInput;

	}

	public static void main(String args[]) {

		if (args.length != 0) {

			if (args[0].equals("-v")) {
				System.out.println("running in verbose mode");
				mVerboseMode = true;
			}

			for (int i = 0; i < args.length; i++) {
				// System.out.println(":"+args[i] + ":");

				if (args[i].equals("-n")) {
					mNoOfTransactions = Integer.parseInt(args[i + 1]);
				} else if (args[i].equals("-s1")) {
					mSupportThresholdFirstPass = Integer.parseInt(args[i + 1]);
				} else if (args[i].equals("-s2")) {
					mSupportThresholdSecondPass = Integer.parseInt(args[i + 1]);
				} else if (args[i].equals("-f")) {
					FileName = args[i+1];
				}

			}
		}

		System.out.println("APRIORI ALGORITHM...");
		System.out.println("The Algorith is running on " + mNoOfTransactions + " transactions");
		System.out.println("The support threshold for the first pass is " + mSupportThresholdFirstPass);
		System.out.println("The support threshold for the second pass is " + mSupportThresholdSecondPass);

		BufferedReader br = null;
		FileReader fr = null;

		String[] firstPassArray = Constants.elementArray;

		HashMap<String, Integer> hmapFirstPass = new HashMap<String, Integer>();
		for (int i = 0; i < firstPassArray.length; i++) {
			hmapFirstPass.put(firstPassArray[i], 0);
		}

		try {
			fr = new FileReader(FileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);

		String transactionString;

		int noOfTransactions = 0;

		try {

			if (mVerboseMode)
				System.out.println("Transactions Being Used are : ");

			while ((transactionString = br.readLine()) != null && noOfTransactions < mNoOfTransactions) {

				noOfTransactions++;

				if (mVerboseMode) {
					System.out.print("<" + noOfTransactions + ", {");
					System.out.println(transactionString + "}>");
				}

				for (int i = 0; i < firstPassArray.length; i++) {

					if (transactionString.contains(firstPassArray[i])) {

						hmapFirstPass.put(firstPassArray[i], hmapFirstPass.get(firstPassArray[i]) + 1);

					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (mVerboseMode)
			System.out.println("confidence value after First Pass" + hmapFirstPass);

		HashMap<String, Integer> secondPassMap = permutation(hmapFirstPass);
		supportValueSecondPass(secondPassMap);

	}

	public static void supportValueSecondPass(HashMap<String, Integer> secondPassMap) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(FileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br = new BufferedReader(fr);

		String transactionString;

		int noOfTransactions = 0;
		try {

			while ((transactionString = br.readLine()) != null && noOfTransactions < mNoOfTransactions) {

				noOfTransactions++;
				Set twoItemsetSet = secondPassMap.entrySet();
				Iterator twoItemsetIetrator = twoItemsetSet.iterator();

				while (twoItemsetIetrator.hasNext()) {
					Map.Entry twoItemsetEntry = (Map.Entry) twoItemsetIetrator.next();

					if (transactionString.contains("" + twoItemsetEntry.getKey().toString().charAt(0))
							&& transactionString.contains("" + twoItemsetEntry.getKey().toString().charAt(1))) {

						secondPassMap.put((String) twoItemsetEntry.getKey(), (int) twoItemsetEntry.getValue() + 1);
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Set twoItemsetSet = secondPassMap.entrySet();
		Iterator twoItemsetSetIterator = twoItemsetSet.iterator();

		if (mVerboseMode)
			System.out.println("confidence values after Second Pass" + secondPassMap);

		System.out.println("2 item Item Set passing the support threshold : ");
		while (twoItemsetSetIterator.hasNext()) {

			Map.Entry mapTuple = (Map.Entry) twoItemsetSetIterator.next();
			if ((Integer) mapTuple.getValue() >= mSupportThresholdSecondPass) {

				System.out.println(mapTuple.getKey() + " : " + mapTuple.getValue());
			}
		}

		// Integer var= hmap.get("E");
		// System.out.println("Value at index 2 is: "+var);
	}

}
