import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();				// �Q�[���X�e�[�W
		Scanner sc = new Scanner(System.in);	// ����
		boolean drawUpdateFlag = false;			// �Q�[���̉�ʍX�V�t���O

		// system("cls");(C, C++)
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// �Q�[���̃��C�����[�v
		while (true) {
			board.draw();
			try {
				System.out.print("x >>");
				int x = sc.nextInt();
				System.out.print("y >>");
				int y = sc.nextInt();

				if (!board.isStageOut(x, y)) {
					// �I�������}�X�����e��������I��
					if (!board.put(x, y)) break;
					else board.setStageCell(x, y, State.OPEN);
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

			// �Q�[����ʂ̍X�V
			cc.execute();
		}
	}
}
