package net.hakugyokurou.fds.node;

public class OperationNode implements IEvaluable {
	
	public static enum Operation {
		ADD {
			@Override
			protected double eval(double l, double r) {
				return l + r;
			}

			@Override
			protected String toString(String l, String r) {
				return l + "+ " + r;
			}
		},
		SUB {
			@Override
			protected double eval(double l, double r) {
				return l - r;
			}

			@Override
			protected String toString(String l, String r) {
				return l + "- " + r;
			}
		},
		MUL {
			@Override
			protected double eval(double l, double r) {
				return l * r;
			}

			@Override
			protected String toString(String l, String r) {
				return l + "* " + r;
			}
		},
		DIV {
			@Override
			protected double eval(double l, double r) {
				return l / r;
			}

			@Override
			protected String toString(String l, String r) {
				return l + "/ " + r;
			}
		};
		
		protected abstract double eval(double l, double r);
		protected abstract String toString(String l, String r);
	}
	
	private final Operation operation;
	private IEvaluable left, right;
	
	public OperationNode(Operation operation) {
		this.operation = operation;
	}
	
	public void setLeft(IEvaluable left) {
		this.left = left;
	}
	
	public void setRight(IEvaluable right) {
		this.right = right;
	}
	
	@Override
	public double eval() {
		return operation.eval(left.eval(), right.eval());
	}

	@Override
	public void verify() {
		if(operation == null)
			throw new InvalidExpressionException("The operation can't be null.");
		if(left == null)
			throw new InvalidExpressionException("The left child expression can't be null.");
		if(right == null)
			throw new InvalidExpressionException("The right child expression can't be null.");
		left.verify();
		right.verify();
	}

	@Override
	public String toString() {
		return operation.toString(left.toString(), right.toString());
	}
}
