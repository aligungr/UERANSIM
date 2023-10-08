//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "utils.hpp"

#include <cassert>
#include <functional>
#include <stdexcept>

#include <utils/linked_list.hpp>

namespace rlc::func
{

/**
 * Inserts the item to the linked list as sorted.
 */
template <typename T>
inline void InsertSortedLinkedList(LinkedList<T> &list, T *item, const IComparator<T> &comparator)
{
    if (list.isEmpty())
    {
        list.addFirst(item);
        return;
    }

    auto cursor = list.getFirst();
    while (cursor != nullptr)
    {
        if (comparator.compare(*cursor->value, *item) > 0)
            break;
        cursor = cursor->getNext();
    }

    if (cursor != nullptr)
    {
        list.addBefore(cursor, item);
    }
    else
    {
        list.addLast(item);
    }
}

/**
 * Returns UMD PDU header size. UMD PDU header size depends on snLength and SI
 */
inline int UmdPduHeaderSize(int snLength, ESegmentInfo si)
{
    switch (si)
    {
    case ESegmentInfo::FULL:
        return 1;
    case ESegmentInfo::FIRST:
        return snLength == 6 ? 1 : 2;
    default:
        return snLength == 6 ? 3 : 4;
    }
}

/**
 * Performs segmentation for UM m_pEntity. The input parameter sdu is the segment to be segmented.
 * This function must be called only if the maxSize is smaller than segments size so that the
 * segmentation operation becomes meaningful.
 * <p>
 * After the successful operation, the input segment is modified and smaller part becomes that segment.
 * The other remaining part is returned from method. That part should be added again to the buffer.
 * <p>
 * This function may fail if the size is not big enough for (header + 1) byte.
 * In such a case, parameter is not modified, and a null value is returned.
 */
inline RlcSduSegment *UmPerformSegmentation(RlcSduSegment *sdu, int maxSize, int snLength)
{
    auto newSi = si::asNotLast(sdu->si);
    auto nextSi = si::asNotFirst(sdu->si);

    int headerSizeAfterSeg = UmdPduHeaderSize(snLength, newSi);
    if (headerSizeAfterSeg + 1 > maxSize)
        return nullptr;

    int overflowed = headerSizeAfterSeg + sdu->size - maxSize;

    if (overflowed <= 0)
    {
        // If maxSize is big enough for the relevant segment,
        // then this segmentation function should not be called.
        throw std::runtime_error("");
    }

    sdu->si = newSi;
    sdu->size -= overflowed;

    auto next = new RlcSduSegment(sdu->sdu);
    next->si = nextSi;
    next->size = overflowed;
    next->so = sdu->so + sdu->size;

    return next;
}

/**
 * Raw comparison function for SN. This function is only used for its special place.
 */
inline int SnCompareRaw(int a, int b)
{
    return a - b;
}

/**
 * Returns AMD PDU header size. AMD PDU header size depends on snLength and SI
 */
inline int AmdPduHeaderSize(int snLength, ESegmentInfo si)
{
    int res = 2;
    if (snLength == 18)
        res++;
    if (!si::hasFirst(si))
        res += 2;
    return res;
}

/**
 * Performs segmentation for AM m_pEntity. The input parameter sdu is the segment to be segmented.
 * This function must be called only if the maxSize is smaller than segments size so that the
 * segmentation operation becomes meaningful.
 * <p>
 * After the successful operation, the input segment is modified and smaller part becomes that segment.
 * The other remaining part is returned from method. That part should be added again to the buffer.
 * <p>
 * This function may fail if the size is not big enough for (header + 1) byte.
 * In such a case, parameter is not modified, and a null value is returned.
 */
inline RlcSduSegment *AmPerformSegmentation(RlcSduSegment *sdu, int maxSize, int snLength)
{
    int headerSize = AmdPduHeaderSize(snLength, sdu->si);
    if (headerSize + 1 > maxSize)
        return nullptr;

    auto si = sdu->si;

    int overflowed = headerSize + sdu->size - maxSize;

    sdu->si = si::asNotLast(si);
    sdu->size -= overflowed;

    auto next = new RlcSduSegment(sdu->sdu);
    next->si = si::asNotFirst(si);
    next->size = overflowed;
    next->so = sdu->so + sdu->size;

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
inline size_t InsertSduToTransmissionBuffer(uint8_t *data, size_t size, int sduId,
                                            LinkedList<RlcSduSegment> &transmissionBuffer, size_t bufferCurrent,
                                            size_t bufferMax)
{
    if (size == 0)
        return 0;

    if (bufferCurrent + size > bufferMax)
        return 0;

    auto segment = new RlcSduSegment(RlcSdu::NewFromData(data, size, sduId));
    segment->sdu->sn = -1;
    segment->sdu->retransmissionCount = -1;
    segment->size = size;
    segment->so = 0;
    segment->si = ESegmentInfo::FULL;

    transmissionBuffer.addLast(segment);

    return size;
}

/**
 * Returns the first SDU segment with given SDU ID. If no such a segment, then null is returned.
 */
inline LinkedItem<RlcSduSegment> *FindFirstSduSegmentWithId(LinkedList<RlcSduSegment> &list, int sduId)
{
    auto cursor = list.getFirst();
    while (cursor != nullptr)
    {
        if (cursor->value->sdu->sduId == sduId)
            return cursor;
        cursor = cursor->getNext();
    }
    return nullptr;
}

/**
 * Returns true iff given two SOs overlap. This function also handles -1 as SO values. It is better to not use
 * this function for general purpose interval overlap checking function. It is specialized for SO overlap checking.
 */
inline bool SoOverlap(int start1, int end1, int start2, int end2)
{
    return start1 < start2 ? (end1 == -1 || end1 >= start2) : (end2 == -1 || start1 <= end2);
}

/**
 * Return true iff there exists some segment in the list such that SN=sn.
 * The list may or may not be sorted. Therefore, iteration is done until the end of the list,
 * i.e no shortcut is performed.
 */
inline bool SduListContainsSn(LinkedList<RlcSduSegment> &list, int sn)
{
    return list.any([sn](RlcSduSegment *s) { return s->sdu->sn == sn; });
}

/**
 * Returns the first item in the list such that SN=sn. If no such an item is found, then null is returned.
 */
template <typename T>
inline LinkedItem<T> *FirstItemWithSn(LinkedList<T> &list, int sn)
{
    auto cursor = list.getFirst();
    while (cursor != nullptr && cursor->value->sn != sn)
        cursor = cursor->getNext();
    return cursor;
}

/**
 * Returns the first item in the list such that SN=sn. If no such an item is found, then null is returned.
 */
inline LinkedItem<RlcSduSegment> *FirstItemWithSn(LinkedList<RlcSduSegment> &list, int sn)
{
    auto cursor = list.getFirst();
    while (cursor != nullptr && cursor->value->sdu->sn != sn)
        cursor = cursor->getNext();
    return cursor;
}

/**
 * Returns the first item that covers given SN and SO. In other words, first we find the first PDU with SN.
 * Then while maintaining the SN=sn invariant, we look for the first PDU that overlaps with the given SO.
 */
template <typename T>
inline LinkedItem<T> *FirstItemIntersecting(LinkedList<T> &list, int sn, int so)
{
    auto cursor = FirstItemWithSn(list, sn);
    while (cursor != nullptr && cursor->value->sn == sn)
    {
        auto startSo = si::requiresSo(cursor->value->si) ? cursor->value->so : 0;
        auto endSo = startSo + cursor->value->size;

        if (so >= startSo && so < endSo)
        {
            return cursor;
        }
        cursor = cursor->getNext();
    }
    return nullptr;
}

/**
 * Finds the next missing block and returns that block. If no such a block is found, then null is returned.
 */
inline bool FindMissingBlock(LinkedList<AmdPdu> &rxBuffer, int startSn, int startSo, int endSn, int endSo,
                             int snModulus, MissingBlock &res)
{
    // Start line >= end line ise missin part yok demektir.
    // Aksi halde bakmaya devam edilir:
    // Start line ile kesişen bir segment var mı yok mu?
    // Eğer varsa,
    //    kesişen segment eğer endlinedan önce (<= değil <) bitiyorsa ve bitmiyorsa durum değişir.
    //    Bitiyorsa:
    //        ve segment sağ kenara sahip değilse yeni start line söz konusu segmentin bitiş noktası olacak şekilde
    //        recursion çağrılır. ve segment sağ kenara sahipise (hasLast) başlangıcı sn=SN+1 ve so=0 olacak şekilde
    //        recursion çağrılır.
    //    Bitmiyorsa:
    //        missin part yok demektir.
    // Eğer yoksa
    //     missing partın başlangıcı start line olur
    //     end noktasından önce ve start linedan sonra bir segment veya segment parçası başlangıcı var mı diye bakılır.
    //     Varsa:
    //        missin partın end noktası start-end line arasındaki starta en yakın ilk segment parçasının in başlangıcı
    //        olır
    //     Yoksa:
    //        end line olur
    // Son
    //
    // NOT: Kayıp blokları bulurken döngüsel arama istemediğimiz için snCompareRx yerine SnCompareRaw kullanılıyor.
    //

    if (SnCompareRaw(startSn, endSn) > 0 || (SnCompareRaw(startSn, endSn) == 0 && startSo >= endSo))
        return false;

    auto segment = FirstItemIntersecting(rxBuffer, startSn, startSo);
    if (segment != nullptr)
    {
        auto endPointSn = segment->value->sn;
        auto endPointSo =
            si::requiresSo(segment->value->si) ? segment->value->so + segment->value->size : segment->value->size;

        // An assertion just in case (checking for infinite recursion)
        if (SnCompareRaw(startSn, endPointSn) == 0 && endPointSo == startSo)
            assert(false);

        if (si::hasLast(segment->value->si))
            return FindMissingBlock(rxBuffer, (endPointSn + 1) % snModulus, 0, endSn, endSo, snModulus, res);
        else
            return FindMissingBlock(rxBuffer, endPointSn, endPointSo, endSn, endSo, snModulus, res);
    }

    res.snStart = startSn;
    res.soStart = startSo;

    auto cursor = rxBuffer.getFirst();

    while (cursor != nullptr)
    {
        auto val = cursor->value;
        auto so = si::requiresSo(val->si) ? val->so : 0;

        if (SnCompareRaw(val->sn, startSn) < 0 || (SnCompareRaw(val->sn, startSn) == 0 && so < startSo))
            cursor = cursor->getNext();
        else
            break;
    }

    if (cursor == nullptr)
    {
        res.snEnd = endSn;
        res.soEnd = endSo;

        // There is no next
        res.snNext = -1;
        res.soNext = -1;

        return true;
    }

    auto startPointSn = cursor->value->sn;
    auto startPointSo = si::requiresSo(cursor->value->si) ? cursor->value->so : 0;

    if (SnCompareRaw(startPointSn, endSn) > 0 || (SnCompareRaw(startPointSn, endSn) == 0 && startPointSo > endSo))
    {
        res.snEnd = endSn;
        res.soEnd = endSo;

        // There is no next
        res.snNext = -1;
        res.soNext = -1;

        return true;
    }

    if (startPointSo != 0)
    {
        res.snEnd = startPointSn;
        res.soEnd = startPointSo - 1;

        if (si::hasLast(cursor->value->si))
        {
            res.snNext = (startPointSn + 1) % snModulus;
            res.soNext = 0;
        }
        else
        {
            res.snNext = startPointSn;
            res.soNext = (si::requiresSo(cursor->value->si) ? cursor->value->so : 0) + cursor->value->size;
        }
    }
    else
    {
        res.snEnd = (startPointSn - 1 + snModulus) % snModulus;
        res.soEnd = 0xFFFF;

        res.snNext = startPointSn;
        res.soNext = 0;
    }
    return true;
}

/**
 * Returns true iff specified part is already covered in the receive buffer.
 * (SO value should be non-negative.)
 */
template <typename T>
inline bool IsAlreadyReceived(LinkedList<T> &rxList, int sn, int so, int size)
{
    auto cursor = rxList.getFirst();
    while (cursor != nullptr && size > 0)
    {
        if (cursor->value->sn == sn)
        {
            if (cursor->value->so <= so && so < cursor->value->so + cursor->value->size)
            {
                int done = cursor->value->size - (so - cursor->value->so);
                size -= done;
                so += done;
            }
            else if (cursor->value->so <= so + size - 1 && so + size - 1 < cursor->value->so + cursor->value->size)
            {
                int done = size - (cursor->value->so - so);
                size -= done;
            }
        }
        cursor = cursor->getNext();
    }
    return size <= 0;
}

/**
 * Returns true if given SN has a missing segment. That is, at least one segment with SN does not exist in the RX list.
 * NOTE: If no such a segment, the false is returned as there is no missing segment
 */
template <typename T>
inline bool HasMissingSegment(LinkedList<T> &list, int sn)
{
    auto cursor = FirstItemWithSn(list, sn);

    if (cursor == nullptr || cursor->value->isProcessed)
        return false;

    int lastByte = -1;

    while (cursor != nullptr && cursor->value->sn == sn)
    {
        if (cursor->value->so > lastByte + 1)
            return true;
        auto newLastByte = cursor->value->so + cursor->value->size - 1;
        if (newLastByte > lastByte)
            lastByte = newLastByte;
        cursor = cursor->getNext();
    }

    return false;
}

/**
 * Returns true if all segments are received with the given SN. This function also means we are ready to Reassemble
 * relevant segments. Therefore, if no such an SN is found, then false is returned. Similarly, if the SN is already
 * processed, then false is returned.
 */
template <typename T>
inline bool IsAllSegmentsReceived(LinkedList<T> &list, int sn)
{
    auto cursor = FirstItemWithSn(list, sn);

    if (cursor != nullptr && cursor->value->isProcessed)
        return false;

    int last = -1;
    while (cursor != nullptr && cursor->value->sn == sn)
    {
        if (cursor->value->so > last + 1)
            return false;
        if (si::hasLast(cursor->value->si))
            return true;
        int newLast = cursor->value->so + cursor->value->size - 1;
        if (newLast > last)
            last = newLast;
        cursor = cursor->getNext();
    }

    return false;
}

/**
 * Returns true if the given SN is already delivered (processed). If no such an RX PDU is found,
 * it is treated as it is already processed.
 */
template <typename T>
inline bool IsDelivered(LinkedList<T> &list, int sn)
{
    auto cursor = FirstItemWithSn(list, sn);
    return cursor != nullptr && cursor->value->isProcessed;
}

/**
 * Removes all the RX PDUs from the list, that satisfy the given predicate.
 * Returns the total size to be decreased in bytes. Note that, if an item is already processed, then
 * its size is treated as zero. Because buffer size is already decremented while setting isProcessed = true.
 */
template <typename T, typename SnPredicate>
inline int DiscardRxPduIf(LinkedList<T> &list, SnPredicate predicate)
{
    int decreased = 0;

    auto cursor = list.getFirst();
    while (cursor != nullptr)
    {
        if (predicate(cursor->value->sn))
        {
            if (!cursor->value->isProcessed)
                decreased += cursor->value->size;
            delete list.removeAndIncrement(cursor);
        }
        else
        {
            cursor = cursor->getNext();
        }
    }

    return decreased;
}

/**
 * Inserts the given RX PDU to the RX buffer as sorted. Comparison is performed according to given comparator.
 * Size of the added item is returned. Note that this function always succeeds. Buffer size checking is not
 * done in this function.
 */
template <typename T>
inline int InsertToRxBuffer(LinkedList<T> &list, T *item, const IComparator<int> &snComparator)
{
    auto comp = [&snComparator](const T &a, const T &b) {
        if (a.sn == b.sn)
            return a.so - b.so;
        return snComparator.compare(a.sn, b.sn);
    };

    InsertSortedLinkedList(list, item, ILambdaComparator<T, decltype(comp)>{comp});
    return item->size;
}

/**
 * Performs reassembling operation for given RX buffer and SN.
 * Reassembled parts are not removed from the list. Just isProcessed=true is assigned for the items.
 * Reassembled full data is written into the given buffer, and total number of bytes written is returned.
 * If the given SN value is already processed, the behaviour is undefined. So don't do this.
 */
template <typename T>
inline int Reassemble(LinkedList<T> &list, int sn, uint8_t *buffer)
{
    auto cursor = FirstItemWithSn(list, sn);
    int written = 0;

    while (cursor != nullptr && cursor->value->sn == sn)
    {
        std::memcpy(buffer + written, cursor->value->data, cursor->value->size);
        written += cursor->value->size;
        cursor->value->isProcessed = true;
        cursor = cursor->getNext();
    }
    return written;
}

/**
 * Calculates a summation according to given parameters.
 */
template <typename T>
inline int ListSum(LinkedList<T> &list, const std::function<int(const T *item)> &fun)
{
    int res = 0;
    auto cursor = list.getFirst();
    while (cursor != nullptr)
    {
        res += fun(cursor->value);
        cursor = cursor->getNext();
    }
    return res;
}

} // namespace rlc::func