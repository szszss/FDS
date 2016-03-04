package net.hakugyokurou.fds.generator;

import static java.lang.Math.*;

import java.util.Random;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.util.WeightedRandomPool;

public abstract class BasicExpressionProvider implements IGeneratorProvider {
	
	private int numbersMin = 3, numbersMax = 3;
	private float weightAdd = 1.0f, weightSub = 0.0f, weightMul = 0.0f, weightDiv = 0.0f;
	private float weightInteger = 1.0f;
	private WeightedRandomPool<E>
	
	protected void setNumbers(int numbersMin, int numbersMax) {
		this.numbersMin = max(numbersMin, 1);
		this.numbersMax = min(max(numbersMin, numbersMax), 100);
	}
	
	protected void setOperationWeights(float add, float sub, float mul, float div) {
		weightAdd = add;
		weightSub = sub;
		weightMul = mul;
		weightDiv = div;
		dirty = true;
	}
	
	
	
	@Override
	public MathExpression generate(int n, int max, Random random) {
		int numbers = numbersMin + random.nextInt(numbersMax - numbersMin);
		
		return null;
	}
}
