public class ExpressionParser {
    public String[] parseExpression(String expression){
        //Удаляем все пробелы
        expression = expression.replaceAll("\\s+", "");

        //Обработка унарного минуса перед скобкой
        expression = expression.replaceAll("(?<![\\d)])-(?=\\()", "0-");

        String regex = "(?<=\\d)(?=[+\\-*/()])|(?<=[+\\-*/()])(?=\\d)|(?<=[+\\-*/()])(?=[+\\-*/()])";

        return expression.split(regex);
    }
}
