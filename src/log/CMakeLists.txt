cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE SPD_LOG_HDR_FILES *.h)
file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(logger ${SPD_LOG_HDR_FILES} ${HDR_FILES} ${SRC_FILES})
target_include_directories(logger PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

target_compile_options(logger PUBLIC -Wno-format-security)
