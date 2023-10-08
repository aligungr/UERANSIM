//
// This file is a part of UERANSIM project.
// Copyright (c) 2023 ALİ GÜNGÖR.
//
// https://github.com/aligungr/UERANSIM/
// See README, LICENSE, and CONTRIBUTING files for licensing details.
//

#pragma once

#include <string>
#include <vector>

namespace io
{

void CreateDirectory(const std::string &path);

bool Exists(const std::string &path);

std::string ReadAllText(const std::string &file);

void WriteAllText(const std::string &path, const std::string &content);

void RelaxPermissions(const std::string &path);

bool Remove(const std::string &path);

std::vector<std::string> GetEntries(const std::string &path);

std::vector<std::string> GetAllEntries(const std::string &path);

void PreOrderEntries(const std::string &root, std::vector<std::string> &visitor);

bool IsDirectory(const std::string &path);

bool IsRegularFile(const std::string &path);

std::string GetStem(const std::string &path);

void AppendPath(std::string &source, const std::string &target);

std::string GetIp4OfInterface(const std::string &ifName);

std::string GetIp6OfInterface(const std::string &ifName);

std::string TryResolveHost(const std::string& name);

} // namespace io
