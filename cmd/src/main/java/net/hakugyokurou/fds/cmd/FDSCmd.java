package net.hakugyokurou.fds.cmd;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public final class FDSCmd {
	
	private static final String TITLE = "fds";
	public static final int RESULT_SUCCESS = 0;
	public static final int RESULT_FAIL_INVAILDOPTS = 1;
	public static final int RESULT_FAIL_READLINE = 2;
	public static final int RESULT_INTERACTION = 200;
	
	private static final String INPUT_EVAL = "e";
	private static final String INPUT_EVAL_FULL = "eval";
	private static final String INPUT_EVAL_DESC = "Read and parse an expression, and then eval it and store the result.";
	private static final String INPUT_EVAL_DEFAULT = "0";
	
	private static final String MISC_INTERACT = "I";
	private static final String MISC_INTERACT_FULL = "interact";
	private static final String MISC_INTERACT_DESC = "Enter interactive mode.";
	
	private static final String MISC_HELP = "h";
	private static final String MISC_HELP_FULL = "help";
	private static final String MISC_HELP_DESC = "Yukari I need your help!";
	private static final String MISC_HELP_DEFAULT = "help";
	
	private FDSCmd() {}
	
	public static void main(String[] args) {
		int result = command(args);
		if(result != RESULT_INTERACTION)
			System.exit(result);
		interact(System.in, System.out, System.getProperties());
		System.exit(0);
	}
	
	public static int command(String ... args) {
		return command(System.getProperties(), args);
	}
	
	public static int command(Properties properties, String ... args) {
		DefaultParser parser = new DefaultParser();
		Options options = init();
		try {
			CommandLine command = parser.parse(options, args, false);
			if(command.hasOption(MISC_INTERACT))
			{
				System.out.println("FDSCmd - Finite Digit Summator Terminal");
				System.out.println("You are entering to interactive mode.");
				System.out.println("Input 'help' to show commands list. Input 'exit' or 'quit' to exit.");
				return RESULT_INTERACTION;
			}
			if(command.hasOption(MISC_HELP))
			{
				miscHelp(options, command.getOptionValue(MISC_HELP, MISC_HELP_DEFAULT));
				return RESULT_SUCCESS;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			return RESULT_FAIL_INVAILDOPTS;
		}
		return RESULT_SUCCESS;
	}
	
	public static void interact(InputStream inputStream, PrintStream outputStream, Properties properties) {
		new InteractiveCmd(inputStream, outputStream, properties).interact();
	}

	private static Options init() {
		Options options = new Options();
		{
			OptionGroup group = new OptionGroup();
			group.addOption(Option.builder(MISC_HELP)
						.longOpt(MISC_HELP_FULL)
						.desc(MISC_HELP_DESC)
						.hasArg()
						.optionalArg(true)
						.build());
			group.addOption(Option.builder(MISC_INTERACT)
						.longOpt(MISC_INTERACT_FULL)
						.desc(MISC_INTERACT_DESC)
						.build());
			options.addOptionGroup(group);
		}
		return options;
	}
	
	private static void miscHelp(Options options, String arg) {
		if(arg.equals(MISC_HELP) || arg.equals(MISC_HELP_FULL))
		{
			HelpFormatter helpFormatter = new HelpFormatter();
			helpFormatter.printHelp(TITLE, "", options, "", true);
		}
	}
}
