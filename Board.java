/*
 * 411�̍D�� - 1�I���W������
 */

import java.util.Random;

public class Board {
	private boolean debugMode = true;				// �f�o�b�O���[�h

	private int stageWidth = 5;						// �Ֆʂ̉���
	private int stageHeight = 5;					// �Ֆʂ̏c��
	private int windowWidth = stageWidth+2;			// ��ʂ̉���
	private int windowHeight = stageHeight+2;		// ��ʂ̏c��

	private Cell[][] stage;						// �X�e�[�W

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

		stageInit();

		// �ǂ𐶐�
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				stage[0][x].setState(State.WALL);
				stage[windowHeight-1][x].setState(State.WALL);
				stage[y][0].setState(State.WALL);
				stage[y][windowWidth-1].setState(State.WALL);
			}
		}

		// ��}�X�𐶐�
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				stage[y+1][x+1].setState(State.SPACE);
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
				print(y == 0 ? "  " : " " + y);
			} else {
				print("  ");
			}

			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x].getState()) {
				case SPACE:
					print("��");
					break;
				case WALL:
					print("��");
					break;
				case BOMB:
					if (debugMode) {
						print("��");
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
				print(y == 0 ? "  " : " " + y);
			} else {
				print("  ");
			}

			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x].getState()) {
				case SPACE:
					print("��");
					break;
				case WALL:
					print("��");
					break;
				case BOMB:
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

	/*
	 * �Ֆʏ�Ƀ����_���Ŕ��e��z�u���郁�\�b�h�ł��B
	 */
	private void putBomb() {
		Random r = new Random();
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				int n = r.nextInt(15);
				if (n > 12) stage[y+1][x+1].setState(State.BOMB);
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
			}
		}

		return res;
	}


	/**
	 * ���̘͂A�Ȃ�}�X�̔��e�̐���0�̎��ɂ��̃}�X�������I�ɊJ�����\�b�h�ł��B
	 */
	public void neighborCountBomb(int x, int y) {
		for (int i = -1; i <= 1; ++i ) {
			for (int j = -1; j < 1; ++j) {
				if (i == 0 && j == 0) continue;			// �������g�̃}�X��������
				if (isStageOut(x+j, y+i)) continue;		// �X�e�[�W�O��������
				if (isBomb(x+j, y+i)) continue;			// �w�肵���}�X�����e��������

				if (countBomb(x+j, y+i) == 0) {	
					stage[y+i][x+j].setState(State.OPEN);
					neighborCountBomb(y+i, x+j);
				}
			}
		}
	}

	/*
	 * �w�肵���}�X�����e���𔻒f���郁�\�b�h�B
	 * ���e�ł����true��Ԃ��A�����łȂ����false��Ԃ��B
	 */
	private boolean isBomb(int x, int y) {
		return stage[y][x].getState() == State.BOMB;
	}

	/**
	 * �Ֆʂ͈̔͊O������s�����\�b�h�ł��B
	 * @param x �w�肵���������n����܂��B
	 * @param y �w�肵���c�����n����܂��B
	 * @return �w�肵���l���Ֆʂ͈̔͊O�ł����true��Ԃ��A�͈͓��ł����false��Ԃ��܂��B
	 */
	public boolean isStageOut(int x, int y) {
		return stage[y][x].getState() == State.WALL;
	}

	/**
	 * �Q�[�����N���A���������肷�郁�\�b�h�ł��B
	 * @return �N���A���Ă���Ȃ�true��Ԃ��A�����łȂ����false���Ԃ���܂��B
	 */
	public boolean isClear() {
		for (int y = 1; y <= stageHeight; ++y) {
			for (int x = 1; x <= stageWidth; ++x) {
				if (stage[y][x].getState() == State.SPACE) return false;
			}
		}

		return true;
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
		stage[y][x].setState(state);
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
