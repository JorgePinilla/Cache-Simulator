/*
  This class contains functions that are used for decoding the address
  and functions that calculate cache settings.
*/
import java.math.*;

public class Util {
	private static final int ADDRESS_32_BIT_HEX = 8;
	private static final String HEX_ADDRESS_PADDING = "0000";
		
	private Util(){
	}

	public static BigInteger log2(BigInteger value){
		int temp = (int) (Math.log10(value.intValue()) / Math.log10(2));
		BigInteger result = new BigInteger(String.valueOf(temp));
		return result;
	}
   
	public static String hexTo32bitBin(String hex){
		String result = "";
		for(int i = 0; i < ADDRESS_32_BIT_HEX - (hex.length() - 2); i++)
			result = result + HEX_ADDRESS_PADDING;
		for (int i = 2; i < hex.length(); i++){
			int temp = 0;
			char c = hex.charAt(i);
			if(c < 'a')
				temp = c - '0';
			else
				temp = (c - 'a') + 10;
			String binary = Integer.toBinaryString(temp);
			for(int j = 0; j < 4 - binary.length(); j++)
				result = result + "0";
			result = result + binary;
		}
		return result;
	}

	public static BigInteger binToDec(String bin){
		BigInteger result = new BigInteger(bin, 2);
		return result;
	}
}
