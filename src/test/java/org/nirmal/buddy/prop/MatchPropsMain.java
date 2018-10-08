package org.nirmal.buddy.prop;

import static org.apache.commons.lang3.StringUtils.center;
import static org.apache.commons.lang3.StringUtils.repeat;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import com.google.common.collect.Lists;

public class MatchPropsMain {

	private static List<String> fileToIgnore = Lists.newArrayList("pom.xml", "log4j2.xml");
	private static List<String> propsToIgnore = Lists.newArrayList("env.key", "env");

	public static void main(String[] args) {
		try {
			String path = "/Users/nirmal.s/Documents/Conns/svn/Branches/esb/mule/ecom/MTW-159/src/main/app";
			String prop = "/Users/nirmal.s/Desktop/dev.ecom.properties";
			//			String path = "/Users/nirmal.s/Documents/Conns/svn/Branches/esb/mule/esign/CHG0032829/src/main/app";
			//			String prop = "/Users/nirmal.s/Desktop/prd.esignature.properties";
			//			String path = "/Users/nirmal.s/Documents/Conns/svn/trunk/esb/mule/conns-api/src/main/app";
			//			String prop = "/Users/nirmal.s/Documents/Conns/svn/trunk/esb/mule/conns-api/src/main/resources/config/loc.connsapi.properties";

			XmlPropParser parse = new XmlPropParser(path,fileToIgnore);
			TreeSet<String> xmlKeys = parse.getAllProperties();

			Properties p1 = new Properties();
			p1.load(new FileInputStream(new File(prop)));

			System.out.println("Files: "+parse.getParsedFilesCount());
			System.out.println("Xml Props: "+xmlKeys.size());
			System.out.println("Props file: "+p1.size());

			printLabel("Missing props (Add)");
			findMissing(xmlKeys, p1);

			printLabel("Extra props (Remove)");
			findExtra(xmlKeys, p1);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void findExtra(TreeSet<String> xmlKeys, Properties p1) {
		int extra = 0;
		for (Object key : p1.keySet()) {
			if(!xmlKeys.contains(key+"")){
				System.out.println(key);
				extra++;
			}
		}
		if(extra == 0)
			System.out.println(" N/A");
	}
	private static void findMissing(TreeSet<String> xmlKeys, Properties p1) {
		int missing = 0;
		for (String key : xmlKeys) {
			if(propsToIgnore.contains(key))
				continue;
			if(!p1.containsKey(key)){
				System.out.println(key);
				missing++;
			}
		}
		if(missing == 0)
			System.out.println(" N/A");
	}

	public static void printLabel(String string) {
		int len = 100;
		System.out.println();
		System.out.println(repeat("*",len));
		System.out.println("*"+center(string, len-2)+"*");
		System.out.println(repeat("*",len));
		System.out.println();

	}
}
