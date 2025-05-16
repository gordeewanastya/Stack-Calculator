

public class Main {
    public static void main(String[] args){
        String expression = "5 + ((1 + 2) * 4) - 3";
        String expression1 = "(11 + 18)*20-2";
        String expression2 = "3 + 4 * (2-1) + 10";
        String expression3 = "-(3 + 2) * 4";

        Calculator calculator = new Calculator();

        System.out.println("Result of calculation: " + calculator.calculate(expression));
        System.out.println("Result of calculation: " + calculator.calculate(expression1));
        System.out.println("Result of calculation: " + calculator.calculate(expression2));
        System.out.println("Result of calculation: " + calculator.calculate(expression3));
    }

}