package net.hakugyokurou.fds.generator;

import net.hakugyokurou.fds.MathExpression;

public class MathExpressionGenerator {

	private final IGeneratorProvider provider;
	
	public MathExpressionGenerator(IGeneratorProvider provider) {
		this.provider = provider;
	}
	
	public MathExpression generate() {
		return provider.generate();
	}
}