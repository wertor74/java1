package Calculator;

import java.util.Scanner;

public class Calculator {
    Number result, operand1, operand2;
    public String [] romanNumberArr = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public Calculator() {
        this.result = new Number(0);
        this.operand1 = new Number(0);
        this.operand2 = new Number(0);
    }
    public void inputExpression () { // читаем из консоли
        String expression = "";
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите выражение:");
            expression = scanner.nextLine();
            expressionAnalysis(expression);
        }
    }
    public void expressionAnalysis (String expression) { // анализируем введённое выражение
        String [] expressionArr = expression.toUpperCase().split(" ");
        // проверяем выражение на два операнда и оператор
        if (expressionArr.length != 3) throw new RuntimeException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        // проверяем первый операнд
        operand1.intValue = arabicNumber(expressionArr[0]);
        if (operand1.intValue != 0) {
            result.intValue = operand1.intValue;
        } else {
            operand1.intValue = romanNumber(expressionArr[0]);
            if (operand1.intValue != 0) {
                result.intValue = operand1.intValue;
                operand1.romanNumber = true;
            } else {
                throw new RuntimeException("т.к. первый операнд не является ни арабским, ни римским числом, либо не входит в диапазон от 1 до 10");
            }
        }
        //проверяем второй операнд
        if (operand1.romanNumber == true) {
            operand2.intValue = romanNumber(expressionArr[2]);
            if (operand2.intValue == 0) {
                throw new RuntimeException("т.к. второй операнд не является римским числом, либо не входит в диапазон от 1 до 10");
            } else {
                operand2.romanNumber = true;
                result.romanNumber = true;
            }
        } else {
            operand2.intValue = arabicNumber(expressionArr[2]);
            if (operand2.intValue == 0) throw new RuntimeException("т.к. второй операнд не является арабским числом, либо не входит в диапазон от 1 до 10");
        }
        // проверяем оператор и вычисляем результат
        switch (expressionArr[1]) {
            case "+" : {
                add(operand2.getIntValue());
                break;
            }
            case "-" : {
                sub(operand2.getIntValue());
                break;
            }
            case "*" : {
                mul(operand2.getIntValue());
                break;
            }
            case "/" : {
                div(operand2.getIntValue());
                break;
            }
            default: throw new RuntimeException("т.к. " + expressionArr[1] + " не является заданным математическим оператором");
        }
    }
    // арабские цифры
    public int arabicNumber (String operand) {
        String [] arabicNumberArr = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int arabicResult = 0;
        for (int i = 0; i < 10; i++) {
            if (arabicNumberArr[i].equals(operand)) arabicResult = i + 1;
        }
        return arabicResult;
    }
    // римские цифры
    public int romanNumber (String operand) {
        int romanResult = 0;
        for (int i = 0; i < 10; i++) {
            if (romanNumberArr[i].equals(operand)) romanResult = i + 1;
        }
        return romanResult;
    }
    // сложение
    public void add (int operand2) {
        result.intValue += operand2;
        getResult(result.getIntValue());
    }
    // вычитание
    public void sub (int operand2) {
        result.intValue -= operand2;
        getResult(result.getIntValue());
    }
    // умножение
    public void mul (int operand2) {
        result.intValue *= operand2;
        getResult(result.getIntValue());
    }
    // деление
    public void div (int operand2) {
        result.intValue /= operand2;
        getResult(result.getIntValue());
    }
    // выводим результат
    public void getResult (int intResult) {
        if (result.getRomanNumber()) {
            if (intResult < 1) throw new RuntimeException("т.к. в римской системе нет нуля и отрицательных чисел");
            result.strValue = romanTransformation(intResult);
        } else {
            result.strValue = String.valueOf(intResult);
        }
        System.out.println(result.toString());
    }
    // результат римскими цифрами
    public String romanTransformation (int intResult) {
        String strResult = "";
        if (intResult == 100) {
            strResult = "C";
        } else if (intResult > 0 && intResult < 11) {
            strResult = romanNumberArr[intResult - 1];
        } else {
            int decIntResult = intResult / 10;
            int unitIntResalt = intResult % 10;
            switch (decIntResult) {
                case 1:
                case 2:
                case 3:
                    for (int i = 0; i < decIntResult; i++) {
                        strResult += romanNumberArr[romanNumberArr.length - 1];
                    }
                        if (unitIntResalt != 0) strResult += romanNumberArr[unitIntResalt - 1];
                        break;
                case 4:
                    strResult = "XL";
                    if (unitIntResalt != 0) strResult += romanNumberArr[unitIntResalt - 1];
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                    strResult = "L";
                    for (int i = decIntResult - 5; i > 0; i--) {
                        strResult += romanNumberArr[romanNumberArr.length - 1];
                    }
                    if (unitIntResalt != 0) strResult +=romanNumberArr[unitIntResalt - 1];
                    break;
                default: strResult = romanNumberArr[romanNumberArr.length - 1] + "C";
            }
        }
        return strResult;
    }
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.inputExpression();
    }
}