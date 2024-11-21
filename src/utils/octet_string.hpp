//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "octet.hpp"

#include <cstddef>
#include <cstdint>
#include <memory>
#include <vector>

class OctetString
{
  private:
    std::vector<uint8_t> m_data;

  public:
    OctetString() : m_data()
    {
    }

    explicit OctetString(std::vector<uint8_t> &&data) : m_data(std::move(data))
    {
    }

    OctetString(OctetString &&octetString) noexcept : m_data(std::move(octetString.m_data))
    {
    }

  public:
    void append(const OctetString &v);
    void appendUtf8(const std::string &v);
    void appendOctet(uint8_t v);
    void appendOctet(int v);
    void appendOctet(int bigHalf, int littleHalf);
    void appendOctet2(octet2 v);
    void appendOctet2(uint16_t v);
    void appendOctet2(int v);
    void appendOctet3(octet3 v);
    void appendOctet3(int v);
    void appendOctet4(octet4 v);
    void appendOctet4(int v);
    void appendOctet4(uint32_t v);
    void appendOctet8(octet8 v);
    void appendOctet8(int64_t v);
    void appendOctet8(uint64_t v);
    void appendPadding(int length);

  public:
    [[nodiscard]] const uint8_t *data() const;
    [[nodiscard]] int length() const;
    uint8_t *data();

  public:
    [[nodiscard]] octet get(int index) const;
    [[nodiscard]] octet2 get2(int index) const;
    [[nodiscard]] octet3 get3(int index) const;
    [[nodiscard]] octet4 get4(int index) const;
    [[nodiscard]] octet8 get8(int index) const;
    [[nodiscard]] int getI(int index) const;
    [[nodiscard]] int get2I(int index) const;
    [[nodiscard]] int get3I(int index) const;
    [[nodiscard]] int get4I(int index) const;
    [[nodiscard]] uint32_t get4UI(int index) const;
    [[nodiscard]] int64_t get8L(int index) const;
    [[nodiscard]] uint64_t get8UL(int index) const;

  public:
    [[nodiscard]] std::string toHexString() const;
    [[nodiscard]] OctetString copy() const;
    [[nodiscard]] OctetString subCopy(int index) const;
    [[nodiscard]] OctetString subCopy(int index, int length) const;

  public:
    inline OctetString &operator=(OctetString &&other) noexcept
    {
        m_data = std::move(other.m_data);
        return *this;
    }

    inline bool operator==(const OctetString &other)
    {
        return m_data == other.m_data;
    }

    inline bool operator!=(const OctetString &other)
    {
        return m_data != other.m_data;
    }

  public:
    static OctetString Empty();
    static OctetString FromHex(const std::string &hex);
    static OctetString FromAscii(const std::string &ascii);
    static OctetString FromArray(const uint8_t *arr, size_t len);
    static OctetString FromSpare(int length);
    static OctetString FromOctet(uint8_t value);
    static OctetString FromOctet(int value);
    static OctetString FromOctet2(octet2 value);
    static OctetString FromOctet2(int value);
    static OctetString FromOctet4(octet4 value);
    static OctetString FromOctet4(int value);
    static OctetString FromOctet4(uint32_t value);
    static OctetString FromOctet8(octet8 value);
    static OctetString FromOctet8(int64_t value);
    static OctetString FromOctet8(uint64_t value);

    static OctetString Concat(const OctetString &a, const OctetString &b);
    static OctetString Xor(const OctetString &a, const OctetString &b);
};
