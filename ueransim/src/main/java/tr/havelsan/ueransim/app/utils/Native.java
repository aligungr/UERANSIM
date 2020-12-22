/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.utils;

import java.nio.ByteBuffer;

public class Native {

    public static native boolean isRoot();

    public static native int tunAllocate(String namePrefix, String[] allocatedName);

    public static native void tunConfigure(String tunName, String ipAddress, boolean configureRouting);

    public static native int read(int fd, ByteBuffer buffer);

    public static native int write(int fd, ByteBuffer buffer, int size);
}
