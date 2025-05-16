import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionConverter {

    public List<String> infixToPostfix(String[] tokens){
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

    private int precedence(String token){
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
}
