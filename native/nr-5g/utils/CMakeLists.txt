cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(utils ${HDR_FILES} ${SRC_FILES})

target_link_libraries(utils pthread)
target_include_directories(utils PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})
target_compile_options(utils PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)
