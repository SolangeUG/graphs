package roadgraph;

import geography.GeographicPoint;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a vertex (an intersection)
 * in a graph of geographic locations.
 * @author Solange U. Gasengayire
 *
 */
public class MapVertex implements Comparable<MapVertex> {

    private GeographicPoint location;
    private List<MapEdge> edges;
    private double distanceToStart;
    private double distanceToGoal;

    /**
     * Create a new vertex (node)
     * @param location This node's geographic location
     */
    public MapVertex(GeographicPoint location) {
        this.location = location;
        this.edges = new LinkedList<>();
        this.distanceToStart = Double.MAX_VALUE;
        this.distanceToGoal = Double.MAX_VALUE;
    }

    /**
     * Return this vertex location
     * @return the location
     */
    GeographicPoint getLocation() {
        return location;
    }

    /**
     * Return a new list of this vertex edges
     * @return the new list of edges
     */
    public List<MapEdge> getEdges() {
        return new LinkedList<>(edges);
    }

    /**
     * Return this node's distance to the start node
     * @return the distanceToSource
     */
    double getDistanceToStart() {
        return distanceToStart;
    }

    /**
     * Update this node's distance to the start node
     * @param distance the new distance value to start node
     */
    void setDistanceToStart(double distance) {
        this.distanceToStart = distance;
    }

    /**
     * Return this node's predicted distance to the start node
     * @return the predicted distance
     */
    double getDistanceToGoal() {
        return distanceToGoal;
    }

    /**
     * Update this node's predicted distance
     * @param distanceToGoal the new predicted distance value
     */
    void setDistanceToGoal(double distanceToGoal) {
        this.distanceToGoal = distanceToGoal;
    }

    /**
     * Add an edge to this vertex list of edges
     * @param edge	The edge to be added
     * @return	true if the edge was successfully added
     * 			false otherwise
     */
    boolean addEdge(MapEdge edge) {
        boolean result = false;
        if (! edges.contains(edge)) {
            result = edges.add(edge);
        }
        return result;
    }

    /**
     * Return the number of this vertex list of edges
     * @return the number of edges
     */
    int getNumEdges() {
        return edges.size();
    }

    /**
     * Return the string representation of this vertex
     * @return this vertex string representation
     */
    public String toString() {
		/*
		double actual = this.getDistance();
		double predic = actual + this.getDistanceToGoal();
		 */
        String result = location.toString();
		/*
		result += " intersects streets: ";
		for (MapEdge edge : this.getEdges()) {
			result += edge.getRoadName() + " ";
		}
		result += "\n actual = " + actual;
		result += "\n predic = " + predic;
		result += "\n";
		*/
        return result;
    }

    /**
     * Compare this object with the specified object for order.
     * @param other The specified object to be compared
     * @return the result of the comparison
     */
    @Override
    public int compareTo(MapVertex other) {
        int result = 0;
        double thisTotalDistance = distanceToStart + distanceToGoal;
        double otherTotalDistance = other.getDistanceToStart() + other.getDistanceToGoal();

        if (thisTotalDistance < otherTotalDistance) {
            result = -1;
        } else if (thisTotalDistance > otherTotalDistance) {
            result = 1;
        }
        return result;
    }
}
