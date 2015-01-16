/**
 * This class file is used to implement the File Input Parser,
 * and it creates the given input graph, by parsing the given input 
 * file to get the total number of nodes, total number of edges, 
 * and the nodes and edges between the nodes
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class parses the input file and creates the input graph, with its nodes
 * and corresponding edges
 * 
 * @author Shalini Ramachandra #1465487
 * 
 */
public class FileInputParser {
	
	private static final String spaceDelimiter = "\\s+";
	private String filepath;
	private int numberOfNodes;
	private int numberOfEdges;
	private Graph graph;

	/**
	 * This constructor sets the value for the class variables.
	 * 
	 * @param filePath
	 *            - The input file, which has to be parsed.
	 */

	public FileInputParser(final String filePath) {

		if (filePath == null || filePath.length() <= 0) {
			throw new IllegalArgumentException(
					"File path cannot be null or empty");
		}

		this.filepath = filePath;
		this.numberOfNodes = -1;
		this.numberOfEdges = -1;
	}

	/**
	 * This method is used to get the graph
	 * 
	 * @return - returns the graph
	 */
	public Graph getGraph() {
		if (this.graph == null)
			this.parseFile();
		return this.graph;
	}

	/**
	 * This method parses the input file and creates the input graph with nodes
	 * and its corresponding edges
	 */
	private void parseFile() {
		Exception exception = null;
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

		try {
			fileReader = new FileReader(this.filepath);
			bufferedReader = new BufferedReader(fileReader);

			this.numberOfNodes = Integer.parseInt(bufferedReader.readLine()
					.trim());
			String[] nodeNames = new String[this.numberOfNodes];
			for (int i = 0; i < this.numberOfNodes; i++)
				nodeNames[i] = bufferedReader.readLine().trim();

			this.numberOfEdges = Integer.parseInt(bufferedReader.readLine()
					.trim());
			Edge[] edges = new Edge[this.numberOfEdges];
			for (int i = 0; i < this.numberOfEdges; i++) {
				String edge = bufferedReader.readLine().trim();
				String[] splitArray = edge.trim().split(spaceDelimiter);
				edges[i] = new Edge();
				edges[i].setStartNode(Integer.parseInt(splitArray[0]));
				edges[i].setEndNode(Integer.parseInt(splitArray[1]));

			}

			this.graph = new Graph(nodeNames, edges);

		} catch (FileNotFoundException e) {
			System.out.println("Invalid file path or name.");
			exception = e;
		} catch (NumberFormatException e) {
			System.out
					.println("Invalid data in input file, number is expected.");
			exception = e;
		} catch (IOException e) {
			System.out.println("Cannot read the file, I/O Exception occurred.");
			exception = e;
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}

				if (bufferedReader != null) {
					bufferedReader.close();
				}

			} catch (IOException e) {
				System.out.println("Cannot close file or buffered reader.");
				exception = e;
			}

			if (exception != null) {
				System.exit(1);
			}
		}

	}

}
