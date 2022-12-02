public class Matrix {
    int vertices;
    int matrix[][];

    Matrix(int numberOfVertices) {
        this.vertices = numberOfVertices;
        // add number 1 to the numberOfVertices to make room for the matrix representation nicely 
        this.matrix = new int[numberOfVertices + 1][numberOfVertices + 1];
    }

    /*
    * [0,1,2,3,4]
    * [1,0,0,0,0]
    * [2,0,0,0,0]
    * [3,0,0,0,0]
    * [4,0,0,0,0]
    */

    // This function create a matrix for a graph that starts at vertex 1
    public void createMatrix() {
        // Length of the Matrix
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the first row is zero to print the values of the vertices
                if (i == 0) {
                    matrix[i][j] = j;
                    // check if the first column is zero to print the values of the vertices
                } else if (j == 0) {
                    matrix[i][j] = i;
                    // else make the relationship (edge) place holder a zero
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    // This function create a Matrix for a graph that starts at vertex zero
    public void createGroundLevelMatrix() {
        int n = matrix.length;
        /*
        * [0,0,1,2,3]
        * [0,0,0,0,0]
        * [1,0,0,0,0]
        * [2,0,0,0,0]
        * [3,0,0,0,0]
    */

        for (int i = 0; i < n; i++) {
            int k = 2;
            for (int j = 0; j < n; j++) {
                if (i < k & j < k) {
                    matrix[i][j] = 0;
                } else if (i == 0 & j >= k) {
                    matrix[i][j] = j - 1;
                } else if (j == 0 & i >= k) {
                    matrix[i][j] = i - 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    // this function takes an array of two integer verticies and then creates an edge between them
    public void addEdge(int[] vertexArray) {
        // Find the location of both vertices and set their relationship to 1
        matrix[vertexArray[0]][vertexArray[1]] = 1;
    }

    // this function takes an array of two integer verticies and then creates an edge between them
    public void addEdgeGroundLevel(int[] vertexArray) {
        // Find the location of both vertices and set their relationship to 1
        matrix[vertexArray[0] + 1][vertexArray[1] + 1] = 1;
    }

    //Display the matrix 
    public void display() {
        // Length of the Matrix
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            System.out.print("[ ");
            for (int j = 0; j < n; j++) {
                System.out.print(+this.matrix[i][j] + " ");
            }
            System.out.print("]");
            System.out.println("");
        }
        System.out.println();
    }
}