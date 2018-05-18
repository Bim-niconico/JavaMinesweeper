import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.InterruptedException;

public class GameControl {
	private Scene gameScene;		// ���݂̃Q�[���̃V�[��
	private Board board;			// �Ֆʂ��Ǘ�����I�u�W�F�N�g
	private Scanner sc;				// ���͗p

	/**
	 * GameControl�N���X�̃R���X�g���N�^�ł��B
	 * ����ɂ���āA�V�����V�[��������܂��B
	 */
	public GameControl() {
		gameScene = new Scene();
		sc = new Scanner(System.in);
		board = new Board();
	}

	/**
	 * �Q�[���̃X�^�[�g��ʂ��\�z���郁�\�b�h�ł��B
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
					System.out.println("���͒l������������܂���B");
					continue;
				}
			} catch (InputMismatchException mismatch) {
				System.out.println("���l�ȊO�����͂���܂����B");
				System.out.println("���l����͂��Ă��������B");
				sc.nextLine();

				try {
					Thread.sleep(3000);
				} catch (InterruptedException Interrupt) {}

				return 0;
			}
		}
	}

	/**
	 * �Q�[���̃v���C��ʂ��\�z���郁�\�b�h�ł��B
	 * �����ɂ̓v���C���[���I��������Փx���n����܂��B
	 * @param select �v���C���[���I��������Փx���n����܂��B
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
		// �v���C���[����
		System.out.println("�t�H�[�}�b�g: command,x,y");
		System.out.println("command : o - �}�X���J��, f - ���𗧂Ă�, m - ���j���[���J��");
		System.out.print("�R�}���h >>");

		try {
			input = sc.next();
		} catch (InputMismatchException mismatch) {
			System.out.println("���l�ȊO�����͂���܂����B");
			System.out.println("���l����͂��Ă��������B");
			sc.nextLine();

			try {
				Thread.sleep(3000);
			} catch (InterruptedException Interrupt) {}
		}

		// ���͂�,��؂�ŕ���
		return input.split(",");
	}

	public int gameMenu() {
		int select = 0;
		return select;
	}

}
