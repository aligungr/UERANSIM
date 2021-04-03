cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE HDR_FILES *.hpp)
file(GLOB_RECURSE SRC_FILES *.cpp)

add_library(sas ${HDR_FILES} ${SRC_FILES})

target_compile_options(sas PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(sas utils)
target_link_libraries(sas udp)
