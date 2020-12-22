// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

#include <sstream>

class tun_config_error : public std::exception
{
    std::string msg;

public:
    tun_config_error(const std::string &message) : msg(message) {}

    tun_config_error(const std::initializer_list<std::string> &lines)
    {
        std::stringstream ss;
        for (auto &line : lines)
            ss << line << std::endl;
        msg = ss.str();
    }

    const char *what() const throw()
    {
        return msg.c_str();
    }
};

int tun_alloc(const char *if_prefix, char **allocated_name);

void configure_tun_interface(const char *tun_name, const char *ip_addr, bool configure_route);
