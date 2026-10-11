//
// This file is a part of UERANSIM project.
// Copyright (c) 2027 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>
#include <utils/octet_string.hpp>

// Performs ECIES Profile B and returns the hex scheme-output.
// ephemeralPrivKey is injected (fixed in tests, random in production).
std::string eciesProfileB(const OctetString &plaintextMsin, const OctetString &hnPublicKey,
                          const OctetString &ephemeralPrivKey);

// True if the value is a usable secp256r1 private key, i.e. 32 bytes in [1, n-1].
// Callers generating an ephemeral key must draw again when this returns false.
bool isValidProfileBPrivateKey(const OctetString &privKey);
