import java.util.Random;

public class Board {
	private int stageWidth = 5;						// �Ֆʂ̉���
	private int stageHeight = 5;					// �Ֆʂ̏c��
	private int windowWidth = stageWidth+2;			// ��ʂ̉���
	private int windowHeight = stageHeight+2;		// ��ʂ̏c��

	private State[][] stage;						// �X�e�[�W

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
		stage = new State[windowWidth][windowHeight];

		// �ǂ𐶐�
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				stage[0][x] = State.WALL;
				stage[windowHeight-1][x] = State.WALL;
				stage[y][0] = State.WALL;
				stage[y][windowWidth-1] = State.WALL;
			}
		}

		// ��}�X�𐶐�
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				stage[y+1][x+1] = State.SPACE;
			}
		}

		// ���e��z�u
		putBomb();
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
				print(y == 0 ? "  " : " " + y);
			} else {
				print("  ");
			}

			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x]) {
				case SPACE:
					print("��");
					break;
				case WALL:
					print("��");
					break;
				case BOMB:
					print("��");
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
				int n = r.nextInt(15);
				if (n > 10) stage[y+1][x+1] = State.BOMB;
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

	/*
	 * �w�肵���}�X�����e���𔻒f���郁�\�b�h�B
	 * ���e�ł����true��Ԃ��A�����łȂ����false��Ԃ��B
	 */
	private boolean isBomb(int x, int y) {
		return stage[y][x] == State.BOMB;
	}

	/**
	 * �Ֆʂ͈̔͊O������s�����\�b�h�ł��B
	 * @param x �w�肵���������n����܂��B
	 * @param y �w�肵���c�����n����܂��B
	 * @return �w�肵���l���Ֆʂ͈̔͊O�ł����true��Ԃ��A�͈͓��ł����false��Ԃ��܂��B
	 */
	public boolean isStageOut(int x, int y) {
		if (x >= stageWidth || y >= stageHeight) return true;
		if (x <= 0 || y <= 0) return true;
		return false;
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
