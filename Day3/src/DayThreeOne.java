import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DayThreeOne {

    public static void main(String[] args) {
        try {
            List<String> in;
            in = Files.readAllLines(Path.of("input3.txt"));
            String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
            Pattern pattern = Pattern.compile(regex);
            int sum = 0;
            List<Integer> nums = new ArrayList<>();
            Matcher matcher = pattern.matcher(in.toString());
            while (matcher.find()) {
                String str = matcher.group();
                str = str.replaceAll("[^-?0-9]+", " ").trim();
                String[] n = str.split(" ");
                int v1 = Integer.parseInt(n[0]);
                int v2 = Integer.parseInt(n[1]);
                sum += v1 * v2;
            }
            System.out.println(sum);

        } catch (Exception e) {
            System.err.println("lala");
        }


    }

}
