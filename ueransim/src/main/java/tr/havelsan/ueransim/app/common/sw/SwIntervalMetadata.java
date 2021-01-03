/*
 * This file is a part of UERANSIM open source project.
 * Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
 *
 * The software and all associated files are licensed under GPL-3.0
 * and subject to the terms and conditions defined in LICENSE file.
 */

package tr.havelsan.ueransim.app.common.sw;

import tr.havelsan.ueransim.app.app.monitor.LoadTestMonitor;

public class SwIntervalMetadata extends SocketWrapper{
    public final LoadTestMonitor.IntervalMetadata intervalMetadata;

    public SwIntervalMetadata(LoadTestMonitor.IntervalMetadata intervalMetadata) {
        this.intervalMetadata = intervalMetadata;
    }
}