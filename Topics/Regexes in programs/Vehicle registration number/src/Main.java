import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

       System.out.print(regNum.matches("[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX]{2}") ? "true" : "false");
    }
}
