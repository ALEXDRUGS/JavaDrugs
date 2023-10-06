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

    /**
     * Поиск индексов чисел сумма которых совпадает с заданной по заданному массиву.
     *
     * @param nums   принимает массив целых чисел.
     * @param target принимает искомую сумму двух чисел массива.
     * @return индексы чисел, чья сумма совпадает с искомой,
     * в виде массива целых чисел.
     */
    public static int[] searchIndexesBySum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return nums;
    }
}