package question1004;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 
 * @author ygh Jun 12, 2017
 */
public class Main {
	public static void main(String[] args) {
		Map<Integer, Integer> relation = new HashMap<Integer, Integer>();
		Map<Integer, Boolean> isLeaf = new HashMap<Integer, Boolean>();
		Scanner sc = new Scanner(System.in);
		int totalNodes, no_leafNode;
		totalNodes = sc.nextInt();
		no_leafNode = sc.nextInt();
		List<Integer> keys = createKeys(totalNodes);
		initIntegerKeyMap(relation, keys, -1);
		initBooleanMap(isLeaf, keys, true);
		buildRelationShip(relation, isLeaf, no_leafNode, sc);
		sc.close();
		findLevelLeaves(relation, isLeaf, totalNodes);

	}

	/**
	 * Find the leaf nodes in each levels,you can get details of algorithms from
	 * question1003.txt
	 * 
	 * @param relation
	 * @param isLeaf
	 * @param totalNodes
	 */
	public static void findLevelLeaves(Map<Integer, Integer> relation, Map<Integer, Boolean> isLeaf, int totalNodes) {
		int i = 0, length = 0, counter = 0, j, newLength;
		/**
		 * @param tempParent
		 *            An integer array to store parent index temporary
		 * @param tempChild
		 *            An integer array to store children index temporary
		 */
		int[] tempParent = new int[totalNodes];
		int[] tempChild = new int[totalNodes];
		length = 0;
		for (Entry<Integer, Integer> entry : relation.entrySet()) {
			if (entry.getValue() == -1) {
				tempParent[length++] = entry.getKey();
			}
		}
		while (length != 0) {
			newLength = 0;
			counter = 0;
			/**
			 * Find the same level all children nodes
			 */
			for (i = 0; i < length; i++) {
				if (isLeaf.get(tempParent[i])) {
					counter++;
					continue;
				}
				for (j = 0; j < totalNodes; j++) {
					if (relation.get(j) == tempParent[i]) {
						tempChild[newLength++] = j;
					}

				}
			}
			System.out.print(counter);
			length = newLength;
			if (length != 0) {
				System.out.print(" ");
				for (i = 0; i < length; i++) {
					tempParent[i] = tempChild[i];
				}
			}
		}
	}

	/**
	 * Build relation by input data
	 * 
	 * @param relation
	 * @param isLeaf
	 * @param no_leafNode
	 * @param sc
	 */
	public static void buildRelationShip(Map<Integer, Integer> relation, Map<Integer, Boolean> isLeaf, int no_leafNode,
			Scanner sc) {
		for (int i = 0; i < no_leafNode; i++) {
			int parentId = sc.nextInt();
			int childMount = sc.nextInt();
			parentId--;
			isLeaf.put(parentId, false);
			for (int j = 0; j < childMount; j++) {
				int childId = sc.nextInt();
				childId--;
				relation.put(childId, parentId);
			}
		}
	}

	/**
	 * Create keys of map
	 * 
	 * @param totalNodes
	 * @return
	 */
	public static List<Integer> createKeys(int totalNodes) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < totalNodes; i++) {
			list.add(i);
		}
		return list;
	}

	/**
	 * Initialize relation map
	 * 
	 * @param map
	 * @param keys
	 * @param initliazedValue
	 */
	public static void initIntegerKeyMap(Map<Integer, Integer> map, List<Integer> keys, int initliazedValue) {
		for (Integer key : keys) {
			map.put(key, initliazedValue);
		}
	}

	/**
	 * Initialize isLeaf map
	 * 
	 * @param map
	 * @param keys
	 * @param initliazedValue
	 */
	public static void initBooleanMap(Map<Integer, Boolean> map, List<Integer> keys, Boolean initliazedValue) {
		for (Integer key : keys) {
			map.put(key, initliazedValue);
		}
	}
}
