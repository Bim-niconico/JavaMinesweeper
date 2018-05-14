import java.util.Random;

public class Board {
	private boolean debugMode = false;				// デバッグモード

	private int stageWidth	 = 10;					// 盤面の横幅
	private int stageHeight  = 10;					// 盤面の縦幅
	private int windowWidth  = stageWidth+2;		// 画面の横幅
	private int windowHeight = stageHeight+2;		// 画面の縦幅


	private int difficulty;							// 難易度 difficulty/100の確立で爆弾が配置される
	private Cell[][] stage;							// ステージ

	/**
	 * Boardクラスのコンストラクタです。
	 * 盤面の状態を初期化し、新しいステージを構築します。
	 */
	public Board() {
		init();
	}

	/**
	 * Boardクラスのコンストラクタです。
	 * 盤面の状態を初期化し、新しいステージを構築します。
	 * また、引数に指定した整数型の数値で画面の大きさを設定します。
	 * @param width 画面の横幅
	 * @param height 画面の縦幅
	 */
	public Board(int width, int height) {
		stageWidth = height;
		stageHeight = width;
		//TODO
	}

	/*
	 * ステージの初期化を行うメソッドです。
	 * 下記、図のように初期化が行われます。
	 * 壁 - ■, スペース - □, 爆弾 - ※
	 * 例:) 盤面のサイズが5x5の場合
	 * ■■■■■■■
	 * ■□□□□※■
	 * ■□※□※□■
	 * ■□□□□□■
	 * ■□※□□□■
	 * ■□※※□□■
	 * ■■■■■■■
	 * 爆弾はランダムで配置されます。
	 */
	private void init() {
		stage = new Cell[windowWidth][windowHeight];
		difficulty = 20;

		stageInit();

		// 壁を生成
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				stage[0][x].state = State.WALL;
				stage[windowHeight-1][x].state = State.WALL;
				stage[y][0].state = State.WALL;
				stage[y][windowWidth-1].state = State.WALL;
			}
		}

		// 空マスを生成
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				stage[y+1][x+1].state = State.SPACE;
			}
		}

		// 爆弾を配置
		putBomb();
	}

	/*
	 * ステージの状態をスペースで初期化します。
	 */
	private void stageInit() {
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x ) {
				stage[y][x] = new Cell(State.SPACE);
			}
		}
	}

	/**
	 * ステージを描画させるメソッドです。
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
					print("□");
					break;
				case WALL:
					print("■");
					break;
				case BOMB:
					// デバッグモード
					if (debugMode) {
						print("※");
						break;
					}

					// 旗が立てられていたら
					if (stage[y][x].isFlag) {
						print("Ｆ");
						break;
					}

					print("□");
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
	 * ゲームオーバーになった際、回答を表示するメソッドです。
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
					print("□");
					break;
				case WALL:
					print("■");
					break;
				case BOMB:
					// 旗が立てられていたら
					if (stage[y][x].isFlag) {
						print("Ｆ");
						break;
					}

					print("※");
					break;
				case OPEN:
					print(" " + countBomb(x, y));
					break;
				case FLAG:
					print("Ｆ");
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
	 * 盤面上にランダムで爆弾を配置するメソッドです。
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
	 * 指定したマスを開くメソッドです。
	 * 開いたらそこのマスが爆弾か判断し、爆弾でなければ周囲の爆弾の数を数えます。
	 * また、周囲に爆弾がなければ、空マスの部分を開けます。
	 * @param x 開くマスの横軸が渡されます。
	 * @param y 開くマスの縦軸が渡されます。
	 * @return そのマスが開けるならtrueを返しそうでなければfalseを返します。
	 */
	public boolean put(int x, int y) {
		if (isBomb(x, y)) return false;
		return true;
	}

	/**
	 * 指定したマスの周りにある爆弾の数を数えます。
	 * @param x 開くマスの横軸が渡されます。
	 * @param y 開くマスの縦軸が渡されます。
	 * @return そのマスの周りにある爆弾の数が返されます。
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
	 * 周囲の連なるマスの爆弾の数が0の時にそのマスを自動的に開くメソッドです。
	 * @param x 探索対象のx軸の座標が渡されます。
	 * @param y 探索対象のy軸の座標が渡されます。
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
	 * 旗を立てることが出来るか判定するメソッドです。
	 * @param x 旗を立てる位置のX軸座標が渡されます。
	 * @param y 旗を立てる位置のY軸座標が渡されます。
	 * @return 旗を立てることが出来ればtrueをそうでなければfalseを返します。
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
	 * 指定したマスが爆弾かを判断するメソッド。
	 * 爆弾であればtrueを返し、そうでなければfalseを返す。
	 * @param x 判定対象のマスのX軸座標が渡されます。
	 * @param y 判定対象のマスのY軸座標が渡されます。
	 * @return 爆弾であればtrueをそうでない場合はfalseを返します。
	 */
	public boolean isBomb(int x, int y) {
		return stage[y][x].state == State.BOMB;
	}

	/**
	 * 盤面の範囲外判定を行うメソッドです。
	 * @param x 指定した横軸が渡されます。
	 * @param y 指定した縦軸が渡されます。
	 * @return 指定した値が盤面の範囲外であればtrueを返し、範囲内であればfalseを返します。
	 */
	public boolean isStageOut(int x, int y) {
		return stage[y][x].state == State.WALL;
	}

	/**
	 * ゲームをクリアしたか判定するメソッドです。
	 * @return クリアしているならtrueを返し、そうでなければfalseが返されます。
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
	 * 指定したマスが開けられている場合にはtrueをそうで無い場合はfalseを返します。
	 */
	private boolean isOpenCell(int x, int y) {
		return stage[y][x].state == State.OPEN;
	}

	/*
	 * 指定したマスに旗が立てられているか判定するメソッド。
	 */
	private boolean isFlag(int x, int y) {
		return stage[y][x].isFlag;
	}

	/* ====================
	 * 以下、アクセッサ
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
	 * 以下、ラッパーメソッド
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
