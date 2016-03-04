package net.hakugyokurou.fds.generator;

import java.util.Random;

import net.hakugyokurou.fds.MathExpression;

public class EasyExpressionProvider extends BasicExpressionProvider{
	
	private static final int NUMBERS_MIN = 3;
	private static final int NUMBERS_MAX = 6;
	
	public EasyExpressionProvider() {
		super(NUMBERS_MIN, NUMBERS_MAX);
	}
}
