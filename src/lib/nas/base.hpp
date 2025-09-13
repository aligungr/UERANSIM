//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <utils/octet_string.hpp>
#include <utils/octet_view.hpp>

namespace nas
{

struct InformationElement
{
    virtual ~InformationElement() = default;
};

struct InformationElement1 : InformationElement
{
};

struct InformationElement2 : InformationElement
{
};

struct InformationElement3 : InformationElement
{
};

struct InformationElement4 : InformationElement
{
};

struct InformationElement6 : InformationElement
{
};

//======================================================================================================
//                                           IE TYPE 1
//======================================================================================================

template <typename T>
static inline T DecodeIe1(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    int octet = stream.readI();
    // int iei = octet >> 4 & 0xF;
    int value = octet & 0xF;
    return T::Decode(value);
}

template <typename T, typename U>
static inline void EncodeIe1(const T &big, const U &little, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    int bigHalf = T::Encode(big) & 0xF;
    int littleHalf = U::Encode(little) & 0xF;
    stream.appendOctet(bigHalf, littleHalf);
}

template <typename T>
static inline void EncodeIe1(int big, const T &little, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    int littleHalf = T::Encode(little) & 0xF;
    stream.appendOctet(big, littleHalf);
}

template <typename T>
static inline void EncodeIe1(const T &big, int little, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    int bigHalf = T::Encode(big) & 0xF;
    stream.appendOctet(bigHalf, little);
}

//======================================================================================================
//                                           IE TYPE 2
//======================================================================================================

template <typename T>
static inline void EncodeIe2(const T &ie, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement2, T>::value);
    // nothing to encode for type-2 information elements
}

template <typename T>
static inline T DecodeIe2(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement2, T>::value);
    // nothing to decode for type-2 information elements
    return {};
}

//======================================================================================================
//                                           IE TYPE 3
//======================================================================================================

template <typename T>
static inline void EncodeIe3(const T &ie, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement3, T>::value);

    T::Encode(ie, stream);
}

template <typename T>
static inline T DecodeIe3(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement3, T>::value);

    return T::Decode(stream);
}

//======================================================================================================
//                                           IE TYPE 4
//======================================================================================================

template <typename T>
static inline void EncodeIe4(const T &ie, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement4, T>::value);

    int index = stream.length();
    stream.appendOctet(0);
    T::Encode(ie, stream);
    int length = stream.length() - index - 1;
    stream.data()[index] = length;
}

template <typename T>
static inline T DecodeIe4(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement4, T>::value);

    return T::Decode(stream, stream.readI());
}

//======================================================================================================
//                                           IE TYPE 6
//======================================================================================================

template <typename T>
static inline void EncodeIe6(const T &ie, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement6, T>::value);

    int index = stream.length();
    stream.appendOctet2(0);
    T::Encode(ie, stream);
    int length = stream.length() - index - 2;
    stream.data()[index] = (length >> 8) & 0xFF;
    stream.data()[index + 1] = length & 0xFF;
}

template <typename T>
static inline T DecodeIe6(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement6, T>::value);
    return T::Decode(stream, stream.read2I());
}

//======================================================================================================
//                                           IE TYPE 2-3-4-6
//======================================================================================================

template <typename T>
static inline T DecodeIe2346(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement2, T>::value || std::is_base_of<InformationElement3, T>::value ||
                  std::is_base_of<InformationElement4, T>::value || std::is_base_of<InformationElement6, T>::value);
    if constexpr (std::is_base_of<InformationElement2, T>::value)
        return DecodeIe2<T>(stream);
    if constexpr (std::is_base_of<InformationElement3, T>::value)
        return DecodeIe3<T>(stream);
    if constexpr (std::is_base_of<InformationElement4, T>::value)
        return DecodeIe4<T>(stream);
    if constexpr (std::is_base_of<InformationElement6, T>::value)
        return DecodeIe6<T>(stream);
    return {};
}

template <typename T>
static inline void Encode2346(const T &ie, OctetString &stream)
{
    static_assert(std::is_base_of<InformationElement2, T>::value || std::is_base_of<InformationElement3, T>::value ||
                  std::is_base_of<InformationElement4, T>::value || std::is_base_of<InformationElement6, T>::value);
    if constexpr (std::is_base_of<InformationElement2, T>::value)
        EncodeIe2<T>(ie, stream);
    if constexpr (std::is_base_of<InformationElement3, T>::value)
        EncodeIe3<T>(ie, stream);
    if constexpr (std::is_base_of<InformationElement4, T>::value)
        EncodeIe4<T>(ie, stream);
    if constexpr (std::is_base_of<InformationElement6, T>::value)
        EncodeIe6<T>(ie, stream);
}

//======================================================================================================
//                                           OTHERS
//======================================================================================================

template <typename T>
static inline bool DecodeListIe(const OctetView &stream, int length, std::vector<T> &output)
{
    size_t readLen = 0;
    while (readLen < static_cast<size_t>(length))
    {
        size_t streamIndex = stream.currentIndex();
        output.push_back(DecodeIe2346<T>(stream));
        readLen += stream.currentIndex() - streamIndex;
    }
    if (readLen > static_cast<size_t>(length))
        return false; // Failure: Value length exceeds total length
    return true;
}

template <typename T>
static inline bool DecodeListVal(const OctetView &stream, int length, std::vector<T> &output)
{
    size_t readLen = 0;
    while (readLen < static_cast<size_t>(length))
    {
        size_t streamIndex = stream.currentIndex();
        output.push_back(T::Decode(stream));
        readLen += stream.currentIndex() - streamIndex;
    }
    if (readLen > static_cast<size_t>(length))
        return false; // Failure: Value length exceeds total length
    return true;
}

size_t EncodeBcdString(OctetString &stream, const std::string &bcd, size_t octetLength, bool skipFirst,
                     int skippedHalfOctet);

std::string DecodeBcdString(const OctetView &stream, int length, bool skipFirst);

void EncodeRoutingIndicator(OctetString &stream, const std::string &bcd);


//======================================================================================================
//                                  MUTATION FUNCTIONS FOR FUZZING
//======================================================================================================

template <typename T, typename U>
static inline void MutateIe1(T &big, U &little)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    T::Mutate(big);
    U::Mutate(little);
}

template <typename T>
static inline void MutateIe1(int big, T &little)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    T::Mutate(little);
}

template <typename T>
static inline void MutateIe1(T &big, int little)
{
    static_assert(std::is_base_of<InformationElement1, T>::value);

    T::Mutate(big);
}

template <typename T>
static inline void MutateIe2(T &ie)
{
    static_assert(std::is_base_of<InformationElement2, T>::value);
    // nothing to mutate for type-2 information elements
}


template <typename T>
static inline void MutateIe3(T &ie)
{
    static_assert(std::is_base_of<InformationElement3, T>::value);

    T::Mutate(ie);
}

template <typename T>
static inline void MutateIe4(T &ie)
{
    static_assert(std::is_base_of<InformationElement4, T>::value);

    T::Mutate(ie);
}


template <typename T>
static inline void MutateIe6(T &ie)
{
    static_assert(std::is_base_of<InformationElement6, T>::value);

    T::Mutate(ie);
}



template <typename T>
static inline void Mutate2346(T &ie)
{
    static_assert(std::is_base_of<InformationElement2, T>::value || std::is_base_of<InformationElement3, T>::value ||
                  std::is_base_of<InformationElement4, T>::value || std::is_base_of<InformationElement6, T>::value);
    if constexpr (std::is_base_of<InformationElement2, T>::value)
        MutateIe2<T>(ie);
    if constexpr (std::is_base_of<InformationElement3, T>::value)
        MutateIe3<T>(ie);
    if constexpr (std::is_base_of<InformationElement4, T>::value)
        MutateIe4<T>(ie);
    if constexpr (std::is_base_of<InformationElement6, T>::value)
        MutateIe6<T>(ie);
}
} // namespace nas