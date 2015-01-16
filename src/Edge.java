/**
 * This class file defines fields and the methods, which every edge in the graph 
 * will have.
 */

/**
 * This class defines the structure of an edge in a graph, i.e.,the start node
 * and the end node
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class Edge {

	private int startNode;
	private int endNode;

	/**
	 * This method is used to get the start node value.
	 * 
	 * @return - Returns the start node value.
	 */
	public int getStartNode() {
		return startNode;
	}

	/**
	 * This method is used to set the start node value.
	 * 
	 * @param startNode
	 *            - The value that has to be set in the start node.
	 */
	public void setStartNode(final int startNode) {
		this.startNode = startNode;
	}

	/**
	 * This method is used to get the end node value.
	 * 
	 * @return - Returns the end node value.
	 */
	public int getEndNode() {
		return endNode;
	}

	/**
	 * This method is used to set the end node value.
	 * 
	 * @param endNode
	 *            - The value that has to be set in the end node.
	 */
	public void setEndNode(final int endNode) {
		this.endNode = endNode;
	}

}
