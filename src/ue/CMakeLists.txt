cmake_minimum_required(VERSION 3.17)

file(GLOB_RECURSE HDR_FILES *.hpp)
file(GLOB_RECURSE SRC_FILES *.cpp)

add_library(ue ${HDR_FILES} ${SRC_FILES})

target_compile_options(ue PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)

target_link_libraries(ue utils)
target_link_libraries(ue rrc)
target_link_libraries(ue crypt)
target_link_libraries(ue nas)
target_link_libraries(ue urs)
target_link_libraries(ue app)

