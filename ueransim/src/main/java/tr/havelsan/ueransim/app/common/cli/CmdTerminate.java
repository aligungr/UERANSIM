/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.cli;

public class CmdTerminate extends CmdMessage {
    public final int code;
    public final String finalOutput;

    public CmdTerminate(int code, String finalOutput, Object... args) {
        this.code = code;
        this.finalOutput = String.format(finalOutput, args);
    }
}
