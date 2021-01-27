cmake_minimum_required(VERSION 3.17)

file(GLOB HDR_FILES *.hpp)
file(GLOB SRC_FILES *.cpp)

add_library(ue ${HDR_FILES} ${SRC_FILES})

target_compile_options(ue PRIVATE -Wall -Wextra -pedantic -Wno-unused-parameter)
target_include_directories(ue PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})

target_link_libraries(ue utils)
target_link_libraries(ue logger)
target_link_libraries(ue rrc)
target_link_libraries(ue crypt)
target_link_libraries(ue nas)
target_link_libraries(ue urs)
target_link_libraries(ue app)

