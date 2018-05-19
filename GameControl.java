import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.InterruptedException;
import java.io.IOException;

public class GameControl {
	private Scene gameScene;		// ���݂̃Q�[���̃V�[��
	private Board board;			// �Ֆʂ��Ǘ�����I�u�W�F�N�g
	private Scanner sc;				// ���͗p
	private StageConfig config;		// �X�e�[�W�̍\��

	private boolean gameOverFlg;	// �Q�[���I�[�o�t���O

	/**
	 * GameControl�N���X�̃R���X�g���N�^�ł��B
	 * ����ɂ���āA�V�����V�[��������܂��B
	 */
	public GameControl() {
		gameScene = new Scene();
		sc = new Scanner(System.in);
		config = new StageConfig();
		gameOverFlg = false;
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
					int open   = stageData[3];
					// �I��������Փx�ɂ���ăX�e�[�W���\�z
					board = new Board(width, height, bomb, open);

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
	 * @return �Q�[���I�[�o�łȂ��ꍇ��true��Ԃ��A�Q�[���I�[�o�Ȃ��false��Ԃ��܂��B
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
				if (command.equals("o")) {			// �}�X���J���ꍇ
					if (board.put(x, y))  {
						board.setStageCell(x, y, State.OPEN);
						if (r.nextInt(100) >= board.getOpenProbability())
							board.neighborCountBomb(x, y);
						break;
					} else {
						gameOverFlg = true;
					}
				} else if (command.equals("f")) {	// ����u���ꍇ
					if (board.checkPutFlag(x, y)) {
						board.putFlag(x, y);
						break;
					}
				} else if (command.equals("m")) {	// ���j���[���J���ꍇ
				}
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
	 * �ȉ��A�A�N�Z�b�T
	 * ====================*/
	public boolean getGameOverFlag() {
		return gameOverFlg;
	}
}
