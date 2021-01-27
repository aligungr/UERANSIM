//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR, Havelsan.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <octet_buffer.hpp>
#include <octet_string.hpp>

namespace nas
{

struct InformationElement
{
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
static inline T DecodeIe1(const OctetBuffer &stream)
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
static inline T DecodeIe2(const OctetBuffer &stream)
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
static inline T DecodeIe3(const OctetBuffer &stream)
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
static inline T DecodeIe4(const OctetBuffer &stream)
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
static inline T DecodeIe6(const OctetBuffer &stream)
{
    static_assert(std::is_base_of<InformationElement6, T>::value);
    return T::Decode(stream, stream.read2I());
}

//======================================================================================================
//                                           IE TYPE 2-3-4-6
//======================================================================================================

template <typename T>
static inline T DecodeIe2346(const OctetBuffer &stream)
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
static inline bool DecodeListIe(const OctetBuffer &stream, int length, std::vector<T> &output)
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
static inline bool DecodeListVal(const OctetBuffer &stream, int length, std::vector<T> &output)
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

void EncodeBcdString(OctetString &stream, const std::string &bcd, size_t octetLength, bool skipFirst,
                     int skippedHalfOctet);

std::string DecodeBcdString(const OctetBuffer &stream, int length, bool skipFirst);

} // namespace nas