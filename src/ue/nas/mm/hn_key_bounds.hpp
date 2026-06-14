//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <cstddef>

struct HnKeyHexBounds
{
    int min;
    int max;
};

// Returns the min/max hex-string length bounds for homeNetworkPublicKey
// based on the protectionScheme value.
// scheme 0: key not required (return {0, 0} as sentinel)
// scheme 1 (Profile A): exactly 64 hex chars (32 bytes x25519)
// scheme 2 (Profile B): 66 (compressed) or 130 (uncompressed) hex chars
inline HnKeyHexBounds hnKeyHexBounds(int scheme)
{
    switch (scheme)
    {
    case 1:
        return {64, 64};
    case 2:
        return {66, 130};
    default:
        return {0, 0};
    }
}

// For scheme 2, validates that hex length is exactly 66 or 130.
// Returns true if valid, false otherwise.
inline bool validateProfileBKeyHexLen(size_t hexLen)
{
    return hexLen == 66 || hexLen == 130;
}
