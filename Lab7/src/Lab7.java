import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

public class Lab7 {
	
	public static FileWriter debugStream;
	

	public static StreamCodec makeStreamCodecFromFile(String keyFile) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader(keyFile));
		String p = "", q = "", e = "";
		String line;
		while ((line = fin.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				if (line.charAt(i) == 'p') {
					i++;
					while(i < line.length() && line.charAt(i) != 'q' && line.charAt(i) != 'e') {
						if (line.charAt(i) == ' ') {
							i++;
							continue;
						}
						p += line.charAt(i);
						i++;
					}
					if (i == line.length()) {
						break;
					}
					i--;
				}
				else if (line.charAt(i) == 'q') {
					i++;
					while(i < line.length() && line.charAt(i) != 'p' && line.charAt(i) != 'e') {
						if (line.charAt(i) == ' ') {
							i++;
							continue;
						}
						q += line.charAt(i);
						i++;
					}
					if (i == line.length()) {
						break;
					}
					i--;
				}
				else if (line.charAt(i) == 'e') {
					i++;
					while(i < line.length() && line.charAt(i) != 'p' && line.charAt(i) != 'q') {
						if (line.charAt(i) == ' ') {
							i++;
							continue;
						}
						e += line.charAt(i);
						i++;
					}
					if (i == line.length()) {
						break;
					}
					i--;
				}
				else {
					fin.close();
					throw new RuntimeException("Invalid token" + line.charAt(i) + " in key file" + keyFile);
				}
			}
		}
		BigInteger pInt = new BigInteger(p);
		BigInteger qInt = new BigInteger(q);
		BigInteger eInt = new BigInteger(e);
		if (pInt.compareTo(BigInteger.ZERO) == 0) 
		{
			fin.close();
			throw new RuntimeException("Missing or invalid p value in key file " + keyFile);
		}
		if (qInt == BigInteger.ZERO) {
			fin.close();
			throw new RuntimeException("Missing or invalid q value in key file " + keyFile);
		}
		if (eInt == BigInteger.ZERO) {
			fin.close();
			throw new RuntimeException("Missing or invalid e value in key file " + keyFile);
		}
		fin.close();
		StreamCodec ans = new StreamCodec(pInt,qInt,eInt);
		return ans;
	}
	
	public static void testEncrypting( StreamCodec wrapper, String plainFile, String encryptedFile) throws IOException {
		FileInputStream fin = new FileInputStream(plainFile);
		PrintWriter fout = new PrintWriter(encryptedFile);
		
		debugStream.write("*** Encrypting " + plainFile +  ", size " + Utilities.fileSizeOf(plainFile) + " bytes -> " + encryptedFile + " ***\n\n");
		debugStream.flush();
		
		wrapper.encryptStream(fin, fout);
		
		fin.close();
		fout.close();
		
		debugStream.write("Encrypted file " + encryptedFile + " size is " + Utilities.fileSizeOf(encryptedFile) + " bytes\n\n");
		debugStream.flush();
	}
	
	public static void testDecrypting(StreamCodec wrapper, String encryptedFile, String decryptedFile) throws IOException {
		BufferedReader fin = new BufferedReader(new FileReader(encryptedFile));
		FileOutputStream fout = new FileOutputStream(decryptedFile);
		
		debugStream.write("*** Decrypting " + encryptedFile +  ", size " + Utilities.fileSizeOf(encryptedFile) + " bytes -> " + decryptedFile + " ***\n\n");
		debugStream.flush();
		
		wrapper.decryptStream(fin, fout);
		
		fin.close();
		fout.close();
		
		debugStream.write("Decrypted file " + decryptedFile + " size is " + Utilities.fileSizeOf(decryptedFile) + " bytes\n\n");
	    debugStream.flush();
	}
	
	public static void testFile(int numberFile) throws IOException {
		 String debugFile = numberFile + "_debug.txt";
		 String plainFile = numberFile + "_in.dat";
		 String keyMaterialFile = numberFile + "_keymat.txt";
		 String encryptedFile = numberFile + "_encrypted.txt";
		 String decryptedFile = numberFile + "_decrypted.dat";
		 
		 System.out.println("*** Testing input file " + plainFile + ", debug output file " + debugFile + " ***\n");
		 System.out.flush();
		 
		 debugStream = new FileWriter(debugFile);
		 StreamCodec rsaWrapper = makeStreamCodecFromFile(keyMaterialFile);
		 
		 testEncrypting(rsaWrapper, plainFile, encryptedFile);
		 testDecrypting(rsaWrapper, encryptedFile, decryptedFile);
		 Utilities.compareFiles(plainFile, decryptedFile);
		 
		 debugStream.write( ">>> Files " + plainFile + " and " + decryptedFile + " are equal.\n");
		 debugStream.flush();
		 debugStream.close();
		 
		 System.out.println("\nOK\n\n");
		 System.out.flush();
	}
	
	public static void main(String[] args) throws IOException {
		try {
			for (int i = 1; i <= 3; i++) {
				testFile(i);
			}
		}
	   
		catch ( Exception ex ) {
				debugStream.write(("Exception: " + ex.getMessage() + "\n"));
				System.out.println("Exception: " +  ex.getMessage());
		}
	}

}
