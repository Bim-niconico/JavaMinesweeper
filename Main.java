import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
	public static final String version = "0.9.1";

	public static void main(String[] args) {
		GameControl game = new GameControl();	// ゲームコントローラ
		// system("cls");(C, C++)
		ConsoleControl cc = new ConsoleControl("cmd", "/c", "cls");

		// スタート画面
		int select = game.gameStart();
		cc.execute();

		// ゲーム画面
		while (!game.getGameOverFlag()) {
			game.gamePlay(select);
			cc.execute();
		}

		// 終了画面
		// game.end();
	}
}
