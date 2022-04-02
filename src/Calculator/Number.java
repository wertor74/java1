package Calculator;
// операнды и результат
public class Number {
    public String strValue; // строковое значение
    public int intValue; // числовое значение
    public boolean romanNumber; // является ли римским числом

    public Number (int intValue) {
        this.intValue = intValue;
        romanNumber = false;
    }

    @Override
    public String toString() {
        return "Результат: " + strValue;
    }
    public int getIntValue () {
        return intValue;
    }
    public boolean getRomanNumber () {
        return romanNumber;
    }
}
