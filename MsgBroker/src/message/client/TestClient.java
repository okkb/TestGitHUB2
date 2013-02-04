package message.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * �׽�Ʈ�� client
 * 
 * @author Simon Lee
 */
public class TestClient {
	/**
	 * Entry Point
	 * 
	 * @param args
	 *            port
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("java TestClient <host name> <port>");
			System.exit(1);
		}

		String host = args[0];
		int port = -1;

		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("java TestClient <host name> <port>");
			System.exit(2);
		}

		// ���� ������ ����
		byte[] data = new byte[300];
		byte[] result = new byte[300];
		for (int i = 0; i < data.length; i++) {
			data[i] = (byte) (Math.random() * 100);
		}

		// ���� �ʵ忡 0000000300 set
		for (int i = 0; i < 10; i++) {
			data[i] = '0';
		}
		data[7] = '3';

		try {
			int i = 0;
			while (true) {
				// ����
				Socket sock = new Socket(host, port);
				sock.setReuseAddress(true);
				DataInputStream dis = new DataInputStream(sock.getInputStream());
				DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
				// �ۼ���
				dos.write(data);
				dos.flush();
				dis.readFully(result);

				// ��� Ȯ�� - ��ġ�ؾ� ����
				if (compare(data, result)) {
					System.out.println((i++) + "��° ���� : ������ ��ġ");
				} else {
					System.out.println((i++) + "������ ����ġ");
				}
				// ���� ����
				try {
					dis.close();
				} catch (Exception ignored) {
				} finally {
					dis = null;
				}
				try {
					dos.close();
				} catch (Exception ignored) {
				} finally {
					dos = null;
				}
				try {
					sock.close();
				} catch (Exception ignored) {
				} finally {
					sock = null;
				}
			}
		} catch (UnknownHostException e) {
			System.err.println("�� �� ���� host");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("��� ����");
			e.printStackTrace();
		}
	}

	/**
	 * Source�� destination�� ��
	 * 
	 * @param src
	 * @param dst
	 * @return ������ true
	 */
	private static boolean compare(byte[] src, byte[] dst) {
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
}
