package question1002;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author ygh
 * Jun 7, 2017
 */
public class Main {

	public static void main(String[] args) {
		List<Polynomial> aPolys = new ArrayList<Polynomial>();
		List<Polynomial> bPolys = new ArrayList<Polynomial>();
		Scanner sc = new Scanner(System.in);
		int aSize = sc.nextInt();
		getInputData(aPolys, aSize, sc);
		int bSize = sc.nextInt();
		getInputData(bPolys, bSize, sc);
		sc.close();
		List<Polynomial> sum = getSum(aPolys, bPolys);
		formatInput(sum);
	}

	public static void getInputData(List<Polynomial> polys, int size, Scanner sc) {
		for (int i = 0; i < size; i++) {
			Polynomial ploy = new Polynomial();
			ploy.setExponents(sc.nextInt());
			ploy.setCoefficients(sc.nextDouble());
			polys.add(ploy);
		}
	}

	public static List<Polynomial> getSum(List<Polynomial> aPolys, List<Polynomial> bPolys) {
		List<Polynomial> sum = new ArrayList<Polynomial>();
		int i = 0, j = 0;
		while (i < aPolys.size() && j < bPolys.size()) {
			if (aPolys.get(i).getExponents() > bPolys.get(j).getExponents()) {
				insert(sum, aPolys.get(i));
				i++;
			} else if (aPolys.get(i).getExponents() < bPolys.get(j).getExponents()) {
				insert(sum, bPolys.get(j));
				j++;
			} else {
				if ((aPolys.get(i).getCoefficients() + bPolys.get(j).getCoefficients()) != 0.0) {
					Polynomial ploy = new Polynomial();
					ploy.setCoefficients(aPolys.get(i).getCoefficients() + bPolys.get(j).getCoefficients());
					ploy.setExponents(aPolys.get(i).getExponents());
					insert(sum, ploy);
				}
				i++;
				j++;
			}
		}
		while (i < aPolys.size()) {
			insert(sum, aPolys.get(i));
			i++;
		}
		while (j < bPolys.size()) {
			insert(sum, bPolys.get(j));
			j++;
		}
		return sum;
	}

	public static void insert(List<Polynomial> list, Polynomial ploy) {
		Polynomial p = new Polynomial(ploy.getExponents(), ploy.getCoefficients());
		list.add(p);
	}

	public static void formatInput(List<Polynomial> list) {
		if (list.size() == 0) {
			System.out.print(list.size());
		} else {
			System.out.print(list.size() + " ");
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				System.out.print(list.get(i).toString());
			} else {
				System.out.print(list.get(i).toString() + " ");
			}
		}
	}

}

class Polynomial {

	public Polynomial(Integer exponents, Double coefficients) {
		super();
		this.exponents = exponents;
		this.coefficients = coefficients;
	}

	public Polynomial() {
		super();
	}

	/**
	 * The exponents of the polynomial
	 */
	private Integer exponents;

	/**
	 * The coefficients of the polynomials
	 */
	private Double coefficients;

	public Integer getExponents() {
		return exponents;
	}

	public void setExponents(Integer exponents) {
		this.exponents = exponents;
	}

	public Double getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(Double coefficients) {
		this.coefficients = coefficients;
	}

	@Override
	public String toString() {
		return this.getExponents() + " " + this.getCoefficients();
	}

}
