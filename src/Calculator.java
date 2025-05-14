import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    private Double result;

    Calculator() {
        result = 0.0;
    }

    public void calculate(String expression){
        String[] tokens  = parseExpression(expression);
        List<String> postfixExpression = infixToPostfix(tokens);
        this.result = evaluatePostfix(postfixExpression);
        System.out.println(result);
    }


    private static String[] parseExpression(String expression){
        //Удаляем все пробелы
        expression = expression.replaceAll("\\s+", "");

        String regex = "(?<=\\d)(?=[+\\-*/()])|(?<=[+\\-*/()])(?=\\d)|(?<=[+\\-*/()])(?=[+\\-*/()])";

        return expression.split(regex);
    }

    private static List<String> infixToPostfix(String[] tokens){
        Stack<String> operators = new Stack<>();
        List<String> postfixExpression = new ArrayList<>();

        for (String token: tokens){
            if (token.matches("\\d+")){
                postfixExpression.add(token);
            }
            else if (token.equals("(")){
                operators.push(token);
            }
            else if (token.equals(")")){
                //выгружать пока не дойдем до "(")
                while (!operators.empty() && !operators.peek().equals("(")){
                    postfixExpression.add(operators.pop());
                }

                if (!operators.empty() && operators.peek().equals("(")){
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Not balanced brackets");
                }

            }
            else {
                while (!operators.empty() && !operators.peek().equals("(") && precedence(operators.peek()) >= precedence(token)){
                    postfixExpression.add(operators.pop());
                }
                operators.push(token);
            }
        }
        while (!operators.empty()){
            postfixExpression.add(operators.pop());
        }

        return postfixExpression;
    }

    private static int precedence(String token){
        switch (token){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    private static double evaluatePostfix(List<String> postfixExpression){
        Stack<Double> operands = new Stack<>();
        double result = 0.0;

        for (String postfixElement: postfixExpression){
            if (isNumber(postfixElement)){
                operands.push(Double.parseDouble(postfixElement));
            }
            else {
                double firstOperand = operands.pop();
                double secondOperand = operands.pop();

                result = applyOperation(postfixElement, firstOperand, secondOperand);

                operands.push(result);
            }
        }

        return result;
    }

    private static double applyOperation(String postfixElement, double firstOperand, double secondOperand){
        switch (postfixElement){
            case "+":
                return secondOperand + firstOperand;
            case "-":
                return secondOperand - firstOperand;
            case "*":
                return secondOperand * firstOperand;
            case "/":
                return secondOperand / firstOperand;
            default:
                return 0.0;
        }
    }

    private static boolean isNumber(String s){
        try{
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }



}
