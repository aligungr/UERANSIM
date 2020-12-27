/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import tr.havelsan.ueransim.app.link.rlc.interfaces.IComparator;
import tr.havelsan.ueransim.app.link.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.app.link.rlc.pdu.RxPdu;
import tr.havelsan.ueransim.utils.LinkedList;
import tr.havelsan.ueransim.utils.exceptions.IncorrectImplementationException;
import tr.havelsan.ueransim.utils.octets.OctetString;

/*
 * This class holds some stateless functions for RLC implementation.
 */
public class RlcFunc {

    /**
     * Inserts the item to the linked list as sorted.
     */
    public static <T> void insertSortedLinkedList(LinkedList<T> list, T item, IComparator<T> comparator) {
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

    /**
     * Constructs RlcSdu and RlcSduSegment from given data and sduId.
     * SN value of RlcSdu is set to -1, it must be set later sometime after this function.
     * <p>
     * Constructed segment is added "to the end" of the transmissionBuffer and size of the RlcSdu is returned.
     * <p>
     * If there is no room in the transmission buffer (determined by bufferCurrent and bufferMax), then no side effect
     * is performed and zero is returned. While checking the available room, only data is counted i.e possible header(s)
     * are not counted.
     */
    public static int insertSduToTransmissionBuffer(OctetString data, int sduId, LinkedList<RlcSduSegment> transmissionBuffer,
                                                    int bufferCurrent, int bufferMax) {
        if (data.length == 0)
            return 0;

        if (bufferCurrent + data.length > bufferMax)
            return 0;

        var sdu = new RlcSdu(sduId, data);
        sdu.sn = -1;
        sdu.retransmissionCount = -1;

        var segment = new RlcSduSegment(sdu);
        segment.size = data.length;
        segment.so = 0;
        segment.si = ESegmentInfo.FULL;

        transmissionBuffer.addLast(segment);

        return segment.size;
    }

    /**
     * Returns the first SDU segment with given SDU ID. If no such a segment, then null is returned.
     */
    public static LinkedList<RlcSduSegment>.Item findFirstSduSegmentWithId(LinkedList<RlcSduSegment> list, int sduId) {
        var cursor = list.getFirst();
        while (cursor != null) {
            if (cursor.value.sdu.sduId == sduId)
                return cursor;
            cursor = cursor.getNext();
        }
        return null;
    }

    /**
     * Returns true iff given two SOs overlap. This function also handles -1 as SO values. It is better to not use
     * this function for general purpose interval overlap checking function. It is specialized for SO overlap checking.
     */
    public static boolean soOverlap(int start1, int end1, int start2, int end2) {
        return start1 < start2 ? (end1 == -1 || end1 >= start2) : (end2 == -1 || start1 <= end2);
    }

    /**
     * Return true iff there exists some segment in the list such that SN=sn.
     * The list may or may not be sorted. Therefore, iteration is done until the end of the list,
     * i.e no shortcut is performed.
     */
    public static boolean sduListContainsSn(LinkedList<RlcSduSegment> list, int sn) {
        for (var item : list.elements()) {
            if (item.sdu.sn == sn)
                return true;
        }
        return false;
    }

    /**
     * Finds the next missing block and returns that block. If no such a block is found, then null is returned.
     */
    public static MissingBlock findMissingBlock(RlcRxBuffer<AmdPdu> rxBuffer, int startSn, int startSo, int endSn, int endSo, int snModulus) {
        // Start line >= end line ise missin part yok demektir.
        // Aksi halde bakmaya devam edilir:
        // Start line ile kesişen bir segment var mı yok mu?
        // Eğer varsa,
        //    kesişen segment eğer endlinedan önce (<= değil <) bitiyorsa ve bitmiyorsa durum değişir.
        //    Bitiyorsa:
        //        yeni start line söz konusu segmentin bitiş noktası olacak şekilde recursion çağrılır.
        //    Bitmiyorsa:
        //        missin part yok demektir.
        // Eğer yoksa
        //     missing partın başlangıcı start line olur
        //     end noktasından önce ve start linedan sonra bir segment veya segment parçası başlangıcı var mı diye bakılır.
        //     Varsa:
        //        missin partın end noktası start-end line arasındaki starta en yakın ilk segment parçasının in başlangıcı olır
        //     Yoksa:
        //        end line olur
        // Son
        //
        // NOT: Kayıp blokları bulurken döngüsel arama istemediğimiz için snCompareRx yerine snCompareRaw kullanılıyor.
        //

        if (RlcFunc.snCompareRaw(startSn, endSn) > 0 || (RlcFunc.snCompareRaw(startSn, endSn) == 0 && startSo >= endSo))
            return null;

        var segment = rxBuffer.firstItemIntersecting(startSn, startSo);
        if (segment != null) {
            var endPointSn = segment.value.sn;
            var endPointSo = segment.value.si.requiresSo() ? segment.value.so + segment.value.size() : segment.value.size();

            // An assertion just in case (checking for infinite recursion)
            if (RlcFunc.snCompareRaw(startSn, endPointSn) == 0 && endPointSo == startSo) {
                throw new IncorrectImplementationException(); // bug found
            }

            return findMissingBlock(rxBuffer, endPointSn, endPointSo, endSn, endSo, snModulus);
        }

        var res = new MissingBlock();
        res.snStart = startSn;
        res.soStart = startSo;

        var cursor = rxBuffer.getList().getFirst();

        while (cursor != null) {
            var val = cursor.value;
            var so = val.si.requiresSo() ? val.so : 0;

            if (RlcFunc.snCompareRaw(val.sn, startSn) < 0 || (RlcFunc.snCompareRaw(val.sn, startSn) == 0 && so < startSo)) {
                cursor = cursor.getNext();
            } else {
                break;
            }
        }

        if (cursor == null) {
            res.snEnd = endSn;
            res.soEnd = endSo;

            // There is no next
            res.snNext = -1;
            res.soNext = -1;

            return res;
        }

        var startPointSn = cursor.value.sn;
        var startPointSo = cursor.value.si.requiresSo() ? cursor.value.so : 0;

        if (RlcFunc.snCompareRaw(startPointSn, endSn) > 0 || (RlcFunc.snCompareRaw(startPointSn, endSn) == 0 && startPointSo > endSo)) {
            res.snEnd = endSn;
            res.soEnd = endSo;

            // There is no next
            res.snNext = -1;
            res.soNext = -1;

            return res;
        }

        if (startPointSo != 0) {
            res.snEnd = startPointSn;
            res.soEnd = startPointSo - 1;

            if (cursor.value.si.hasLast()) {
                res.snNext = (startPointSn + 1) % snModulus;
                res.soNext = 0;
            } else {
                res.snNext = startPointSn;
                res.soNext = (cursor.value.si.requiresSo() ? cursor.value.so : 0) + cursor.value.size();
            }
        } else {
            res.snEnd = (startPointSn - 1 + snModulus) % snModulus;
            res.soEnd = 0xFFFF;

            res.snNext = startPointSn;
            res.soNext = 0;
        }
        return res;
    }

    /**
     * Returns true iff specified part is already covered in the receive buffer.
     * (SO value should be non-negative.)
     */
    public static <T extends RxPdu> boolean isAlreadyReceived(LinkedList<T> rxList, int sn, int so, int size) {
        var cursor = rxList.getFirst();
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
