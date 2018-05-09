import java.util.Random;

public class Board {
	private int stageWidth = 5;						// 盤面の横幅
	private int stageHeight = 5;					// 盤面の縦幅
	private int windowWidth = stageWidth+2;			// 画面の横幅
	private int windowHeight = stageHeight+2;		// 画面の縦幅

	private State[][] stage;						// ステージ

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
		stage = new State[windowWidth][windowHeight];

		// 壁を生成
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				stage[0][x] = State.WALL;
				stage[windowHeight-1][x] = State.WALL;
				stage[y][0] = State.WALL;
				stage[y][windowWidth-1] = State.WALL;
			}
		}

		// 空マスを生成
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				stage[y+1][x+1] = State.SPACE;
			}
		}

		// 爆弾を配置
		putBomb();
	}

	/**
	 * ステージを描画させるメソッドです。
	 */
	public void draw() {
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x]) {
				case SPACE:
					print("□");
					break;
				case WALL:
					print("■");
					break;
				case BOMB:
					print("※");
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
				int n = r.nextInt(15);
				if (n > 10) stage[y+1][x+1] = State.BOMB;
			}
		}
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
