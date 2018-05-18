public class StageConfig {
	private int[][] config = {
		{10, 10, 20},
		{15, 15, 30},
		{15, 15, 40},
	};

	public int[] getConfig(Difficulty d) {
		if (d != Difficulty.CUSTOM)
			return config[d.ordinal()];
		return null;
	}
}
