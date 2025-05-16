import java.util.List;
import java.util.Stack;

public class PostfixEvaluator {

    public  double evaluate(List<String> postfixExpression){
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

    private  double applyOperation(String postfixElement, double firstOperand, double secondOperand){
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

    private  boolean isNumber(String s){
        try{
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
