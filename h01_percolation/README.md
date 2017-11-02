# Percolation

For this exercise we have a sqware of n*n cells and we open once cell at a time. When a cell is opened it gets connected to the cells around it that are also oppened. The goal is to detect when the sqware percolates (a top cell is connected to a bottom cell).

The more difficult thing was to understand the backwash. I have implemented a very simple fix by using a second union find object. More details in this article: https://www.sigmainfy.com/blog/avoid-backwash-in-percolation.html  

An exercise for [Union-Find](https://algs4.cs.princeton.edu/15uf/)
[algs4.jar Docs](https://algs4.cs.princeton.edu/code/index.php)
