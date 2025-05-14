

public class Main {
    public static void main(String[] args){
        String expression2 = "3 + 4 * (2-1) + 10";
        String expression = "5 + ((1 + 2) * 4) - 3";
        String expression1 = "(11 + 18)*20-2";

        Calculator calculator = new Calculator();

        calculator.calculate(expression);
        calculator.calculate(expression1);
        calculator.calculate(expression2);

    }

}