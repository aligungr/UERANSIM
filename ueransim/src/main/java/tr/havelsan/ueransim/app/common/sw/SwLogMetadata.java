/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

import tr.havelsan.ueransim.utils.Severity;
import tr.havelsan.ueransim.utils.Tag;

public class SwLogMetadata extends SocketWrapper {
    public final Severity[] severities;
    public final Tag[] tags;

    public SwLogMetadata(Severity[] severities, Tag[] tags) {
        this.severities = severities;
        this.tags = tags;
    }
}
