import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        ConvertCheckNums ccn = new ConvertCheckNums();
        int operation = ccn.checkOperations(s);
        String[] nums = s.split(ccn.splitS[operation]);
        nums[0] = nums[0].trim();
        nums[1] =  nums[1].trim();
        if (ConvertCheckNums.checkNums(nums[0], nums[1])) {
            int x = 0;
            int y = 0;
            x = ConvertCheckNums.toArabic(nums[0]);
            y = ConvertCheckNums.toArabic(nums[1]);
            if (ConvertCheckNums.arithmOperations[operation] == "-" && x <= y) {
                throw new Exception("В римских числах нет нуля и отрицательных чисел");
            } else if (ConvertCheckNums.arithmOperations[operation] == "/" && x < y) {
                throw new Exception("Делитель больше делимого");
            } else {
                System.out.println(ConvertCheckNums.toRoman(ConvertCheckNums.calculate(x, y, ConvertCheckNums.arithmOperations[operation])));
            }
        } else {
            int x = Integer.parseInt(nums[0]);
            int y = Integer.parseInt(nums[1]);
            System.out.println(ConvertCheckNums.calculate(x, y, ConvertCheckNums.arithmOperations[operation]));
        }

    }
}