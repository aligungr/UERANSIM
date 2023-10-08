//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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