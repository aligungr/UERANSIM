cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE YAML_HDR_FILES *.h)
file(GLOB_RECURSE YAML_SRC_FILES *.cpp)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(utils ${YAML_HDR_FILES} ${YAML_SRC_FILES} ${HDR_FILES} ${SRC_FILES})

target_link_libraries(utils pthread)
target_link_libraries(utils ext)

target_compile_options(utils PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter -Wno-format-security)
target_compile_options(utils PUBLIC -Wno-format-security)
