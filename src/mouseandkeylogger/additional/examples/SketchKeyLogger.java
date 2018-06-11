package mouseandkeylogger.additional.examples;

import mouseandkeylogger.Logger;
import processing.core.PApplet;

public class SketchKeyLogger extends PApplet {

    private final StringBuffer mString = new StringBuffer();

    public void settings() {
        size(400, 300);
    }

    public void setup() {
        noStroke();
        fill(0);

        new Logger(this);
        textFont(createFont("Courier", 10));
    }

    public void draw() {
        background(255);
        text(mString.toString(), 10, 10, width - 20, height - 20);
    }

    public void keyTypedLogger(char key) {
        final char DELETE_KEY = 8; // this is the int value for the delete key
        if (key == DELETE_KEY) {
            /* delete last char */
            if (mString.length() > 0) {
                mString.deleteCharAt(mString.length() - 1);
            }
        } else {
            /* add key to string */
            mString.append(key);
        }
    }

    public static void main(String[] args) {
        PApplet.main(SketchKeyLogger.class.getName());
    }
}
