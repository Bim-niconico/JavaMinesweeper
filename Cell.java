public class Cell {
	private State state;			// �}�X�̏��
	private boolean checkFlag;		// �}�X���T���ς݂�
	private boolean selectFlag;		// �}�X���I������Ă��邩

	/**
	 * Cell�N���X�̃f�t�H���g�R���X�g���N�^�ł��B
	 * �f�t�H���g�R���X�g���N�^�ŏ��������ꂽ�ꍇ�ɂ͂��ׂ�State.SPACE�ŏ���������܂��B
	 */
	public Cell() {
		this.state = State.SPACE;
		checkFlag = false;
		selectFlag = false;
	}

	/**
	 * Cell�N���X�̃R���X�g���N�^�ł��B
	 * �}�X�̏�Ԃ̏������w�肵���l�ł��s���܂��B
	 * @param state �}�X�̏�����Ԃ̒l���n����܂��B
	 */
	public Cell(State state) {
		this();
		this.state = state;
	}

	/* ====================
	 * �ȉ��A�A�N�Z�b�T
	 * ====================*/
	public void setState(State s) {
		state = s;
	}

	public State getState() {
		return state;
	}

	public void setCheckFlag(boolean b) {
		checkFlag = b;
	}

	public boolean getCheckFlag() {
		return checkFlag;
	}

	public void setSelectFlag(boolean b) {
		selectFlag = b;
	}

	public boolean getSelectFlag() {
		return selectFlag;
	}
}
