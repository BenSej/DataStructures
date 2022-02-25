import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;

public class BlockWriter {
	private FileOutputStream fout;
	private BigInteger plainBig;
	
	public BlockWriter(FileOutputStream out) {
		fout = out;
	}
	
	public void setPlainBig(BigInteger b) {
		plainBig = b;
	}
	
	public void writeData(int count) throws IOException {
		if (count == 0) {
			throw new RuntimeException("BlockWriter.write - Invalid parameter count " + count);
		}
		
		Lab7.debugStream.write("BlockWriter.writeData - Writing 0x" + Utilities.toHex(plainBig).toUpperCase() + " as " + count + " bytes\n");

		byte[] bytes = plainBig.toByteArray();
		int temp;
		int startingPos = 0;
		int offset = 0;
		if (count > bytes.length) {
			offset = count - bytes.length;
			for (int i = 0; i < offset; i++) {
				char c = (char) 0;
				fout.write(c);
				Lab7.debugStream.write(" [" + i + "] " + Utilities.bytePresentation(c));
			}
		}
		if (bytes.length > count) {
			startingPos = bytes.length - count;
		}
		for (int i = startingPos; i < bytes.length; i++) {
			temp = Byte.toUnsignedInt(bytes[i]);
			char c = (char) temp;
			fout.write(c);
			Lab7.debugStream.write(" [" + (i + offset) + "] " + Utilities.bytePresentation(c));
		}
		Lab7.debugStream.write("\n\n");
		Lab7.debugStream.flush();
	}
}

