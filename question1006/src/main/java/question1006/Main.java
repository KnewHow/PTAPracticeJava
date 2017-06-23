package question1006;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author ygh Jun 14, 2017
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		sc.nextLine();
		List<Person> list = getRecords(sc, size);
		getRequestId(list);
		sc.close();
	}

	public static List<Person> getRecords(Scanner sc, int size) {
		List<Person> list = new ArrayList<Person>();
		for (int i = 0; i < size; i++) {
			String id = sc.nextLine();
			String[] strs = id.split(" ");
			id = strs[0];
			String[] inSeconds = strs[1].split("\\:");
			String[] outSeconds = strs[2].split("\\:");
			Person person = new Person(id, getSeconds(inSeconds), getSeconds(outSeconds));
			list.add(person);
		}
		return list;
	}

	public static void getRequestId(List<Person> list) {
		String unlockid = list.get(0).getId();
		String lockid = list.get(0).getId();
		int minInSeconds = list.get(0).getInSeconds();
		int maxOutSeconds = list.get(0).getOutSeconds();
		for (int i = 1; i < list.size(); i++) {
			Person person = list.get(i);
			if (person.getInSeconds() < minInSeconds) {
				minInSeconds = person.getInSeconds();
				unlockid = person.getId();
			}

			if (person.getOutSeconds() > maxOutSeconds) {
				maxOutSeconds = person.getOutSeconds();
				lockid = person.getId();
			}
		}
		System.out.println(unlockid + " " + lockid);
	}

	public static int getSeconds(String[] strs) {
		int hour = Integer.parseInt(strs[0]);
		int minutes = Integer.parseInt(strs[1]);
		int seconds = Integer.parseInt(strs[2]);
		return (hour * 3600 + minutes * 60 + seconds);

	}
}

class Person {
	private String id;
	private Integer inSeconds;
	private Integer outSeconds;

	public Person(String id, Integer inSeconds, Integer outSeconds) {
		super();
		this.id = id;
		this.inSeconds = inSeconds;
		this.outSeconds = outSeconds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getInSeconds() {
		return inSeconds;
	}

	public void setInSeconds(Integer inSeconds) {
		this.inSeconds = inSeconds;
	}

	public Integer getOutSeconds() {
		return outSeconds;
	}

	public void setOutSeconds(Integer outSeconds) {
		this.outSeconds = outSeconds;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", inSeconds=" + inSeconds + ", outSeconds=" + outSeconds + "]";
	}

}
