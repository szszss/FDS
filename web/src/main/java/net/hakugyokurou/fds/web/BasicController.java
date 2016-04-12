package net.hakugyokurou.fds.web;

import java.util.ArrayList;

import com.jfinal.core.Controller;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.generator.MathExpressionGenerator;

public abstract class BasicController extends Controller {

	protected static final int AMOUNT = 20;
	
	public void index() {
		ArrayList<MathExpression> exprs = createExpressions();
		setAttr("js", printJs(exprs));
		setAttr("questions", printTable(exprs));
		setAttr("diff", getDiff());
		renderJsp("/answer.jsp");
		/*try {
			ArrayList<MathExpression> exprs = createExpressions();
			for(MathExpression expr : exprs)
			{
				renderText(expr + "\r\n");
			}
		} catch (Exception e) {
			renderText(e.getMessage());
		}*/
	}
	
	public String printJs(ArrayList<MathExpression> exprs) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">\r\n")
		.append("var answer = new Array();\r\n");
		for(int i = 0, size = exprs.size(); i < size; i++)
		{
			sb.append("answer[" + i + "] = " + exprs.get(i).eval() + ";\r\n");
		}
		sb.append("function checkAnswer(tfId, trId, anId) {\r\n")
		.append("document.getElementById(trId).className=\"danger\";\r\n")
		.append("var v = document.getElementById(tfId).value;\r\n")
		.append("if(v.indexOf(\"/\") >= 0) { var arr = v.split(\"/\",2);var n = parseFloat(arr[0]); var d = parseFloat(arr[1]);\r\n")
		.append("if(Math.abs(n / d - answer[anId]) < 0.1) { document.getElementById(trId).className=\"success\"; } } else { \r\n")
		.append("var n = parseFloat(v);\r\n")
		.append("if(Math.abs(n - answer[anId]) < 0.1) { document.getElementById(trId).className=\"success\"; }")
		.append("} \r\n")
		.append("}")
		.append("</script>");
		return sb.toString();
	}
	
	public String printTable(ArrayList<MathExpression> exprs) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0, size = exprs.size(); i < size; i++)
		{
			sb.append("<tr id=\"" + "_tr" + String.valueOf(i)  + "\">\r\n")
			.append("<td>")
				.append(i+1)
			.append("</td>\r\n")
			.append("<td>")
				.append(exprs.get(i))
			.append("</td>\r\n")
			.append("<td>\r\n")
				.append("<div class=\"input-group\">\r\n")
					.append("<input type=\"text\" class=\"form-control\" id=\"_tf" 
							+ String.valueOf(i) + "\" onchange=\"checkAnswer(this.id, '" + "_tr" + String.valueOf(i)  + "', " + i + ");\" />\r\n")
					/*.append("<span class=\"input-group-btn\">")
						.append("<button class=\"btn btn-default\" type=\"button\">Button</button>")
					.append("</span>")*/
				.append("</div>\r\n")
			.append("</td>\r\n")
			.append("</tr>\r\n");
			
			/*
			 * 
			 * <div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="text" class="form-control">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Button</button>
                    </span>
                  </div>
			 */
		}
		return sb.toString();
	}
	
	public abstract ArrayList<MathExpression> createExpressions();
	
	protected abstract String getDiff();
	
	public static class EasyController extends BasicController {
		@Override
		public ArrayList<MathExpression> createExpressions() {
			return MathExpressionGenerator.generateEasy(AMOUNT);
		}

		@Override
		protected String getDiff() {
			return "Easy";
		}
	}
	
	public static class NormalController extends BasicController {
		@Override
		public ArrayList<MathExpression> createExpressions() {
			return MathExpressionGenerator.generateNormal(AMOUNT);
		}

		@Override
		protected String getDiff() {
			return "Normal";
		}
	}
	
	public static class HardController extends BasicController {
		@Override
		public ArrayList<MathExpression> createExpressions() {
			return MathExpressionGenerator.generateHard(AMOUNT);
		}

		@Override
		protected String getDiff() {
			return "Hard";
		}
	}
	
	public static class LunaticController extends BasicController {
		@Override
		public ArrayList<MathExpression> createExpressions() {
			return MathExpressionGenerator.generateLunatic(AMOUNT);
		}

		@Override
		protected String getDiff() {
			return "Lunatic";
		}
	}
}
