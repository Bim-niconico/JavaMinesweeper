public class Cell {
	public State state;			// マスの状態
	public boolean checkFlag;		// マスが探索済みか
	public boolean selectFlag;		// マスが選択されているか

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
}
