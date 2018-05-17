import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();				// ゲームステージ
		Scanner sc = new Scanner(System.in);	// 入力
		Random r = new Random();				// 乱数
		Scene s = new Scene();					// 画面のシーン

		// フラグ
		boolean drawUpdateFlag = false;			// ゲームの画面更新フラグ
		boolean clearFlag = false;				// ゲームのクリア判定フラグ
		boolean gameOverFlag = false;			// ゲームオーバの判定フラグ

		// system("cls");(C, C++)
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// ゲームのメインループ
		while (true) {
			// スタート画面
			

			// ゲーム画面


			// 終了画面


			if (board.isClear()) {
				clearFlag = true;
				break;
			}

			board.draw();
			try {
				System.out.println("フォーマット : action,x,y");
				System.out.println("action : \"f\" - 旗 \"o\" - 開く");
				System.out.print("コマンドを入力 >>");
				String input = sc.next();

				// コマンドを分割
				String[] f = input.split(",", 3);
				String command = f[0];
				int x = Integer.parseInt(f[1]);
				int y = Integer.parseInt(f[2]);

				if ((command.charAt(0) != 'f' && command.charAt(0) != 'o')) {
					cc.execute();
					System.out.println("無効な値です。");
					continue;
				}

				if (!board.isStageOut(x, y)) {
					// 旗を置かない場合
					if (command.charAt(0) == 'o' || !board.checkPutFlag(x, y)) {
						// 選択したマスが爆弾だったら終了
						if (!board.put(x, y)) {
							gameOverFlag = true;
							break;
						}
						else {
							board.setStageCell(x, y, State.OPEN);
							if ((100 - board.getDifficulty()) >= r.nextInt(100))
								board.neighborCountBomb(x, y);
						}
					// 旗を置く場合
					} else {
						if (board.checkPutFlag(x, y))
							board.putFlag(x, y);
						else if (board.isBomb(x, y)) {
							gameOverFlag = true;
							break;
						} else {
							cc.execute();
							System.out.println("その位置に旗は置けません。");
							continue;
						}
					}
				} else {
					cc.execute();
					System.out.println("範囲外の数値です。");
					continue;
				}
			} catch (InputMismatchException e) {
				sc.next();
				cc.execute();
				System.out.println("数値以外の値が入力されました。");
				System.out.println("正しい数値を入力してください。");
				continue;
			}

			// ゲーム画面の削除
			cc.execute();
		}
		// ゲームクリア判定
		if (clearFlag) System.out.println("おめでとうございます！");
		// ゲームオーバー判定
		if (gameOverFlag) {
			cc.execute();
			board.answer();
			System.out.println("ゲームオーバー");
		}
	}
}
