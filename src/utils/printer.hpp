//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <sstream>
#include <string>

class Printer
{
    std::stringstream m_ss{};
    int m_indent{};

  private:
    void appendIndentation();

  public:
    void appendKeyValue(const std::string &key, const std::string &value);

    void appendKeyValue(const std::string &key, int16_t value);
    void appendKeyValue(const std::string &key, uint16_t value);
    void appendKeyValue(const std::string &key, int32_t value);
    void appendKeyValue(const std::string &key, uint32_t value);
    void appendKeyValue(const std::string &key, int64_t value);
    void appendKeyValue(const std::string &key, uint64_t value);

    std::string makeString() const;

    void trim();
};