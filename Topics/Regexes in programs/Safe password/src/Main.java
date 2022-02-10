import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        var x = scanner.nextLine();
        System.out.print(x.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-z_A-Z\\d]{12,}$") ? "YES" : "NO");
    }
}
