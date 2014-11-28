package factors.engine;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class PrimeFactorsEngineTest {

	@Test
	public void primeFactorsEngineTest001() {
		BigInteger[] expected = new BigInteger[] {
				new BigInteger("5"), new BigInteger("17")};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(
				new BigInteger("85"), new BigInteger("2"), new BigInteger("17"));
		assertArrayEquals(actual, expected);
	}
	
	@Test
	public void primeFactorsEngineTest002() {
		BigInteger[] expected = new BigInteger[] {new BigInteger("5")};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(
				new BigInteger("85"), new BigInteger("2"), new BigInteger("16"));
		assertArrayEquals(actual, expected);
	}
	
	@Test
	public void primeFactorsEngineTest003() {
		BigInteger[] expected = new BigInteger[] {};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(
				new BigInteger("85"), new BigInteger("2"), new BigInteger("4"));
		assertArrayEquals(actual, expected);
	}
	
	@Test
	public void primeFactorsEngineTest004() {
		BigInteger[] expected = new BigInteger[] {
				new BigInteger("2"), new BigInteger("2"),
				new BigInteger("2"), new BigInteger("3"),
				new BigInteger("11")
		};
		BigInteger[] actual = PrimeFactorsEngine.findPrimeFactors(
				new BigInteger("264"), new BigInteger("2"), new BigInteger("17"));
		assertArrayEquals(actual, expected);
	}
}
