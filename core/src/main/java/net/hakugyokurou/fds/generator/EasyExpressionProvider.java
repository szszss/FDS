package net.hakugyokurou.fds.generator;

public class EasyExpressionProvider extends BasicExpressionProvider{
	
	public static final EasyExpressionProvider INSTANCE = new EasyExpressionProvider();
	
	public EasyExpressionProvider() {
		setNumbers(3, 6);
		setDifficulty(0.1f);
		setOperationWeights(0.5f, 0.25f, 0.125f, 0.0625f);
		setValueWeights(0.8f, 0.1f, 0.1f);
	}
}
