#pragma once

#include <exception>
#include <stdexcept>

class SctpError : public std::runtime_error
{
  public:
    explicit SctpError(const std::string &what) : std::runtime_error(what)
    {
    }
    explicit SctpError(const char *what) : std::runtime_error(what)
    {
    }
};