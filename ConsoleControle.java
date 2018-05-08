import java.io.IOException;

public class ConsoleControle {
	private ProcessBuilder pb;

	// constructor
	public ConsoleControle(String... command) {
		pb = new ProcessBuilder(command);
	}

	// execute the command
	public void execute() throws IOException, InterruptedException {
		pb.inheritIO().start().waitFor();
	}
}
