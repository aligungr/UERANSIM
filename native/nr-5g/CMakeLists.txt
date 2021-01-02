cmake_minimum_required(VERSION 3.17)
project(ueransim)

set(CMAKE_CXX_STANDARD 17)

add_subdirectory(asn/asn1c)
add_subdirectory(asn/rrc)
add_subdirectory(asn/ngap)
add_subdirectory(utils)
add_subdirectory(sctp)
add_subdirectory(gnb)
add_subdirectory(rlc)

add_executable(ueransim main.cpp)

target_link_libraries(ueransim pthread)

target_link_libraries(ueransim asn-rrc)
target_link_libraries(ueransim asn-ngap)
target_link_libraries(ueransim utils)
target_link_libraries(ueransim gnb)
target_link_libraries(ueransim rlc)

target_compile_options(ueransim PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)
