import java.util.*;

public class Task3 {
    public Map <String, Integer> delenie (Integer delimoe, Integer delitel) {
        Map <String, Integer> result = new LinkedHashMap<>();
        result.put("Частное", delimoe / delitel);
        result.put("Остаток", delimoe % delitel);
        return result;
    }
 /*   void sortAndPrint(List<Person> list) {
        list.sort(Comparator.comparing(a -> a.age));
        list.forEach(System.out::println);
    }*/
    String reverseChars(String str) {
        switch (str.length()) {
            case 0: return "";
            case 1: return str;
            default: {
                return str.substring(str.length() - 1) + reverseChars(str.substring(0, str.length() - 1));
            }
        }
    }
    public void randomArticle() {
        SplittableRandom splittableRandom = new SplittableRandom();
        SplittableRandom random = new SplittableRandom();
        String result = "";
        for (int i = 0; i < 5; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            result += c;
        }
        System.out.println(result.toUpperCase() + splittableRandom.nextInt(10000, 99999));
    }

    public static void main(String[] args) {
        Task3 task3 = new Task3();
//        System.out.println(task3.delenie(1, 2));
//        System.out.println(task3.reverseChars("abv"));
        task3.randomArticle();
    }
}
