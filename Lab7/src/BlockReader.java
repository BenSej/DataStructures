import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

public class BlockReader {
	private FileInputStream fin;
	private int width;
	
	public BlockReader(FileInputStream in) {
		fin = in;
		width = 0;
	}
	
	public BigInteger readData(int count) throws IOException {
		BigInteger bigNumber = BigInteger.valueOf(0);
		if (count == 0) {
			throw new RuntimeException("BlockReader.read - Invalid parameter count " + count);
		}
		Lab7.debugStream.write("BlockReader.readData - Requested count " + count + " bytes, got\n");
		width = 0;
		for (int i = count; i > 0; i--) {
			int bInt = fin.read();
			if (bInt != -1) {
				char b = (char) bInt;
				Lab7.debugStream.write(" [" + width + "] " + Utilities.bytePresentation(b));
				width++;
				bigNumber = bigNumber.shiftLeft(8);
				bigNumber = bigNumber.add(new BigInteger((Integer.toString((int) b))));
			}
			else {
				break;
			}
		}
		Lab7.debugStream.write("\nBlockReader.readData - Read 0x" + Utilities.toHex(bigNumber).toUpperCase() + " as " + width + " bytes" + "\n\n");
		Lab7.debugStream.flush();
		
		return bigNumber;
	}
	
	public int getWidth() {
		return width;
	}
}

