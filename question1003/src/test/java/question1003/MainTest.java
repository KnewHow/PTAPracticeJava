package question1003;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}

		for (Integer i : list) {
			if (i == 1 || i == 5) {
				continue;
			}
			System.out.println(i + " ");
		}
	}
}
