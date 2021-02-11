//
// This file is a part of UERANSIM open source project.
// Copyright (c) 2021 ALİ GÜNGÖR.
//
// The software and all associated files are licensed under GPL-3.0
// and subject to the terms and conditions defined in LICENSE file.
//

#include <string>

namespace io
{

void CreateDirectory(const std::string &path);

bool Exists(const std::string &path);

std::string ReadAllText(const std::string &file);

void WriteAllText(const std::string &path, const std::string &content);

void RelaxPermissions(const std::string &path);

bool Remove(const std::string &path);

} // namespace io
