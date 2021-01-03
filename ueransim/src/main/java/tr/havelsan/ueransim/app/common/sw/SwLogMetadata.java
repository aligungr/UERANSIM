/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
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
