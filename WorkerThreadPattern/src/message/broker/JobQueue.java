package message.broker;
 
import java.util.LinkedList;
import java.util.Queue;
    
public class JobQueue { 
	private Queue<Job> que = new LinkedList<Job>();
	private Mutex key = new Mutex();
	private Mutex stopKey = new Mutex();
	
	public void enQueue(Job job) throws InterruptedException {
		key.acquire(); // KEY ȹ�� ����
		que.add(job);
		stopKey.release(); // STOP_KEY �ݳ�( deQueue()�� while{}�� Ż�� )
		key.release(); // KEY �ݳ�
	}

	public Job deQueue() throws InterruptedException {
		key.acquire(); // KEY ȹ�� ����
		while (que.size() <= 0) {
			key.release(); // KEY �ݳ�
			stopKey.acquire(); //ù ȸ���� STOP_KEY �� ����, �� �ѹ��� ���� �ι�° ȸ������ enQueue()�� ��ٸ���.
			key.acquire();// KEY ȹ�� ����
		}
		Job job = que.remove();
		stopKey.release();// STOP_KEY �ݳ�
		key.release();// KEY �ݳ�
		return job;
	}
}
