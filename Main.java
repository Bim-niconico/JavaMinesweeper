import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
	public static final String version = "0.9.1";

	public static void main(String[] args) {
		GameControl game = new GameControl();	// �Q�[���R���g���[��
		// system("cls");(C, C++)
		ConsoleControl cc = new ConsoleControl("cmd", "/c", "cls");

		// �X�^�[�g���
		int select = game.gameStart();
		cc.execute();

		// �Q�[�����
		while (!game.getGameOverFlag()) {
			game.gamePlay(select);
			cc.execute();
		}

		// �I�����
		// game.end();
	}
}
