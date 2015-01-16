/**
 * This class file is used to implement the maximal matching algorithm.
 */

import java.util.ArrayList;

/**
 * This class implements the Ford-Fulkerson algorithm, using shortest augmenting
 * paths method to find the maximal matching for a bipartite graph.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class MaximalMatchingAlgorithm {

	/**
	 * This method is used to perform the maximal matching algorithm
	 * 
	 * @param inputGraph
	 *            - The input graph
	 * @return - Returns the residual graph, which contains the maximal
	 *         matching.
	 */
	public Graph performMaximalMatching(final Graph inputGraph) {

		// Creating residual graph
		Graph residualGraph = createResidualGraph(inputGraph);

		boolean done = false;
		while (!done) {
			// Constructing level graph LG from Gf using breadth-first search
			// (delete back and cross edges).
			LevelGraph levelGraph = LevelGraph.create(residualGraph);

			// If no path exists from source to sink then level graph would not
			// be created, thus it would be null.
			if (levelGraph == null) {
				done = true;
				break;
			}

			// Initializing location and path values.
			int location = 0;
			ArrayList<Edge> path = new ArrayList<Edge>();

			while (!levelGraph.isStuck(0)) {
				if (location == levelGraph.getNumberOfNodes() - 1) {
					// Augmenting the flow with path, updating Residual Graph
					// and deleting the edges from level graph
					for (int i = 0; i < path.size(); i++) {
						Edge edge = path.get(i);
						int startNode = edge.getStartNode();
						int endNode = edge.getEndNode();
						residualGraph.setValueInAdjacencyMatrix(startNode,
								endNode, 0);
						residualGraph.setValueInAdjacencyMatrix(endNode,
								startNode, 1);
						levelGraph.setValueInAdjacencyMatrix(startNode,
								endNode, 0);
					}
					location = 0;
					path = new ArrayList<Edge>();

				} else {
					// Retreating if stuck, deleting the current location and
					// incoming edges from LG and deleting edges from the path.
					if (levelGraph.isStuck(location)) {
						Edge edge = path.remove(path.size() - 1);
						location = edge.getStartNode();
						levelGraph.setValueInAdjacencyMatrix(
								edge.getStartNode(), edge.getEndNode(), 0);

					} else {
						//Advancing along some edge in LG.
						for (int i = 0; i < levelGraph.getNumberOfNodes(); i++) {
							if (levelGraph.getValueInAdjacencyMatrix(location,
									i) == 1) {
								Edge edge = new Edge();
								edge.setStartNode(location);
								edge.setEndNode(i);
								path.add(edge);  //Updating path.
								location = i;   //Updating location.
								break;
							}
						}
					}
				}

			}
		}

		return residualGraph;
	}

	/**
	 * This method is used to create the residual graph for the given input
	 * graph
	 * 
	 * @param graph
	 *            - The input graph for which the residual graph has to be
	 *            constructed
	 * @return - Returns the residual graph
	 */
	private static Graph createResidualGraph(final Graph graph) {

		// Adding source and sink nodes
		String[] nodesInResidualGraph = new String[graph.getNumberOfNodes() + 2];
		int[][] adjacencyMatrixForResidualGraph = new int[graph
				.getNumberOfNodes() + 2][graph.getNumberOfNodes() + 2];
		String sourceNode = "Source";
		String sinkNode = "Sink";
		nodesInResidualGraph[0] = sourceNode;
		for (int i = 1; i <= graph.getNumberOfNodes(); i++)
			nodesInResidualGraph[i] = graph.getNode(i - 1);
		nodesInResidualGraph[graph.getNumberOfNodes() + 1] = sinkNode;

		// Adding appropriate edges from source and to sink nodes
		for (int i = 1; i < (nodesInResidualGraph.length / 2); i++) {
			adjacencyMatrixForResidualGraph[0][i] = 1;
		}
		for (int i = (nodesInResidualGraph.length / 2); i < nodesInResidualGraph.length - 1; i++)
			adjacencyMatrixForResidualGraph[i][graph.getNumberOfNodes() + 1] = 1;
		for (int i = 0; i < graph.getNumberOfNodes(); i++) {
			for (int j = 0; j < graph.getNumberOfNodes(); j++) {
				adjacencyMatrixForResidualGraph[i + 1][j + 1] = graph
						.getValueInAdjacencyMatrix(i, j);
			}
		}

		// Creating the residual graph using the above computed values.
		Graph residualGraph = new Graph(nodesInResidualGraph,
				adjacencyMatrixForResidualGraph);
		return residualGraph;
	}
}
