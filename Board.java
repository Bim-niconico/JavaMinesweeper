<<<<<<< HEAD
import java.util.Random;

public class Board {
	private int stageWidth = 5;
	private int stageHeight = 5;
	private int windowWidth = stageWidth+2;
	private int windowHeight = stageHeight+2;

	private State[][] stage;
=======
public class Board {
	public static final int BOARDSIZE = 8;
	public static final int BLACK = 0;
	public static final int WHITE = 1;
	public static final int MARK = 2;
	public static final int NONE = 9;

	private int[][] board;
>>>>>>> parent of 3b136a0... var 0.5

	public Board() {
		init();
	}

<<<<<<< HEAD
	public Board(int x, int y) {
		stageWidth = x;
		stageHeight = y;
		//TODO
	}

	private void init() {
		stage = new State[windowWidth][windowHeight];

		// make wall
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				stage[0][x] = State.WALL;
				stage[windowHeight-1][x] = State.WALL;
				stage[y][0] = State.WALL;
				stage[y][windowWidth-1] = State.WALL;
			}
		}

		// make space
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				stage[y+1][x+1] = State.SPACE;
=======
	private void init() {
		board = new int[BOARDSIZE][BOARDSIZE];
		
		for (int i = 0; i < BOARDSIZE; ++i) {
			for (int j = 0; j < BOARDSIZE; ++j) {
				board[i][j] = NONE;
			}
		}

		board[BOARDSIZE/2-1][BOARDSIZE/2-1] = WHITE;
		board[BOARDSIZE/2][BOARDSIZE/2] = WHITE;
		board[BOARDSIZE/2][BOARDSIZE/2-1] = BLACK;
		board[BOARDSIZE/2-1][BOARDSIZE/2] = BLACK;
	}

	public void showBoard(int turn) {
		System.out.println((turn == BLACK) ? "黒のターンです。" : "白のターンです。");
		System.out.println("  0 1 2 3 4 5 6 7");
		for (int i = 0; i < BOARDSIZE; ++i) {
			System.out.print(" " + i);
			for (int j = 0; j < BOARDSIZE; ++j) {
				switch (board[i][j]) {
					case NONE:
						System.out.print("□"); break;
					case BLACK:
						System.out.print("○"); break;
					case WHITE:
						System.out.print("●"); break;
					case MARK:
						System.out.print("＊"); break;
					default:
						System.out.print("er"); break;
				}
>>>>>>> parent of 3b136a0... var 0.5
			}
		}

<<<<<<< HEAD
		// put bomb
		putBomb();
	}

	// stage draw
	public void draw() {
		for (int y = 0; y < windowHeight; ++y) {
			for (int x = 0; x < windowWidth; ++x) {
				switch (stage[y][x]) {
				case SPACE:
					print(" ");
					break;
				case WALL:
					print("#");
					break;
				case BOMB:
					print("*");
					break;
				default:
					print("e");
					break;
				}
			}
			println();
		}
	}

	// random put bomb to stage
	private void putBomb() {
		Random r = new Random();
		for (int y = 0; y < stageHeight; ++y) {
			for (int x = 0; x < stageWidth; ++x) {
				int n = r.nextInt(15);
				if (n > 10) stage[y+1][x+1] = State.BOMB;
			}
		}
	}

	// accessor
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

	// wrapper
	private void print(Object obj) {
		System.out.print(obj);
	}

	private void println() {
		System.out.println();
	}

	private void println(Object obj) {
		System.out.println(obj);
=======
	public boolean put(int height, int width, int turn) {
		return putCheck(height, width, turn);
	}

	public boolean inputCheck(int height, int width) {
		if (height <= 0 || height > BOARDSIZE)
			return true;
		if (width <= 0 || width > BOARDSIZE)
			return true;

		return false;
	}

	private boolean putCheck(int height, int width, int turn) {
		return putAroundCheck(height, width, turn);
	}

	private boolean putAroundCheck(int height, int width, int turn) {
		for (int i = -1; i <= 1; ++i) {
			for (int j = -1; j <= 1; ++j) {
				if (i == 0 && j == 0) continue;
				if ((height+i) < 0 || (width+j) < 0) continue;
				if ((height+i) >= BOARDSIZE || (width+j) >= BOARDSIZE) continue;
				if (board[height+i][width+j] == NONE) continue;
				if (board[height+i][width+j] == MARK) continue;
				if (board[height+i][width+j] == turn) continue;

				if (board[height+i][width+j] != turn) {
					if (putLineCheck(height, width, i, j, turn)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean putLineCheck(int height, int width, int vecY, int vecX, int turn) {
		int tmpHeight = height;
		int tmpWidth  = width;

		while (true) {
			tmpHeight += vecY;
			tmpWidth  += vecX;

			if (tmpHeight >= BOARDSIZE || tmpWidth >= BOARDSIZE)
				return false;
			if (tmpHeight < 0 || tmpWidth < 0)
				return false;

			if (board[tmpHeight][tmpWidth] == NONE)
				continue;
			if (board[tmpHeight][tmpWidth] == MARK)
				continue;

			if (board[tmpHeight][tmpWidth] == turn) {
				flip(height, width, vecY, vecX, tmpHeight, tmpWidth, turn);
				return true;
			}
		}
	}

	public void putMark(int turn) {
		for (int i = 0; i < BOARDSIZE; ++i) {
			for (int j = 0; j < BOARDSIZE; ++j) {
				if (board[i][j] == MARK) board[i][j] = NONE;
			}
		}

		for (int i = 0; i < BOARDSIZE; ++i) {
			for (int j = 0; j < BOARDSIZE; ++j) {
				if (put(i, j, turn))
					if (board[i][j] == NONE) board[i][j] = MARK;
			}
		}
	}

	public void flip(int height,
					 int width,
					 int vecY,
					 int vecX,
					 int tmpVecY,
					 int tmpVecX,
					 int turn) {
		while (true) {
			height += vecY;
			width  += vecX;

			if (vecY > 0)
				if (height >= tmpVecY || width >= tmpVecX) break;
			else
				if (height <= tmpVecY || width <= tmpVecX) break;

			if (height >= BOARDSIZE || width >= BOARDSIZE) break;
			if (height < 0 || width < 0) break;

			board[height][width] = turn;
		}
>>>>>>> parent of 3b136a0... var 0.5
	}

	/* debug */
	public void setBoard(int height, int width, int turn) {
		board[height][width] = turn;
	}

	public int[][] getBoard() {
		return board;
	}
	/* debug */

}
