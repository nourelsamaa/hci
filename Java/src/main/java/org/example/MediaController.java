package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MediaController {

    private static final Map<String, Integer> KEY_MAP = new HashMap<>();

    static {
        // Map keys
        KEY_MAP.put("next", KeyEvent.VK_N);
        KEY_MAP.put("previous", KeyEvent.VK_P);
        KEY_MAP.put("mute", KeyEvent.VK_M);
        KEY_MAP.put("volume up", KeyEvent.VK_UP);
        KEY_MAP.put("volume down", KeyEvent.VK_DOWN);
    }

    // Simulate key press for specified key
    public void pressKey(String key) {
        if (KEY_MAP.containsKey(key)) {
            int keyCode = KEY_MAP.get(key);
            try {
                Robot robot = new Robot();
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
                System.out.println("'" + key + "' key pressed successfully");
            } catch (AWTException e) {
                e.printStackTrace();
                System.err.println("Error pressing '" + key + "' key");
            }
        } else {
            System.out.println("Unknown command");
        }
    }

    // You might have additional methods or functionalities here
}
