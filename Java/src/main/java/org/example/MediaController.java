package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class MediaController {

    // Simulate key press for Volume Up
    public static void pressVolumeUpKey() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_UP);
            System.out.println("Volume Up key pressed successfully");
        } catch (AWTException e) {
            e.printStackTrace();
            System.err.println("Error pressing Volume Up key");
        }
    }

    // Simulate key press for Volume Down
    public static void pressVolumeDownKey() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            System.out.println("Volume Down key pressed successfully");
        } catch (AWTException e) {
            e.printStackTrace();
            System.err.println("Error pressing Volume Down key");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a command (e.g., 'volume up', 'volume down'):");
        String userInput = scanner.nextLine().toLowerCase();

        // Map user input to actions
        switch (userInput) {
            case "volume up":
                pressVolumeUpKey();
                break;
            case "volume down":
                pressVolumeDownKey();
                break;
            default:
                System.out.println("Unknown command");
        }
    }
}
