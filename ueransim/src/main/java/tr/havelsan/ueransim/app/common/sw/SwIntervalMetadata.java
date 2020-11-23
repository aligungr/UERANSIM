/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.common.sw;

import tr.havelsan.ueransim.app.app.monitor.LoadTestMonitor;

public class SwIntervalMetadata extends SocketWrapper{
    public final LoadTestMonitor.IntervalMetadata intervalMetadata;

    public SwIntervalMetadata(LoadTestMonitor.IntervalMetadata intervalMetadata) {
        this.intervalMetadata = intervalMetadata;
    }
}