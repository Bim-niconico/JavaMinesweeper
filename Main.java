import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();				// ゲームステージ
		Scanner sc = new Scanner(System.in);	// 入力

		// フラグ
		boolean drawUpdateFlag = false;			// ゲームの画面更新フラグ
		boolean clearFlag = false;				// ゲームのクリア判定フラグ
		boolean gameOverFlag = false;			// ゲームオーバの判定フラグ

		// system("cls");(C, C++)
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// ゲームのメインループ
		while (true) {
			if (board.isClear()) {
				clearFlag = true;
				break;
			}

			board.draw();
			try {
				System.out.print("x >>");
				int x = sc.nextInt();
				System.out.print("y >>");
				int y = sc.nextInt();

				if (!board.isStageOut(x, y)) {
					// 選択したマスが爆弾だったら終了
					if (!board.put(x, y)) {
						gameOverFlag = true;
						break;
					}
					else {
						board.setStageCell(x, y, State.OPEN);
						board.neighborCountBomb(x, y);
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

			// ゲーム画面の更新
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
