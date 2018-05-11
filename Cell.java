public class Cell {
	private State state;			// マスの状態
	private boolean checkFlag;		// マスが探索済みか
	private boolean selectFlag;		// マスが選択されているか

	/**
	 * Cellクラスのデフォルトコンストラクタです。
	 * デフォルトコンストラクタで初期化された場合にはすべてState.SPACEで初期化されます。
	 */
	public Cell() {
		this.state = State.SPACE;
		checkFlag = false;
		selectFlag = false;
	}

	/**
	 * Cellクラスのコンストラクタです。
	 * マスの状態の初期化指定した値でを行います。
	 * @param state マスの初期状態の値が渡されます。
	 */
	public Cell(State state) {
		this();
		this.state = state;
	}

	/* ====================
	 * 以下、アクセッサ
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
