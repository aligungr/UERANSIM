//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "printer.hpp"
#include <utils/common.hpp>

void Printer::appendKeyValue(const std::string &key, const std::string &value)
{
    appendIndentation();
    m_ss << key << ": " << value << "\n";
}

std::string Printer::makeString() const
{
    return m_ss.str();
}

void Printer::appendIndentation()
{
    m_ss << std::string(m_indent, ' ');
}

void Printer::trim()
{
    utils::Trim(m_ss);
}

void Printer::appendKeyValue(const std::initializer_list<std::pair<std::string, std::string>> &pairs)
{
    size_t maxKey = 0;
    for (auto &pair : pairs)
        maxKey = std::max(maxKey, pair.first.size());
    for (auto &pair : pairs)
    {
        appendIndentation();
        m_ss << pair.first;
        m_ss << std::string(maxKey - pair.first.size(), ' ');
        m_ss << " : ";
        m_ss << pair.second;
        m_ss << "\n";
    }
}
