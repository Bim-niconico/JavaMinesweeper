import java.io.IOException;

public class ConsoleControle {
	private ProcessBuilder pb;

	/**
	 * ConsoleControle�N���X�̃R���X�g���N�^�ł��B
	 * �����Ɏw�肵���O���R�}���h�����s����������\�z���܂��B
	 * ���ۂɎ��s������ɂ�ConsoleControle#execute()���g�p���܂��B
	 * @param command ���s���������O���R�}���h
	 */
	public ConsoleControle(String... command) {
		pb = new ProcessBuilder(command);
	}

	/**
	 * �R���X�g���N�^�Ŏw�肳�ꂽ�O���R�}���h�����s���郁�\�b�h�ł��B
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void execute() throws IOException, InterruptedException {
		pb.inheritIO().start().waitFor();
	}
}
