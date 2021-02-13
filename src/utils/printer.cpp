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

void Printer::appendKeyValue(const std::string &key, int16_t value)
{
    appendKeyValue(key, std::to_string(value));
}

void Printer::appendKeyValue(const std::string &key, uint16_t value)
{
    appendKeyValue(key, std::to_string(value));
}

void Printer::appendKeyValue(const std::string &key, int32_t value)
{
    appendKeyValue(key, std::to_string(value));
}

void Printer::appendKeyValue(const std::string &key, uint32_t value)
{
    appendKeyValue(key, std::to_string(value));
}

void Printer::appendKeyValue(const std::string &key, int64_t value)
{
    appendKeyValue(key, std::to_string(value));
}

void Printer::appendKeyValue(const std::string &key, uint64_t value)
{
    appendKeyValue(key, std::to_string(value));
}
