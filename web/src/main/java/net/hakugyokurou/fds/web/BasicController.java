package net.hakugyokurou.fds.web;

import java.util.ArrayList;

import com.jfinal.core.Controller;

import net.hakugyokurou.fds.MathExpression;

public abstract class BasicController extends Controller {

	protected static final int AMOUNT = 20;
	
	public void index() {
		try {
			ArrayList<MathExpression> exprs = createExpressions();
			for(MathExpression expr : exprs)
			{
				renderText(expr + "\r\n");
			}
		} catch (Exception e) {
			renderText(e.getMessage());
		}
	}
	
	public abstract ArrayList<MathExpression> createExpressions();
}
