import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var arrLst = new ArrayList<String>();
        for (var ch: scanner.nextLine().split(" ")) {
            arrLst.add(ch);
        }

        var result = Collections.frequency(arrLst, scanner.next());
        System.out.print(result);

    }
}