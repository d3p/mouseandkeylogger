package mouseandkeylogger.additional.examples;

import mouseandkeylogger.Logger;
import processing.core.PApplet;

public class SketchMouseLogger extends PApplet {

    private Logger mLogger;

    private float mMouseX;
    private float mMouseY;
    private int mMouseColor;
    private boolean drawCircle = false;

    public void setup() {
        size(400, 300);

        noFill();
        background(255);

        mLogger = new Logger(this);
    }

    public void draw() {
        stroke(mMouseColor);
        point(mMouseX, mMouseY);

        if (drawCircle) {
            ellipse(mMouseX, mMouseY, 20, 20);
            drawCircle = false;
        }
    }

    public void mouseMovedLogger(int x, int y) {
        /* map to window space */
        mMouseX = map(x, 0, mLogger.screenWidth(), 0, width);
        mMouseY = map(y, 0, mLogger.screenHeight(), 0, height);
        mMouseColor = mLogger.screenColor(x, y);
    }

    public void mousePressedLogger(int button) {
        drawCircle = true;
    }

    public static void main(String[] args) {
        PApplet.main(SketchMouseLogger.class.getName());
    }
}
