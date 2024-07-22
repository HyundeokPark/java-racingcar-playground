import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITER = ",|:";
    private static final String DELIMITER_KEY = "delimeter";
    private static final String NUMBER_KEY = "number";
    public static int splitAndSum(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        Map<String,String> delimiterAndNumber = getDelimiterAndNumber(input);
        String[] values = delimiterAndNumber.get(NUMBER_KEY).split(delimiterAndNumber.get(DELIMITER_KEY));
        return calculateSum(values);
    }

    private static  int calculateSum(String[] tokens) {
        int sum = 0;
        for (String token : tokens) {
            sum += new StringNumber(token).getDigitValue();
        }
        return sum;
    }

    private static Map<String, String> getDelimiterAndNumber(String input) {
        Map<String,String> map = new HashMap<>();
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(input);
        if (m.find()) {
            map.put(DELIMITER_KEY,m.group(1));
            map.put(NUMBER_KEY,m.group(2));
            return map;
        }
        map.put(DELIMITER_KEY, DEFAULT_DELIMITER);
        map.put(NUMBER_KEY, input);
        return map;
    }
}

class StringNumber {
    private String value;
    private int digitValue ;

    public StringNumber( String value) {
        this.value = value;
        this.digitValue = Integer.parseInt(value);
        if (digitValue < 0) {
            throw new RuntimeException();
        }
    }
    public int getDigitValue() {
        return digitValue;
    }
}
