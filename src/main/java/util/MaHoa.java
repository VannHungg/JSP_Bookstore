package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MaHoa {
	public static String toMD5(String str) {
		String salt = "ahfushj;chvkkshued";
		str += salt;
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(str.getBytes()); 
			BigInteger no = new BigInteger(1, messageDigest);
			
			String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            result = hashtext; 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
