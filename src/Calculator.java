import java.util.List;

public class Calculator {
    private final  ExpressionParser parser = new ExpressionParser();
    private final  ExpressionConverter converter = new ExpressionConverter();
    private final  PostfixEvaluator postfixEvaluator = new PostfixEvaluator();

    public double calculate(String expression){
        String[] tokens  = parser.parseExpression(expression);
        List<String> postfixExpression = converter.infixToPostfix(tokens);
       return postfixEvaluator.evaluate(postfixExpression);
    }

}
