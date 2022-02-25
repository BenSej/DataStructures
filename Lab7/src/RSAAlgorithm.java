import java.io.IOException;
import java.math.BigInteger;

public class RSAAlgorithm {
	private BigInteger p,q,e,n,d,totient;
	
	public BigInteger getP() {
		return p;
	}
	private BigInteger modularMultiplicativeInverse(BigInteger given, BigInteger modulus) {
		BigInteger ans = given.modInverse(modulus);
		return ans;
	}
	public RSAAlgorithm(BigInteger givenP, BigInteger givenQ, BigInteger givenE) throws IOException {
		p = givenP;
		q = givenQ;
		e = givenE;
		n = p.multiply(q);
		totient = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));
		d = modularMultiplicativeInverse(e, totient);
		Lab7.debugStream.write("--- RSAAlgorithm" + "\n p " + p + "\n q " + q + "\n e " + e + "\n n " + n + "\n d " + d + "\n\n");
		Lab7.debugStream.flush();
	}
	
	public BigInteger encrypt( BigInteger plainBig) throws IOException {
		BigInteger cipherTextNumber = plainBig.modPow(e, n);
		Lab7.debugStream.write("RSAAlgorithm.encrypt 0x" + Utilities.toHex(plainBig).toUpperCase() + " -> 0x" + Utilities.toHex(cipherTextNumber).toUpperCase() + "\n\n");
		Lab7.debugStream.flush();
		return cipherTextNumber;
	}
	
	public BigInteger decrypt(BigInteger cipherBig) throws IOException {
		BigInteger plainTextNumber = cipherBig.modPow(d, n);
		Lab7.debugStream.write("RSAAlgorithm.decrypt 0x" + Utilities.toHex(cipherBig).toUpperCase() + " -> 0x" + Utilities.toHex(plainTextNumber).toUpperCase() + "\n\n");
		Lab7.debugStream.flush();
		return plainTextNumber;
	}
	
	public BigInteger getN() {
		return n;
	}
}  
