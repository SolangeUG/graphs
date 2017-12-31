package roadgraph;

import geography.GeographicPoint;

/**
 * This class represents an edge (street/road segment)
 * in a graph of geographic locations.
 * @author Solange U. Gasengayire
 *
 */
public class MapEdge {

    private GeographicPoint start;
    private GeographicPoint end;
    private String roadName;
    private String roadType;
    private double length;

    /**
     * Create a new edge
     * @param start	The starting location of this edge
     * @param end	The end location of this edge
     * @param name	The road name of this edge
     * @param type	The road type of this edge
     * @param length The length of this edge
     */
    public MapEdge(GeographicPoint start, GeographicPoint end,
                   String name, String type, double length) {

        this.start = start;
        this.end = end;
        this.roadName = name;
        this.roadType = type;
        this.length = length;
    }

    /**
     * Return this edge's start location
     * @return the start geographic location
     */
    public GeographicPoint getStart() {
        return start;
    }

    /**
     * Return this edge's end location
     * @return the end geographic location
     */
    public GeographicPoint getEnd() {
        return end;
    }

    /**
     * Return this edge's name
     * @return the road name
     */
    public String getRoadName() {
        return roadName;
    }

    /**
     * Return this edge's type
     * @return the road type
     */
    public String getRoadType() {
        return roadType;
    }

    /**
     * Return this edge's length
     * @return the length
     */
    public double getLength() {
        return length;
    }

    /**
     * Return the string representation of this edge
     * @return this edge's string representation
     */
    public String toString() {
        String result = "[SP: " + start.toString() + " | ";
        result += "RN: " + roadName + " | ";
        result += "RT: " + roadType + " | ";
        result += "EP: " + end.toString() + "]";
        return result;
    }
}
