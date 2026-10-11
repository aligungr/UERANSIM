//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <optional>
#include <stdexcept>

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

// The contents of a TLV or TLV-E IE are decoded within their own length: octets the decoder leaves unread (spare octets
// added by a later release, for one) are skipped, and a decoder that reads too far throws instead of running into the
// next IE.
template <typename T>
static inline T DecodeIe4(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement4, T>::value);

    int length = stream.readI();
    OctetView contents = stream.subView(static_cast<size_t>(length));
    return T::Decode(contents, length);
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

    int length = stream.read2I();
    OctetView contents = stream.subView(static_cast<size_t>(length));
    return T::Decode(contents, length);
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

// Decodes an optional IE whose IEI has been read. TS 24.501 §7.7.1: a syntactically incorrect optional IE is treated as
// not present. A TLV or TLV-E IE is stepped over by its length whatever its contents hold, and when that length runs
// past the end of the message, there is nothing left after it to decode either.
template <typename T>
static inline std::optional<T> DecodeOptionalIe2346(const OctetView &stream)
{
    static_assert(std::is_base_of<InformationElement2, T>::value || std::is_base_of<InformationElement3, T>::value ||
                  std::is_base_of<InformationElement4, T>::value || std::is_base_of<InformationElement6, T>::value);

    if constexpr (std::is_base_of<InformationElement4, T>::value || std::is_base_of<InformationElement6, T>::value)
    {
        size_t lengthSize = std::is_base_of<InformationElement4, T>::value ? 1 : 2;
        if (stream.remainingSize() < lengthSize)
        {
            stream.subView(stream.remainingSize());
            return std::nullopt;
        }
        size_t length = lengthSize == 1 ? stream.readI() : stream.read2US();
        if (length > stream.remainingSize())
        {
            stream.subView(stream.remainingSize());
            return std::nullopt;
        }

        OctetView contents = stream.subView(length);
        try
        {
            return T::Decode(contents, static_cast<int>(length));
        }
        catch (const std::out_of_range &)
        {
            return std::nullopt;
        }
        catch (const std::runtime_error &)
        {
            return std::nullopt;
        }
    }
    else
    {
        return DecodeIe2346<T>(stream);
    }
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

// Decodes `length` octets of list elements. Lists are decoded within the contents of their IE (see DecodeIe4 and
// DecodeIe6), so an element that runs past them throws there. An element that consumes no octets would never bring the
// list to its end, so it throws too, as does one that runs past `length`.
template <typename T, typename Decoder>
static inline void DecodeList(const OctetView &stream, int length, std::vector<T> &output, Decoder decode)
{
    if (length < 0)
        throw std::runtime_error("NAS list has a negative length");

    size_t end = stream.currentIndex() + static_cast<size_t>(length);
    while (stream.currentIndex() < end)
    {
        size_t start = stream.currentIndex();
        output.push_back(decode(stream));
        if (stream.currentIndex() == start)
            throw std::runtime_error("NAS list element consumed no octets");
    }
    if (stream.currentIndex() != end)
        throw std::runtime_error("NAS list element runs past the end of the list");
}

template <typename T>
static inline void DecodeListIe(const OctetView &stream, int length, std::vector<T> &output)
{
    DecodeList(stream, length, output, [](const OctetView &s) { return DecodeIe2346<T>(s); });
}

template <typename T>
static inline void DecodeListVal(const OctetView &stream, int length, std::vector<T> &output)
{
    DecodeList(stream, length, output, [](const OctetView &s) { return T::Decode(s); });
}

size_t EncodeBcdString(OctetString &stream, const std::string &bcd, size_t octetLength, bool skipFirst,
                     int skippedHalfOctet);

std::string DecodeBcdString(const OctetView &stream, int length, bool skipFirst);

void EncodeRoutingIndicator(OctetString &stream, const std::string &bcd);

} // namespace nas