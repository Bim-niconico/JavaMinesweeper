import java.io.IOException;

public class ConsoleControl {
	private ProcessBuilder pb;

	/**
	 * ConsoleControleクラスのコンストラクタです。
	 * 引数に指定した外部コマンドを実行させる環境を構築します。
	 * 実際に実行させるにはConsoleControle#execute()を使用します。
	 * @param command 実行させたい外部コマンド
	 */
	public ConsoleControl(String... command) {
		pb = new ProcessBuilder(command);
	}

	/**
	 * コンストラクタで指定された外部コマンドを実行するメソッドです。
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void execute() {
		try {
			pb.inheritIO().start().waitFor();
		} catch (IOException e) {
		} catch (Exception e) {
			System.out.println("致命的なエラーが発生しました。");
			System.out.println("アプリケーションを終了します。");
		}
	}
}
