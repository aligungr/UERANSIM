//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#pragma once

#include <cstring>
#include <istream>
#include <memory>
#include <optional>
#include <ostream>
#include <stdexcept>
#include <string>
#include <unordered_map>
#include <utility>
#include <vector>

static void NameControl(const std::string &name)
{
    if (name.empty())
        throw std::runtime_error("Invalid OptionItem: name is too short");
    for (char c : name)
    {
        if (c >= '0' && c <= '9')
            continue;
        if (c >= 'a' && c <= 'z')
            continue;
        if (c >= 'A' && c <= 'Z')
            continue;
        if (c == '-')
            continue;
        throw std::runtime_error("Invalid OptionItem: name contains illegal character");
    }
}

namespace opt
{

struct OptionItem
{
    std::optional<char> shortName{};
    std::optional<std::string> longName{};
    std::optional<std::string> description{};
    std::optional<std::string> argument{};

    OptionItem(const std::optional<char> &shortName, std::optional<std::string> longName,
               std::optional<std::string> description, std::optional<std::string> argument)
        : shortName(shortName), longName(std::move(longName)), description(std::move(description)),
          argument(std::move(argument))
    {
        // WARN: Using this->... because of std::move
        if (!this->shortName.has_value() && !this->longName.has_value())
            throw std::runtime_error("shortName and longName both cannot be empty");

        if (this->longName.has_value())
            NameControl(*this->longName);
        if (this->argument.has_value())
            NameControl(*this->argument);
    }
};

struct OptionsDescription
{
    std::string projectName{};
    std::string version{};
    std::string appDescription{};
    std::string copyright{};
    std::string programName{};
    std::vector<OptionItem> items{};
    std::vector<std::string> usages{};
    std::vector<std::string> examples{};
    bool helpIfEmpty{};
    bool hideDefaultOptionsInUsage{};

    OptionsDescription(std::string projectName, std::string version, std::string appDescription, std::string copyright,
                       std::string programName, std::vector<std::string> usages, std::vector<std::string> examples,
                       bool helpIfEmpty, bool hideDefaultOptionsInUsage)
        : projectName(std::move(projectName)), version(std::move(version)), appDescription(std::move(appDescription)),
          copyright(std::move(copyright)), programName(std::move(programName)), usages(std::move(usages)),
          examples(std::move(examples)), helpIfEmpty(helpIfEmpty), hideDefaultOptionsInUsage(hideDefaultOptionsInUsage)
    {
    }
};

class IOptionsHandler
{
  public:
    virtual std::ostream &ostream(bool isError) = 0;
    virtual void status(int code) = 0;
};

class OptionsResult
{
  private:
    std::vector<std::string> m_positionalParams{};
    std::unordered_map<std::string, std::string> m_options{};
    IOptionsHandler *m_handler;
    OptionsDescription m_description;

  public:
    OptionsResult(int argc, char **argv, const OptionsDescription &desc, bool freeArgv, IOptionsHandler *handler);
    OptionsResult(const std::vector<std::string> &args, const OptionsDescription &desc, IOptionsHandler *handler);

  public:
    bool hasFlag(const OptionItem &item) const;
    bool hasFlag(const std::optional<char> &shortName, const std::optional<std::string>& longName) const;
    int positionalCount() const;
    int count() const;
    std::string getPositional(int index) const;
    std::string getOption(const OptionItem &item) const;
    std::string getOption(const std::optional<char> &shortName, const std::optional<std::string>& longName) const;

  public:
    void showHelp() const;
    void showVersion() const;
    void showError(const std::string &msg) const;
};

enum class ExpansionResult
{
    SUCCESS,
    ILLEGAL_CHARACTER,
    UNDEFINED_VARIABLE,
    CMD_SUBS_NOT_ALLOWED,
    SYNTAX_ERROR,
    UNSPECIFIED_ERROR
};

ExpansionResult PerformExpansion(const std::string &command, std::vector<std::string> &argv);

bool ReadLine(std::istream &istream, std::ostream &ostream, std::string &line, std::vector<std::string> &tokens,
              bool &isEof);

} // namespace opt
