import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();

		// system("cls");
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// game main loop
		while (true) {
			board.draw();
			cc.execute();
		}
	}
}
