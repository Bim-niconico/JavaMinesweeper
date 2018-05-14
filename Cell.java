public class Cell {
	public State state;				// �}�X�̏��
	public boolean checkFlag;		// �}�X���T���ς݂�
	public boolean selectFlag;		// �}�X���I������Ă��邩
	public boolean isFlag;			// ���𗧂ĂĂ��邩�̃t���O

	/**
	 * Cell�N���X�̃f�t�H���g�R���X�g���N�^�ł��B
	 * �f�t�H���g�R���X�g���N�^�ŏ��������ꂽ�ꍇ�ɂ͂��ׂ�State.SPACE�ŏ���������܂��B
	 */
	public Cell() {
		this.state = State.SPACE;
		checkFlag = false;
		selectFlag = false;
		isFlag = false;
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
}
