public class StageConfig {
	private int[][] config = {
		{10, 10, 20, 50},
		{15, 15, 35, 60},
		{20, 20, 50, 70},
	};

	public int[] getConfig(Difficulty d) {
		if (d != Difficulty.CUSTOM)
			return config[d.ordinal()];
		return null;
	}
}
