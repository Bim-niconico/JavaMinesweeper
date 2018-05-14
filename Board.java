import java.util.Random;

public class Board {
	private boolean debugMode = false;				// �f�o�b�O���[�h

	private int stageWidth	 = 10;					// �Ֆʂ̉���
	private int stageHeight  = 10;					// �Ֆʂ̏c��
	private int windowWidth  = stageWidth+2;		// ��ʂ̉���
	private int windowHeight = stageHeight+2;		// ��ʂ̏c��


	private int difficulty;							// ��Փx difficulty/100�̊m���Ŕ��e���z�u�����
	private Cell[][] stage;							// �X�e�[�W

	/**
	 * Board�N���X�̃R���X�g���N�^�ł��B
	 * �Ֆʂ̏�Ԃ����������A�V�����X�e�[�W���\�z���܂��B
	 */
	public Board() {
		init();
	}

	/**
	 * Board�N���X�̃R���X�g���N�^�ł��B
	 * �Ֆʂ̏�Ԃ����������A�V�����X�e�[�W���\�z���܂��B
	 * �܂��A�����Ɏw�肵�������^�̐��l�ŉ�ʂ̑傫����ݒ肵�܂��B
	 * @param width ��ʂ̉���
	 * @param height ��ʂ̏c��
	 */
	public Board(int width, int height) {
		stageWidth = height;
		stageHeight = width;
		//TODO
	}

	/*
	 * �X�e�[�W�̏��������s�����\�b�h�ł��B
	 * ���L�A�}�̂悤�ɏ��������s���܂��B
	 * �� - ��, �X�y�[�X - ��, ���e - ��
	 * ��:) �Ֆʂ̃T�C�Y��5x5�̏ꍇ
	 * ��������������
	 * ��������������
	 * ��������������
	 * ��������������
	 * ��������������
	 * ��������������
	 * ��������������
	 * ���e�̓����_���Ŕz�u����܂��B
	 */
	private void init() {
		stage = new Cell[windowWidth][windowHeight];
		difficulty = 20;

		stageInit();

		// �ǂ𐶐�
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				stage[0][x].state = State.WALL;
				stage[windowHeight-1][x].state = State.WALL;
				stage[y][0].state = State.WALL;
				stage[y][windowWidth-1].state = State.WALL;
			}
		}

		// ��}�X�𐶐�
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				stage[y+1][x+1].state = State.SPACE;
			}
		}

		// ���e��z�u
		putBomb();
	}

	/*
	 * �X�e�[�W�̏�Ԃ��X�y�[�X�ŏ��������܂��B
	 */
	private void stageInit() {
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x ) {
				stage[y][x] = new Cell(State.SPACE);
			}
		}
	}

	/**
	 * �X�e�[�W��`�悳���郁�\�b�h�ł��B
	 */
	public void draw() {
		for (int i = 0; i < stageWidth; ++i) {
			if (i == 0) print("    ");
			print(" " + (i+1));
		}
		println();

		for (int y = 0; y < windowHeight; ++y) {
			if (y != windowHeight-1) {
				if (y == 0) print("  ");
				else if (y < 10) print(" " + y);
				else print(y);
			} else {
				print("  ");
			}

			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x].state) {
				case SPACE:
					print("��");
					break;
				case WALL:
					print("��");
					break;
				case BOMB:
					// �f�o�b�O���[�h
					if (debugMode) {
						print("��");
						break;
					}

					// �������Ă��Ă�����
					if (stage[y][x].isFlag) {
						print("�e");
						break;
					}

					print("��");
					break;
				case OPEN:
					print(" " + countBomb(x, y));
					break;
				default:
					print("er");
					break;
				}
			}
			println();
		}
	}

	/**
	 * �Q�[���I�[�o�[�ɂȂ����ہA�񓚂�\�����郁�\�b�h�ł��B
	 */
	public void answer() {
		for (int i = 0; i < stageWidth; ++i) {
			if (i == 0) print("    ");
			print(" " + (i+1));
		}
		println();

		for (int y = 0; y < windowHeight; ++y) {
			if (y != windowHeight-1) {
				if (y == 0) print("  ");
				else if (y < 10) print(" " + y);
				else print(y);
			} else {
				print("  ");
			}

			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x].state) {
				case SPACE:
					print("��");
					break;
				case WALL:
					print("��");
					break;
				case BOMB:
					// �������Ă��Ă�����
					if (stage[y][x].isFlag) {
						print("�e");
						break;
					}

					print("��");
					break;
				case OPEN:
					print(" " + countBomb(x, y));
					break;
				case FLAG:
					print("�e");
					break;
				default:
					print("er");
					break;
				}
			}
			println();
		}
	}

	/*
	 * �Ֆʏ�Ƀ����_���Ŕ��e��z�u���郁�\�b�h�ł��B
	 */
	private void putBomb() {
		Random r = new Random();
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				int n = r.nextInt(100);
				if (n <= difficulty) stage[y+1][x+1].state = State.BOMB;
			}
		}
	}

	/**
	 * �w�肵���}�X���J�����\�b�h�ł��B
	 * �J�����炻���̃}�X�����e�����f���A���e�łȂ���Ύ��͂̔��e�̐��𐔂��܂��B
	 * �܂��A���͂ɔ��e���Ȃ���΁A��}�X�̕������J���܂��B
	 * @param x �J���}�X�̉������n����܂��B
	 * @param y �J���}�X�̏c�����n����܂��B
	 * @return ���̃}�X���J����Ȃ�true��Ԃ������łȂ����false��Ԃ��܂��B
	 */
	public boolean put(int x, int y) {
		if (isBomb(x, y)) return false;
		return true;
	}

	/**
	 * �w�肵���}�X�̎���ɂ��锚�e�̐��𐔂��܂��B
	 * @param x �J���}�X�̉������n����܂��B
	 * @param y �J���}�X�̏c�����n����܂��B
	 * @return ���̃}�X�̎���ɂ��锚�e�̐����Ԃ���܂��B
	 */
	public int countBomb(int x, int y) {
		int res = 0;

		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				if (isStageOut(x+j, y+i)) continue;
				if (isBomb(x+j, y+i)) ++res;
				stage[y+i][x+j].checkFlag = true;
			}
		}

		return res;
	}


	/**
	 * ���̘͂A�Ȃ�}�X�̔��e�̐���0�̎��ɂ��̃}�X�������I�ɊJ�����\�b�h�ł��B
	 * @param x �T���Ώۂ�x���̍��W���n����܂��B
	 * @param y �T���Ώۂ�y���̍��W���n����܂��B
	 */
	public void neighborCountBomb(int x, int y) {
		for (int vecY = -1; vecY <= 1; ++vecY) {
			for (int vecX = -1; vecX <= 1; ++vecX) {
				int dx = x+vecX; int dy = y+vecY;

				if (isStageOut(dx, dy) || isOpenCell(dx, dy)) continue;
				if (countBomb(dx, dy) == 0) {
					stage[y][x].state = State.OPEN;
					neighborCountBomb(dx, dy);
				} else {
					if (!isBomb(dx, dy))
						stage[dy][dx].state = State.OPEN;
				}
			}
		}
	}

	/**
	 * ���𗧂Ă邱�Ƃ��o���邩���肷�郁�\�b�h�ł��B
	 * @param x ���𗧂Ă�ʒu��X�����W���n����܂��B
	 * @param y ���𗧂Ă�ʒu��Y�����W���n����܂��B
	 * @return ���𗧂Ă邱�Ƃ��o�����true�������łȂ����false��Ԃ��܂��B
	 */
	public boolean checkPutFlag(int x, int y) {
		if (isStageOut(x, y) ||
			isOpenCell(x, y) ||
			isFlag(x, y)) return false;

		return true;
	}

	public void putFlag(int x, int y) {
		stage[y][x].isFlag = true;
	}

	/**
	 * �w�肵���}�X�����e���𔻒f���郁�\�b�h�B
	 * ���e�ł����true��Ԃ��A�����łȂ����false��Ԃ��B
	 * @param x ����Ώۂ̃}�X��X�����W���n����܂��B
	 * @param y ����Ώۂ̃}�X��Y�����W���n����܂��B
	 * @return ���e�ł����true�������łȂ��ꍇ��false��Ԃ��܂��B
	 */
	public boolean isBomb(int x, int y) {
		return stage[y][x].state == State.BOMB;
	}

	/**
	 * �Ֆʂ͈̔͊O������s�����\�b�h�ł��B
	 * @param x �w�肵���������n����܂��B
	 * @param y �w�肵���c�����n����܂��B
	 * @return �w�肵���l���Ֆʂ͈̔͊O�ł����true��Ԃ��A�͈͓��ł����false��Ԃ��܂��B
	 */
	public boolean isStageOut(int x, int y) {
		return stage[y][x].state == State.WALL;
	}

	/**
	 * �Q�[�����N���A���������肷�郁�\�b�h�ł��B
	 * @return �N���A���Ă���Ȃ�true��Ԃ��A�����łȂ����false���Ԃ���܂��B
	 */
	public boolean isClear() {
		for (int y = 1; y <= stageHeight; ++y) {
			for (int x = 1; x <= stageWidth; ++x) {
				if (stage[y][x].state == State.SPACE) return false;
			}
		}

		return true;
	}

	/*
	 * �w�肵���}�X���J�����Ă���ꍇ�ɂ�true�������Ŗ����ꍇ��false��Ԃ��܂��B
	 */
	private boolean isOpenCell(int x, int y) {
		return stage[y][x].state == State.OPEN;
	}

	/*
	 * �w�肵���}�X�Ɋ������Ă��Ă��邩���肷�郁�\�b�h�B
	 */
	private boolean isFlag(int x, int y) {
		return stage[y][x].isFlag;
	}

	/* ====================
	 * �ȉ��A�A�N�Z�b�T
	 * ====================*/
	public int getStageWidth() {
		return stageWidth;
	}

	public int getStageHeight() {
		return stageHeight;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setStageCell(int x, int y, State state) {
		stage[y][x].state = state;
	}

	public int getDifficulty() {
		return difficulty;
	}

	/* ====================
	 * �ȉ��A���b�p�[���\�b�h
	 * ====================*/
	private void print(Object obj) {
		System.out.print(obj);
	}

	private void println() {
		System.out.println();
	}

	private void println(Object obj) {
		System.out.println(obj);
	}
}
