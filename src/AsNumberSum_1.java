public class AsNumberSum_1 {
    static int HEAD_NUMBER, count = -1;
    public static String asNumberSum (int number) {
        if (count == -1) {
            HEAD_NUMBER = number;
            count ++;
        }
        switch (number) {
            case 1: return "1";
            default: {
                return "=" + asNumberSum(number / number - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(asNumberSum(4));
    }
}
