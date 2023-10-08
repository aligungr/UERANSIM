//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
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

void Printer::increment(int delta)
{
    m_indent++;
}

void Printer::decrement(int delta)
{
    m_indent--;
    if (m_indent < 0)
        m_indent = 0;
}

void Printer::append(const std::string &str)
{
    appendIndentation();
    m_ss << str;
}

void Printer::appendKeyValueList(const std::initializer_list<std::pair<std::string, std::string>> &pairs)
{
    size_t maxKey = 0;
    for (auto &pair : pairs)
        maxKey = std::max(maxKey, pair.first.size());
    bool isFirst = true;
    for (auto &pair : pairs)
    {
        appendIndentation();
        if (isFirst)
        {
            isFirst = false;
            m_ss << "- ";
        }
        else
        {
            m_ss << "  ";
        }
        m_ss << pair.first;
        m_ss << std::string(maxKey - pair.first.size(), ' ');
        m_ss << " : ";
        m_ss << pair.second;
        m_ss << "\n";
    }
}
