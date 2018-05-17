import java.util.Scanner;

public class Scene {
	public enum SceneID {
		START,	// スタート画面
		GAME,	// ゲーム画面
		MENU,	// メニュー画面
		END,	// 終了画面
	}

	private SceneID scene;

	public Scene() {}

	private void sceneStart() {
		println("■■■■■■■■■■");
		println("■                ■");
		println("■   ﾏｲﾝｽｲｰﾊﾟｰ    ■");
		println("■                ■");
		println("■                ■");
		println("■    1.EASY      ■");
		println("■    2.NORMAL    ■");
		println("■    3.HARD      ■");
		println("■    4.CUSTOM    ■");
		println("■                ■");
		println("■■■■■■■■■■");
	}

	private void sceneMenu() {
		println("■■■■■■■■■■");
		println("■                ■");
		println("■ 1.ゲームに戻る ■");
		println("■ 2.リセットする ■");
		println("■ 3.タイトル画面 ■");
		println("■                ■");
		println("■■■■■■■■■■");
	}

	private void sceneEnd() {
		println("■■■■■■■■■■");
		println("■                ■");
		println("■ お疲れ様でした ■");
		println("■                ■");
		println("■■■■■■■■■■");
	}

	/* ====================
	 * 以下、アクセッサ
	 * ====================*/
	public SceneID getSceneID() {
		return scene;
	}

	public void setSceneID(SceneID id) {
		scene = id;
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
