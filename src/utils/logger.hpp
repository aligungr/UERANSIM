//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include "nts.hpp"

#include <memory>
#include <string>
#include <vector>

#include <spdlog/fwd.h>

enum class Severity
{
    DEBUG,
    INFO,
    WARN,
    ERR,
    FATAL
};

class Logger
{
  private:
    spdlog::logger *logger;

  public:
    Logger(const std::string &name, const std::vector<std::shared_ptr<spdlog::sinks::sink>> &sinks);
    virtual ~Logger();

  private:
    void logImpl(Severity severity, const std::string &msg);

  public:
    template <typename... Args>
    inline void debug(const std::string &fmt, Args &&...args)
    {
        log(Severity::DEBUG, fmt, args...);
    }

    inline void debug(const std::string &fmt)
    {
        log(Severity::DEBUG, "%s", fmt.c_str());
    }

    template <typename... Args>
    inline void info(const std::string &fmt, Args &&...args)
    {
        log(Severity::INFO, fmt, args...);
    }

    inline void info(const std::string &fmt)
    {
        log(Severity::INFO, "%s", fmt.c_str());
    }

    template <typename... Args>
    inline void warn(const std::string &fmt, Args &&...args)
    {
        log(Severity::WARN, fmt, args...);
    }

    inline void warn(const std::string &fmt)
    {
        log(Severity::WARN, "%s", fmt.c_str());
    }

    template <typename... Args>
    inline void err(const std::string &fmt, Args &&...args)
    {
        log(Severity::ERR, fmt, args...);
    }

    inline void err(const std::string &fmt)
    {
        log(Severity::ERR, "%s", fmt.c_str());
    }

    template <typename... Args>
    inline void fatal(const std::string &fmt, Args &&...args)
    {
        log(Severity::FATAL, fmt, args...);
    }

    inline void fatal(const std::string &fmt)
    {
        log(Severity::FATAL, "%s", fmt.c_str());
    }

    template <typename... Args>
    inline void log(Severity severity, const std::string &fmt, Args &&...args)
    {
        int size = snprintf(nullptr, 0, fmt.c_str(), args...);
        std::string res;
        res.resize(size);
        snprintf(&res[0], size + 1, fmt.c_str(), args...);
        logImpl(severity, res);
    }

    void flush();

    /* Specific logs */
    void unhandledNts(const NtsMessage& msg);
};

class LogBase
{
  private:
    // std::shared_ptr<spdlog::sinks::sink> fileSink;
    std::shared_ptr<spdlog::sinks::sink> consoleSink;

  public:
    explicit LogBase(const std::string &filename);
    virtual ~LogBase();

    Logger *makeLogger(const std::string &loggerName, bool useConsole = true);
    std::unique_ptr<Logger> makeUniqueLogger(const std::string &loggerName, bool useConsole = true);
    std::shared_ptr<Logger> makeSharedLogger(const std::string &loggerName, bool useConsole = true);
};
