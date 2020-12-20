// Copyright (c) 2020 ALİ GÜNGÖR (aligng1620@gmail.com)
// This software and all associated files are licensed under GPL-3.0.

#pragma once

int tun_alloc(const char *if_prefix, char **allocated_name);

void configure_tun_interface(const char *tun_name, const char *ip_addr);