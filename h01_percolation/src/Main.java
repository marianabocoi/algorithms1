import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String fileName = "test-data/input10-no.txt";
        List<String> list = new ArrayList<>();
        int n = -1;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            n = Integer.parseInt(br.readLine());
            list = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("N is: " + n);
        Percolation percolation = new Percolation(n);
        list.forEach(s -> {
            System.out.println("Oppening: " + s);
            String[] position = s.trim().split("\\s+");
            int row = Integer.parseInt(position[0]);
            int col = Integer.parseInt(position[1]);
            percolation.open(row, col);
        });

        System.out.println("How many open sites? " + percolation.numberOfOpenSites());
        System.out.println("Should be full " + percolation.isFull(3, 1));
        System.out.println("Should not be full " + percolation.isFull(6, 1));
        System.out.println("Does it percolate? " + percolation.percolates());
    }
}
