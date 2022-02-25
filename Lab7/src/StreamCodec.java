import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class StreamCodec {
	
	private RSAAlgorithm algo;
	private boolean isInfileRead = false;
	
	private int calculateBlockMaxLength(BigInteger n) {
		int max = 0;
		BigInteger fs = new BigInteger("ff", 16);
		while (fs.compareTo(n) == -1) {
			fs = fs.shiftLeft(8);
			fs = fs.add(new BigInteger("ff", 16));
			max++;
		}
		if (max < 1) {
			throw new RuntimeException("calculateChunkSize - Modulus " + n + " is too small");
		}
		return max;
	}
	
	public StreamCodec(BigInteger p, BigInteger q, BigInteger e) throws IOException {
		algo = new RSAAlgorithm(p,q,e);
	}
	
	public void encryptStream(FileInputStream fin, PrintWriter fout) throws UnsupportedEncodingException, IOException {
		BigInteger plainBig, cipherBig;
		int maxBlockLength = calculateBlockMaxLength(algo.getN());
		int blockLength;
		int blockNum = 0;
		BlockReader br = new BlockReader(fin);
		while (!isInfileRead) {
			Lab7.debugStream.write("--- StreamCodec.encryptStream block #" + blockNum + ", max length " + maxBlockLength + " ---\n\n");
			Lab7.debugStream.flush();
			plainBig  = br.readData(maxBlockLength);
			blockLength = br.getWidth();
			if (blockLength < maxBlockLength) {
				isInfileRead = true;
			}
			cipherBig = algo.encrypt(plainBig);
			fout.write(Integer.toHexString(blockLength).toUpperCase() + " " + Utilities.toHex(cipherBig).toUpperCase() + "\n");
			blockNum++;
		}
	}

	public RSAAlgorithm getAlgo() {
		return algo;
	}
	public void decryptStream(BufferedReader fin, FileOutputStream fout) throws IOException {
		String line;
		int blockLength;
		BigInteger cipherBig, plainBig;
		String[] splits = new String[2];
		int lineNum = 0;
		BlockWriter bw = new BlockWriter(fout);
		while ((line=fin.readLine()) != null) {
			splits = line.split(" ");
			blockLength = Integer.parseInt(splits[0], 16);
			cipherBig = new BigInteger(splits[1], 16);
			Lab7.debugStream.write("--- StreamCodec.decryptStream block #" + lineNum + ", length " + blockLength + " ---\n\n");
            Lab7.debugStream.flush();
            plainBig = algo.decrypt(cipherBig);
            bw.setPlainBig(plainBig);
            bw.writeData(blockLength);
            lineNum++;
		}
	}
}
