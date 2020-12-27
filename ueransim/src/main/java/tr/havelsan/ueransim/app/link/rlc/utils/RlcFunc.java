/*
 * Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
 * This software and all associated files are licensed under GPL-3.0.
 */

package tr.havelsan.ueransim.app.link.rlc.utils;

import tr.havelsan.ueransim.app.link.rlc.interfaces.IComparator;
import tr.havelsan.ueransim.app.link.rlc.interfaces.ISnCompare;
import tr.havelsan.ueransim.app.link.rlc.interfaces.ISnPredicate;
import tr.havelsan.ueransim.app.link.rlc.pdu.AmdPdu;
import tr.havelsan.ueransim.app.link.rlc.pdu.RxPdu;
import tr.havelsan.ueransim.utils.LinkedList;
import tr.havelsan.ueransim.utils.OctetOutputStream;
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
            list.addBefore(cursor, item);
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
    public static MissingBlock findMissingBlock(LinkedList<AmdPdu> rxBuffer, int startSn, int startSo, int endSn, int endSo, int snModulus) {
        // Start line >= end line ise missin part yok demektir.
        // Aksi halde bakmaya devam edilir:
        // Start line ile kesişen bir segment var mı yok mu?
        // Eğer varsa,
        //    kesişen segment eğer endlinedan önce (<= değil <) bitiyorsa ve bitmiyorsa durum değişir.
        //    Bitiyorsa:
        //        ve segment sağ kenara sahip değilse yeni start line söz konusu segmentin bitiş noktası olacak şekilde recursion çağrılır.
        //        ve segment sağ kenara sahipise (hasLast) başlangıcı sn=SN+1 ve so=0 olacak şekilde recursion çağrılır.
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

        var segment = RlcFunc.firstItemIntersecting(rxBuffer, startSn, startSo);
        if (segment != null) {
            var endPointSn = segment.value.sn;
            var endPointSo = segment.value.si.requiresSo() ? segment.value.so + segment.value.size() : segment.value.size();

            // An assertion just in case (checking for infinite recursion)
            if (RlcFunc.snCompareRaw(startSn, endPointSn) == 0 && endPointSo == startSo) {
                throw new IncorrectImplementationException(); // bug found
            }

            if (segment.value.si.hasLast()) {
                return findMissingBlock(rxBuffer, (endPointSn + 1) % snModulus, 0, endSn, endSo, snModulus);
            } else {
                return findMissingBlock(rxBuffer, endPointSn, endPointSo, endSn, endSo, snModulus);
            }
        }

        var res = new MissingBlock();
        res.snStart = startSn;
        res.soStart = startSo;

        var cursor = rxBuffer.getFirst();

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

    /*
     * Returns the first item in the list such that SN=sn. If no such an item is found, then null is returned.
     */
    public static <T extends RxPdu> LinkedList<T>.Item firstItemWithSn(LinkedList<T> list, int sn) {
        var cursor = list.getFirst();
        while (cursor != null && cursor.value.sn != sn)
            cursor = cursor.getNext();
        return cursor;
    }

    /*
     * Returns true if given SN has a missing segment. That is, at least one segment with SN does not exist in the RX list.
     * NOTE: If no such a segment, the false is returned as there is no missing segment
     */
    public static <T extends RxPdu> boolean hasMissingSegment(LinkedList<T> list, int sn) {
        var cursor = RlcFunc.firstItemWithSn(list, sn);

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

    /*
     * Returns true if all segments are received with the given SN. This function also means we are ready to reassemble
     * relevant segments. Therefore, if no such an SN is found, then false is returned. Similarly, if the SN is already
     * processed, then false is returned.
     */
    public static <T extends RxPdu> boolean isAllSegmentsReceived(LinkedList<T> list, int sn) {
        var cursor = RlcFunc.firstItemWithSn(list, sn);

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

    /**
     * Returns the first item that covers given SN and SO. In other words, first we find the first PDU with SN.
     * Then while maintaining the SN=sn invariant, we look for the first PDU that overlaps with the given SO.
     */
    public static <T extends RxPdu> LinkedList<T>.Item firstItemIntersecting(LinkedList<T> list, int sn, int so) {
        var cursor = RlcFunc.firstItemWithSn(list, sn);
        while (cursor != null && cursor.value.sn == sn) {
            var startSo = cursor.value.si.requiresSo() ? cursor.value.so : 0;
            var endSo = startSo + cursor.value.size();

            if (so >= startSo && so < endSo) {
                return cursor;
            }
            cursor = cursor.getNext();
        }
        return null;
    }

    /**
     * Returns true if the given SN is already delivered (processed). If no such an RX PDU is found,
     * it is treated as it is already processed.
     */
    public static <T extends RxPdu> boolean isDelivered(LinkedList<T> list, int sn) {
        var cursor = RlcFunc.firstItemWithSn(list, sn);
        return cursor != null && cursor.value._isProcessed;
    }

    /*
     * Removes all the RX PDUs from the list, that satisfy the given predicate.
     * Returns the total size to be decreased in bytes. Note that, if an item is already processed, then
     * its size is treated as zero. Because buffer size is already decremented while setting isProcessed = true.
     */
    public static <T extends RxPdu> int discardRxPduIf(LinkedList<T> list, ISnPredicate predicate) {
        int decreased = 0;

        var cursor = list.getFirst();
        while (cursor != null) {
            if (predicate.decide(cursor.value.sn)) {
                if (!cursor.value._isProcessed)
                    decreased += cursor.value.size();
                cursor = list.removeAndNext(cursor);
            } else {
                cursor = cursor.getNext();
            }
        }

        return decreased;
    }

    /**
     * Inserts the given RX PDU to the RX buffer as sorted. Comparison is performed according to given comparator.
     * Size of the added item is returned. Note that this function always succeeds. Buffer size checking is not
     * done in this function.
     */
    public static <T extends RxPdu> int insertToRxBuffer(LinkedList<T> list, T item, ISnCompare compare) {
        RlcFunc.insertSortedLinkedList(list, item, (a, b) -> {
            if (a.sn == b.sn)
                return Integer.compare(a.so, b.so);
            return compare.compare(a.sn, b.sn);
        });
        return item.data.length;
    }

    /**
     * Performs reassembling operation for given RX buffer and SN.
     * Reassembled parts are not removed from the list. Just isProcessed=true is assigned for the items.
     * Reassembled full data is written into the given stream, and total number of bytes written is returned.
     * If the given SN value is already processed, the behaviour is undefined. So don't do this.
     */
    public static <T extends RxPdu> int reassemble(LinkedList<T> list, int sn, OctetOutputStream stream) {
        var cursor = RlcFunc.firstItemWithSn(list, sn);
        int written = 0;

        while (cursor != null && cursor.value.sn == sn) {
            stream.writeOctetString(cursor.value.data);
            written += cursor.value.size();
            cursor.value._isProcessed = true;
            cursor = cursor.getNext();
        }
        return written;
    }
}
