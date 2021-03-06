import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.InterruptedException;
import java.io.IOException;

public class GameControl {
	private Scene gameScene;		// 現在のゲームのシーン
	private Board board;			// 盤面を管理するオブジェクト
	private Scanner sc;				// 入力用
	private StageConfig config;		// ステージの構成

	private boolean gameOverFlg;	// ゲームオーバフラグ

	/**
	 * GameControlクラスのコンストラクタです。
	 * これによって、新しいシーンが作られます。
	 */
	public GameControl() {
		gameScene = new Scene();
		sc = new Scanner(System.in);
		config = new StageConfig();
		gameOverFlg = false;
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
					int[] stageData = new int[3];	// ステージデータ
					switch (select) {
					case 1: stageData = config.getConfig(Difficulty.EASY); break;
					case 2: stageData = config.getConfig(Difficulty.NORMAL); break;
					case 3: stageData = config.getConfig(Difficulty.HARD); break;
					case 4: break; // TODO
					default: System.exit(0);	// 本当は駄目、後で修正要
					}

					int width  = stageData[0];
					int height = stageData[1];
					int bomb   = stageData[2];
					int open   = stageData[3];
					// 選択した難易度によってステージを構築
					board = new Board(width, height, bomb, open);

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
	 * @param level プレイヤーが選択した難易度が渡されます。
	 * @return ゲームオーバでない場合はtrueを返し、ゲームオーバならばfalseを返します。
	 */
	public void gamePlay(int level) {
		Random r = new Random();
		gameScene.setSceneID(SceneID.PLAY);
		board.draw();

		while (true) {
			String[] commands = inputMessage();
			String command = commands[0];
			int x = Integer.parseInt(commands[1]);
			int y = Integer.parseInt(commands[2]);
			
			if (board.isStageOut(x, y)) continue;

			if (command.length() == 1) {
				if (command.equals("o")) {			// マスを開く場合
					if (board.put(x, y))  {
						board.setStageCell(x, y, State.OPEN);
						if (r.nextInt(100) >= board.getOpenProbability())
							board.neighborCountBomb(x, y);
						break;
					} else {
						gameOverFlg = true;
					}
				} else if (command.equals("f")) {	// 旗を置く場合
					if (board.checkPutFlag(x, y)) {
						board.putFlag(x, y);
						break;
					}
				} else if (command.equals("m")) {	// メニューを開く場合
				}
			}
		}
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
		gameScene.selectView(Scene.MENU);

		select = sc.nextInt();
		System.out.print("1-3 >>");

		switch (select) {
		case 1:
			board.draw(); break;
		case 2:
		}


		return select;
	}

	/* ====================
	 * 以下、アクセッサ
	 * ====================*/
	public boolean getGameOverFlag() {
		return gameOverFlg;
	}
}
