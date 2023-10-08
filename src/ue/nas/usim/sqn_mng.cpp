//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "sqn_mng.hpp"

#include <stdexcept>

namespace nr::ue
{

SqnManager::SqnManager(uint64_t indBitLen, uint64_t wrappingDelta)
    : m_indBitLen{indBitLen}, m_wrappingDelta{wrappingDelta}, m_sqnArr(1ull << m_indBitLen)
{
    if (m_indBitLen < 2 || m_indBitLen > 16)
        throw std::runtime_error("bad indBitLen");
}

uint64_t SqnManager::getSeqFromSqn(uint64_t sqn) const
{
    sqn &= ~((1ull << m_indBitLen) - 1ull);
    sqn >>= m_indBitLen;
    sqn &= (1ull << 48ull) - 1ull;
    return sqn;
}

uint64_t SqnManager::getIndFromSqn(uint64_t sqn) const
{
    return sqn & ((1ull << m_indBitLen) - 1ull);
}

uint64_t SqnManager::getSeqMs() const
{
    return getSeqFromSqn(getSqnMs());
}

uint64_t SqnManager::getSqnMs() const
{
    return *std::max_element(m_sqnArr.begin(), m_sqnArr.end());
}

bool SqnManager::checkSqn(uint64_t sqn)
{
    uint64_t seq = getSeqFromSqn(sqn);
    uint64_t ind = getIndFromSqn(sqn);

    if (seq - getSeqMs() > m_wrappingDelta)
        return false;
    if (seq <= getSeqFromSqn(m_sqnArr[ind]))
        return false;

    m_sqnArr[ind] = sqn;
    return true;
}

bool SqnManager::checkSqn(const OctetString &sqn)
{
    OctetString str;
    str.appendOctet2(0);
    str.append(sqn);
    return checkSqn(str.get8UL(0));
}

OctetString SqnManager::getSqn() const
{
    return OctetString::FromOctet8(getSqnMs()).subCopy(2);
}

} // namespace nr::ue
