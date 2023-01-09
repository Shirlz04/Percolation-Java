// Models an N-by-N percolation system.
public class Percolation {
    private boolean [][] matrix;
    private int count = 0;
    private int [] [] code; 
    private WeightedQuickUnionUF uf;   
    // Creates an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        try 
        {
            matrix = new boolean [N][N];
            int codeCount = 0; 
            int size = N*N+2;
            uf = new WeightedQuickUnionUF(size);
            code = new int [N][N];
            for (int c = 0; c < N; c++) {
                for (int a = 0; a < N; a++) {
                    matrix[c][a] = false;
                    codeCount += 1;
                    code[c][a] = codeCount;
                }
            }
           
            
        }
        catch (IllegalArgumentException exception) {
            StdOut.println("Size cannot be less than or equal to zero");
        }
    }

    // Opens site (i, j) if it is not open already.
    public void open(int i, int j) {
        int code1;
        if (!isOpen(i, j)) {
            matrix[i][j] = true;
            count += 1;
            code1 = encode(i, j);
            if (i == 0) {
                uf.union(0, encode(i, j));
            }
            try 
            {
                if (matrix[i-1][j] && !uf.connected(encode(i-1, j), code1)) {
                    uf.union(encode(i-1, j), code1);
                }
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                if (matrix[i+1][j] && !uf.connected(encode(i+1, j), code1)) {
                    uf.union(encode(i+1, j), code1);
                }
            }
            try {
                if (matrix[i+1][j] && !uf.connected(encode(i+1, j), code1)) {
                    uf.union(encode(i+1, j), code1);
                }
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                if (matrix[i-1][j] && !uf.connected(encode(i-1, j), code1)) {
                    uf.union(encode(i-1, j), code1);
                }
            }
            try {
                if (matrix[i][j-1] && !uf.connected(encode(i, j-1), code1)) {
                    uf.union(encode(i, j-1), code1);
                }
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                if (matrix[i][j+1] && !uf.connected(encode(i, j+1), code1)) {
                    uf.union(encode(i, j+1), code1);
                }
            }
            try
            {
                if (matrix[i][j+1] && !uf.connected(encode(i, j+1), code1)) {
                    uf.union(encode(i, j+1), code1);
                }
            }
            catch (ArrayIndexOutOfBoundsException exception) {
                if (matrix[i][j-1] && !uf.connected(encode(i, j-1), code1)) {
                    uf.union(encode(i, j-1), code1);
                }
            }
        }
    }
    

    public boolean isOpen(int i, int j) {
        try 
        {
            return matrix[i][j];
        }
        catch (ArrayIndexOutOfBoundsException exception) {
            StdOut.println("Index is out of bounds");
            return false;
        }
    }

    // Checks if site (i, j) is full.
    public boolean isFull(int i, int j) {
        try 
        {
            return uf.connected(0, encode(i, j));
        }
        catch (ArrayIndexOutOfBoundsException exception) {
            StdOut.println("Index is out of bounds");
            return false;
        }
    }

    // Returns the number of open sites.
    public int numberOfOpenSites() {
        return count;
    }

    // Checks if the system percolates.
    public boolean percolates() {
        boolean result = false;
        int size = matrix.length * matrix.length+1;
        int x = size - 1;
        for (int a = 0; a < matrix.length; a++) {
            if (uf.connected(0, x-a)) {
                uf.union(size, x-a);
            }
        }
        return uf.connected(0, size);
    }

    // Returns an integer ID (1...N) for site (i, j).
    private int encode(int i, int j) {
        int num = 0;
        int size = code.length;
        num = code[i][j];
        return num;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Percolation perc = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        }
        else {
            StdOut.println("does not percolate");
        }
        
        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
    
}
