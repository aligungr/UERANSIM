/*
 * MIT License
 *
 * Copyright (c) 2020 ALİ GÜNGÖR
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package tr.havelsan.ueransim.utils.console;

import tr.havelsan.ueransim.utils.Severity;
import tr.havelsan.ueransim.utils.Tag;
import tr.havelsan.ueransim.utils.jcolor.AnsiColorFormat;


public class Logging {

    public static final String GLOBAL_LOGGER = "global";

    private static final Logger logger = new Logger(GLOBAL_LOGGER);

    public static void debug(Tag tag, String message, Object... args) {
        logger.debug(tag, message, args);
    }

    public static void info(Tag tag, String message, Object... args) {
        logger.info(tag, message, args);
    }

    public static void success(Tag tag, String message, Object... args) {
        logger.success(tag, message, args);
    }

    public static void warning(Tag tag, String message, Object... args) {
        logger.warning(tag, message, args);
    }

    public static void error(Tag tag, String message, Object... args) {
        logger.error(tag, message, args);
    }

    public static void funcIn(String name, Object... args) {
        logger.funcIn(name, args);
    }

    public static void funcOut() {
        logger.funcOut();
    }

    public static void log(Severity severity, AnsiColorFormat ansiColorFormat, int depth, Tag tag, String message, Object... args) {
        logger.log(severity, ansiColorFormat, depth, tag, message, args);
    }
}
