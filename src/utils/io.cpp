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
    if (!std::filesystem::exists(path))
    {
        if (!std::filesystem::is_directory(path))
            throw std::runtime_error("Required path '" + path + "' exists but not a directory.");

        std::filesystem::create_directory(path);
        std::filesystem::permissions(path, std::filesystem::perms::all);
    }
}

bool Exists(const std::string &path)
{
    return std::filesystem::exists(path);
}

} // namespace io
