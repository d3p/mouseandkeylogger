package mouseandkeylogger;

import java.lang.reflect.Method;
import java.util.logging.Level;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class Logger implements NativeKeyListener, NativeMouseInputListener, NativeMouseWheelListener {

    private static final java.util.logging.Logger mOutputLogger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());

    private final Logger me;

    private final Object mListener;

    private static final String KEY_PRESSED = "keyPressedLogger";
    private static final String KEY_TYPED = "keyTypedLogger";
    private static final String KEY_RELEASED = "keyReleasedLogger";
    private static final String MOUSE_MOVED = "mouseMovedLogger";
    private static final String MOUSE_PRESSED = "mousePressedLogger";
    private static final String MOUSE_RELEASES = "mouseReleasedLogger";

    private Method mMethodKeyPressed;
    private Method mMethodKeyTyped;
    private Method mMethodKeyReleased;
    private Method mMethodMouseMoved;
    private Method mMethodMousePressed;
    private Method mMethodMouseRelease;

    public char key;
    public int keyCode;
    public int mouseX;
    public int mouseY;

    public static boolean PRINT_LOG = false;

    private java.awt.Robot mRobot;

    public Logger(Object pListener) {
        me = this;
        mListener = pListener;
        mOutputLogger.setUseParentHandlers(false);
        mOutputLogger.setLevel(Level.ALL);

        try {
            mRobot = new java.awt.Robot();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /* find methods in client listener */
        try {
            mMethodKeyPressed = mListener.getClass().getDeclaredMethod(KEY_PRESSED, new Class[]{Integer.TYPE});
        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
        }
        try {
            mMethodKeyTyped = mListener.getClass().getDeclaredMethod(KEY_TYPED, new Class[]{Character.TYPE});
        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
        }
        try {
            mMethodKeyReleased = mListener.getClass().getDeclaredMethod(KEY_RELEASED, new Class[]{Integer.TYPE});
        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
        }
        try {
            mMethodMouseMoved = mListener.getClass().getDeclaredMethod(MOUSE_MOVED, new Class[]{Integer.TYPE, Integer.TYPE});
        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
        }
        try {
            mMethodMousePressed = mListener.getClass().getDeclaredMethod(MOUSE_PRESSED, new Class[]{Integer.TYPE});
        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
        }
        try {
            mMethodMouseRelease = mListener.getClass().getDeclaredMethod(MOUSE_RELEASES, new Class[]{Integer.TYPE});
        } catch (NoSuchMethodException ex) {
//            ex.printStackTrace();
        }

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("### There was a problem registering the native hook.");
            System.err.println(ex.getMessage());
            System.exit(1);
        }

        /* register listeners */
        prepareExitHandler();
        if (mMethodKeyPressed != null || mMethodKeyTyped != null || mMethodKeyReleased != null) {
            GlobalScreen.addNativeKeyListener(this);
        }
        if (mMethodMousePressed != null || mMethodMouseRelease != null) {
            GlobalScreen.addNativeMouseListener(this);
        }
        if (mMethodMouseMoved != null) {
            GlobalScreen.addNativeMouseMotionListener(this);
        }
        if (false) {
            GlobalScreen.addNativeMouseWheelListener(this);
        }
    }

    public int screenWidth() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    public int screenHeight() {
        return java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public int screenColor(int x, int y) {
        return mRobot.getPixelColor(x, y).getRGB();
    }

    private void prepareExitHandler() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("### logger removing listeners");
                GlobalScreen.removeNativeKeyListener(me);
                GlobalScreen.removeNativeMouseListener(me);
                GlobalScreen.removeNativeMouseMotionListener(me);
                GlobalScreen.removeNativeMouseWheelListener(me);
            }
        }));
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        displayEventInfo(e);
        if (mMethodKeyPressed != null) {
            try {
                mMethodKeyPressed.invoke(mListener, e.getKeyCode());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        displayEventInfo(e);
        if (mMethodKeyTyped != null) {
            try {
                char c = e.getKeyChar();
                /* transform a newline into '\n' */
                if (c == 13) {
                    c = 10;
                }
                mMethodKeyTyped.invoke(mListener, c);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        displayEventInfo(e);
        if (mMethodKeyReleased != null) {
            try {
                mMethodKeyReleased.invoke(mListener, e.getKeyCode());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeMouseClicked(NativeMouseEvent e) {
        displayEventInfo(e);
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        displayEventInfo(e);
        if (mMethodMousePressed != null) {
            try {
                mMethodMousePressed.invoke(mListener, e.getButton());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        displayEventInfo(e);
        if (mMethodMouseRelease != null) {
            try {
                mMethodMouseRelease.invoke(mListener, e.getButton());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        displayEventInfo(e);
        if (mMethodMouseMoved != null) {
            try {
                mMethodMouseMoved.invoke(mListener, e.getX(), e.getY());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        displayEventInfo(e);
    }

    public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
        displayEventInfo(e);
    }

    private static final void displayEventInfo(final NativeInputEvent e) {
        if (PRINT_LOG) {
            System.out.println(e.paramString());
        }
    }
}
