package org.nirmal.buddy.prop;

import static org.apache.commons.lang3.StringUtils.abbreviate;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

@SuppressWarnings("all")
public class PropertyCompare {

	private static final int MAX_KEY_LEN = 40;
	private static final int MAX_VAL_LEN = 50;


	public static void main(String[] args) {

		args = new String[]{"/Users/nirmal.s/Desktop/prd.ecom.properties_new","/Users/nirmal.s/Desktop/prd.ecom.properties"};
		try {
			Properties p1 = new Properties();
			p1.load(new FileInputStream(new File((args[0]))));
			Properties p2 = new Properties();
			p2.load(new FileInputStream(new File((args[1]))));

			MatchPropsMain.printLabel("Left unique");
			compareUnique(p1,p2);

			MatchPropsMain.printLabel("Right unique");
			compareUnique(p2,p1);

			MatchPropsMain.printLabel("Mismatch");
			compareMisMatch(p2,p1);

			//			printLabel("Similar");
			//			compareSimilar(p2,p1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void compareUnique(Properties p1, Properties p2) {
		ArrayList<String> set = getSortedProps(p1);
		for (String key : set) {
			if(!p2.containsKey(key))
				print(key,p1.getProperty(key));
		}
	}


	private static ArrayList<String> getSortedProps(Properties p1) {
		ArrayList<String> set = new ArrayList<String>();
		Enumeration keysEnum = p1.keys();
		while(keysEnum.hasMoreElements()){
			set.add((String)keysEnum.nextElement());
		}
		Collections.sort(set);
		return set;
	}

	private static void compareMisMatch(Properties p1, Properties p2) {
		ArrayList<String> set = getSortedProps(p1);
		for (String key : set) {
			String val1 = p1.getProperty(key);
			String val2 = p2.getProperty(key);
			if(val2 !=null && !val1.trim().equals(val2.trim())){
				print(key,val1,val2);
			}

		}
	}
	private static void compareSimilar(Properties p1, Properties p2) {
		ArrayList<String> set = getSortedProps(p1);
		for (String key : set) {
			String val1 = p1.getProperty(key);
			String val2 = p2.getProperty(key);
			if(val2 !=null && val1.trim().equals(val2.trim())){
				print(key,val1,val2);
			}

		}
	}

	private static void print(String key, String val1, String val2) {
		val1 = abbreviate(val1, 0,MAX_VAL_LEN);
		val2 = abbreviate(val2, 0,MAX_VAL_LEN);
		System.out.println(String.format("%-"+MAX_KEY_LEN+"s = %-"+MAX_VAL_LEN+"s | %-"+MAX_VAL_LEN+"s", key,val1,val2));

	}

	private static void print(String key, String property) {
		System.out.println(String.format("%-"+MAX_KEY_LEN+"s = %s", key,property));
	}

}
