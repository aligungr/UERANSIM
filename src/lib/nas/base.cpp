//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "base.hpp"

#include <stdexcept>

void nas::EncodeBcdString(OctetString &stream, const std::string &bcd, size_t octetLength, bool skipFirst,
                          int skippedHalfOctet)
{
    size_t requiredHalfOctets = bcd.length();
    if (skipFirst)
        requiredHalfOctets++;
    if (requiredHalfOctets % 2 != 0)
        requiredHalfOctets++;

    size_t requiredOctets = requiredHalfOctets / 2;
    if (octetLength == ~0U)
        octetLength = requiredOctets;
    if (octetLength < requiredOctets)
        throw std::runtime_error("octetLength should be greater or equal to " + std::to_string(requiredOctets));

    std::vector<int> halfOctets(requiredHalfOctets);
    for (size_t i = 0; i < bcd.length(); i++)
    {
        char c = bcd[i];
        if (c < '0' || c > '9')
            throw std::runtime_error("BCD string contains invalid characters");
        halfOctets[i] = c - '0';
    }

    if (skipFirst)
    {
        for (size_t i = bcd.length(); i >= 1; i--)
            halfOctets[i] = halfOctets[i - 1];
        halfOctets[0] = skippedHalfOctet & 0xF;
    }

    size_t spare = requiredHalfOctets - (bcd.length() + (skipFirst ? 1 : 0));
    for (size_t i = 0; i < spare; i++)
        halfOctets[i + bcd.length() + (skipFirst ? 1 : 0)] = 0xF;

    for (size_t i = 0; i < requiredHalfOctets / 2; i++)
    {
        int little = halfOctets[2 * i];
        int big = halfOctets[2 * i + 1];
        int octet = big << 4 | little;
        stream.appendOctet(octet);
    }
}

std::string nas::DecodeBcdString(const OctetView &stream, int length, bool skipFirst)
{
    if (length == 0)
        return "";

    constexpr char digits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '?', '?', '?', '?', '?', '?'};
    int offset = 0, i = 0;

    size_t stringLength = length * 2;
    if ((stream.peek(length - 1) >> 4) == 0xF)
        stringLength--;

    std::string str(stringLength, '0');

    while (offset < length)
    {
        int octet = stream.readI();
        if (!skipFirst)
        {
            str[i] = digits[octet & 0x0f];
            i++;
        }
        skipFirst = false;
        octet = octet >> 4;

        if (offset == length - 1 && octet == 0x0f)
            break;
        str[i] = digits[octet & 0x0f];
        i++;
        offset++;
    }

    return str;
}
