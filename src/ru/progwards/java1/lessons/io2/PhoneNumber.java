package ru.progwards.java1.lessons.io2;

public class PhoneNumber {
    public static String format(String phone) {
        StringBuilder stringBuilder = new StringBuilder();
        //удаляем лишние символы
        for (char c : phone.toCharArray())
            if (Character.isDigit(c))
                stringBuilder.append(c);
        // проверяем количество цифр в номере
        if (stringBuilder.length() > 11 || stringBuilder.length() < 10) {
            throw new RuntimeException("Неправильный номер: " + stringBuilder);
        }
        //форматируем под выходной шаблон
        switch (stringBuilder.length()) {
            case 10:
                stringBuilder.insert(0,"+7");
                break;
            case 11:
                stringBuilder.delete(0, 1);
                stringBuilder.insert(0, "+7");
        }
        stringBuilder.insert(2, '(');
        stringBuilder.insert(6, ')');
        stringBuilder.insert(10, '-');
        phone = stringBuilder.toString();
        return phone;
    }

    public static void main(String[] args) {
        String phone = "8 999 111 22 33";
        System.out.println(format(phone));
    }
}
