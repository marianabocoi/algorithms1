import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final int topNode;
    private final int bottomNode;
    private int openSites;
    private boolean[][] surface;
    private final WeightedQuickUnionUF unionFind;
    private final WeightedQuickUnionUF fillUnionFind;


    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        surface = new boolean[n][n];
        unionFind = new WeightedQuickUnionUF(n * n + 2);
        fillUnionFind = new WeightedQuickUnionUF(n * n + 1);
        topNode = n * n;
        bottomNode = n * n + 1;
        openSites = 0;
    }


    public void open(int row, int col) {
        throwIfNotValidCell(row, col);
        if (surface[row - 1][col - 1]) {
            return;
        }
        surface[row - 1][col - 1] = true;
        int index = getIndex(row, col);
        maybeConnectTopOrBottomNode(row, index);
        maybeConnect(index, row - 1, col);
        maybeConnect(index, row + 1, col);
        maybeConnect(index, row, col - 1);
        maybeConnect(index, row, col + 1);
        openSites++;
    }

    private void throwIfNotValidCell(int row, int col) {
        if (row <= 0 || col <= 0 || row >= n + 1 || col >= n + 1) {
            throw new IllegalArgumentException("Cell not in square.");
        }
    }

    private void maybeConnect(int index, int row2, int col2) {
        try {
            if (isOpen(row2, col2)) {
                unionFind.union(getIndex(row2, col2), index);
                fillUnionFind.union(getIndex(row2, col2), index);
            }
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    private void maybeConnectTopOrBottomNode(int row, int index) {
        if (row == 1) {
            unionFind.union(index, topNode);
            fillUnionFind.union(index, topNode);
        }
        if (row == n) {
            unionFind.union(index, bottomNode);
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    public boolean isOpen(int row, int col) {
        throwIfNotValidCell(row, col);
        return surface[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        throwIfNotValidCell(row, col);
        return fillUnionFind.connected(topNode, getIndex(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return unionFind.connected(topNode, bottomNode);
    }
}
