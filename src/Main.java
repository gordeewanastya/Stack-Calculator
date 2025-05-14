import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        String expression = "3 + 4 * (2-1) + 10";
        //String bracketsExpression = "((2+3) - 4) * 10";
        //String bracketsExpression = "{[()]}";
        String bracketsExpression = "}[()]}";

        //parseExpression(expression);
        //System.out.println(stackPractice(bracketsExpression));

        System.out.println(infixToPostfix(parseExpression(expression)));
    }

    public static String[] parseExpression(String expression){
        //Удаляем все пробелы
        expression = expression.replaceAll("\\s+", "");

        String regex = "(?<=\\d)(?=[+\\-*/()])|(?<=[+\\-*/()])(?=\\d)|(?<=[+\\-*/()])(?=[+\\-*/()])";

        String[] tokens = expression.split(regex);

//        for (String token : tokens){
//            System.out.print(token + " ");
//        }

        return tokens;
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

    public static void calculate(String[] tokens){
        Stack<String> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();

        for (String token : tokens){
            if (token.matches("\\d")){
                    operands.push(Integer.parseInt(token));
            } else if (token.matches("[+\\-*/()]")){
                    int firstOp = operands.pop();
                }
        }

    }







    public static boolean stackPractice(String bracketsExpression){
        Stack<Character> stack = new Stack<>();

        for(char c : bracketsExpression.toCharArray()){
            if ( c == '(' || c == '{' || c == '['){
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']'){
                if (stack.empty()){
                    return false;
                }
                char top = stack.pop();
                if (!isMathingPair(top, c)){
                    return false;
                }
            }
        }
        return stack.empty();

    }

    private static boolean isMathingPair(char openBracket, char closeBracket){
        return (openBracket == '(' && closeBracket == ')') ||
                (openBracket == '[' && closeBracket == ']') ||
                (openBracket == '{' && closeBracket == '}');
    }
}