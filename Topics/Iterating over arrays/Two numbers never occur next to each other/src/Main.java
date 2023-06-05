import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean result = true;
        int length = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String[] arr = line.split(" ");
        int[] numbers = new int[arr.length];
        int n = sc.nextInt();
        int m = sc.nextInt();
        for(int i = 0; i<length;i++) {
            numbers[i] = Integer.parseInt(arr[i]);
        }
        for(int i = 1; i < length; i++) {
            if((numbers[i-1] == n && numbers[i] == m) || (numbers[i-1] == m && numbers[i] == n)) {
                result = false;
                break;
            }
        }
        System.out.println(result);
    }
}