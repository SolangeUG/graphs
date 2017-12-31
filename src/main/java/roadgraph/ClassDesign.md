Class Design Assignment
----

#### New Classes

**`MapEdge.java`**

This is a fairly simple class that represents a street/road segment (an edge) in a graph of geographic points.

As properties, it has:
- a start geographic point
- and end geographic point
- a road name
- a road type
- and a length (distance between the start and end points)

As methods, it only has getter methods for the properties above, and an overridden `MapEdge::toString()` for debugging reasons.

**`MapVertex.java`**

This also is a simple class that represents an intersection (a node or vertex) in a graph of geographic points.

As properties, it has:
- a location (its geographic point on the map)
- a list of adjacent edges

And as methods, it only has getter methods for the two properties above, and an overridden `MapVertex::toString()` for debugging reasons.
	
#### MapGraph
		
With the new classes to hold the data needs for this assignment, I chose to use a `HashMap<GeographicPoint, MapVertex>` to represent the graph's vertices (nodes). 
Each entry of the hashmap corresponds to a vertex in the graph.

With this representation, implementing methods like `MapGraph::getNumVertices`, `MapGraph::getVertices`, `MapGraph::NumEdges` becomes fairly easy.

As for `MapGrah::addVertex`, if the supplied argument is valid, then a new MapVertex is created and added to the hashmap.
And for `MapGraph::addEdge`, when the supplied parameter values are valid, a new MapEdge is created and then added to the corresponding MapVertex list of edges. 
An additional private method was added to verify the validity of the arguments supplied by the calling client code to the `MapGraph::addEdge` method.

In implementing the `MapGraph::bfs` method, I followed the guidelines from this week's Maze example. 
This led me to adding two more private methods: `MapGraph::bfsSearch` and `MapGraph::constructPath`.

`MapGraph::bfsSearch` only purpose is to find a path between the start vertex and the goal vertex using the Breadth-First algorithm.
`MapGraph::constructPath` reconstructs the path from those two vertices if it exists.


#### Design justification

For this assignment, we needed to store data about geographic locations and connecting those geographic locations in a graph.
Therefore, in graphs (as a data structure) terminology, a vertex (node) seemed suitable to hold geographic location data, whereas an edge would represent the connecting structure between the vertices.

And since a vertex "knows" its adjacent edges, it made it quite simple to represent the map graph in terms of its vertices alone. 
Therefore, a hashmap seemed suitable for such a representation, since it's fast to read, add and retrieve information from it (those are Θ(1) operations
for a hashmap), compared to a list for example - where those operations would be Θ(n) operations, and n the number of vertices involved.
		
			 