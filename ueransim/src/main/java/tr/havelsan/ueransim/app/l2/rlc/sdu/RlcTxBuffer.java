/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.l2.rlc.sdu;

import java.util.ArrayList;
import java.util.List;

public class RlcTxBuffer {

    private int maxSize;
    private int currentSize;
    private List<RlcSduSegment> segments;

    public RlcTxBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        this.segments = new ArrayList<>();
    }

    public boolean hasRoom(int length) {
        return currentSize + length <= maxSize;
    }

    public void appendSegment(RlcSduSegment segment) {
        currentSize += segment.size;
        segments.add(segment);
    }
}
