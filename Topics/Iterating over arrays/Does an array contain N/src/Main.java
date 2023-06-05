import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String[] arr = line.split(" ");
        int[] num = new int[length];
        boolean result = false;
        int n = sc.nextInt();
        for(int i = 0; i<length; i++) {
            num[i] = Integer.parseInt(arr[i]);
        }
        for(int i = 0; i<length; i++) {
            if(num[i] == n) {
                result = true;
                break;
            }
        }
        System.out.println(result);
    }
}