

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidAlgorithmParameterException;


public class aes {
	
	public static String bytes2String(byte buf[]) {
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < buf.length; i++) {
	      String hex = Integer.toHexString(buf[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      sb.append(hex);
	    }
	    return sb.toString();
	  }
	
	 public static byte[] string2Bytes(String hexString) {
		    int len = hexString.length() / 2;
		    byte[] result = new byte[len];
		    for (int i = 0; i < len; i++) {
		      result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		    }
		    return result;
		  }
	
	public static String encrypt( String key, String data) 
			throws java.io.UnsupportedEncodingException, 
				NoSuchAlgorithmException,
				NoSuchPaddingException,
				InvalidKeyException,
				InvalidAlgorithmParameterException,
				IllegalBlockSizeException,
				BadPaddingException {
		
    	SecretKeySpec newKey = new SecretKeySpec(key.getBytes(), "AES");
    	Cipher cipher = null;
		cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, newKey);
		return bytes2String(cipher.doFinal(data.getBytes()));
	}
	
	public static String decrypt(String key, String data) 
			throws java.io.UnsupportedEncodingException, 
			NoSuchAlgorithmException,
			NoSuchPaddingException,
			InvalidKeyException,
			InvalidAlgorithmParameterException,
			IllegalBlockSizeException,
			BadPaddingException {
		
		SecretKeySpec newKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, newKey);
		return new String(cipher.doFinal(string2Bytes(data)));
	}
	
	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		System.out.print(encrypt("9ce62c1836d128cfc875c9026db7564b","12345678"));
		System.out.print("\n");
		System.out.print(decrypt("9ce62c1836d128cfc875c9026db7564b","98efc456143e68a8d8f65476603a5dc2"));
	}
}