package roadgraph;


import geography.GeographicPoint;
import util.GraphLoader;

import java.util.*;
import java.util.function.Consumer;

/**
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between
 * @author UCSD MOOC development team
 * @author Solange U. Gasengayire
 *
 */
public class MapGraph {

	// DONE: Add your member variables here in WEEK 3
	private HashMap<GeographicPoint, MapVertex> mapVertices;

	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph() {
		// DONE: Implement in this constructor in WEEK 3
		this.mapVertices = new HashMap<>();
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		// DONE: Implement this method in WEEK 3
		return mapVertices.size();
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices() {
		// DONE: Implement this method in WEEK 3
		return new HashSet<>(mapVertices.keySet());
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges() {
		// DONE: Implement this method in WEEK 3
		int numEdges = 0;
		for (MapVertex vertex : mapVertices.values()) {
			numEdges += vertex.getNumEdges();
		}
		return numEdges;
	}

	
	
	/**
	 * Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * 		   was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location) {
		// DONE: Implement this method in WEEK 3
		boolean result = false;
		if (location != null && ! mapVertices.containsKey(location)) {
			MapVertex vertex = new MapVertex(location);
			MapVertex added = mapVertices.put(location, vertex);
			result = vertex.equals(added);
		}
		return result;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   	   added as nodes to the graph, if any of the arguments is null,
	 *   	   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		// DONE: Implement this method in WEEK 3
		boolean valid = verifyArguments(from, to, roadName, roadType, length);
		if (! valid) {
			String message = "One or more supplied arguments is (are) not valid.";
			throw new IllegalArgumentException(message);
		}

		MapEdge edge = new MapEdge(from, to, roadName, roadType, length);
		MapVertex start = mapVertices.get(from);
		start.addEdge(edge);
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// DONE: Implement this method in WEEK 3
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		// Variable initialization
		MapVertex sVertex = mapVertices.get(start);
		MapVertex gVertex = mapVertices.get(goal);

		if (start == null || goal == null || sVertex == null || gVertex == null) {
			return null;
		}

		// Path search
		HashMap<MapVertex, MapVertex> parentMap = new HashMap<>();
		boolean found = bfsSearch(sVertex, gVertex, parentMap, nodeSearched);

		if (! found) {
			return null;
		}

		// Path reconstruction
		return constructPath(sVertex, gVertex, parentMap);
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 4

		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched) {
		// TODO: Implement this method in WEEK 4
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/**
	 * Determine if the arguments of the addEdge method are valid.
	 * Valid arguments are :
	 * 		- all NOT NULL
	 * 		- "from" and "to" geographic points must already be part of the graph
	 * 		- and the length must be NOT less than 0
	 *
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @return true if ALL the arguments are valid
	 * 		   false otherwise
	 */
	private boolean verifyArguments(GeographicPoint from, GeographicPoint to,
									String roadName, String roadType, double length) {

		boolean result =
				from == null
				|| to == null
				|| roadName == null
				|| roadType == null
				|| !mapVertices.containsKey(from)
				|| !mapVertices.containsKey(to)
				|| length < 0;
		return (! result);
	}

	/**
	 * The actual Breadth-First seach method
	 * @param start	The starting map vertex (intersection)
	 * @param goal	The goal map vertex (intersection)
	 * @param parentMap	The map that links each vertex to the one
	 * 					from which it was discovered
	 * @param nodeSearched A hook for visualization
	 * @return true if a path exists between the start and the goal vertices
	 * 		   false otherwise
	 */
	private boolean bfsSearch(MapVertex start, MapVertex goal,
							  HashMap<MapVertex, MapVertex> parentMap,
							  Consumer<GeographicPoint> nodeSearched) {

		boolean found = false;

		HashSet<MapVertex> visited = new HashSet<>();
		Queue<MapVertex> toExplore = new LinkedList<>();
		toExplore.add(start);

		while (! toExplore.isEmpty()) {
			MapVertex current = toExplore.poll();

			// For visualization purposes.
			nodeSearched.accept(current.getLocation());

			if (current == goal) {
				found = true;
				break;
			}

			List<MapVertex> neighbors = getNeighbors(current);
			ListIterator<MapVertex> it = neighbors.listIterator(neighbors.size());
			while (it.hasPrevious()) {
				MapVertex next = it.previous();
				if (! visited.contains(next)) {
					visited.add(next);
					parentMap.put(next, current);
					toExplore.add(next);
				}
			}
		}

		return found;
	}

	/**
	 * Return the list of adjacent vertices to the input vertex
	 * @param vertex The input vertex
	 * @return the list of the input vertex neighbors
	 */
	private List<MapVertex> getNeighbors(MapVertex vertex) {
		List<MapVertex> neighbors = new ArrayList<>();

		// Neighbor vertices are geographic points at the end of
		// each of this vertex edges
		for (MapEdge edge : vertex.getEdges()) {
			MapVertex a = mapVertices.get(edge.getStart());
			MapVertex b = mapVertices.get(edge.getEnd());
			if (! a.equals(vertex) && ! neighbors.contains(a)) {
				neighbors.add(a);
			}
			if (! b.equals(vertex) && ! neighbors.contains(b)) {
				neighbors.add(b);
			}
		}
		return neighbors;
	}

	/**
	 * This method constructs the path found by the BFS method
	 * @param start	The starting map vertex (intersection)
	 * @param goal	The goal map vertex (intersection)
	 * @param parentMap The map that links each vertex to the one
	 * 					from which it was discovered
	 * @return the reconstructed path from the start vertex to the goal vertex
	 */
	private List<GeographicPoint> constructPath(MapVertex start, MapVertex goal,
												HashMap<MapVertex, MapVertex> parentMap) {
		LinkedList<GeographicPoint> path = new LinkedList<>();
		// Reconstruction of the path from the goal vertex back to the start vertex
		MapVertex current = goal;
		while (current != start) {
			path.addFirst(current.getLocation());
			current = parentMap.get(current);
		}
		path.addFirst(start.getLocation());
		return path;
	}


	/**
	 * Main tester method
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		/*MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		*/
		
	}
	
}
