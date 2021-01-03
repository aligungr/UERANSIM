/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.utils.jcolor;

public class AnsiPalette {

    public static final AnsiColorFormat PAINT_LOG_NORMAL = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_WHITE_TEXT());
    public static final AnsiColorFormat PAINT_LOG_SUCCESS = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_GREEN_TEXT(), AnsiColorAttribute.BOLD());
    public static final AnsiColorFormat PAINT_LOG_WARNING = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_YELLOW_TEXT(), AnsiColorAttribute.BOLD());
    public static final AnsiColorFormat PAINT_LOG_ERROR = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_RED_TEXT(), AnsiColorAttribute.BOLD());

    public static final AnsiColorFormat PAINT_IMPORTANT_INFO = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_BLUE_TEXT(), AnsiColorAttribute.BOLD());
    public static final AnsiColorFormat PAINT_IMPORTANT_WARNING = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_YELLOW_TEXT(), AnsiColorAttribute.BOLD());

    public static final AnsiColorFormat PAINT_DIVIDER = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_BLUE_TEXT(), AnsiColorAttribute.BOLD());
    public static final AnsiColorFormat PAINT_INPUT = new AnsiColorFormat(AnsiColorAttribute.BRIGHT_BLUE_TEXT(), AnsiColorAttribute.BOLD());

}
