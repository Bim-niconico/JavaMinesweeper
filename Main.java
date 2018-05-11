import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();				// �Q�[���X�e�[�W
		Scanner sc = new Scanner(System.in);	// ����

		// �t���O
		boolean drawUpdateFlag = false;			// �Q�[���̉�ʍX�V�t���O
		boolean clearFlag = false;				// �Q�[���̃N���A����t���O
		boolean gameOverFlag = false;			// �Q�[���I�[�o�̔���t���O

		// system("cls");(C, C++)
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// �Q�[���̃��C�����[�v
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
					// �I�������}�X�����e��������I��
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
		// �Q�[���N���A����
		if (clearFlag) System.out.println("���߂łƂ��������܂��I");
		// �Q�[���I�[�o�[����
		if (gameOverFlag) {
			cc.execute();
			board.answer();
			System.out.println("�Q�[���I�[�o�[");
		}
	}
}
