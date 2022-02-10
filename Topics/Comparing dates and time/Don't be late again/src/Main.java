import java.util.*;
import java.time.LocalTime;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final LocalTime closeTime = LocalTime.parse("20:00");

        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String name = scanner.next();
            LocalTime time = LocalTime.parse(scanner.next());

            if (closeTime.compareTo(time) < 0) {
                System.out.println(name);
            }
        }
    }
}
