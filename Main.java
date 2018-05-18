import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		GameControl game = new GameControl();	// �Q�[���R���g���[��
		// system("cls");(C, C++)
		ConsoleControl cc = new ConsoleControl("cmd", "/c", "cls");

		boolean gameOverFlg = false;			// �Q�[���I�[�o�[�t���O

		// �X�^�[�g���
		int select = game.gameStart();
		cc.execute();

		while (true) {
			// �Q�[�����
			game.gamePlay(select);
			cc.execute();
		}

		// �I�����
		// game.end();
	}
}
