package net.hakugyokurou.fds.cmd;

import static net.hakugyokurou.fds.cmd.InteractiveCmd.CAPGROUP_COMMAND;
import static net.hakugyokurou.fds.cmd.InteractiveCmd.CAPGROUP_EXPRESSION;
import static net.hakugyokurou.fds.cmd.InteractiveCmd.CAPGROUP_SEGMENT;

import java.io.File;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Matcher;

import net.hakugyokurou.fds.MathExpression;
import net.hakugyokurou.fds.parser.MathExpressionParser;
import net.hakugyokurou.fds.util.AnswerHelper;

enum EnumStates {
	STANDBY {
		@Override
		public EnumStates transfer(InteractiveCmd context) {
			PrintStream out = context.outputStream;
			String cmd = extractCommand(context.matcher).toLowerCase();
			if(cmd.equals("help"))
			{
				out.println("Commands:");
				out.println("help : Print all available commands.");
				out.println("parse <expr> : Read and parse an expression and store it as question.");
				out.println("fromfile \"<filepath>\" : Read and parse expressions from a file and store them as questions.");
				out.println("answer : Answer questions.");
				out.println("list : Print all stored questions.");
				out.println("clear : Remove all stored questions.");
				out.println("exit/quit : Exit from interactive mode.");
				return STANDBY;
			}
			if(cmd.equals("parse"))
			{
				return PARSE;
			}
			if(cmd.equals("answer"))
			{
				if(context.maxQuestionId == 0)
				{
					out.println("There's no question yet.");
					return STANDBY;
				}
				context.currentQuestionId = context.statsAccepted = context.statsSkipped = 0;
				MathExpression expr = context.expressions.get(0);
				out.println("Question 1/" + context.maxQuestionId);
				out.println(expr);
				return ANSWER;
			}
			if(cmd.equals("fromfile"))
			{
				return READFILE;
			}
			if(cmd.equals("list"))
			{
				for(MathExpression expression : context.expressions)
				{
					out.println(expression);
				}
				return STANDBY;
			}
			if(cmd.equals("clear"))
			{
				context.expressions.clear();
				context.currentQuestionId = context.statsAccepted = context.statsSkipped = 0;
				context.maxQuestionId = 0;
				out.println("All questions purged.");
				return STANDBY;
			}
			if(cmd.equals("exit") || cmd.equals("quit"))
			{
				return EXIT;
			}
			throw new IllegalArgumentException("Wrong input, unknown command: " + cmd);
		}
	},
	PARSE {
		@Override
		public EnumStates transfer(InteractiveCmd context) {
			PrintStream out = context.outputStream;
			String expr = extractExpression(context.matcher);
			MathExpression expression = MathExpressionParser.parseLine(new StringReader(expr));
			context.expressions.add(context.maxQuestionId++, expression);
			out.println("Expression has been stored as Question " + context.maxQuestionId);
			return STANDBY;
		}
	},
	READFILE {
		@Override
		public EnumStates transfer(InteractiveCmd context) {
			String str = context.matcher.group(CAPGROUP_SEGMENT);
			if(str != null)
			{
				str = str.substring(1, str.length()-1);
				File file = new File(str);
				try {
					ArrayList<MathExpression> expressions = MathExpressionParser.parseFile(file);
					context.expressions = expressions;
					context.currentQuestionId = context.statsAccepted = context.statsSkipped = 0;
					context.maxQuestionId = expressions.size();
					PrintStream out = context.outputStream;
					for(MathExpression expr : expressions)
						out.println(expr);
					out.println(expressions.size() + " expression(s) loaded.");
				} catch (Exception e) {
					context.outputStream.println("Can't parse:" + str + " Exception:" + e.getMessage());
				}
				return STANDBY;
			}
			context.outputStream.println("Wrong input, not a segment:" + context.matcher.group(0));
			return STANDBY;
		}	
	},
	ANSWER {
		@Override //TODO:233333
		public EnumStates transfer(InteractiveCmd context) {
			PrintStream out = context.outputStream;
			try {
				String cmd = context.matcher.group(CAPGROUP_COMMAND);
				if(cmd != null)
				{
					cmd = cmd.toLowerCase();
					if(cmd.equals("skip"))
					{
						context.statsSkipped++;
						out.println("Skip");
						if(++context.currentQuestionId >= context.maxQuestionId)
						{
							out.println("");
							out.println("STATS:");
							out.println("Correct:" + context.statsAccepted + "  Skipped:" + context.statsSkipped);
							return STANDBY;
						}
						else
						{
							MathExpression expression = context.expressions.get(context.currentQuestionId);
							out.println("Question " + (context.currentQuestionId+1) + "/" + context.maxQuestionId);
							out.println(expression);
							return ANSWER;
						}
					}
					else if(cmd.equals("giveup"))
					{
						return STANDBY;
					}
				}
				String expr = extractExpression(context.matcher);
				double answer = AnswerHelper.parseSingleNumber(expr);
				MathExpression expression = context.expressions.get(context.currentQuestionId);
				boolean correct = AnswerHelper.checkAnswer(answer, expression.eval());
				if(correct)
				{
					out.println("Correct!");
					context.statsAccepted++;
					if(++context.currentQuestionId >= context.maxQuestionId)
					{
						out.println("");
						out.println("STATS:");
						out.println("Correct:" + context.statsAccepted + "  Skipped:" + context.statsSkipped);
						return STANDBY;
					}
					else
					{
						expression = context.expressions.get(context.currentQuestionId);
						out.println("Question " + (context.currentQuestionId+1) + "/" + context.maxQuestionId);
						out.println(expression);
					}
				}
				else
				{
					out.println("Incorrect");
					out.println("Input 'skip' to skip this question. Input 'giveup' to stop answering.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				out.println("Wrong input.");
			}			
			return ANSWER;
		}	
	},
	EXIT {
		@Override
		public EnumStates transfer(InteractiveCmd context) {
			throw new IllegalStateException("FATAL ERROR: THIS PROGRAM SHOULD STOPPED!");
		}
	};
	
	public abstract EnumStates transfer(InteractiveCmd context);
	
	private static String extractCommand(Matcher matcher) {
		String cmd = matcher.group(CAPGROUP_COMMAND);
		if(cmd == null)
			throw new IllegalArgumentException("Wrong input, expect a command, but got: " + matcher.group(0));
		return cmd;
	}
	
	private static String extractExpression(Matcher matcher) {
		String expr = matcher.group(CAPGROUP_SEGMENT);
		if(expr != null)
		{
			return expr.substring(1, expr.length()-1);
		}
		expr = matcher.group(CAPGROUP_EXPRESSION);
		if(expr == null)
			throw new IllegalArgumentException("Wrong input, expect an expression, but got: " + matcher.group(0));
		return expr;
	}
}
