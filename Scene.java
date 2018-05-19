import java.util.Scanner;

public class Scene {
	private SceneID scene;		// 現在のゲームシーン

	public Scene() {
		scene = SceneID.START;
	}

	/*
	 * ゲームのスタート画面です。
	 */
	private void sceneStart() {
		println("■■■■■■■■■■");
		println("■                ■");
		println("■   ﾏｲﾝｽｲｰﾊﾟｰ    ■");
		println("■     (β)       ■");
		println("■                ■");
		println("■    1.EASY      ■");
		println("■    2.NORMAL    ■");
		println("■    3.HARD      ■");
		println("■    4.CUSTOM    ■");
		println("■                ■");
		println("■■■■■■■■■■");
	}

	/*
	 * ゲームのメニュー画面です。
	 */
	private void sceneMenu() {
		println("■■■■■■■■■■");
		println("■                ■");
		println("■ 1.ゲームに戻る ■");
		println("■ 2.リセットする ■");
		println("■ 3.タイトル画面 ■");
		println("■                ■");
		println("■■■■■■■■■■");
	}

	/*
	 * ゲームの終了画面です。
	 */
	private void sceneEnd() {
		println("■■■■■■■■■■");
		println("■                ■");
		println("■ お疲れ様でした ■");
		println("■                ■");
		println("■■■■■■■■■■");
	}

	/*
	 * 指定した画面を描画するメソッドです。
	 */
	public void selectView(SceneID id) {
		if (id != SceneID.PLAY) {
			switch (id) {
			case START:
				sceneStart(); break;
			case MENU:
				sceneMenu();	break;
			case END:
				sceneEnd(); break;
			default:
				print("致命的なエラー");
			}
		}
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
