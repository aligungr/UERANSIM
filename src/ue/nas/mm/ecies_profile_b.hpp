//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
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
