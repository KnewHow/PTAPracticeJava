package question1001;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Integer a, b;
		Scanner sc = new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		sc.close();
		new Sum(a, b).formatOutput();
	}

}

class Sum {
	private Integer a;

	private Integer b;

	public Sum(Integer a, Integer b) {
		super();
		this.a = a;
		this.b = b;
	}

	public Sum() {
		super();
		this.a = 0;
		this.b = 0;
	}

	public Integer getA() {
		return a;
	}

	public void setA(Integer a) {
		this.a = a;
	}

	public Integer getB() {
		return b;
	}

	public void setB(Integer b) {
		this.b = b;
	}

	public Integer getSum() {
		return this.a + this.b;
	}

	public List<String> getFormatOutput() {
		Integer sum = getSum();
		List<String> list = new ArrayList<String>();
		if (sum == 0) {
			list.add("0");
		} else if (sum < 0) {
			getFormatOutput(list, sum * -1);
			list.add("-");
		} else {
			getFormatOutput(list, sum);
		}
		return list;
	}

	public void getFormatOutput(List<String> list, Integer sum) {
		int i = 0;
		while (sum != 0) {
			list.add(String.valueOf(sum % 10));
			i++;
			if (i % 3 == 0 && sum >= 10) {
				list.add(",");
			}
			sum /= 10;
		}
	}

	public void formatOutput() {
		List<String> list = getFormatOutput();
		int i;
		for (i = list.size() - 1; i >= 0; i--) {
			System.out.print(list.get(i));
		}
	}

}
