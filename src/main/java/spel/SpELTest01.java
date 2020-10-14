package spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class SpELTest01 {

	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("'abc'").getValue().equals("abc"));
		System.out.println(parser.parseExpression("'''abc'").getValue().equals("'abc"));
		System.out.println("------------------------------------------------------------");
		System.out.println(parser.parseExpression("'abc'.equals('abc')").getValue());
		System.out.println(parser.parseExpression("'abc'.equals('efg')").getValue());
		System.out.println(parser.parseExpression("!'abc'.equals('efg')").getValue());
	}

}
