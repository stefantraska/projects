package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		adjacencyMap = new HashMap<>();
		dataMap = new HashMap<>();
	}
	
	public void addVertex(String vertexName, E data) {
		dataMap.put(vertexName, data);
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
	}
	
	public void addDirectedEdge(String startVertexName, 
			String endVertexName, int cost) {
		adjacencyMap.get(startVertexName).put(endVertexName, cost);
	}
	
	public String toString() {
		
		String result = "";
		
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		};
		
		// Gets vertices and sorts them
		ArrayList<String> vertices = new ArrayList<>();
		for(String vertex : dataMap.keySet()) {
			vertices.add(vertex);
		}
		vertices.sort(comparator);
		
		result += "Vertices: ";
		result += vertices.toString();
		result += "\nEdges:";
		
		// Prints edges of each vertex in sorted order
		for(String vertex : vertices) {
			result += "\nVertex(" + vertex + ")--->{";
			
			ArrayList<String> adjacentValues= new ArrayList<>();
			for(String adjacent : adjacencyMap.get(vertex).keySet()) {
				adjacentValues.add(adjacent);
			}
			adjacentValues.sort(comparator);
			
			for(int i = 0; i < adjacentValues.size()-1; i++) {
				result += adjacentValues.get(i) + "=" 
						+ adjacencyMap.get(vertex).get(adjacentValues.get(i))
						+ ", ";
			}
			if(adjacentValues.size() != 0) {
				result += adjacentValues.get(adjacentValues.size()-1) + "=" 
						+ adjacencyMap.get(vertex).get(adjacentValues.get(adjacentValues.size()-1))
						+ "}";
			}else {
				result += "}";
			}
			
		}
		
		return result;
	}
	
	public Map<String, Integer> getAdjacentVertices(String vertexName) {
		return adjacencyMap.get(vertexName);
	}
	
	public int getCost(String startVertexName,
	          String endVertexName) {
		return adjacencyMap.get(startVertexName).get(endVertexName);
	}
	
	public Set<String> getVertices() {
		return adjacencyMap.keySet();
	}
	
	public E getData(String vertex) {
		return dataMap.get(vertex);
	}
	
	public void doDepthFirstSearch(String startVertexName,
            CallBack<E> callback) {
		
		ArrayList<String> visited = new ArrayList<>();
		Deque<String> discovered = new ArrayDeque<>();
		discovered.push(startVertexName);
		
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		};
		
		// Core algorithm of depth first search
		while(discovered.size() != 0) {
			String X = discovered.pop();
			if(visited.indexOf(X) == -1) {
				visited.add(X);
				callback.processVertex(X, dataMap.get(X));
				
				ArrayList<String> adjacentValues = new ArrayList<>();
				for(String Y : adjacencyMap.get(X).keySet()) {
					if(visited.indexOf(Y) == -1) {
						adjacentValues.add(Y);
					}
				}
				adjacentValues.sort(comparator);
				for(String Y : adjacentValues) {
					discovered.push(Y);
				}
			}
		}
		
	}
	
	public void doBreadthFirstSearch(String startVertexName,
            CallBack<E> callback) {
		
		ArrayList<String> visited = new ArrayList<>();
		ArrayList<String> discovered = new ArrayList<>();
		discovered.add(startVertexName);
		
		Comparator<String> comparator = new Comparator<String>() {
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		};
		
		// Core algorithm of breadth first search
		while(discovered.size() != 0) {
			String X = discovered.remove(0);
			if(visited.indexOf(X) == -1) {
				visited.add(X);
				callback.processVertex(X, dataMap.get(X));
				
				ArrayList<String> adjacentValues = new ArrayList<>();
				for(String Y : adjacencyMap.get(X).keySet()) {
					if(visited.indexOf(Y) == -1) {
						adjacentValues.add(Y);
					}
				}
				adjacentValues.sort(comparator);
				for(String Y : adjacentValues) {
					discovered.add(Y);
				}
			}
		}
		
	}
	
	public int doDijkstras(String startVertexName, String endVertexName,
            ArrayList<String> shortestPath) {
		
		// S is the list of checked vertices
		ArrayList<String> S = new ArrayList<>();
		HashMap<String, Integer> cost = new HashMap<>();
		for(String vertex : dataMap.keySet()) {
			cost.put(vertex, Integer.MAX_VALUE);
		}
		
		HashMap<String, String> predecessor = new HashMap<>();
		for(String vertex : dataMap.keySet()) {
			predecessor.put(vertex, "");
		}
		
		ArrayList<String> vertices = new ArrayList<>();
		for(String s : cost.keySet()) {
			vertices.add(s);
		}
		cost.put(startVertexName, 0);
		
		String currentSmallestCostVertex = "";
		boolean loopContinues = true;
		
		/*
		 * Core algorithm of Dijkstras - finds shortest path
		 * */
		
		while(!S.contains(endVertexName) && loopContinues) {
			
			// smallestCostVertex represents node K not in S with smallest cost
			String smallestCostVertex = vertices.get(0);
			
			// Finds vertex wth smallest cost
			for(String vertex : vertices) {
				if(cost.get(vertex) < cost.get(smallestCostVertex)) {
					smallestCostVertex = vertex;
				}
			}
			
			// Checks if loop should end
			if(smallestCostVertex.equals(currentSmallestCostVertex)) {
				loopContinues = false;
			}
			currentSmallestCostVertex = smallestCostVertex;

			// Adds smallest cost vertex to list of checked vertices
			S.add(smallestCostVertex);
			vertices.remove(smallestCostVertex);
			
			// For each node J not in S adjacent to K
			for(String J : adjacencyMap.keySet()) {
				if(!S.contains(J)) {
					if(adjacencyMap.get(smallestCostVertex).keySet().contains(J) 
							&& cost.get(smallestCostVertex) + adjacencyMap.get(smallestCostVertex).get(J) 
							< cost.get(J)) {
						cost.put(J, cost.get(smallestCostVertex) + adjacencyMap.get(smallestCostVertex).get(J));
						predecessor.put(J, smallestCostVertex);
					}
				}
			}
		}
		
		/* 
		 * Get the reversed path, reverse it, then
		 * insert to shortestPath.
		 */
		ArrayList<String> reversedPath = new ArrayList<>();
		reversedPath.add(endVertexName);
		currentSmallestCostVertex = "";
		loopContinues = true;
		while(!reversedPath.contains(startVertexName)) {
			if(currentSmallestCostVertex.equals(reversedPath.get(reversedPath.size() - 1))) {
				shortestPath.add("None");
				return -1;
			}
			reversedPath.add(predecessor.get(reversedPath.get(reversedPath.size() - 1)));
		}
		for(int i = reversedPath.size()-1; i >= 0; i--) {
			shortestPath.add(reversedPath.get(i));
		}
		
		return cost.get(endVertexName);
	}

}