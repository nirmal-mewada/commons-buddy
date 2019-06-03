package org.nirmal.buddy.xml;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author nirmal.s
 *
 */
public class XmlPaths {

	public static void main(String[] args) {
		try {
			long time = System.currentTimeMillis();
			String data = "<make-payment-request xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n" +
					"	xmlns=\"http://www.princetonecom.com/fundingPortal/makepaymentrequest\">\r\n" +
					"	<identity>\r\n" +
					"		<business-id>aa</business-id>\r\n" +
					"		<login>aa</login>\r\n" +
					"		<password>aaa</password>\r\n" +
					"	</identity>\r\n" +
					"	<funding-token-id>aaaaa</funding-token-id>\r\n" +
					"	<nacha-standard-entry-class>PPD</nacha-standard-entry-class>\r\n" +
					"	<credit-debit-indicator>DEBIT</credit-debit-indicator>\r\n" +
					"	<requested-payment-date>2007-08-30</requested-payment-date>\r\n" +
					"	<remittance>\r\n" +
					"		<billing-account>\r\n" +
					"			<billing-account-number>10000</billing-account-number>\r\n" +
					"		</billing-account>\r\n" +
					"		<remit-amount>11.11</remit-amount>\r\n" +
					"		<remit-fee>0</remit-fee>\r\n" +
					"		<fee-waiver-reason>Courtesy</fee-waiver-reason>\r\n" +
					"		<payment-remit-field>\r\n" +
					"			<value>12343</value>\r\n" +
					"		</payment-remit-field>\r\n" +
					"		<payment-remit-field>\r\n" +
					"			<value>12344</value>\r\n" +
					"		</payment-remit-field>\r\n" +
					"	</remittance>\r\n" +
					"	<product>CONNECT_WEB</product>\r\n" +
					"	<transaction-code>123456789</transaction-code>\r\n" +
					"	<email-address>shan@princetonecom.com</email-address>\r\n" +
					"</make-payment-request>\r\n" +
					"";
			boolean includeParentNode = false;
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			docBuilderFactory.setNamespaceAware(true);

			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

			Document doc = docBuilder.parse(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8.name())));

			XPathFactory fact = XPathFactory.newInstance();
			XPath xpath = fact.newXPath();

			NodeList allElements = (NodeList)xpath.evaluate("//*", doc, XPathConstants.NODESET);
			int maxLen = 0;
			for (int i = 0; i < allElements.getLength(); i++){
				String path = getXPath(allElements.item(i));
				if(path.length() > maxLen)
					maxLen = path.length();
			}
			for (int i = 0; i < allElements.getLength(); i++){
				Node currentElement = allElements.item(i);
				Object child = xpath.evaluate("*", currentElement, XPathConstants.NODE);
				String path = getXPath(currentElement);
				if(child == null){
					String val = currentElement.getTextContent();
					//					System.out.println(path + "," + val);
					System.out.println(String.format("%-"+maxLen+"s", path) + " , " + val);
				} else if(includeParentNode){
					System.out.println(path + "," + "N/A");
				}
			}
			System.out.println("Time: "+ (System.currentTimeMillis() - time));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static String getXPath(Node node){
		Node parent = node.getParentNode();
		if (parent == null)	{
			return "";
		}
		return getXPath(parent) + "/" + node.getNodeName();
	}
}
