import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class Utilities {
	public static String toHex(BigInteger decimal) {
		return decimal.toString(16);
	}
	
	public static long fileSizeOf(String file) {
		return new File(file).length();
	}
	
	public static void compareFiles(String firstFile, String secondFile) throws IOException {
		FileInputStream fin1 = new FileInputStream(firstFile);
		FileInputStream fin2 = new FileInputStream(secondFile);
		
		char tempc1;
		char tempc2;
		int currPos = 0;
		int lineCounter = 1;
		byte[] temp1 = fin1.readAllBytes();
		byte[] temp2 = fin2.readAllBytes();
		
		String s1 = new String(temp1, "ISO-8859-1");
		String s2 = new String(temp2, "ISO-8859-1");
		
		if (s1.length() != s2.length()) {
			fin1.close();
			fin2.close();
			throw new RuntimeException(
                    "Sizes of the files are different"
                  + ", file " + firstFile  + " has size " + (s1.length())
                  + ", file " + secondFile + " has size " + (s2.length())
            );
		}
		
		for (int i = 0; i < s2.length(); i++) {
			currPos++;
			tempc1 = s1.charAt(i);
			tempc2 = s2.charAt(i);
			if (tempc1 != tempc2) {
				fin1.close();
		    	fin2.close();
		    	throw new RuntimeException(
                        "Files are different at position " + (currPos)
                      + " on line " + lineCounter + ": file " + firstFile  + " has character " + (tempc1)
                      + ", file " + secondFile + " has character " + (tempc2)
                 );
			}
			if (tempc2 == '\n') {
				lineCounter++;
				currPos = 0;
				continue;
			}
		}
		
        fin1.close();
    	fin2.close();

	}
	
	public static String bytePresentation(char givenByte) {
		if ( givenByte == '\n' ) return "\\n";
		if ( givenByte == '\r' ) return "\\r";
		if ( givenByte == '\t' ) return "\\t";
		if ( givenByte >= ' ' && givenByte <= '~' )
      		 return "'" + givenByte + "'";
		int intGivenByte = (int) givenByte;
		if (intGivenByte < 10) {
			return "0x0" + Integer.toHexString(intGivenByte);
		}
		return "0x" + Integer.toHexString(intGivenByte).toUpperCase();
	}
}
