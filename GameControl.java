import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.InterruptedException;

public class GameControl {
	private Scene gameScene;		// 現在のゲームのシーン
	private Board board;			// 盤面を管理するオブジェクト
	private Scanner sc;				// 入力用

	/**
	 * GameControlクラスのコンストラクタです。
	 * これによって、新しいシーンが作られます。
	 */
	public GameControl() {
		gameScene = new Scene();
		sc = new Scanner(System.in);
		board = new Board();
	}

	/**
	 * ゲームのスタート画面を構築するメソッドです。
	 */
	public int gameStart() {
		gameScene.setSceneID(SceneID.START);
		gameScene.selectView(SceneID.START);
		
		System.out.print("1-4 >>");
		while (true) {
			try {
				int select = sc.nextInt();
				boolean isBreak = (select >= 1 && select <= 4) ? true : false;
				if (isBreak) {
					return select;
				} else {
					System.out.println("入力値が正しくありません。");
					continue;
				}
			} catch (InputMismatchException mismatch) {
				System.out.println("数値以外が入力されました。");
				System.out.println("数値を入力してください。");
				sc.nextLine();

				try {
					Thread.sleep(3000);
				} catch (InterruptedException Interrupt) {}

				return 0;
			}
		}
	}

	/**
	 * ゲームのプレイ画面を構築するメソッドです。
	 * 引数にはプレイヤーが選択した難易度が渡されます。
	 * @param select プレイヤーが選択した難易度が渡されます。
	 */
	public void gamePlay(int level) {
		gameScene.setSceneID(SceneID.PLAY);
		board.draw();

		String[] commands = inputMessage();
		char command = commands[0].charAt(0);
		int x = Integer.parseInt(commands[1]);
		int y = Integer.parseInt(commands[2]);

	}

	private String[] inputMessage() {
		String input = "";
		// プレイヤー入力
		System.out.println("フォーマット: command,x,y");
		System.out.println("command : o - マスを開く, f - 旗を立てる, m - メニューを開く");
		System.out.print("コマンド >>");

		try {
			input = sc.next();
		} catch (InputMismatchException mismatch) {
			System.out.println("数値以外が入力されました。");
			System.out.println("数値を入力してください。");
			sc.nextLine();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException Interrupt) {}
		}

		// 入力を,区切りで分割
		return input.split(",");
	}

	public int gameMenu() {
		int select = 0;
		return select;
	}

}
