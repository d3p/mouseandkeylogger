import mouseandkeylogger.*;

Logger mLogger;
float mMouseX;
float mMouseY;
int mMouseColor;
boolean drawCircle = false;
void setup() {
    size(400, 300);
    noFill();
    background(255);
    mLogger = new Logger(this);
}
void draw() {
    stroke(mMouseColor);
    point(mMouseX, mMouseY);
    if (drawCircle) {
        ellipse(mMouseX, mMouseY, 20, 20);
        drawCircle = false;
    }
}
void mouseMovedLogger(int x, int y) {
    /* map to window space */
    mMouseX = map(x, 0, mLogger.screenWidth(), 0, width);
    mMouseY = map(y, 0, mLogger.screenHeight(), 0, height);
    mMouseColor = mLogger.screenColor(x, y);
}
void mousePressedLogger(int button) {
    drawCircle = true;
}
