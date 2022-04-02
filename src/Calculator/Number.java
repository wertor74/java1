package Calculator;
// операнды и результат
public class Number {
    public String strValue;
    public int intValue;
    public boolean romanNumber;

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
