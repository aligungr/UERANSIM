//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include "io.hpp"

#include <filesystem>
#include <fstream>
#include <ostream>

namespace io
{

std::string ReadAllText(const std::string &file)
{
    std::ifstream ifs{};
    ifs.exceptions(std::ifstream::failbit | std::ifstream::badbit);
    ifs.open(file);
    std::string content((std::istreambuf_iterator<char>(ifs)), (std::istreambuf_iterator<char>()));
    return content;
}

void CreateDirectory(const std::string &path)
{
    if (!Exists(path))
    {
        std::filesystem::create_directory(path);
        RelaxPermissions(path);
    }
}

bool Exists(const std::string &path)
{
    return std::filesystem::exists(path);
}

void WriteAllText(const std::string &path, const std::string &content)
{
    std::ofstream ofs{};
    ofs.exceptions(std::ofstream::failbit | std::ofstream::badbit);
    ofs.open(path);
    ofs << content;
    ofs.close();
    RelaxPermissions(path);
}

void RelaxPermissions(const std::string &path)
{
    std::filesystem::permissions(path, std::filesystem::perms::all);
}

bool Remove(const std::string &path)
{
    std::error_code err{};
    std::filesystem::remove_all(path, err);
    return (bool)err;
}

} // namespace io