//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ram.code;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class GlobalKeyboardShortcut implements NativeKeyListener {
    controlPanel myUi;
    String content = null;
    JSONObject savedData = null;

    GlobalKeyboardShortcut(controlPanel myUi) {
        this.myUi = myUi;

        try {
            this.content = new String(Files.readAllBytes(Paths.get("shortcuts.json")));
            this.savedData = new JSONObject(this.content);
        } catch (IOException var3) {
            this.savedData = null;
        }

    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        if (this.myUi.shortcut && this.savedData == null) {
            try {
                switch (e.getKeyCode()) {
                    case 3658:
                        this.myUi.getdbStrikes("");
                        break;
                    case 3662:
                        this.myUi.placeTable();
                        break;
                    case 3663:
                        this.myUi.exitAll();
                        break;
                    case 57416:
                        this.myUi.buyTrigger("S");
                        break;
                    case 57419:
                        this.myUi.buyTrigger("B");
                        break;
                    case 57421:
                        this.myUi.buyTrigger2("B");
                        break;
                    case 57424:
                        this.myUi.buyTrigger2("S");
                }
            } catch (Exception var3) {
            }
        }

    }
}
