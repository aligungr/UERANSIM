/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.utils;

import java.nio.ByteBuffer;

public class Native {

    public static native boolean isRoot();

    public static native int tunAllocate(String namePrefix, String[] allocatedName, String[] error);

    public static native void tunConfigure(String tunName, String ipAddress, boolean configureRouting, String[] error);

    public static native int read(int fd, ByteBuffer buffer);

    public static native int write(int fd, ByteBuffer buffer, int size);
}
