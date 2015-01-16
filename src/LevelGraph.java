/**
 * This class file is used to construct the level graph from any graph, using
 * BFS technique.
 */

import java.util.ArrayList;

/**
 * This class file is used to construct the level graph from any graph, using
 * BFS technique. This class inherits the properties of a graph.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class LevelGraph extends Graph {

	private int[] layerNumberForNodes;

	/**
	 * This method is used to set the values in the level graph
	 * 
	 * @param nodes
	 *            - The nodes in the level graph
	 * @param adjacencyMatrix
	 *            - The adjacency matrix of a level graph
	 * @param layerNumberForNodes
	 *            - The layer number of each of the node in the level graph
	 */
	public LevelGraph(final String[] nodes, final int[][] adjacencyMatrix,
			final int[] layerNumberForNodes) {
		super(nodes, adjacencyMatrix);
		this.layerNumberForNodes = layerNumberForNodes;
	}

	/**
	 * This method is used to get the layer number of a particular node
	 * 
	 * @param index
	 *            - The index of the node
	 * @return - The layer number of the node
	 */
	public int getLayerNumberForNodes(final int index) {
		return layerNumberForNodes[index];
	}

	/**
	 * This method is used to check if we are stuck at a given location in the
	 * graph, i.e., if there are not outgoing edges in the graph to reach at the
	 * sink.
	 * 
	 * @param location
	 *            - The location for which we need to determine if we are stuck
	 *            or not.
	 * @return - Returns a value true, if we are stuck else returns false.
	 */
	public boolean isStuck(final int location) {
		for (int i = 0; i < this.getNumberOfNodes(); i++) {
			if (this.getValueInAdjacencyMatrix(location, i) == 1)
				return false;
		}
		return true;
	}

	/**
	 * This method is used to create the level graph, given the residual graph
	 * 
	 * @param residualGraph
	 *            - The residual graph, for which the level graph has to be
	 *            constructed
	 * @return - Returns the level graph constructed from the residual graph
	 */
	public static LevelGraph create(final Graph residualGraph) {

		// Constructing nodes for level graph
		String[] nodesInLevelGraph = new String[residualGraph
				.getNumberOfNodes()];
		for (int i = 0; i < residualGraph.getNumberOfNodes(); i++) {
			nodesInLevelGraph[i] = residualGraph.getNode(i);
		}

		// Setting dicovered of all the nodes to false, execpt for the source
		// node.
		boolean[] discovered = new boolean[residualGraph.getNumberOfNodes()];
		for (int i = 0; i < residualGraph.getNumberOfNodes(); i++)
			discovered[i] = false;
		discovered[0] = true;

		int layerCount = 0;
		ArrayList<ArrayList<Integer>> layerList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> firstLayer = new ArrayList<Integer>();
		firstLayer.add(0);
		layerList.add(firstLayer);

		// Adjacency Matrix and layer number for nodes for a level graph
		int[][] adjacencyMatrixForLevelGraph = new int[residualGraph
				.getNumberOfNodes()][residualGraph.getNumberOfNodes()];
		int[] layerNumberForNodes = new int[residualGraph.getNumberOfNodes()];
		layerNumberForNodes[0] = layerCount;

		// Applying extension of BFS technique to construct the level graph's
		// layer elements
		while (!layerList.get(layerCount).isEmpty()) {
			ArrayList<Integer> layerElements = new ArrayList<Integer>();
			layerList.add(layerCount + 1, layerElements);
			ArrayList<Integer> currentLayer = layerList.get(layerCount);
			for (int i = 0; i < currentLayer.size(); i++) {

				int uIndex = currentLayer.get(i);
				for (int v = 0; v < residualGraph.getNumberOfNodes(); v++) {
					if (residualGraph.getValueInAdjacencyMatrix(uIndex, v) == 1) {
						if (discovered[v] == false) {
							discovered[v] = true;
							layerNumberForNodes[v] = layerCount + 1;
							layerElements.add(v);
						}

						// Not including the back and cross edges.
						if (layerNumberForNodes[v] > layerNumberForNodes[uIndex]) {
							adjacencyMatrixForLevelGraph[uIndex][v] = 1;
						}
					}
				}
			}
			layerCount++;
		}

		// If there is no way to get the sink from the source in the residual
		// graph
		if (discovered[residualGraph.getNumberOfNodes() - 1] == false) {
			return null;
		}

		// Constructing level graph from all of the above computed values
		LevelGraph levelGraph = new LevelGraph(nodesInLevelGraph,
				adjacencyMatrixForLevelGraph, layerNumberForNodes);
		return levelGraph;
	}
}
