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
        RlcFunc.insertSortedLinkedList(list, rxPdu, (a, b) -> {
            if (a.sn == b.sn)
                return Integer.compare(a.so, b.so);
            return snCompare.compare(a.sn, b.sn);
        });

        // No check for max size, it should be done externally using hasRoomFor method.
        currentSize += rxPdu.data.length;
    }

    // TODO: Bug getList alıp remove işlemlerinde vs. currentSize problem.
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

    public OctetString reassemble(int sn) {
        var cursor = RlcFunc.firstItemWithSn(list, sn);

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
        var cursor = RlcFunc.firstItemWithSn(list, sn);
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
}
