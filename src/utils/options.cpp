//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "options.hpp"

#include <algorithm>
#include <chrono>
#include <cstdlib>
#include <cstring>
#include <ctime>
#include <iostream>
#include <sstream>

#include <wordexp.h>

static constexpr const size_t MAX_WIDTH = 90;

static char **ConvertArgs(const std::vector<std::string> &vec)
{
    char **res = static_cast<char **>(malloc(sizeof(char *) * vec.size()));
    for (size_t i = 0; i < vec.size(); i++)
        res[i] = strdup(vec[i].c_str());
    return res;
}

static std::string ItemNameDisplay(const opt::OptionItem &item)
{
    std::stringstream ss{};
    ss << "  ";
    if (item.shortName.has_value())
    {
        ss << "-" << *item.shortName;
        if (item.longName.has_value())
            ss << ", ";
    }
    if (item.longName.has_value())
        ss << "--" << *item.longName;
    if (item.argument.has_value())
        ss << " <" << *item.argument << ">";
    return ss.str();
}

class DefaultOptionsHandler : public opt::IOptionsHandler
{
  public:
    DefaultOptionsHandler() noexcept = default;

  public:
    std::ostream &ostream(bool isError) override
    {
        return isError ? std::cerr : std::cout;
    }

    void status(int code) override
    {
        exit(code);
    }
};

static DefaultOptionsHandler g_defaultOptsHandler{};

opt::OptionsResult::OptionsResult(const std::vector<std::string> &args, const opt::OptionsDescription &desc,
                                  IOptionsHandler *handler)
    : OptionsResult(static_cast<int>(args.size()), ConvertArgs(args), desc, true, handler)
{
}

opt::OptionsResult::OptionsResult(int argc, char **argv, const opt::OptionsDescription &desc, bool freeArgv,
                                  IOptionsHandler *handler)
    : m_handler{handler ? handler : &g_defaultOptsHandler}, m_description{desc}
{
    // Hide the first arguments
    argc--;
    argv++;

    if (argc <= 0 && desc.helpIfEmpty)
        showHelp();

    for (int i = 0; i < argc; i++)
    {
        auto &arg = argv[i];

        if (strlen(arg) == 0 || strcmp(arg, "-") == 0 || strcmp(arg, "--") == 0)
        {
            showError("Empty option is not allowed");
            break;
        }

        int dashCount = 0;
        if (arg[0] == '-')
        {
            dashCount++;
            if (arg[1] == '-')
            {
                dashCount++;
                if (arg[2] == '-')
                {
                    showError("Invalid argument: " + std::string(arg));
                    break;
                }
            }
        }

        if (dashCount == 0)
            m_positionalParams.emplace_back(arg);
        else
        {
            const OptionItem *parsingItem{};

            if (dashCount == 1)
            {
                if (strcmp(arg, "-v") == 0)
                {
                    showVersion();
                    break;
                }
                if (strcmp(arg, "-h") == 0)
                {
                    showHelp();
                    break;
                }

                for (auto &item : desc.items)
                    if (strlen(arg) == 2 && item.shortName.has_value() && *item.shortName == arg[1])
                        parsingItem = &item;
            }
            else
            {
                if (strcmp(arg, "--version") == 0)
                {
                    showVersion();
                    break;
                }
                if (strcmp(arg, "--help") == 0)
                {
                    showHelp();
                    break;
                }

                for (auto &item : desc.items)
                    if (item.longName.has_value() && *item.longName == arg + 2)
                        parsingItem = &item;
            }

            if (parsingItem == nullptr)
            {
                showError("Option not recognized: " + std::string(arg));
                break;
            }

            if (parsingItem->argument.has_value())
            {
                if (i == argc - 1 || strlen(argv[i + 1]) == 0 || argv[i + 1][0] == '-')
                {
                    showError("Argument <" + *parsingItem->argument + "> is expected for option: " + arg);
                    break;
                }

                if (parsingItem->shortName.has_value())
                {
                    std::string key = std::string(1, *parsingItem->shortName);
                    if (m_options.count(key))
                    {
                        showError("Option " + std::string{arg} + " already used");
                        break;
                    }
                    m_options[key] = argv[i + 1];
                    i++;
                }
                else
                {
                    std::string key = *parsingItem->longName;
                    if (m_options.count(key))
                    {
                        showError("Option " + std::string{arg} + " already used");
                        break;
                    }
                    m_options[key] = argv[i + 1];
                    i++;
                }
            }
            else
            {
                if (parsingItem->shortName.has_value())
                {
                    std::string key = std::string(1, *parsingItem->shortName);
                    if (m_options.count(key))
                    {
                        showError("Option " + std::string{arg} + " already used");
                        break;
                    }
                    m_options[key] = {};
                }
                if (parsingItem->longName.has_value())
                {
                    std::string key = *parsingItem->longName;
                    if (m_options.count(key))
                    {
                        showError("Option " + std::string{arg} + " already used");
                        break;
                    }
                    m_options[key] = {};
                }
            }
        }
    }

    // Restore first argument
    argc++;
    argv--;

    if (freeArgv)
    {
        for (int i = 0; i < argc; i++)
            free(argv[i]);
        free(argv);
    }
}

bool opt::OptionsResult::hasFlag(const opt::OptionItem &item) const
{
    return hasFlag(item.shortName, item.longName);
}

bool opt::OptionsResult::hasFlag(const std::optional<char> &shortName, const std::optional<std::string> &longName) const
{
    if (shortName.has_value() && m_options.count(std::string(1, *shortName)) > 0)
        return true;
    if (longName.has_value() && m_options.count(*longName) > 0)
        return true;
    return false;
}

int opt::OptionsResult::positionalCount() const
{
    return static_cast<int>(m_positionalParams.size());
}

int opt::OptionsResult::count() const
{
    return static_cast<int>(m_options.size());
}

void opt::OptionsResult::showHelp() const
{
    auto &ostream = m_handler->ostream(false);

    std::time_t now = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
    auto parts = std::localtime(&now);
    auto year = 1900 + parts->tm_year;

    if (!m_description.projectName.empty())
        ostream << m_description.projectName << " ";
    if (!m_description.version.empty())
        ostream << m_description.version << " ";
    if (!m_description.projectName.empty() || !m_description.version.empty())
        ostream << "| ";
    if (!m_description.appDescription.empty())
        ostream << m_description.appDescription << " ";
    if (!m_description.copyright.empty())
    {
        if (!m_description.appDescription.empty())
            ostream << "| ";
        ostream << "Copyright (c) " << year << " " << m_description.copyright;
    }

    ostream << std::endl;

    if (!m_description.usages.empty())
    {
        ostream << "Usage:" << std::endl;
        for (auto &usage : m_description.usages)
            ostream << "  " << m_description.programName << " " << usage << std::endl;
        ostream << std::endl;
    }

    if (!m_description.examples.empty())
    {
        ostream << (m_description.examples.size() > 1 ? "Examples:" : "Example:") << std::endl;
        for (auto &example : m_description.examples)
            ostream << "  " << m_description.programName << " " << example << std::endl;
        ostream << std::endl;
    }

    std::vector<OptionItem> items = m_description.items;
    if (!m_description.hideDefaultOptionsInUsage)
    {
        items.emplace_back('h', "help", "Show this help message and exit", std::nullopt);
        items.emplace_back('v', "version", "Show version information and exit", std::nullopt);
    }

    if (!items.empty())
        ostream << "Options:" << std::endl;

    size_t maxLengthOfItemName = 0;
    for (auto &item : items)
        maxLengthOfItemName = std::max(maxLengthOfItemName, ItemNameDisplay(item).size());

    for (auto &item : items)
    {
        std::string nameDisplay = ItemNameDisplay(item);
        ostream << nameDisplay;
        ostream << "  ";

        size_t delta = maxLengthOfItemName - nameDisplay.size();
        ostream << std::string(delta, ' ');

        if (item.description.has_value())
        {
            std::string desc = *item.description;

            while (desc.size() > MAX_WIDTH)
            {
                std::string s = desc.substr(0, MAX_WIDTH);
                ostream << s << std::endl << std::string(maxLengthOfItemName + 2, ' ');
                s = desc.substr(MAX_WIDTH);
                desc = std::move(s);
            }

            ostream << desc;
        }
        ostream << std::endl;
    }

    m_handler->status(1);
}

void opt::OptionsResult::showVersion() const
{
    m_handler->ostream(false) << m_description.version << std::endl;
    m_handler->status(1);
}

void opt::OptionsResult::showError(const std::string &msg) const
{
    m_handler->ostream(true) << "ERROR: " << msg << std::endl;
    m_handler->status(1);
}

std::string opt::OptionsResult::getPositional(int index) const
{
    if (positionalCount() <= index)
        return {};
    return m_positionalParams[index];
}

std::string opt::OptionsResult::getOption(const OptionItem &item) const
{
    return getOption(item.shortName, item.longName);
}

std::string opt::OptionsResult::getOption(const std::optional<char> &shortName,
                                          const std::optional<std::string> &longName) const
{
    if (shortName.has_value() && m_options.count(std::string(1, *shortName)) > 0)
        return m_options.at(std::string(1, *shortName));
    if (longName.has_value() && m_options.count(*longName))
        return m_options.at(*longName);
    return {};
}

opt::ExpansionResult opt::PerformExpansion(const std::string &command, std::vector<std::string> &argv)
{
    wordexp_t p = {};

    int ret = wordexp(command.c_str(), &p, WRDE_NOCMD | WRDE_UNDEF);
    if (ret == 0)
    {
        for (size_t i = 0; i < p.we_wordc; i++)
            argv.emplace_back(p.we_wordv[i]);
        wordfree(&p);
        return ExpansionResult::SUCCESS;
    }

    wordfree(&p);

    if (ret == WRDE_BADCHAR)
        return ExpansionResult::ILLEGAL_CHARACTER;
    if (ret == WRDE_BADVAL)
        return ExpansionResult::UNDEFINED_VARIABLE;
    if (ret == WRDE_CMDSUB)
        return ExpansionResult::CMD_SUBS_NOT_ALLOWED;
    if (ret == WRDE_SYNTAX)
        return ExpansionResult::SYNTAX_ERROR;
    return ExpansionResult::UNSPECIFIED_ERROR;
}

bool opt::ReadLine(std::istream &istream, std::ostream &ostream, std::string &line, std::vector<std::string> &tokens,
                   bool &isEof)
{
    ostream << "$ ";
    std::string input{};
    isEof = false;

    while (true)
    {
        std::string ln{};
        std::getline(istream, ln);
        if (!istream)
        {
            isEof = true;
            return false;
        }
        input += ln;

        std::vector<std::string> vec{};
        auto exp = PerformExpansion(input, vec);
        if (exp == ExpansionResult::SUCCESS)
        {
            line = input;
            tokens = vec;
            return true;
        }
        if (exp == ExpansionResult::SYNTAX_ERROR)
        {
            ostream << "> ";
            input += "\n";
            continue;
        }
        return false;
    }
}
