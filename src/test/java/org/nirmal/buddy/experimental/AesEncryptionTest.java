package org.nirmal.buddy.experimental;

import org.nirmal.buddy.crypt.AesEncryption;

/**
 * The Class AesEncryptionTest.
 *
 * @author nirmal.s
 */
public class AesEncryptionTest {

    public static void main(String[] args) {
        try {
            String key = "conns-secret-keyconns-secret-key"; // 256 bit key
            //String key = args[0]; // 256 bit key
            //			String key = "connsdevactivemq"; // 256 bit key
            //			String key = "c0Nns@crEdIt@2017$45767265049193"; // 256 bit key

            String enc = "YFz0Y5AvFUEi5GCXN84JsAMiz56qWTImC0aEu\r\n" +
                    "X9w9qNO3I3m9bxghx_OdnpiKhJ0eLElCfsYtyu5rDmWCGI5zA-rPDavzCF457ZrJGyA35nEpwkzFwtcR4tfnmoQ-HD-6z2fWoCxjORQLB1L31uyb3KKZ6NPgXmlPkqcI1hMxg5GqgdIJFRtD-qNarXMmKzCR5mR246lC_oqFGUuxKCglVvlbNFIi8apWzaWrxP1tvvnr2pNVB9VQ\r\n" +
                    "zQ4DSPKdk6XDzCx6fAVyF7Uf2jwTg3T5GjTwk29N8eS8Ec7Noeg4Nt0LPsTkn0eD0eQfHmn-gli7HQdZRFdcj6UdInTB2ugYmhTotp4Fc9YvCNdZiFyOvNUTD-zw18PCp9tIyyZAYuqFCR4Id0yVLhqAUJnnpQUhWBtePE7aRrfJ_8xNFKYzhzfG_fdZSZYuwZ6G-0uV5KskFsUI\r\n" +
                    "9NMry4i0FC3AeJaglB3DOOXLAXOv2_oLjlBnLwkBuClIzYVF80PJF6HM30ZXAZkjbssty-k8CyVpvK5mJ5b0qbOx7EFJyvqSy6s9w4";
            String decdata = AesEncryption.decrypt(enc,key);
            System.out.println(decdata);
            System.out.println(AesEncryption.encrypt(decdata,key));

            //			String data = "{\"MagentoProgAppID\": \"35\",\"MagentoCreditAppID\": \"83121\",\"UniqueID\": \"51A03401409F19F3BD080004AC1A6F8D\",\"OccupationDetails\": {\"PayFrequency\": \"MONTHLY\",\"HireDate\": \"2017-01-20\",\"LastPayDate\": \"2017-05-01\"},\"HaveBankAccount\": \"Y\",\"HasDirectDeposit\": \"N\",\"BankAccount\": { \"RoutingNumber\": \"555544222\", \"AccountNumber\": \"55552244866868\", \"AccountOpenDate\": \"2017-01-01\" },\"CardNumber\": \"343436\",\"MarketingOptOut\": \"Y\"}";
            //			System.out.println(AesEncryption.encrypt(data, key));
            //System.out.println(generateEncryptionSecret());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*private static String generateEncryptionSecret() throws UnsupportedEncodingException {
		  try {
		    KeyGenerator generator = KeyGenerator.getInstance("AES");
		    generator.init(256);

		    SecretKey key = generator.generateKey();
		    return new String(key.getEncoded(), CHAR_SET_UTF8);
		  } catch (NoSuchAlgorithmException ex) {
		    ex.printStackTrace();
		    return null;
		  }
		}*/

}
