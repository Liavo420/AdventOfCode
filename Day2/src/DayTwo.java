import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayTwo {

    static List<List<Integer>> getInputFromFile() {
        List<List<Integer>> input = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get("src/input.txt"));
            for (String line : lines) {
                List<Integer> temp = new ArrayList<>();
                String[] arr = line.split(" ");
                for (String number : arr) {
                    temp.add(Integer.parseInt(number));
                }
                input.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Failed to get file");
            return new ArrayList<>();
        }
        return input;
    }

    static boolean checkSafety(List<Integer> report) {
        boolean isDecremental = true;
        boolean isIncremental = true;
        boolean isSafe = true;
        for (int i = 0; i < report.size() - 1; i++) {
            int current = report.get(i);
            int next = report.get(i + 1);
            int diff = current - next;
            if (diff > 0 && diff <= 3 && isDecremental) {
                isIncremental = false;
            } else if (diff < 0 && diff >= -3 && isIncremental) {
                isDecremental = false;
            } else {
                isDecremental = false;
                isIncremental = false;
                isSafe = false;
                break;
            }
        }
        return isSafe;
    }

    public static void main(String[] args) {
        var input = getInputFromFile();
        int countOfSafeReports = 0;
        //part 1:

        for (List<Integer> report : input) {
            if (checkSafety(report))
                countOfSafeReports++;
        }

        System.out.println("Safe reports part 1 : " + countOfSafeReports);
        countOfSafeReports = 0;

        //part 2:
        for (List<Integer> report : input) {
            boolean isSafe = checkSafety(report);
            if (isSafe) {
                countOfSafeReports++;
            } else if (!isSafe) {
                for (int i = 0; i < report.size(); i++) {
                    List<Integer> faultyReport = new ArrayList<>(report);
                    faultyReport.remove(i);
                    if (checkSafety(faultyReport)) {
                        countOfSafeReports++;
                        break;
                    }
                }
            }
        }
        System.out.println("Safe reports part 2 : " + countOfSafeReports);
    }
}

