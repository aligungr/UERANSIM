#pragma once

#include <cstring>
#include <memory>
#include <vector>

namespace nr::rlc
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

    int calculatedSize(bool isShortSn) const;
};

struct RlcSdu
{
    const int sduId;
    const int size;
    uint8_t *const data;
    int sn;
    int retransmissionCount;

    RlcSdu(uint8_t *data, int size, int sduId)
        : sduId(sduId), size(size), data(new uint8_t[size]), sn(0), retransmissionCount(0)
    {
        std::memcpy(this->data, data, size);
    }

    ~RlcSdu()
    {
        delete[] data;
    }
};

struct RlcSduSegment
{
    RlcSdu *sdu;
    int size;
    int so;
    ESegmentInfo si;
};

class RlcTimer
{
    const long period;
    long startedAt; // 0 means not started

  public:
    explicit RlcTimer(long period) : period{period}, startedAt(0)
    {
    }

    // Returns true iff the timer was running and just expired and has been reset.
    bool cycle(long currentTime)
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

    inline void start(long currentTime)
    {
        startedAt = currentTime;
    }

    inline void stop()
    {
        startedAt = 0;
    }

    inline bool isRunning() const
    {
        return startedAt != 0;
    }

    inline bool stoppedOrExpired(long currentTime) const
    {
        return !isRunning() || currentTime - startedAt > period;
    }
};

template <typename T>
struct IComparator
{
    virtual int compare(const T &a, const T &b) const = 0;
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

    inline int compare(const int &a, const int &b) const override
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

} // namespace nr::rlc
