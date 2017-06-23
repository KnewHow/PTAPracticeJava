package question1005;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author ygh Jun 13, 2017
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long sum = getSum(sc);
		sc.close();
		if (sum == 0) {
			System.out.println("zero");
			return;
		}
		List<Integer> digitList = getDigit(sum);
		formateOutput(digitList);
	}

	/**
	 * A method to get sum from input data
	 * 
	 * @param sc
	 * @return
	 */
	public static long getSum(Scanner sc) {
		long sum = 0;
		String str = sc.nextLine();
		char[] array = str.toCharArray();
		for (int i = 0; i < array.length; i++) {
			sum = sum + Integer.parseInt(array[i] + "");
		}
		return sum;
	}

	/**
	 * Get all digit by a number
	 * 
	 * @param sum
	 * @return
	 */
	public static List<Integer> getDigit(long sum) {
		List<Integer> list = new ArrayList<Integer>();
		while (sum != 0) {
			list.add((int) (sum % 10));
			sum = sum / 10;
		}
		return list;
	}

	public static void formateOutput(List<Integer> list) {
		String[] arr = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
		for (int i = list.size() - 1; i >= 0; i--) {
			if (i != 0) {
				System.out.print(arr[list.get(i)] + " ");
			} else {
				System.out.print(arr[list.get(i)]);
			}
		}
	}
}
