import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThreeTwo {
    static List<String> getInput(String filename) {
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(filename));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to open the file");
        }
        return input;
    }

    public static void main(String[] args) {
        var input = getInput("input3.txt");

        List<String> parsed = new ArrayList<>();
        String mulFunRegex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";
        String doFunRegex = "do\\(\\)";
        String dontFunRegex = "don\\'t\\(\\)";
        String regexForParsingNumbers = "\\d{1,3}";

        Pattern stringPattern = Pattern.compile(mulFunRegex);
        Matcher matcher = stringPattern.matcher(input.toString());


        Pattern numberPattern = Pattern.compile(regexForParsingNumbers);


        int sum = 0;
        boolean enabled = true;

        while (matcher.find()) {
            if (matcher.group().equals("don't()")) {
                enabled = false;
                //System.out.println(matcher.group());
                continue;
            }
            if (matcher.group().equals("do()")) {
                enabled = true;
                continue;
            }
            if (enabled) {
                String string = matcher.group();
                Matcher numberMatcher = numberPattern.matcher(string);
                int multiply = 1;
                while (numberMatcher.find()) {
                    multiply *= Integer.parseInt(numberMatcher.group());
                }
                sum += multiply;
                parsed.add(string);
                //System.out.println(string);
            }

        }
        System.out.println(sum);

    }
}
