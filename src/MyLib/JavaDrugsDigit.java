package MyLib;

public class JavaDrugsDigit {
    /**
     * Сортировка массива целых чисел пузырьком.
     *
     * @param nums принимает массив целых чисел.
     */
    public static int[] sortNums(int[] nums) {
        boolean sorted = false;
        int temp;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < nums.length - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    temp = nums[i];
                    nums[i] = nums[i + 1];
                    nums[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return nums;
    }
}