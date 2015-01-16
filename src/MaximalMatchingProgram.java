/**
 * This is the main class file which implements the ford fulkerson algorithm 
 * using shortest augmenting path to determine maximal matching 
 * in the given bipartite graph.
 */

/**
 * This is the main program for implementation of the maximal matching
 * algorithm.
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class MaximalMatchingProgram {

	/**
	 * This is the main program for implementation of the maximal matching
	 * algorithm.
	 * 
	 * @param args
	 *            - Command line arguments passed. This is not used in our
	 *            program.
	 */
	public static void main(String[] args) {

		FileInputParser parser = new FileInputParser("program3data.txt");
		Graph inputGraph = parser.getGraph();
		MaximalMatchingAlgorithm maximalMatching = new MaximalMatchingAlgorithm();
		Graph residualGraph = maximalMatching
				.performMaximalMatching(inputGraph);
		printMaximalMatching(residualGraph);
	}

	/**
	 * This method is used to print the set of matched pair of nodes in the
	 * console
	 * 
	 * @param residualGraph
	 *            - The graph after performing the maximal matching algorithm
	 */
	private static void printMaximalMatching(final Graph residualGraph) {
		int size = residualGraph.getNumberOfNodes();
		for (int j = 1; j <= ((size / 2) - 1); j++) {
			for (int i = (size / 2); i <= size - 2; i++) {
				int value = residualGraph.getValueInAdjacencyMatrix(i, j);
				if (value == 1) {
					System.out.println(residualGraph.getNode(j) + " / "
							+ residualGraph.getNode(i));
					break;
				}
			}
		}

	}
}
