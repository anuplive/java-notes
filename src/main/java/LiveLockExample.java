public class LiveLockExample {
	static class PoliteWorker {
		private String name;
		private boolean isPolite;
		private boolean hasBridgeLock = false;

		public PoliteWorker(String name, boolean isPolite) {
			this.name = name;
			this.isPolite = isPolite;
		}

		public String getName() {
			return name;
		}

		public boolean isPolite() {
			return isPolite;
		}

		public boolean hasBridgeLock() {
			return hasBridgeLock;
		}

		public void tryToTakeBridge(PoliteWorker other) {
			while (!hasBridgeLock()) {
				System.out.println(this.name + " is trying to take the bridge...");

				try {
					Thread.sleep(100); // Simulating some work
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				if (other.isPolite() && !other.hasBridgeLock()) {
					System.out.println(this.name + " is being polite and steps back for " + other.getName());
					continue; // Try again
				}

				// Try to acquire the bridge lock
				synchronized (this) {
					if (!other.hasBridgeLock() && hasBridgeLock()) {
						System.out.println(this.name + " successfully acquired the bridge!");
						hasBridgeLock = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		final PoliteWorker worker1 = new PoliteWorker("Worker 1", true);
		final PoliteWorker worker2 = new PoliteWorker("Worker 2", true);

		Thread thread1 = new Thread(() -> worker1.tryToTakeBridge(worker2));
		thread1.start();

		Thread thread2 = new Thread(() -> worker2.tryToTakeBridge(worker1));
		thread2.start();
	}
}
