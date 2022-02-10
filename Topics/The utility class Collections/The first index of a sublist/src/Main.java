import java.util.*;
import static java.util.Collections.indexOfSubList;
import static java.util.Collections.lastIndexOfSubList;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var lstSource = new ArrayList<String>();
        for (var item:scanner.nextLine().split(" ")) {

                lstSource.add(item);


        }
        var lstTarget = new ArrayList<String>();
        for (var item:scanner.nextLine().split(" ")) {

                lstTarget.add(item);

        }

        var index1 = Collections.indexOfSubList(lstSource,lstTarget);
        var index2 = Collections.lastIndexOfSubList(lstSource,lstTarget);

        System.out.print(index1 + " " + index2);




    }
}