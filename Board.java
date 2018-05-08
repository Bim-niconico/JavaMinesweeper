import java.util.Random;

public class Board {
	private int stageWidth = 5;
	private int stageHeight = 5;
	private int windowWidth = stageWidth+2;
	private int windowHeight = stageHeight+2;

	private State[][] stage;

	public Board() {
		init();
	}

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
			}
		}

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
	}
}
