import ui.Frame;

public class Engine {
   
    private Thread gameThread;
    private boolean running;
    private int fps;
    private int ups;
    private int fpsCount;
    private int upsCount;
    private int fpsTime;
    private int upsTime;
    private int fpsTimeCount;
    private int upsTimeCount;
    private int fpsTimeCountMax;
    private int upsTimeCountMax;

    private Frame frame;

    public Engine(Frame frame) {
        this.frame = frame;
        fps = 0;
        ups = 0;
        fpsCount = 0;
        upsCount = 0;
        fpsTime = 0;
        upsTime = 0;
        fpsTimeCount = 0;
        upsTimeCount = 0;
        fpsTimeCountMax = 120;
        upsTimeCountMax = 240;
    }

    public void start() {
        running = true;
        gameThread = new Thread(() -> {
            gameLoop();
        });
        gameThread.start();
    }

    public void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double nsPerUpdate = 1000000000.0 / 60;
        double nsPerRender = 1000000000.0 / 60;
        double deltaUpdate = 0;
        double deltaRender = 0;
        while (running) {
            long now = System.nanoTime();
            deltaUpdate += (now - lastTime) / nsPerUpdate;
            deltaRender += (now - lastTime) / nsPerRender;
            lastTime = now;
            if (deltaUpdate >= 1) {
                update();
                upsCount++;
                deltaUpdate--;
            }
            if (deltaRender >= 1) {
                render();
                fpsCount++;
                deltaRender--;
            }
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                fps = fpsCount;
                ups = upsCount;
                fpsCount = 0;
                upsCount = 0;
            }
        }
    }

    private void update() {
        frame.getActivePanel().update();
    }

    private void render() {
        frame.repaint();
    }

    public int getFps() {
        return fps;
    }

    public int getUps() {
        return ups;
    }

    public int getFpsTime() {
        return fpsTime;
    }

    public int getUpsTime() {
        return upsTime;
    }

    public int getFpsTimeCount() {
        return fpsTimeCount;
    }

    public int getUpsTimeCount() {
        return upsTimeCount;
    }

    public int getFpsTimeCountMax() {
        return fpsTimeCountMax;
    }

    public int getUpsTimeCountMax() {
        return upsTimeCountMax;
    }

    public void setFpsTimeCountMax(int fpsTimeCountMax) {
        this.fpsTimeCountMax = fpsTimeCountMax;
    }

    public void setUpsTimeCountMax(int upsTimeCountMax) {
        this.upsTimeCountMax = upsTimeCountMax;
    }

    public Frame getFrame() {
        return frame;
    }
    
}
