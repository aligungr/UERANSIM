//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#include "logger.hpp"

#include <vector>

#include <spdlog/sinks/basic_file_sink.h>
#include <spdlog/sinks/stdout_color_sinks.h>

Logger::Logger(const std::string &name, const std::vector<std::shared_ptr<spdlog::sinks::sink>> &sinks)
{
    logger = new spdlog::logger(name, std::begin(sinks), std::end(sinks));

    logger->set_level(spdlog::level::debug);
    logger->flush_on(spdlog::level::warn);
}

Logger::~Logger()
{
    delete logger;
}

void Logger::logImpl(Severity severity, const std::string &msg)
{
    switch (severity)
    {
    case Severity::DEBUG:
        logger->debug(msg);
        break;
    case Severity::INFO:
        logger->info(msg);
        break;
    case Severity::WARN:
        logger->warn(msg);
        break;
    case Severity::ERR:
        logger->error(msg);
        break;
    case Severity::FATAL:
        logger->critical(msg);
        logger->flush();
        std::terminate();
        break;
    }
}

void Logger::flush()
{
    logger->flush();
}

void Logger::unhandledNts(const NtsMessage& msg)
{
    err("Unhandled NTS message received with type %d", (int)msg.msgType);
}

LogBase::LogBase(const std::string &filename)
{
    // fileSink = std::make_shared<spdlog::sinks::basic_file_sink_mt>(filename);
    // fileSink->set_level(spdlog::level::trace);

    consoleSink = std::make_shared<spdlog::sinks::stdout_color_sink_mt>();
    consoleSink->set_level(spdlog::level::trace); // todo: set to 'trace'.
}

LogBase::~LogBase() = default;

Logger *LogBase::makeLogger(const std::string &loggerName, bool useConsole)
{
    std::vector<std::shared_ptr<spdlog::sinks::sink>> v;
    // v.push_back(fileSink);
    if (useConsole)
        v.push_back(consoleSink);

    return new Logger(loggerName, v);
}

std::unique_ptr<Logger> LogBase::makeUniqueLogger(const std::string &loggerName, bool useConsole)
{
    std::vector<std::shared_ptr<spdlog::sinks::sink>> v;
    // v.push_back(fileSink);
    if (useConsole)
        v.push_back(consoleSink);

    return std::make_unique<Logger>(loggerName, v);
}

std::shared_ptr<Logger> LogBase::makeSharedLogger(const std::string &loggerName, bool useConsole)
{
    std::vector<std::shared_ptr<spdlog::sinks::sink>> v;
    // v.push_back(fileSink);
    if (useConsole)
        v.push_back(consoleSink);

    return std::make_shared<Logger>(loggerName, v);
}
