/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.app.entry;

class BaseApp {

    private static boolean initialized = false;

    static void main(String[] args) {
        if (initialized)
            return;

        initialized = true;

        String path = System.getProperty("user.dir") + "/build/";

        System.load(path + "libcrypto-native.so");
        System.load(path + "libngap-native.so");
        System.load(path + "libapp-native.so");
    }
}
