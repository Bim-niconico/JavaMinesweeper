import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException{
		Board board = new Board();				// �Q�[���X�e�[�W
		Scanner sc = new Scanner(System.in);	// ����
		Random r = new Random();				// ����
		Scene s = new Scene();					// ��ʂ̃V�[��

		// �t���O
		boolean drawUpdateFlag = false;			// �Q�[���̉�ʍX�V�t���O
		boolean clearFlag = false;				// �Q�[���̃N���A����t���O
		boolean gameOverFlag = false;			// �Q�[���I�[�o�̔���t���O

		// system("cls");(C, C++)
		ConsoleControle cc = new ConsoleControle("cmd", "/c", "cls");

		// �Q�[���̃��C�����[�v
		while (true) {
			// �X�^�[�g���
			

			// �Q�[�����


			// �I�����


			if (board.isClear()) {
				clearFlag = true;
				break;
			}

			board.draw();
			try {
				System.out.println("�t�H�[�}�b�g : action,x,y");
				System.out.println("action : \"f\" - �� \"o\" - �J��");
				System.out.print("�R�}���h����� >>");
				String input = sc.next();

				// �R�}���h�𕪊�
				String[] f = input.split(",", 3);
				String command = f[0];
				int x = Integer.parseInt(f[1]);
				int y = Integer.parseInt(f[2]);

				if ((command.charAt(0) != 'f' && command.charAt(0) != 'o')) {
					cc.execute();
					System.out.println("�����Ȓl�ł��B");
					continue;
				}

				if (!board.isStageOut(x, y)) {
					// ����u���Ȃ��ꍇ
					if (command.charAt(0) == 'o' || !board.checkPutFlag(x, y)) {
						// �I�������}�X�����e��������I��
						if (!board.put(x, y)) {
							gameOverFlag = true;
							break;
						}
						else {
							board.setStageCell(x, y, State.OPEN);
							if ((100 - board.getDifficulty()) >= r.nextInt(100))
								board.neighborCountBomb(x, y);
						}
					// ����u���ꍇ
					} else {
						if (board.checkPutFlag(x, y))
							board.putFlag(x, y);
						else if (board.isBomb(x, y)) {
							gameOverFlag = true;
							break;
						} else {
							cc.execute();
							System.out.println("���̈ʒu�Ɋ��͒u���܂���B");
							continue;
						}
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

			// �Q�[����ʂ̍폜
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
