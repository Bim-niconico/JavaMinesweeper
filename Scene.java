import java.util.Scanner;

public class Scene {
	private SceneID scene;		// ���݂̃Q�[���V�[��

	public Scene() {
		scene = SceneID.START;
	}

	/*
	 * �Q�[���̃X�^�[�g��ʂł��B
	 */
	private void sceneStart() {
		println("��������������������");
		println("��                ��");
		println("��   ϲݽ���߰    ��");
		println("��     (��)       ��");
		println("��                ��");
		println("��    1.EASY      ��");
		println("��    2.NORMAL    ��");
		println("��    3.HARD      ��");
		println("��    4.CUSTOM    ��");
		println("��                ��");
		println("��������������������");
	}

	/*
	 * �Q�[���̃��j���[��ʂł��B
	 */
	private void sceneMenu() {
		println("��������������������");
		println("��                ��");
		println("�� 1.�Q�[���ɖ߂� ��");
		println("�� 2.���Z�b�g���� ��");
		println("�� 3.�^�C�g����� ��");
		println("��                ��");
		println("��������������������");
	}

	/*
	 * �Q�[���̏I����ʂł��B
	 */
	private void sceneEnd() {
		println("��������������������");
		println("��                ��");
		println("�� �����l�ł��� ��");
		println("��                ��");
		println("��������������������");
	}

	/*
	 * �w�肵����ʂ�`�悷�郁�\�b�h�ł��B
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
				print("�v���I�ȃG���[");
			}
		}
	}

	/* ====================
	 * �ȉ��A�A�N�Z�b�T
	 * ====================*/
	public SceneID getSceneID() {
		return scene;
	}

	public void setSceneID(SceneID id) {
		scene = id;
	}

	/* ====================
	 * �ȉ��A���b�p�[���\�b�h
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
