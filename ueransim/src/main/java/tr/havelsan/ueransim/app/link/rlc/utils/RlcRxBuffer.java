/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import tr.havelsan.ueransim.app.link.rlc.interfaces.ISnCompare;
import tr.havelsan.ueransim.app.link.rlc.interfaces.ISnPredicate;
import tr.havelsan.ueransim.app.link.rlc.pdu.RxPdu;
import tr.havelsan.ueransim.utils.LinkedList;
import tr.havelsan.ueransim.utils.OctetOutputStream;
import tr.havelsan.ueransim.utils.octets.OctetString;

public class RlcRxBuffer<T extends RxPdu> {

    private final ISnCompare snCompare;
    private final int maxSize;
    private final LinkedList<T> list;
    private int currentSize;

    public RlcRxBuffer(ISnCompare snCompare, int maxSize) {
        this.snCompare = snCompare;
        this.maxSize = maxSize;
        this.list = new LinkedList<>();
    }

    public void add(T rxPdu) {
        RlcUtils.insertSortedLinkedList(list, rxPdu, (a, b) -> {
            if (a.sn == b.sn)
                return Integer.compare(a.so, b.so);
            return snCompare.compare(a.sn, b.sn);
        });

        // No check for max size, it should be done externally using hasRoomFor method.
        currentSize += rxPdu.data.length;
    }

    public LinkedList<T> getList() {
        return list;
    }

    public boolean hasRoomFor(int size) {
        return currentSize + size <= maxSize;
    }

    public boolean hasRoomFor(T rxPdu) {
        return hasRoomFor(rxPdu.data.length);
    }

    public void clear() {
        list.clear();
        currentSize = 0;
    }

    public LinkedList<T>.Item firstItemWithSn(int sn) {
        var cursor = list.getFirst();
        if (cursor == null)
            return null;
        while (cursor != null && cursor.value.sn != sn)
            cursor = cursor.getNext();
        return cursor;
    }

    public boolean hasMissingSegment(int sn) {
        var cursor = firstItemWithSn(sn);

        if (cursor == null || cursor.value._isProcessed)
            return false;

        int lastByte = -1;

        while (cursor != null && cursor.value.sn == sn) {
            if (cursor.value.so > lastByte + 1)
                return true;
            var newLastByte = cursor.value.so + cursor.value.size() - 1;
            if (newLastByte > lastByte)
                lastByte = newLastByte;
            cursor = cursor.getNext();
        }

        return false;
    }

    public boolean isAllSegmentsReceived(int sn) {
        var cursor = firstItemWithSn(sn);

        if (cursor != null && cursor.value._isProcessed)
            return false;

        int last = -1;
        while (cursor != null && cursor.value.sn == sn) {
            if (cursor.value.so > last + 1)
                return false;
            if (cursor.value.si.hasLast())
                return true;
            int newLast = cursor.value.so + cursor.value.size() - 1;
            if (newLast > last)
                last = newLast;
            cursor = cursor.getNext();
        }

        return false;
    }

    public OctetString reassemble(int sn) {
        var cursor = firstItemWithSn(sn);

        var stream = new OctetOutputStream();
        while (cursor != null && cursor.value.sn == sn) {
            stream.writeOctetString(cursor.value.data);
            currentSize -= cursor.value.size();
            cursor.value._isProcessed = true;
            cursor = cursor.getNext();
        }

        return stream.length() == 0 ? null : stream.toOctetString();
    }

    public boolean isDelivered(int sn) {
        var cursor = firstItemWithSn(sn);
        return cursor != null && cursor.value._isProcessed;
    }

    public void discardSegmentIf(ISnPredicate predicate) {
        var cursor = list.getFirst();
        while (cursor != null) {
            if (predicate.decide(cursor.value.sn)) {
                if (!cursor.value._isProcessed)
                    currentSize -= cursor.value.size();
                cursor = list.removeAndNext(cursor);
            } else {
                cursor = cursor.getNext();
            }
        }
    }

    public boolean isAlreadyReceived(int sn, int so, int size) {
        var cursor = list.getFirst();
        while (cursor != null && size > 0) {
            if (cursor.value.sn == sn) {
                if (cursor.value.so <= so && so < cursor.value.so + cursor.value.size()) {
                    int done = cursor.value.size() - (so - cursor.value.so);
                    size -= done;
                    so += done;
                } else if (cursor.value.so <= so + size - 1 && so + size - 1 < cursor.value.so + cursor.value.size()) {
                    int done = size - (cursor.value.so - so);
                    size -= done;
                }
            }
            cursor = cursor.getNext();
        }
        return size <= 0;
    }
}
