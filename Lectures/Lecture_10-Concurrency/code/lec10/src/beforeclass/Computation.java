package beforeclass;

// This class has a race condition in it.
public class Computation {
	private boolean ready = false;
	private int answer = 0;
	
	// computeAnswer runs in one thread
	private void computeAnswer() {
		answer = 42;
		ready = true;
	}
	
	private void useAnswer() {
		while (!ready) {
			Thread.yield();
		}
		System.out.println(answer);
	}
	
	// number of trails to run the computation
	private static final int TRIES = 10;
	
	public static void main(String[] args) {
		for (int i = 0; i < TRIES; i = i + 1) {
			final Computation e = new Computation();
			
			new Thread(new Runnable() {
				public void run() {
					e.useAnswer();
				}
			}).start();
			
			new Thread(new Runnable() {
				public void run() {
					Thread.yield();
					e.computeAnswer();
				}
			}).start();
		}
	}
}
