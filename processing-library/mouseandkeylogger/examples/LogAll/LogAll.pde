import mouseandkeylogger.*;

final StringBuffer mString = new StringBuffer();
void setup() {
    size(400, 300);
    noStroke();
    fill(0);
    background(255);
    new Logger(this);
}
/* if the methods below are present they will be called by the logger once one of the events occurs */
void keyPressedLogger(int key) {
    System.out.println("### key pressed : " + key + " (keyCode)");
}
void keyTypedLogger(char key) {
    System.out.println("### key typed   : " + key);
}
void keyReleasedLogger(int key) {
    System.out.println("### key released: " + key + " (keyCode)");
}
void mouseMovedLogger(int x, int y) {
    System.out.println("### mouse moved           : " + x + ", " + y);
}
void mousePressedLogger(int button) {
    System.out.println("### mouse pressed button  : " + button);
}
void mouseReleasedLogger(int button) {
    System.out.println("### mouse released button : " + button);
}
