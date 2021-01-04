package com.day.seven;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

//https://adventofcode.com/2020/day/7
public class HandyHaversacks {

	public static void main(String[] args) throws IOException {
		File file = new File(
				"C:\\Users\\jmeth\\Desktop\\Workspaces\\Github_Contributions\\AdventOfCode\\src\\com\\day\\seven\\example.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));

		HashMap<String, HashMap<String, Integer>> hm = new HashMap<String, HashMap<String, Integer>>();
		String s;

		// 1) parsing data into usable hashmap
		while ((s = br.readLine()) != null) {// note: parsing 1 line at a time
			// System.out.println(s); //prints out entire line
			String str = s.replace(".", ""); // remove period at end of all lines
			String[] arrOfStr = str.split("contain"); // separate key from associated values in text; key in position 0
														// and values in position 1; key of outer hashmap in hm
			String[] arrOfStr2 = arrOfStr[1].split(","); // separate multiple values of each key
			// arrOfStr[0] is key

// print out parsed data [not fully parsed yet]
//		for(int i=0;i<arrOfStr.length;i++) {
//			System.out.println(arrOfStr[i]); 
//		}

			HashMap tempNew = new HashMap<String, Integer>(); // inner hashmap of hm
			for (int i = 0; i < arrOfStr2.length; i++) {
				// System.out.println(arrOfStr2[i].trim());
				try {
					String[] sub = arrOfStr2[i].trim().split(" ");
					String secondKey = "";

					int num = Integer.parseInt(sub[0]);

					for (int j = 1; j < sub.length; j++) {
						// System.out.println(sub[j]);
						secondKey += sub[j] + " ";
					}

					// System.out.println(num);
					// System.out.println(secondKey.trim()); //have 2 entries for some because: bright white bag & bright white bags

					tempNew.put(secondKey.trim(), num);

					hm.put(arrOfStr[0].trim(), tempNew);
				} catch (Exception e) {
					// System.out.println("Error");
					hm.put(arrOfStr[0].trim(), tempNew);
				}
			}
		}

		// System.out.println(hm); // doesnt print out hash map
		System.out.println(hm.keySet());
		System.out.println("------");
		int numPossible = 0;
		ArrayList<String> tryAgain = new ArrayList<String>();
		ArrayList<String> keepAgain = new ArrayList<String>();//might be better to use set instead of arraylist here; have to remove dupes later on otherwise
		tryAgain.add("shiny gold bags");
		System.out.println(tryAgain);
		while (true) {
			Iterator iter = hm.keySet().iterator();
			while (iter.hasNext()) {
				String sss = (String) iter.next();
				//System.out.println(sss);
				if (hm.get(sss).isEmpty()) {
					 //System.out.println("true");
				} else {
					StringBuilder sb = new StringBuilder(tryAgain.get(0));
					sb.deleteCharAt(sb.length() - 1);
					if (hm.get(sss).containsKey(tryAgain.get(0)) || hm.get(sss).containsKey(sb.toString())) {//checking if inner hashmap keys possess given bag in their associated values list
						tryAgain.add(sss);
						keepAgain.add(sss);
						numPossible += 1;
					} else {
						// System.out.println("hi");
					}
				}

			}
			tryAgain.remove(0);
			// System.out.println(numPossible);
			System.out.println(tryAgain);
			// System.out.println(tryAgain.size());

			if (tryAgain.size() == 0) {
				break;
			}
			// break;
		}
		//System.out.println(keepAgain);
		HashSet<String> remDup = new HashSet<>(keepAgain);
		System.out.println("---");
		System.out.println(remDup); //list of bag colors that can eventually contain at least one shiny gold bag
		System.out.println("answer: " + remDup.size());

	}

}
