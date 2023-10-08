//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <initializer_list>
#include <map>
#include <memory>
#include <optional>
#include <string>
#include <utility>
#include <vector>

class Json
{
  public:
    enum class Type
    {
        NULL_TYPE,
        STRING,
        BOOL,
        NUMBER,
        OBJECT,
        ARRAY,
    };

  private:
    Type m_type{};
    std::string m_strVal{};
    int64_t m_intVal{};

    // - Holding children via vector instead of map/unordered_map (because insertion order is needed to be preserved).
    //   Therefore add/remove operation is O(n), but performance is not a concern for JSONs. (Add operation is also
    //   O(n) since we compare to check the key is already present)
    // - NOTE: We're using Json type itself here which is incomplete herein. But C++17 allows std::vector, std::list,
    //   and std::forward_list to have incomplete types. For older versions we would need pointer etc.
    std::vector<std::pair<std::string, Json>> m_children{};

  private:
    typedef decltype(m_children.begin()) iterator;
    typedef decltype(const_cast<const std::vector<std::pair<std::string, Json>> &>(m_children).begin()) const_iterator;

  public:
    Json();
    /* no-explicit */ Json(std::nullptr_t v);
    /* no-explicit */ Json(std::string str);
    /* no-explicit */ Json(bool v);
    /* no-explicit */ Json(uint8_t v);
    /* no-explicit */ Json(int8_t v);
    /* no-explicit */ Json(uint16_t v);
    /* no-explicit */ Json(int16_t v);
    /* no-explicit */ Json(uint32_t v);
    /* no-explicit */ Json(int32_t v);
    /* no-explicit */ Json(int64_t v);
    /* no-explicit */ Json(uint64_t v) = delete;

    template <std::size_t N>
    inline /* no-explicit */ Json(const char (&v)[N]) : Json(std::string(v))
    {
    }

    template <typename T>
    Json(T) = delete;

  public:
    static Json Arr(std::initializer_list<Json> &&elements);
    static Json Arr(const std::vector<Json> &elements);
    static Json Arr(std::vector<Json> &&elements);
    static Json Obj(std::initializer_list<std::pair<std::string, Json>> &&elements);

  public:
    [[nodiscard]] Type type() const;
    [[nodiscard]] bool isNull() const;
    [[nodiscard]] bool isString() const;
    [[nodiscard]] bool isBool() const;
    [[nodiscard]] bool isNumber() const;
    [[nodiscard]] bool isObject() const;
    [[nodiscard]] bool isArray() const;
    [[nodiscard]] bool isPrimitive() const;
    [[nodiscard]] int itemCount() const;

  public:
    [[nodiscard]] std::string str() const;
    [[nodiscard]] int int32() const;
    [[nodiscard]] int64_t int64() const;
    [[nodiscard]] bool boolean() const;

  public:
    void push(Json element);
    void put(std::string key, Json value);

  public:
    [[nodiscard]] iterator begin();
    [[nodiscard]] const_iterator begin() const;
    [[nodiscard]] iterator end();
    [[nodiscard]] const_iterator end() const;

  public:
    [[nodiscard]] std::string dumpJson() const;
    [[nodiscard]] std::string dumpYaml() const;
};

Json ToJson(std::nullptr_t);
Json ToJson(bool v);
Json ToJson(const std::string &v);
Json ToJson(uint8_t v);
Json ToJson(int8_t v);
Json ToJson(uint16_t v);
Json ToJson(int16_t v);
Json ToJson(uint32_t v);
Json ToJson(int32_t v);
Json ToJson(int64_t v);

template <typename T>
inline Json ToJson(const std::optional<T> &v)
{
    return v.has_value() ? ToJson(*v) : Json{nullptr};
}

template <typename T>
inline Json ToJson(T *v)
{
    return v != nullptr ? ToJson(*v) : Json{nullptr};
}

template <typename T>
inline Json ToJson(const std::unique_ptr<T> &v)
{
    return v != nullptr ? ToJson(*v) : Json{nullptr};
}

template <typename T>
inline Json ToJson(const T *v)
{
    return v != nullptr ? ToJson(*v) : Json{nullptr};
}

template <typename T>
inline Json ToJson(const std::vector<T> &v)
{
    Json j = Json::Arr({});
    for (auto &item : v)
        j.push(ToJson(item));
    return j;
}

template <typename T>
Json ToJson(T) = delete;
