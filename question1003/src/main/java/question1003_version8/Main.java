package question1003_version8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 
 * @author ygh Jun 9, 2017 TODO Only get 13 scores
 */
public class Main {

	public static void main(String[] args) {
		/**
		 * A list to store all cities
		 */
		// List<City> cityList = new ArrayList<City>();
		Map<Integer, City> cityMap = new HashMap<Integer, City>();
		Scanner sc = new Scanner(System.in);
		int totalCity = sc.nextInt();
		int totalEdge = sc.nextInt();
		int source = sc.nextInt();
		int destination = sc.nextInt();
		for (int i = 0; i < totalCity; i++) {
			City city = new City();
			city.setId(i);
			city.setTeams(sc.nextInt());
			cityMap.put(i, city);
		}
		/**
		 * Insert edge into graph,the graph is no-directed
		 */
		for (int i = 0; i < totalEdge; i++) {
			int id = sc.nextInt();
			int des = sc.nextInt();
			int distance = sc.nextInt();
			AdjacentCity ac = new AdjacentCity();
			ac.setCity(cityMap.get(des));
			ac.setDistance(distance);
			cityMap.get(id).getAdjacentCities().add(ac);

			ac = new AdjacentCity();
			ac.setCity(cityMap.get(id));
			ac.setDistance(distance);
			cityMap.get(des).getAdjacentCities().add(ac);

		}
		sc.close();
		/**
		 * @param shortestPathAmount
		 *            A map to store shortest amounts from staring point to
		 *            other point which can be connected from stating point. Key
		 *            is the city id,value is integer value indicating the
		 *            shortest amounts
		 * @param gatherTeams
		 *            A map to store the gather teams from starting point to
		 *            other points which can be connected from stating point.
		 *            Key is the city id,value is integer value indicating the
		 *            gather teams amounts
		 * @param distance
		 *            A map to store the shortest path length from starting to
		 *            other points which can be connected from stating point.
		 *            Key is the city id,value is integer value indicating the
		 *            gather teams
		 */
		Map<Integer, Integer> shortestPathAmount = new HashMap<Integer, Integer>();
		Map<Integer, Integer> gatherTeams = new HashMap<Integer, Integer>();
		Map<Integer, Integer> distance = new HashMap<Integer, Integer>();
		/**
		 * Use a list to create keys
		 */
		List<Integer> keys = new ArrayList<Integer>();
		for (int i = 0; i < totalCity; i++) {
			keys.add(i);
		}
		/**
		 * Initialize map with keys and value
		 */
		fullMap(distance, keys, Integer.MAX_VALUE);
		fullMap(gatherTeams, keys, 0);
		fullMap(shortestPathAmount, keys, 0);
		save(cityMap, source, shortestPathAmount, gatherTeams, distance);
		System.out.println(shortestPathAmount.get(destination) + " " + gatherTeams.get(destination));
	}

	/**
	 * A change method for Dijkstra
	 * 
	 * @param cityMap
	 *            A map to store city information,key is the id of city, value
	 *            is the object of <code>{@link City}</code>
	 * @param startPointId
	 *            The city of starting point
	 * @param shortestPathAmount
	 *            A map to store shortest amounts from staring point to other
	 *            point which can be connected from stating point. Key is the
	 *            city id,value is integer value indicating the shortest amounts
	 * @param gatherTeams
	 *            A map to store the gather teams from starting point to other
	 *            points which can be connected from stating point. Key is the
	 *            city id,value is integer value indicating the gather teams
	 *            amounts
	 * @param distance
	 *            A map to store the shortest path length from starting to other
	 *            points which can be connected from stating point. Key is the
	 *            city id,value is integer value indicating the gather teams
	 *            amounts
	 */
	public static void save(Map<Integer, City> cityMap, Integer startPointId, Map<Integer, Integer> shortestPathAmount,
			Map<Integer, Integer> gatherTeams, Map<Integer, Integer> distance) {
		/**
		 * Define map to flag whether the point has been accessed,true indicates
		 * the point has been accessed,otherwise not
		 */
		Map<Integer, Boolean> isVisited = new HashMap<Integer, Boolean>();
		for (Integer key : cityMap.keySet()) {
			isVisited.put(key, false);
		}
		List<AdjacentCity> acList;
		/**
		 * Initialize starting point information
		 */
		distance.put(startPointId, 0);
		shortestPathAmount.put(startPointId, 1);
		isVisited.put(startPointId, true);
		gatherTeams.put(startPointId, cityMap.get(startPointId).getTeams());
		acList = cityMap.get(startPointId).getAdjacentCities();
		/**
		 * Initialize starting point adjacent point
		 */
		for (AdjacentCity ac : acList) {
			distance.put(ac.getCity().getId(), ac.getDistance());
			shortestPathAmount.put(ac.getCity().getId(), shortestPathAmount.get(startPointId));
			gatherTeams.put(ac.getCity().getId(),
					gatherTeams.get(startPointId) + cityMap.get(ac.getCity().getId()).getTeams());
		}
		while (true) {
			Integer id = findMinDist(distance, isVisited);
			if (id == -1) {
				return;
			}
			isVisited.put(id, true);
			acList = cityMap.get(id).getAdjacentCities();
			for (AdjacentCity ac : acList) {
				if (isVisited.get(ac.getCity().getId())) {
					continue;
				}
				/**
				 * If shortest is not equal,update teamNumber into add and
				 * shortestPathNumber into parent vertex
				 */
				if (distance.get(id) + ac.getDistance() < distance.get(ac.getCity().getId())) {
					distance.put(ac.getCity().getId(), distance.get(id) + ac.getDistance());
					shortestPathAmount.put(ac.getCity().getId(), shortestPathAmount.get(id));
					gatherTeams.put(ac.getCity().getId(),
							gatherTeams.get(id) + cityMap.get(ac.getCity().getId()).getTeams());
					/**
					 * If the shortest equal,update the shortestPathNumber into
					 * sum of the itself and current point
					 *
					 */
				} else if (distance.get(id) + ac.getDistance() == distance.get(ac.getCity().getId())) {
					distance.put(ac.getCity().getId(), distance.get(id) + ac.getDistance());
					shortestPathAmount.put(ac.getCity().getId(),
							shortestPathAmount.get(id) + shortestPathAmount.get(ac.getCity().getId()));

					if (gatherTeams.get(ac.getCity().getId()) < gatherTeams.get(id)
							+ cityMap.get(ac.getCity().getId()).getTeams()) {
						gatherTeams.put(ac.getCity().getId(),
								gatherTeams.get(id) + cityMap.get(ac.getCity().getId()).getTeams());
					}
				}
			}
		}

	}

	/**
	 * Find the the index of point in the graph whose distance is minimal and
	 * has not been accessed.
	 * 
	 * @param distance
	 *            A map to store the shortest path length from starting to other
	 *            points which can be connected from stating point. Key is the
	 *            city id,value is integer value indicating the gather teams
	 *            amounts
	 * @param isVisited
	 *            map to flag whether the point has been accessed,true indicates
	 *            the point has been accessed,otherwise not
	 * @return A id of city whose distance is minimal and is not be accessed
	 */
	public static Integer findMinDist(Map<Integer, Integer> distance, Map<Integer, Boolean> isVisited) {
		Integer minDis = Integer.MAX_VALUE;
		Integer id = -1;
		for (Entry<Integer, Integer> entry : distance.entrySet()) {
			if (isVisited.get(entry.getKey())) {
				continue;
			}
			int dis = entry.getValue();
			if (dis < minDis) {
				minDis = dis;
				id = entry.getKey();
			}
		}
		if (minDis == Integer.MAX_VALUE) {
			id = -1;
		}
		return id;

	}

	/**
	 * A method to initialize map
	 * 
	 * @param map
	 *            A map need to be initialized
	 * @param keys
	 *            A list of map keys
	 * @param value
	 *            The value the map need to initialize
	 */
	public static void fullMap(Map<Integer, Integer> map, List<Integer> keys, int value) {
		for (Integer key : keys) {
			map.put(key, value);
		}
	}

}

class City {

	/**
	 * The id of the city
	 */
	private Integer id;
	/**
	 * The amounts of teams in this city.
	 */
	private Integer teams;

	/**
	 * A list of the adjacent cities
	 */
	private List<AdjacentCity> adjacentCities;

	public City() {
		super();
		this.adjacentCities = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeams() {
		return teams;
	}

	public void setTeams(Integer teams) {
		this.teams = teams;
	}

	public List<AdjacentCity> getAdjacentCities() {
		return adjacentCities;
	}

	public void setAdjacentCities(List<AdjacentCity> adjacentCities) {
		this.adjacentCities = adjacentCities;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(this.getId() + ":");
		for (AdjacentCity ac : adjacentCities) {
			sb.append(ac.getCity().getId() + " ");
		}
		return sb.toString();
	}

}

/**
 * A class to indicate adjacent cities
 * 
 * @author ygh Jun 9, 2017
 */
class AdjacentCity {
	/**
	 * A city of adjacent self
	 */
	private City city;
	/**
	 * The distance between source and adjacent city
	 */
	private Integer distance;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "AdjacentCity [city=" + city + ", distance=" + distance + "]";
	}

}
