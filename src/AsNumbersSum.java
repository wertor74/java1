public class AsNumbersSum {
    static int HEAD_NUMBER, HEAD_INNUMBER, count = -1;
    public static String asNumbersSum(int number) {
        if (count == -1) {
            HEAD_NUMBER = number;
            count++;
        }
        switch (number) {
            case 1: {
                return "";
            }
            default: {
                count = 0;
                if (HEAD_NUMBER - (number - 1) == 1) return number + "=" + (number - 1) + "+1" + asNumbersSum(number - 1);
                return "=" + (number - 1) + "+" + inNumberSum(number - 1, HEAD_NUMBER - (number - 1)) + asNumbersSum(number - 1);
            }
        }
    }
    public static String inNumberSum(int headNumber, int inNumber) {
        if (count == 0) {
            HEAD_INNUMBER = inNumber;
            count++;
        }
        switch (inNumber) {
            case 1: {
                if (headNumber == 1) return "1";
                if (headNumber < HEAD_INNUMBER) return "";
                return String.valueOf(HEAD_INNUMBER);
            }
            default: {
                if (headNumber == 1) return inNumberSum(headNumber,  inNumber - 1) + "+" + 1;
                if (headNumber >= HEAD_INNUMBER) return inNumberSum(headNumber, inNumber - 1) + "=" + headNumber + "+" + (HEAD_INNUMBER - (inNumber - 1)) + "+" + inNumberSum(1, inNumber - 1);
                int quotient = HEAD_INNUMBER / headNumber;
                int remainder = HEAD_INNUMBER % headNumber;
                //inNumber --;
                return inNumberSum(headNumber, inNumber- 1) + "=" + headNumber + "+" + (HEAD_INNUMBER - inNumber) + "+" + inNumberSum(1, inNumber);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(asNumbersSum(8));
    }
}
