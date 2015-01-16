/**
 * This class defines the structure of a graph, and the fields and methods in a graph
 */

/**
 * This class defines the structure of a graph, nodes and edges; and the way the
 * nodes and edges are represented.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class Graph {

	private String[] nodes;
	private int[][] adjacencyMatrix;

	/**
	 * This method is used to set the value for the graph, given its nodes and
	 * edges
	 * 
	 * @param nodes
	 *            - The nodes in the graph
	 * @param edges
	 *            - The edges in the graph
	 */
	public Graph(final String[] nodes, final Edge[] edges) {
		if (nodes == null) {
			throw new IllegalArgumentException(
					"Names of nodes in a graph cannot be null");
		}

		if (edges == null) {
			throw new IllegalArgumentException(
					"Edges in a graph cannot be null");
		}

		this.nodes = nodes;
		this.adjacencyMatrix = new int[this.nodes.length][this.nodes.length];
		for (int i = 0; i < edges.length; i++) {
			int x = edges[i].getStartNode();
			int y = edges[i].getEndNode();
			this.adjacencyMatrix[x - 1][y - 1] = 1;
		}
	}

	/**
	 * This method is used to set the value for the graph, given its nodes and
	 * adjacency matrix
	 * 
	 * @param nodes
	 *            - The nodes in the graph
	 * @param adjacencyMatrix
	 *            - The edges in the graph represented as adjacency matrix
	 */
	public Graph(final String[] nodes, final int[][] adjacencyMatrix) {
		if (nodes == null) {
			throw new IllegalArgumentException(
					"Names of nodes in a graph cannot be null");
		}

		if (adjacencyMatrix == null) {
			throw new IllegalArgumentException(
					"Adjacency matrix for a graph cannot be null");
		}

		this.nodes = nodes;
		this.adjacencyMatrix = adjacencyMatrix;

	}

	/**
	 * This method is used to return the total number of nodes in the graph
	 * 
	 * @return - Number of nodes in the graph
	 */
	public int getNumberOfNodes() {

		return this.nodes.length;
	}

	/**
	 * This method is used to get a node in the graph, given its index
	 * 
	 * @param index
	 *            - Index value for the node
	 * @return - Returns the node
	 */
	public String getNode(final int index) {
		return this.nodes[index];
	}

	/**
	 * This method is used to get the value in the adjacency matrix, if there is
	 * an edge between two nodes in the graph the value is will be 1; otherwise
	 * it will be 0.
	 * 
	 * @param x
	 *            - Index of the start node
	 * 
	 * @param y
	 *            - Index of the end node
	 * 
	 * @return - Returns the value in the adjacency matrix.
	 */
	public int getValueInAdjacencyMatrix(final int x, final int y) {

		return this.adjacencyMatrix[x][y];
	}

	/**
	 * This method is used to set the value in the adjacency matrix, if there is
	 * an edge between two nodes in the graph the value is will be set to 1;
	 * otherwise it will be set to 0.
	 * 
	 * @param x
	 *            - Index of the start node
	 * @param y
	 *            - Index of the end node
	 * @param value
	 *            - The value that has to be set in the adjacency matrix
	 */
	public void setValueInAdjacencyMatrix(final int x, final int y,
			final int value) {
		this.adjacencyMatrix[x][y] = value;
	}

	/**
	 * This method is used to get the string value of node, given its index
	 * value.
	 * 
	 * @param node
	 *            - The node for which the index has to be found.
	 * @return - Returns the index of the node.
	 */
	public int getNodeIndex(final String node) {
		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i].equals(node)) {
				return i;
			}
		}

		return -1;
	}

}
