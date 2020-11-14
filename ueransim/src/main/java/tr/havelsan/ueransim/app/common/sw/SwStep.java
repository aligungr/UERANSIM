/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

import tr.havelsan.ueransim.utils.Severity;

public class SwStep extends SocketWrapper {

    public final String loggerName;
    public final Severity severity;
    public final String messageName;
    public final String messageBody;

    public SwStep(String loggerName, Severity severity, String messageName, String messageBody) {
        this.loggerName = loggerName;
        this.severity = severity;
        this.messageName = messageName;
        this.messageBody = messageBody;
    }
}
