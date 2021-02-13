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
#include <utility>

class Printer
{
    std::stringstream m_ss{};
    int m_indent{};

  private:
    void appendIndentation();

  public:
    void append(const std::string &str);
    void appendKeyValue(const std::string &key, const std::string &value);
    void appendKeyValue(const std::initializer_list<std::pair<std::string, std::string>> &pairs);
    void appendKeyValueList(const std::initializer_list<std::pair<std::string, std::string>> &pairs);

    std::string makeString() const;

    void trim();

    void increment(int delta = 1);
    void decrement(int delta = 1);
};