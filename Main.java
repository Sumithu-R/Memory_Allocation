import java.util.*;

public class Main {
    private static Map<String, List<String>> partitions = new HashMap<>();
    private static List<Integer> jobs = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize predefined partitions with 2 blocks for each size
        initializePartitions();

        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Inform user about predefined partitions
        System.out.println("Predefined partition sizes:");
        System.out.println("50 KB: Block1, Block2");
        System.out.println("100 KB: Block3, Block4");
        System.out.println("200 KB: Block5, Block6");
        System.out.println("--------------------------------------------------");

        // Input job sizes
        System.out.println("Enter job sizes (comma-separated, e.g., 50,100,200): ");
        String jobInput = scanner.nextLine();
        System.out.println();
        String[] jobStrings = jobInput.split(",");
        for (String job : jobStrings) {
            jobs.add(Integer.parseInt(job.trim()));
        }

        // Perform memory allocation
        allocateMemory();

        // Close the scanner
        scanner.close();
    }

    private static void initializePartitions() {
        // Predefined partition blocks (2 blocks for each size)
        partitions.put("50 KB", new ArrayList<>(Arrays.asList("Block1", "Block2")));
        partitions.put("100 KB", new ArrayList<>(Arrays.asList("Block3", "Block4")));
        partitions.put("200 KB", new ArrayList<>(Arrays.asList("Block5", "Block6")));
    }

    private static void allocateMemory() {
        // Perform allocation
        for (int job : jobs) {
            String sizeCategory = job + " KB";
            if (partitions.containsKey(sizeCategory) && !partitions.get(sizeCategory).isEmpty()) {
                String allocatedBlock = partitions.get(sizeCategory).remove(0);
                System.out.println("Job requiring " + job + " KB allocated to " + allocatedBlock + ".");
            } else {
                System.out.println("Job requiring " + job + " KB could not be allocated.");
            }
        }

        // Display remaining free partitions
        System.out.println("\nRemaining free partitions based on Quick Fit:");
        partitions.forEach((size, blocks) -> {
            System.out.println(size + ": " + blocks);
        });
    }
}
