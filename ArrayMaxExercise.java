
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/*
 * for loop expanded:
 * 
		for (A; B; C) {
			// one step of loop
		}
	
		A;
		while (b == true) {
			// one step of loop
			C;
		}
 */

public class ArrayMaxExercise {

	// mantissa * 2 ^ exponent
	// 0 <= 22 < 1
	// exponent is a signed int

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (false) {
			Scanner input = new Scanner(System.in);

			double[] entered = new double[3];
			System.out.println("Enter 3 values to find the maximum:");
			for (int i = 0; i < 3; i++) {
				entered[i] = input.nextDouble();
			}
			System.out.println("Maximum is - " + getMaxValue(entered));
		}

		Assert.assertEquals(10, getMaxValue(new double[] { 10, 4, 6 }), 0.00001);

		int n = 10000;
		long start = System.currentTimeMillis();
		int sum = 0;
		int loop = 0;
		for (int i = 0; i < 1000000000; i++) {
			sum = linearSum(n);
			loop++;
		}
		long end = System.currentTimeMillis();

		System.out.println("loop = " + loop);

		System.out.println("linearSum(" + n + ") = " + linearSum(n));
		System.out.println("took " + (end - start) + " millis");

		new ArrayMaxExercise().testLinearSum();
		
		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				System.out.print("\t");
				System.out.print(x + y);
			}
			System.out.println();
		}
		
		
		// O(x * y)
		
	}

	@Test
	public void testLinearSum() {
		assertEquals(1, linearSum(1));
		assertEquals(3, linearSum(2));
		assertEquals(6, linearSum(3));
		assertEquals(55, linearSum(10));
		assertEquals(171, linearSum(18));
		assertEquals(123000 * (123000 + 1) / 2, linearSum(123000));
	}
	
	public void assertEquals(final int expected, final int actual) {
		if (expected != actual) {
			throw new RuntimeException("expected: " + expected + " but actual: " + actual);
		}
	}

	// Find maximum (largest) value in array using loop
	// method
	public static double getMaxValue(double[] a) {
		double currentMax = a[0];
		for (int i = 1; i < a.length; i++) {
			if (a[i] > currentMax) {
				currentMax = a[i];
			}
		}
		return currentMax;
	}

	public static int linearSum(int n) {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum = sum + i;
		}
		return sum;
	}

	/*
	 * public static double max(double[] array) { // Validates input if (array==
	 * null) { throw new IllegalArgumentException("The Array must not be null");
	 * } else if (array.length == 0) { throw new IllegalArgumentException(
	 * "Array cannot be empty."); }
	 * 
	 * // Finds and returns max double max = array[0]; for (int j = 1; j <
	 * array.length; j++) { if (Double.isNaN(array[j])) { return Double.NaN; }
	 * if (array[j] > max) { max = array[j]; } }
	 * 
	 * return max;
	 */
}
