package net.hakugyokurou.fds.web;

import java.util.ArrayList;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.generator.MathExpressionGenerator;

public class EasyController extends BasicController {

	@Override
	public ArrayList<MathExpression> createExpressions() {
		return MathExpressionGenerator.generateEasy(AMOUNT);
	}
}