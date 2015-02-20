package mouseandkeylogger.additional.examples;

import mouseandkeylogger.Logger;
import processing.core.PApplet;

public class SketchLogAll extends PApplet {

    private final StringBuffer mString = new StringBuffer();

    public void setup() {
        size(400, 300);

        noStroke();
        fill(0);
        background(255);

        new Logger(this);

    }
    /* if the methods below are present they will be called by the logger once one of the events occurs */

    public void keyPressedLogger(int key) {
        System.out.println("### key pressed : " + key + " (keyCode)");
    }

    public void keyTypedLogger(char key) {
        System.out.println("### key typed   : " + key);
    }

    public void keyReleasedLogger(int key) {
        System.out.println("### key released: " + key + " (keyCode)");
    }

    public void mouseMovedLogger(int x, int y) {
        System.out.println("### mouse moved           : " + x + ", " + y);
    }

    public void mousePressedLogger(int button) {
        System.out.println("### mouse pressed button  : " + button);
    }

    public void mouseReleasedLogger(int button) {
        System.out.println("### mouse released button : " + button);
    }

    public static void main(String[] args) {
        PApplet.main(SketchLogAll.class.getName());
    }
}
