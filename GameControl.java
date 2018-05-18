import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;
import java.lang.InterruptedException;

public class GameControl {
	private Scene gameScene;		// ���݂̃Q�[���̃V�[��
	private Board board;			// �Ֆʂ��Ǘ�����I�u�W�F�N�g
	private Scanner sc;				// ���͗p
	private StageConfig config;		// �X�e�[�W�̍\��

	/**
	 * GameControl�N���X�̃R���X�g���N�^�ł��B
	 * ����ɂ���āA�V�����V�[��������܂��B
	 */
	public GameControl() {
		gameScene = new Scene();
		sc = new Scanner(System.in);
		config = new StageConfig();
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
					int[] stageData = new int[3];	// �X�e�[�W�f�[�^
					switch (select) {
					case 1: stageData = config.getConfig(Difficulty.EASY); break;
					case 2: stageData = config.getConfig(Difficulty.NORMAL); break;
					case 3: stageData = config.getConfig(Difficulty.HARD); break;
					case 4: break; // TODO
					default: System.exit(0);	// �{���͑ʖځA��ŏC���v
					}

					int width  = stageData[0];
					int height = stageData[1];
					int bomb   = stageData[2];
					// �I��������Փx�ɂ���ăX�e�[�W���\�z
					board = new Board(width, height, bomb);

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
	 * @param level �v���C���[���I��������Փx���n����܂��B
	 */
	public void gamePlay(int level) {
		gameScene.setSceneID(SceneID.PLAY);
		board.draw();

		String[] commands = inputMessage();
		String command = commands[0];
		int x = Integer.parseInt(commands[1]);
		int y = Integer.parseInt(commands[2]);

		if (command.length() == 1) {
			if (command.equals("o")) {			// �}�X���J���ꍇ
			} else if (command.equals("f")) {	// ����u���ꍇ
			} else if (command.equals("m")) {	// ���j���[���J���ꍇ
			}
		}

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
