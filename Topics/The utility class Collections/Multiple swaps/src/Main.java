import java.util.*;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var items = new ArrayList<String>();
        for (var i: scanner.nextLine().split(" ")) {
            items.add(i);
        }

        var size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            Collections.swap(items, scanner.nextInt(), scanner.nextInt());
        }

        for (var i = 0; i < items.size(); i++) {
            System.out.print(items.get(i));
            if (i < items.size() - 1) {
                System.out.print(" ");
            }
        }




    }
}