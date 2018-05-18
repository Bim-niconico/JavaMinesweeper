import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		GameControl game = new GameControl();	// ゲームコントローラ
		// system("cls");(C, C++)
		ConsoleControl cc = new ConsoleControl("cmd", "/c", "cls");

		boolean gameOverFlg = false;			// ゲームオーバーフラグ

		// スタート画面
		int select = game.gameStart();
		cc.execute();

		while (true) {
			// ゲーム画面
			game.gamePlay(select);
			cc.execute();
		}

		// 終了画面
		// game.end();
	}
}
