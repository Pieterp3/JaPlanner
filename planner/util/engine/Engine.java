package util.engine;

public abstract class Engine {

    private Thread thread;
    private long delay;
    private boolean running;

    public Engine(int eps) {
        this.delay = 1000 / eps;
        thread = null;
    }

    public void start() {
        running = true;
        if (thread != null) {
            stop();
        }
        thread = new Thread(() -> {
            loop();
        });
        thread.start();
    }

    public void stop() {
        running = false;
        if (thread == null) { return; }
        try {
            thread.join();
            thread = null;
        } catch (InterruptedException e) {
            thread = null;
            System.out.println("Interrupted engine thread exception: " + e.getMessage());
        }
    }

    protected abstract void execute();

    private void loop() {
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.currentTimeMillis();
            if (timer - now < delay) {
                try { Thread.sleep(delay - (timer - now)); } catch (InterruptedException e) { }
            }
            execute();
        }
    }

}
