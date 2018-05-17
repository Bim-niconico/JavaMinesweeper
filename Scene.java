import java.util.Scanner;

public class Scene {
	public enum SceneID {
		START,	// X^[gζΚ
		GAME,	// Q[ζΚ
		MENU,	// j[ζΚ
		END,	// IΉζΚ
	}

	private SceneID scene;

	public Scene() {}

	private void sceneStart() {
		println("‘‘‘‘‘‘‘‘‘‘");
		println("‘                ‘");
		println("‘   Ο²έ½²°Κί°    ‘");
		println("‘                ‘");
		println("‘                ‘");
		println("‘    1.EASY      ‘");
		println("‘    2.NORMAL    ‘");
		println("‘    3.HARD      ‘");
		println("‘    4.CUSTOM    ‘");
		println("‘                ‘");
		println("‘‘‘‘‘‘‘‘‘‘");
	}

	private void sceneMenu() {
		println("‘‘‘‘‘‘‘‘‘‘");
		println("‘                ‘");
		println("‘ 1.Q[Ιίι ‘");
		println("‘ 2.Zbg·ι ‘");
		println("‘ 3.^CgζΚ ‘");
		println("‘                ‘");
		println("‘‘‘‘‘‘‘‘‘‘");
	}

	private void sceneEnd() {
		println("‘‘‘‘‘‘‘‘‘‘");
		println("‘                ‘");
		println("‘ ¨ζκlΕ΅½ ‘");
		println("‘                ‘");
		println("‘‘‘‘‘‘‘‘‘‘");
	}

	/* ====================
	 * ΘΊAANZbT
	 * ====================*/
	public SceneID getSceneID() {
		return scene;
	}

	public void setSceneID(SceneID id) {
		scene = id;
	}

	/* ====================
	 * ΘΊAbp[\bh
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
