import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean result = false;
        int length = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine();
        String[] arr = line.split(" ");
        int[] nums = new int[arr.length];
        for(int i = 0; i < length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        } 
        int n = sc.nextInt();
        int m = sc.nextInt();
        for(int i = 0; i<nums.length; i++) {
            if (nums[i] == n) {
                result = true;
                break;
            }
        }
        for(int j = 0; j<nums.length; j++) {
            if(nums[j] == m) {
                result = true;
                break;
                }
            }
        for (int i = 1; i < nums.length; i++) {
            if ((nums[i-1] == n && nums[i] == m) || (nums[i-1] == m && nums[i] == n)) {
                result = true;
                break;
            } else result = false;
        }
        if(result == true) System.out.println(true);
        else System.out.println(false);
    }
}
