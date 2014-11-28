package factors.engine;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeFactorsEngine {
	/**
	 * Given the range of values (low <= x <= high) to search through,
	 * find all prime factors of a number N (N >= 2), such that x divides
	 * N evenly. Repeated prime factors will be found multiple time.
	 * @param N the number to be factored in to primes
	 * @param low the lower bound of prime factors
	 * @param high the upper bound of prime factors
	 * @param certainty a measure of the uncertainty that the caller is willing
	 * 					to tolerate: if the call returns true the probability that
	 * 					this BigInteger is prime exceeds (1 - 1/(2^certainty)).
	 * @return an array of prime factors for N
	 */
	public static BigInteger[] findPrimeFactors(BigInteger N,
			BigInteger low, BigInteger high, int certainty) {
		List<BigInteger> primeFactors = new ArrayList<BigInteger>();
		BigInteger x = new BigInteger(low.toByteArray());
		BigInteger upperBound = high.add(new BigInteger("1"));
		while (x.compareTo(upperBound) == -1) {	// iterate over x from low to high
			if (x.isProbablePrime(certainty)) {
				while (N.remainder(x).compareTo(new BigInteger("0")) == 0) {
					primeFactors.add(x);
					N = N.divide(x);
				}
			}
			x = x.add(new BigInteger("1"));
		}
		return primeFactors.toArray(new BigInteger[0]);
	}
}
