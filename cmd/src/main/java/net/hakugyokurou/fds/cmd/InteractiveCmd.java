package net.hakugyokurou.fds.cmd;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.hakugyokurou.fds.MathExpression;

public final class InteractiveCmd {
	
	/**
	 * 用于匹配输入命令的正则表达式,它有3个捕获组,捕获组1为捕获指令(例如pan),
	 * 2为捕获被引号包围的内容(比如"1c0xr8Hy.h0yx.SSTM"),
	 * 3为捕获表达式(比如233 + 666),
	 */
	private static final Pattern PATTERN = Pattern.compile("([a-zA-Z]+)|(\"[^\"]*\")|[\\s]*([^a-zA-Z\"]*)");
	static final int CAPGROUP_COMMAND = 1;
	static final int CAPGROUP_SEGMENT = 2;
	static final int CAPGROUP_EXPRESSION = 3;
	final Matcher matcher = PATTERN.matcher("");
	private final InputStream inputStream;
	private final BufferedReader bufferedReader;
	PrintStream outputStream;
	final Properties properties;
	ArrayList<MathExpression> expressions = new ArrayList<MathExpression>();
	int currentQuestionId = 0, maxQuestionId = 0, statsAccepted = 0, statsSkipped = 0;
	private EnumStates state = EnumStates.STANDBY;
	
	public InteractiveCmd(InputStream inputStream, PrintStream outputStream, Properties properties) {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		this.properties = properties;
		bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	}
	
	public void interact() {
		mainloop: while(true)
		{
			try {
				matcher.reset(bufferedReader.readLine());
				while(matcher.find())
				{
					String matched = matcher.group(CAPGROUP_EXPRESSION);
					if(matched!= null && matched.trim().isEmpty())
						continue;
					EnumStates nextState = state.transfer(this);
					if(nextState == EnumStates.EXIT)
						break mainloop;
					state = nextState;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} //do not close a stream from stdio.
		}
	}
}