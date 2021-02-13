//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
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

opt::OptionsResult::OptionsResult(int argc, char **argv, const opt::OptionsDescription &desc) : m_description{desc}
{
    argc--;
    argv++;

    if (argc == 0)
        help();

    for (int i = 0; i < argc; i++)
    {
        auto &arg = argv[i];

        if (strlen(arg) == 0)
            continue;
        if (strcmp(arg, "-") == 0)
            error("Empty option is not allowed");
        if (strcmp(arg, "--") == 0)
            error("Empty option is not allowed");

        int dashCount = 0;
        if (arg[0] == '-')
        {
            dashCount++;
            if (arg[1] == '-')
            {
                dashCount++;
                if (arg[2] == '-')
                    error("Invalid argument: " + std::string(arg));
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
                    version();
                if (strcmp(arg, "-h") == 0)
                    help();

                for (auto &item : desc.items)
                    if (strlen(arg) == 2 && item.shortName.has_value() && *item.shortName == arg[1])
                        parsingItem = &item;
            }
            else
            {
                if (strcmp(arg, "--version") == 0)
                    version();
                if (strcmp(arg, "--help") == 0)
                    help();

                for (auto &item : desc.items)
                    if (item.longName.has_value() && *item.longName == arg + 2)
                        parsingItem = &item;
            }

            if (parsingItem == nullptr)
                error("Option not recognized: " + std::string(arg));

            if (parsingItem->argument.has_value())
            {
                if (i == argc - 1 || strlen(argv[i + 1]) == 0 || argv[i + 1][0] == '-')
                    error("Argument <" + *parsingItem->argument + "> is expected for option: " + arg);

                if (parsingItem->shortName.has_value())
                {
                    std::string key = std::string(1, *parsingItem->shortName);
                    if (m_options.count(key))
                        error("Option " + std::string{arg} + " already used");
                    m_options[key] = argv[i + 1];
                    i++;
                }
                else
                {
                    std::string key = *parsingItem->longName;
                    if (m_options.count(key))
                        error("Option " + std::string{arg} + " already used");
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
                        error("Option " + std::string{arg} + " already used");
                    m_options[key] = {};
                }
                if (parsingItem->longName.has_value())
                {
                    std::string key = *parsingItem->longName;
                    if (m_options.count(key))
                        error("Option " + std::string{arg} + " already used");
                    m_options[key] = {};
                }
            }
        }
    }
}

bool opt::OptionsResult::hasFlag(const opt::OptionItem &item) const
{
    if (item.shortName.has_value() && m_options.count(std::string(1, *item.shortName)) > 0)
        return true;
    if (item.longName.has_value() && m_options.count(*item.longName) > 0)
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

void opt::OptionsResult::help() const
{
    std::time_t now = std::chrono::system_clock::to_time_t(std::chrono::system_clock::now());
    auto parts = std::localtime(&now);
    auto year = 1900 + parts->tm_year;

    std::cout << m_description.projectName << " " << m_description.version << " " << m_description.appDescription
              << " | Copyright (c) " << year << " " << m_description.copyright << std::endl;

    std::cout << "Usage:" << std::endl;
    for (auto &usage : m_description.usages)
        std::cout << "  " << m_description.programName << " " << usage << std::endl;
    std::cout << std::endl;

    std::cout << "Options:" << std::endl;
    std::vector<OptionItem> items = m_description.items;
    items.emplace_back('h', "help", "Show this help message and exit", std::nullopt);
    items.emplace_back('v', "version", "Show version information and exit", std::nullopt);

    size_t maxLengthOfItemName = 0;
    for (auto &item : items)
        maxLengthOfItemName = std::max(maxLengthOfItemName, ItemNameDisplay(item).size());

    for (auto &item : items)
    {
        std::string nameDisplay = ItemNameDisplay(item);
        std::cout << nameDisplay;
        std::cout << "  ";

        size_t delta = maxLengthOfItemName - nameDisplay.size();
        std::cout << std::string(delta, ' ');

        if (item.description.has_value())
        {
            std::string desc = *item.description;

            while (desc.size() > MAX_WIDTH)
            {
                std::string s = desc.substr(0, MAX_WIDTH);
                std::cout << s << std::endl << std::string(maxLengthOfItemName + 2, ' ');
                s = desc.substr(MAX_WIDTH);
                desc = std::move(s);
            }

            std::cout << desc;
        }
        std::cout << std::endl;
    }

    exit(1);
}

void opt::OptionsResult::version() const
{
    std::cout << m_description.version << std::endl;
    exit(1);
}

void opt::OptionsResult::error(const std::string &msg) const
{
    std::cerr << "ERROR: " << msg << std::endl;
    exit(1);
}

std::string opt::OptionsResult::getPositional(int index) const
{
    if (positionalCount() <= index)
        error("Missing positional parameter at index " + std::to_string(index));
    return m_positionalParams[index];
}

std::string opt::OptionsResult::getOption(const OptionItem &item) const
{
    if (item.shortName.has_value() && m_options.count(std::string(1, *item.shortName)) > 0)
        return m_options.at(std::string(1, *item.shortName));
    if (item.longName.has_value() && m_options.count(*item.longName))
        return m_options.at(*item.longName);
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

bool opt::ReadLine(std::istream &istream, std::ostream &ostream, std::string &line, std::vector<std::string> &tokens)
{
    std::cout << ">> ";
    std::string input{};

    while (true)
    {
        std::string ln{};
        std::getline(std::cin, ln);
        if (!std::cin)
            return false;
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
            std::cout << ".. ";
            input += "\n";
            continue;
        }
        return false;
    }
}
