//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstring>
#include <memory>
#include <vector>

namespace rlc
{

enum class ESegmentInfo
{
    // 00, Data field contains all bytes of an RLC SDU
    FULL = 0b00,
    // 01, Data field contains the first segment of an RLC SDU
    FIRST = 0b01,
    // 10, Data field contains the last segment of an RLC SDU
    LAST = 0b10,
    // 11, Data field contains neither the first nor last segment of an RLC SDU
    MIDDLE = 0b11,
};

namespace si
{

inline bool hasFirst(const ESegmentInfo &si)
{
    return si == ESegmentInfo::FULL || si == ESegmentInfo::FIRST;
}

inline bool hasLast(const ESegmentInfo &si)
{
    return si == ESegmentInfo::FULL || si == ESegmentInfo::LAST;
}

inline bool requiresSo(const ESegmentInfo &si)
{
    return si == ESegmentInfo::LAST || si == ESegmentInfo::MIDDLE;
}

inline ESegmentInfo asNotLast(const ESegmentInfo &si)
{
    if (si == ESegmentInfo::LAST)
        return ESegmentInfo::MIDDLE;
    if (si == ESegmentInfo::FULL)
        return ESegmentInfo::FIRST;
    return si;
}

inline ESegmentInfo asNotFirst(const ESegmentInfo &si)
{
    if (si == ESegmentInfo::FIRST)
        return ESegmentInfo::MIDDLE;
    if (si == ESegmentInfo::FULL)
        return ESegmentInfo::LAST;
    return si;
}

} // namespace si

struct NackBlock
{
    int nackSn;
    int soStart;

    // (The special SOend value "1111111111111111" is used to indicate that the missing portion
    // of the RLC SDU includes all bytes to the last byte of the RLC SDU.)
    int soEnd;

    int nackRange;
};

struct RxPdu
{
    ESegmentInfo si;
    int so;
    int sn;
    uint8_t *data;
    int size;
    bool isProcessed;

    virtual ~RxPdu()
    {
        delete[] data;
    }
};

struct AmdPdu : RxPdu
{
    bool p;
};

struct UmdPdu : RxPdu
{
};

struct StatusPdu
{
    int ackSn;
    std::vector<NackBlock> nackBlocks{};

    [[nodiscard]] int calculatedSize(bool isShortSn) const;
};

class RlcSdu
{
  public:
    const int sduId;
    const int size;
    uint8_t *const data;
    int sn;
    int retransmissionCount;

    int _refCount;

  private:
    RlcSdu(uint8_t *data, int size, int sduId)
        : sduId(sduId), size(size), data(data), sn(0), retransmissionCount(0), _refCount(0)
    {
    }

  public:
    ~RlcSdu()
    {
        delete[] data;
    }

    static RlcSdu *NewFromData(uint8_t *data, int size, int sduId)
    {
        auto *copy = new uint8_t[size];
        std::memcpy(copy, data, size);
        return new RlcSdu(copy, size, sduId);
    }
};

struct RlcSduSegment
{
    RlcSdu *const sdu;
    int size;
    int so;
    ESegmentInfo si;

    explicit RlcSduSegment(RlcSdu *sdu) : sdu(sdu), size{}, so{}, si{}
    {
        sdu->_refCount++;
    }

    ~RlcSduSegment()
    {
        if (--sdu->_refCount == 0)
            delete sdu;
    }
};

class RlcTimer
{
    const int64_t period;
    int64_t startedAt; // 0 means not started

  public:
    explicit RlcTimer(int64_t period) : period{period}, startedAt(0)
    {
    }

    // Returns true iff the timer was running and just expired and has been reset.
    bool cycle(int64_t currentTime)
    {
        // If timer is running and expired
        if (startedAt != 0 && currentTime > startedAt + period)
        {
            // Stop timer
            startedAt = 0;
            // Handle expire actions
            return true;
        }
        return false;
    }

    inline void start(int64_t currentTime)
    {
        startedAt = currentTime;
    }

    inline void stop()
    {
        startedAt = 0;
    }

    [[nodiscard]] inline bool isRunning() const
    {
        return startedAt != 0;
    }

    [[nodiscard]] inline bool stoppedOrExpired(int64_t currentTime) const
    {
        return !isRunning() || currentTime - startedAt > period;
    }
};

template <typename T>
struct IComparator
{
    [[nodiscard]] virtual int compare(const T &a, const T &b) const = 0;
};

template <typename T, typename U>
struct ILambdaComparator : IComparator<T>
{
    const U &u;

    explicit ILambdaComparator(const U &u) : u(u)
    {
    }

    int compare(const T &a, const T &b) const override
    {
        return u(a, b);
    }
};

template <typename TComparator>
struct ISnCompare : IComparator<int>
{
    const TComparator &comp;

    explicit ISnCompare(const TComparator &comp) : comp(comp)
    {
    }

    [[nodiscard]] inline int compare(const int &a, const int &b) const override
    {
        return comp(a, b);
    }
};

struct MissingBlock
{
    // Start SN and SO of the missing block
    int snStart;
    int soStart;

    // End SN and SO of the missing block
    int snEnd;
    int soEnd;

    // After finding this missing block, these SN and SO values indicate the resuming point of finding the
    // next missing block. If no resume to do, these values are set to -1.
    int snNext;
    int soNext;
};

} // namespace rlc
