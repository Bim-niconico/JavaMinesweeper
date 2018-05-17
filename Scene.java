import java.util.Scanner;

public class Scene {
	public enum SceneID {
		START,	// �X�^�[�g���
		GAME,	// �Q�[�����
		MENU,	// ���j���[���
		END,	// �I�����
	}

	private SceneID scene;

	public Scene() {}

	private void sceneStart() {
		println("��������������������");
		println("��                ��");
		println("��   ϲݽ���߰    ��");
		println("��                ��");
		println("��                ��");
		println("��    1.EASY      ��");
		println("��    2.NORMAL    ��");
		println("��    3.HARD      ��");
		println("��    4.CUSTOM    ��");
		println("��                ��");
		println("��������������������");
	}

	private void sceneMenu() {
		println("��������������������");
		println("��                ��");
		println("�� 1.�Q�[���ɖ߂� ��");
		println("�� 2.���Z�b�g���� ��");
		println("�� 3.�^�C�g����� ��");
		println("��                ��");
		println("��������������������");
	}

	private void sceneEnd() {
		println("��������������������");
		println("��                ��");
		println("�� �����l�ł��� ��");
		println("��                ��");
		println("��������������������");
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
