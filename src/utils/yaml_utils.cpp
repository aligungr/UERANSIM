//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "yaml_utils.hpp"
#include "common.hpp"
#include "io.hpp"

#include <cctype>
#include <stdexcept>

#include <yaml-cpp/yaml.h>

namespace yaml
{

static YAML::Node OctalFix(const YAML::Node &node)
{
    auto s = node.as<std::string>();

    if (!std::all_of(s.begin(), s.end(), ::isdigit))
        return node;
    if (!s.empty() && s[0] != '0')
        return node;

    s.erase(0, std::min(s.find_first_not_of('0'), s.size() - 1));
    return YAML::Node{s};
}

[[noreturn]] void FieldError(const std::string &name, const std::string &error)
{
    throw std::runtime_error("Field '" + name + "' " + error + ".");
}

bool HasField(const YAML::Node &node, const std::string &name)
{
    return node[name].IsDefined() && !node[name].IsNull();
}

void AssertHasField(const YAML::Node &node, const std::string &name)
{
    if (!HasField(node, name))
        FieldError(name, "is missing");
}

void AssertHasFields(const YAML::Node &node, const std::vector<std::string> &fields)
{
    for (auto &field : fields)
        AssertHasField(node, field);
}

int GetInt32(const YAML::Node &node, const std::string &name)
{
    AssertHasInt32(node, name);
    return OctalFix(node[name]).as<int>();
}

int GetInt32(const YAML::Node &node, const std::string &name, std::optional<int> minValue, std::optional<int> maxValue)
{
    int value = GetInt32(node, name);
    if (minValue.has_value() && value < minValue)
        FieldError(name, "is too small");
    if (maxValue.has_value() && value > maxValue)
        FieldError(name, "is too big");
    return value;
}

void AssertHasInt32(const YAML::Node &node, const std::string &name)
{
    AssertHasField(node, name);
    try
    {
        OctalFix(node[name]).as<int>();
    }
    catch (const std::runtime_error &e)
    {
        FieldError(name, "has invalid type");
    }
}

void AssertHasString(const YAML::Node &node, const std::string &name)
{
    AssertHasField(node, name);
    try
    {
        node[name].as<std::string>();
    }
    catch (const std::runtime_error &e)
    {
        FieldError(name, "has invalid type");
    }
}

std::string GetString(const YAML::Node &node, const std::string &name)
{
    AssertHasString(node, name);
    return node[name].as<std::string>();
}

std::string GetString(const YAML::Node &node, const std::string &name, std::optional<int> minLength,
                      std::optional<int> maxLength)
{
    std::string value = GetString(node, name);
    if (minLength.has_value() && static_cast<int>(value.length()) < *minLength)
        FieldError(name, "is too small");
    if (maxLength.has_value() && static_cast<int>(value.length()) > *maxLength)
        FieldError(name, "is too large");
    return value;
}

void AssertHasInt64(const YAML::Node &node, const std::string &name)
{
    AssertHasField(node, name);
    try
    {
        OctalFix(node[name]).as<int64_t>();
    }
    catch (const std::runtime_error &e)
    {
        FieldError(name, "has invalid type");
    }
}

int64_t GetInt64(const YAML::Node &node, const std::string &name)
{
    AssertHasInt64(node, name);
    return OctalFix(node[name]).as<int64_t>();
}

int64_t GetInt64(const YAML::Node &node, const std::string &name, std::optional<int64_t> minValue,
                 std::optional<int64_t> maxValue)
{
    int64_t value = GetInt64(node, name);
    if (minValue.has_value() && value < minValue)
        FieldError(name, "is too small");
    if (maxValue.has_value() && value > maxValue)
        FieldError(name, "is too big");
    return value;
}

std::string GetIp4(const YAML::Node &node, const std::string &name)
{
    std::string s = GetString(node, name);

    s = io::GetHostByName(s);

    int version = utils::GetIpVersion(s);
    if (version == 6)
        FieldError(name, "must be a valid IPv4 address, FQDN or a valid network interface with a IPv4 address");
    if (version == 4)
        return s;

    auto ipFromIf = io::GetIp4OfInterface(s);
    if (ipFromIf.empty())
        FieldError(name, "must be a valid IPv4 address, FQDN or a valid network interface with a IPv4 address");
    return ipFromIf;
}

std::string GetIp6(const YAML::Node &node, const std::string &name)
{
    std::string s = GetString(node, name);

    s = io::GetHostByName(s);

    int version = utils::GetIpVersion(s);
    if (version == 4)
        FieldError(name, "must be a valid IPv6 address, FQDN or a valid network interface with a IPv6 address");
    if (version == 6)
        return s;

    auto ipFromIf = io::GetIp6OfInterface(s);
    if (ipFromIf.empty())
        FieldError(name, "must be a valid IPv6 address, FQDN or a valid network interface with a IPv6 address");
    return ipFromIf;
}

std::string GetIp(const YAML::Node &node, const std::string & name)
{
    std::string s = GetString(node, name);

    s = io::GetHostByName(s);

    int version = utils::GetIpVersion(s);
    if (version == 6 || version == 4)
        return s;
    auto ip4FromIf = io::GetIp4OfInterface(s);
    if (!ip4FromIf.empty())
        return ip4FromIf;
    auto ip6FromIf = io::GetIp6OfInterface(s);
    if (!ip6FromIf.empty())
        return ip6FromIf;
    FieldError(name, "must be a valid IP address, FQDN or a valid network interface with an IP address");
}

void AssertHasBool(const YAML::Node &node, const std::string &name)
{
    AssertHasField(node, name);
    try
    {
        node[name].as<bool>();
    }
    catch (const std::runtime_error &e)
    {
        FieldError(name, "has invalid type");
    }
}

bool GetBool(const YAML::Node &node, const std::string &name)
{
    AssertHasBool(node, name);
    return node[name].as<bool>();
}

void AssertHasSequence(const YAML::Node &node, const std::string &name)
{
    AssertHasField(node, name);
    if (node[name].Type() != YAML::NodeType::Sequence)
        FieldError(name, "must be a sequence");
}

std::vector<YAML::Node> GetSequence(const YAML::Node &node, const std::string &name)
{
    AssertHasSequence(node, name);
    std::vector<YAML::Node> res{};
    for (const auto &i : node[name])
        res.push_back(i);
    return res;
}

} // namespace yaml
