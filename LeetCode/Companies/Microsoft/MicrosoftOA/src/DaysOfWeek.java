/**
 * @author weltond
 * @project MicrosoftOA
 * @date 1/18/2020
 *
 * {Monday -> Sunday}.
 * Given S = "Wed", K = 2, return "Fri".
 * Given S = "Sat", K = 23, return "Mon".
 */
public class DaysOfWeek {
    public String dayOfWeek(String s, int k) {
        String[] week = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        int i = 0;
        for (; i < 7; i++) {
            if (s.equals(week[i])) break;
        }
        return week[(i + k) % 7];
    }

    public static void main(String[] args) {
        DaysOfWeek dow = new DaysOfWeek();
        System.out.println(dow.dayOfWeek("Sat", 23));
    }
}
