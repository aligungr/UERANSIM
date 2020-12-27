/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import tr.havelsan.ueransim.utils.LinkedList;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;

import java.util.Comparator;

/*
 * This class holds some stateless functions for RLC implementation.
 */
public class RlcFunc {

    /**
     * Inserts the item to the linked list as sorted.
     */
    public static <T> void insertSortedLinkedList(LinkedList<T> list, T item, Comparator<T> comparator) {
        if (list.isEmpty()) {
            list.addFirst(item);
            return;
        }

        var cursor = list.getFirst();
        while (cursor != null) {
            if (comparator.compare(cursor.value, item) > 0)
                break;
            cursor = cursor.getNext();
        }

        if (cursor != null) {
            list.addAfter(cursor, item);
        } else {
            list.addLast(item);
        }
    }

    /*
     * Returns UMD PDU header size. UMD PDU header size depends on snLength and SI
     */
    public static int umdPduHeaderSize(int snLength, ESegmentInfo si) {
        switch (si) {
            case FULL:
                return 1;
            case FIRST:
                return snLength == 6 ? 1 : 2;
            default:
                return snLength == 6 ? 3 : 4;
        }
    }

    /**
     * Performs segmentation for UM entity. The input parameter sdu is the segment to be segmented.
     * This function must be called only if the maxSize is smaller than segments size so that the
     * segmentation operation becomes meaningful.
     * <p>
     * After the successful operation, the input segment is modified and smaller part becomes that segment.
     * The other remaining part is returned from method. That part should be added again to the buffer.
     * <p>
     * This function may fail if the size is not big enough for (header + 1) byte.
     * In such a case, parameter is not modified, and a null value is returned.
     */
    public static RlcSduSegment umPerformSegmentation(RlcSduSegment sdu, int maxSize, int snLength) {
        var newSi = sdu.si.asNotLast();
        var nextSi = sdu.si.asNotFirst();

        int headerSizeAfterSeg = RlcFunc.umdPduHeaderSize(snLength, newSi);
        if (headerSizeAfterSeg + 1 > maxSize)
            return null;

        int overflowed = headerSizeAfterSeg + sdu.size - maxSize;

        if (overflowed <= 0) {
            // If maxSize is big enough for the relevant segment,
            // then this segmentation function should not be called.
            throw new IncorrectImplementationException();
        }

        sdu.si = newSi;
        sdu.size -= overflowed;

        var next = new RlcSduSegment(sdu.sdu);
        next.si = nextSi;
        next.size = overflowed;
        next.so = sdu.so + sdu.size;

        return next;
    }

    /**
     * Raw comparison function for SN. This function is only used for its special place.
     */
    public static int snCompareRaw(int a, int b) {
        return a - b;
    }

    /*
     * Returns AMD PDU header size. AMD PDU header size depends on snLength and SI
     */
    public static int amdPduHeaderSize(int snLength, ESegmentInfo si) {
        int res = 2;
        if (snLength == 18) res++;
        if (!si.hasFirst()) res += 2;
        return res;
    }

    /**
     * Performs segmentation for AM entity. The input parameter sdu is the segment to be segmented.
     * This function must be called only if the maxSize is smaller than segments size so that the
     * segmentation operation becomes meaningful.
     * <p>
     * After the successful operation, the input segment is modified and smaller part becomes that segment.
     * The other remaining part is returned from method. That part should be added again to the buffer.
     * <p>
     * This function may fail if the size is not big enough for (header + 1) byte.
     * In such a case, parameter is not modified, and a null value is returned.
     */
    public static RlcSduSegment amPerformSegmentation(RlcSduSegment sdu, int maxSize, int snLength) {
        int headerSize = RlcFunc.amdPduHeaderSize(snLength, sdu.si);
        if (headerSize + 1 > maxSize)
            return null;

        var si = sdu.si;

        int overflowed = headerSize + sdu.size - maxSize;

        sdu.si = si.asNotLast();
        sdu.size -= overflowed;

        var next = new RlcSduSegment(sdu.sdu);
        next.si = si.asNotFirst();
        next.size = overflowed;
        next.so = sdu.so + sdu.size;

        return next;
    }
}
