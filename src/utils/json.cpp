//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "json.hpp"

#include <sstream>
#include <utility>

static std::string EscapeJson(const std::string &str)
{
    std::string output;
    output.reserve(str.length());

    for (char i : str)
    {
        switch (i)
        {
        case '"':
            output += "\\\"";
            break;
        case '/':
            output += "\\/";
            break;
        case '\b':
            output += "\\b";
            break;
        case '\f':
            output += "\\f";
            break;
        case '\n':
            output += "\\n";
            break;
        case '\r':
            output += "\\r";
            break;
        case '\t':
            output += "\\t";
            break;
        case '\\':
            output += "\\\\";
            break;
        default:
            output += i;
            break;
        }
    }
    return output;
}

static void AppendJson(const Json &json, std::stringstream &stream, int indentation)
{
    std::string indent(indentation, ' ');

    switch (json.type())
    {
    case Json::Type::NULL_TYPE:
        stream << "null";
        break;
    case Json::Type::STRING:
        stream << "\"" << EscapeJson(json.str()) << "\"";
        break;
    case Json::Type::NUMBER:
    case Json::Type::BOOL:
        stream << json.str();
        break;
    case Json::Type::OBJECT: {
        stream << "{\n";
        int index = 0;
        for (auto &item : json)
        {
            stream << indent << " " << item.first << ": ";
            AppendJson(item.second, stream, indentation + 1);
            if (index == json.itemCount() - 1)
                stream << "\n";
            else
                stream << ",\n";
            index++;
        }
        stream << indent << "}";
        break;
    }
    case Json::Type::ARRAY: {
        stream << "[\n";
        int index = 0;
        for (auto &item : json)
        {
            stream << indent << " ";
            AppendJson(item.second, stream, indentation + 1);
            if (index == json.itemCount() - 1)
                stream << "\n";
            else
                stream << ",\n";
            index++;
        }
        stream << indent << "]";
        break;
    }
    }
}

static void AppendYaml(const Json &json, std::stringstream &stream, int indentation, bool initialIndentation)
{
    std::string indent(indentation, ' ');

    switch (json.type())
    {
    case Json::Type::NULL_TYPE:
        stream << "null";
        break;
    case Json::Type::STRING:
    case Json::Type::NUMBER:
    case Json::Type::BOOL:
        stream << json.str(); // TODO: Escape YAML
        break;
    case Json::Type::OBJECT: {
        int index = 0;
        for (auto &item : json)
        {
            if (index > 0 || initialIndentation)
                stream << indent;
            stream << item.first << ": ";

            int initialPos = -1;
            if (item.second.isPrimitive())
                AppendYaml(item.second, stream, indentation + 1, false);
            else
            {
                stream << "\n";
                initialPos = static_cast<int>(stream.tellp());
                AppendYaml(item.second, stream, indentation + 1, true);
            }
            if (index != json.itemCount() - 1 && initialPos != stream.tellp())
                stream << "\n";
            index++;
        }
        break;
    }
    case Json::Type::ARRAY: {
        int index = 0;
        for (auto &item : json)
        {
            if (index > 0 || initialIndentation)
                stream << indent;
            stream << "- ";
            AppendYaml(item.second, stream, indentation + 2, false);
            if (index != json.itemCount() - 1)
                stream << "\n";
            index++;
        }
        break;
    }
    }
}

Json::Json() : m_type{Type::NULL_TYPE}
{
}

Json::Json(std::nullptr_t) : m_type{Type::NULL_TYPE}
{
}

Json::Json(std::string str) : m_type{Type::STRING}, m_strVal(std::move(str))
{
}

Json::Json(bool v) : m_type{Type::BOOL}, m_strVal(v ? "true" : "false"), m_intVal{v}
{
}

Json::Json(uint8_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{static_cast<int64_t>(v)}
{
}

Json::Json(int8_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{static_cast<int64_t>(v)}
{
}

Json::Json(uint16_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{static_cast<int64_t>(v)}
{
}

Json::Json(int16_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{static_cast<int64_t>(v)}
{
}

Json::Json(uint32_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{static_cast<int64_t>(v)}
{
}

Json::Json(int32_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{static_cast<int64_t>(v)}
{
}

Json::Json(int64_t v) : m_type{Type::NUMBER}, m_strVal(std::to_string(v)), m_intVal{v}
{
}

Json::Type Json::type() const
{
    return m_type;
}

bool Json::isString() const
{
    return m_type == Type::STRING;
}

bool Json::isBool() const
{
    return m_type == Type::BOOL;
}

bool Json::isObject() const
{
    return m_type == Type::OBJECT;
}

bool Json::isNumber() const
{
    return m_type == Type::NUMBER;
}

bool Json::isArray() const
{
    return m_type == Type::ARRAY;
}

bool Json::isNull() const
{
    return m_type == Type::NULL_TYPE;
}

bool Json::isPrimitive() const
{
    return !isObject() && !isArray();
}

std::string Json::str() const
{
    return m_strVal;
}

int Json::int32() const
{
    return static_cast<int>(m_intVal);
}

int64_t Json::int64() const
{
    return m_intVal;
}

bool Json::boolean() const
{
    return m_intVal;
}

int Json::itemCount() const
{
    return static_cast<int>(m_children.size());
}

Json::iterator Json::begin()
{
    return m_children.begin();
}

Json::const_iterator Json::begin() const
{
    return m_children.begin();
}

Json::iterator Json::end()
{
    return m_children.end();
}

Json::const_iterator Json::end() const
{
    return m_children.end();
}

std::string Json::dumpJson() const
{
    std::stringstream ss{};
    AppendJson(*this, ss, 0);
    return ss.str();
}

std::string Json::dumpYaml() const
{
    std::stringstream ss{};
    AppendYaml(*this, ss, 0, false);
    return ss.str();
}

Json Json::Arr(std::initializer_list<Json> &&elements)
{
    Json json{};
    json.m_type = Type::ARRAY;
    for (auto &item : elements)
        json.push(item);
    return json;
}

Json Json::Arr(const std::vector<Json> &elements)
{
    Json json{};
    json.m_type = Type::ARRAY;
    for (auto &item : elements)
        json.push(item);
    return json;
}

Json Json::Arr(std::vector<Json> &&elements)
{
    Json json{};
    json.m_type = Type::ARRAY;
    for (auto &item : elements)
        json.push(std::move(item));
    return json;
}

Json Json::Obj(std::initializer_list<std::pair<std::string, Json>> &&elements)
{
    Json json{};
    json.m_type = Type::OBJECT;
    for (auto &item : elements)
        json.put(item.first, item.second);
    return json;
}

void Json::push(Json element)
{
    if (m_type != Type::ARRAY)
        return;

    m_children.emplace_back(std::to_string(m_children.size()), std::move(element));
}

void Json::put(std::string key, Json value)
{
    if (m_type != Type::OBJECT)
        return;

    // Replace if the key is already present
    for (auto &item : m_children)
    {
        if (item.first == key)
        {
            item.second = std::move(value);
            return;
        }
    }

    m_children.emplace_back(std::move(key), std::move(value));
}

Json ToJson(std::nullptr_t)
{
    return nullptr;
}

Json ToJson(bool v)
{
    return v;
}

Json ToJson(const std::string &v)
{
    return v;
}

Json ToJson(uint8_t v)
{
    return v;
}

Json ToJson(int8_t v)
{
    return v;
}

Json ToJson(uint16_t v)
{
    return v;
}

Json ToJson(int16_t v)
{
    return v;
}

Json ToJson(uint32_t v)
{
    return v;
}

Json ToJson(int32_t v)
{
    return v;
}

Json ToJson(int64_t v)
{
    return v;
}
