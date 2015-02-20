package mouseandkeylogger.additional.test;

import mouseandkeylogger.Logger;

public class TestLoggerListener {

    public void keyPressedLogger(int key) {
        System.out.println("### pressed key: " + key);
    }

    public void keyTypedLogger(char key) {
        System.out.println("### typed key: " + key);
    }

    public void keyReleasedLogger(int key) {
        System.out.println("### released key: " + key);
    }

    public void mouseMovedLogger(int x, int y) {
        System.out.println("### mouse moved: " + x + ", " + y);
    }

    public void mousePressedLogger(int button) {
        System.out.println("### mouse pressed button #" + button);
    }

    public void mouseReleasedLogger(int button) {
        System.out.println("### mouse released button #" + button);
    }

    public static void main(String[] args) {
        new Logger(new TestLoggerListener());
    }
}
