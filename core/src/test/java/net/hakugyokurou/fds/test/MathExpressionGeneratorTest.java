package net.hakugyokurou.fds.test;

import static org.junit.Assert.*;
import static java.lang.System.out;

import org.junit.Test;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.generator.MathExpressionGenerator;

public class MathExpressionGeneratorTest {

	@Test
	public void testGenerateEasy() {
		for(int i = 0; i < 100; i++)
		{
			MathExpression expr = MathExpressionGenerator.generateLunatic();
			out.println(expr + "= " + expr.eval());
		}
	}
}