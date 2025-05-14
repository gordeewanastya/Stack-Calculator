import java.util.Stack;

public class StackBracketsSolution
{
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

    public static void main(String[] args) {
        String bracketsExpressionValid = "{[()]}";
        String bracketsExpressionNotValid = "}[()]}";

        System.out.println(stackPractice(bracketsExpressionValid));
        System.out.println(stackPractice(bracketsExpressionNotValid));
    }
}
