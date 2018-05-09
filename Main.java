import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();
		Scanner sc = new Scanner(System.in);

		// system("cls");
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// game main loop
		while (true) {
			board.draw();
			try {
				int x = sc.nextInt();
				int y = sc.nextInt();

				if (!board.isStageOut(x, y)) {
					// 選択したマスが爆弾だったら終了
					if (!board.put(x, y)) break;
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

			cc.execute();
		}
	}
}
