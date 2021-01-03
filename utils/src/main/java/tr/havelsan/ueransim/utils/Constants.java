/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils;

public final class Constants {
    public static final String NAS_IMPL_PREFIX = "tr.havelsan.ueransim.nas.impl";

    public static boolean USE_LONG_MNC = true;
    public static boolean TREAT_ERRORS_AS_FATAL = false;

    public static String VERSION;
    public static int MAJOR;
    public static int MINOR;
    public static int PATCH;

    public static final int CLI__RECEIVER_BUFFER_SIZE = 8 * 1024;
    public static final int CLI__CYCLE_TYPE_HEARTBEAT = 1;
    public static final int CLI__HEARTBEAT_PERIOD = 8000;
    public static final int CLI__PORT = 49973;

    public static boolean NO_ROUTE_CONFIG = false;
}
