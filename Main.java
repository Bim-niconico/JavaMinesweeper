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
					// �I�������}�X�����e��������I��
					if (!board.put(x, y)) break;
				} else {
					cc.execute();
					System.out.println("�͈͊O�̐��l�ł��B");
					continue;
				}
			} catch (InputMismatchException e) {
				sc.next();
				cc.execute();
				System.out.println("���l�ȊO�̒l�����͂���܂����B");
				System.out.println("���������l����͂��Ă��������B");
				continue;
			}

			cc.execute();
		}
	}
}
