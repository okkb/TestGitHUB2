package message.broker;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
  
public class Job {
	private Socket socket = null;
	private Socket serverSocket = null;
	private DataInputStream cdis;
	private DataOutputStream cdos;
	private DataInputStream sdis;
	private DataOutputStream sdos;
	private byte[] clientInput = new byte[300];
	private byte[] serverInput = new byte[300];


	public Job(Socket socket, String serverName, int serverPort)
			throws IOException // ���� , ����������, ������Ʈ
	{
		this.socket = socket;
		this.serverSocket = new Socket(serverName, serverPort); // ���� ������ socket
		this.cdis = new DataInputStream(this.socket.getInputStream());
		this.cdos = new DataOutputStream(this.socket.getOutputStream());
		this.sdis = new DataInputStream(this.serverSocket.getInputStream());
		this.sdos = new DataOutputStream(this.serverSocket.getOutputStream());
	}

	public void execute() {
		try {
			cdis.readFully(clientInput); // from client
			int msgLength = checkMsgLength(clientInput);
			int totalLength = msgLength+10;
			byte[] serverOutput = new byte[totalLength];// �޽������븸ŭ ����
			System.arraycopy(clientInput, 0, serverOutput, 0, 10); //�����ʵ� copy
			System.arraycopy(clientInput, 10, serverOutput, 10, msgLength);// �޽����ʵ� copy
			
			sdos.write(serverOutput); // to server
			sdis.readFully(serverInput); // from server
			if (compare(serverOutput, serverInput)) {  // ������  Ŭ���̾�Ʈ�� ����
				cdos.write(serverInput);
			} else {
			cdos.writeBytes("[from Server] Message Spec ���� ���� ����");
			}
		} catch (IOException e) {
			System.err.println("Job �ۼ��� �� : " + e.getCause());
			e.printStackTrace();
		}

		try {
			cdos.close();
		} catch (IOException e) {
			System.err.println("cdos.close() : " + e.getCause());
			e.printStackTrace();
		} finally {
			cdos = null;
		}
		try { 
			socket.close();
		} catch (IOException e) {
			System.err.println("socket.close() : " + e.getCause());
			e.printStackTrace();
		} finally {
			socket = null;
		}
		try {
			sdis.close();
		} catch (IOException e) {
			System.err.println("sdis.close() : " + e.getCause());
			e.printStackTrace();
		} finally {
			sdis = null;
		}
		try {
			sdos.close();
		} catch (IOException e) {
			System.err.println("	sdos.close() : " + e.getCause());
			e.printStackTrace();
		} finally {
			sdos = null;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("serverSocket.close() : " + e.getCause());
			e.printStackTrace();
		} finally {
			serverSocket = null;
		}
	}

	private static boolean compare(byte[] src, byte[] dst) { // ������ �˻�
		boolean result = true;

		if (src == null || dst == null || src.length != dst.length) {
			result = false;
		} else {
			for (int i = 0; i < src.length; i++) {
				if (src[i] != dst[i]) {
					result = false;
					break;
				}
			}
		}
		return result;
	}

	private static int checkMsgLength(byte[] data) {// �޽����ʵ� ũ�� ����
		byte[] msgSize = new byte[10];
		for (int i = 0; i < 10; i++) {
			msgSize[i] = data[i];
		}
		char[] len = new char[10];
		for (int i = 0; i < 10; i++) {
			if (msgSize[i] != 0) {
				int k = 0;
				for (int j = i; j < 10; j++) {
					len[k] = (char) msgSize[j];
					k++;
				}
				break;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len.length; i++) {
			sb.append(len[i]);
		}
		return Integer.parseInt(sb.toString()) - 10;
	}
}
