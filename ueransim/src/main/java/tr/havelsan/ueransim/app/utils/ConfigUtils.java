/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.utils;

import tr.havelsan.ueransim.utils.FileUtils;
import tr.havelsan.ueransim.utils.console.Logger;

public class ConfigUtils {

    public static Logger createLoggerFor(String name) {
        var logger = new Logger(name);
        loggingToFile(logger, name, false);
        return logger;
    }

    public static void loggingToFile(Logger logger, String name, boolean standardPrint) {
        if (name.contains(".") || name.contains("/"))
            throw new IllegalArgumentException("name contains '.' or '/'");

        logger.getConsole().setStandardPrintEnabled(standardPrint);
        logger.getConsole().addPrintHandler(str -> FileUtils.appendToFile("logs/" + name + ".log", str));
    }
}
