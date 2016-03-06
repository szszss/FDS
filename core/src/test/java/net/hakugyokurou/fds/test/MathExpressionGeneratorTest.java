package net.hakugyokurou.fds.test;

import static org.junit.Assert.*;
import static java.lang.System.out;

import org.junit.Test;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.generator.MathExpressionGenerator;

public class MathExpressionGeneratorTest {

	@Test
	public void testGenerateEasy() {
		MathExpression expr = MathExpressionGenerator.generateEasy();
		out.println(expr + "= " + expr.eval());
	}
}