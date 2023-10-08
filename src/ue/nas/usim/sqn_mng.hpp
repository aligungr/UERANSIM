//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <algorithm>
#include <cstdint>
#include <vector>

#include <utils/octet_string.hpp>

namespace nr::ue
{

class SqnManager
{
  private:
    uint64_t m_indBitLen;
    uint64_t m_wrappingDelta;

    std::vector<uint64_t> m_sqnArr;

  public:
    SqnManager(uint64_t indBitLen, uint64_t wrappingDelta);

  private:
    [[nodiscard]] uint64_t getSeqFromSqn(uint64_t sqn) const;
    [[nodiscard]] uint64_t getIndFromSqn(uint64_t sqn) const;
    [[nodiscard]] uint64_t getSeqMs() const;
    [[nodiscard]] uint64_t getSqnMs() const;
    bool checkSqn(uint64_t sqn);

  public:
    [[nodiscard]] OctetString getSqn() const;
    bool checkSqn(const OctetString &sqn);
};

} // namespace nr::ue
