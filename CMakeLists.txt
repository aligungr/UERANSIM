cmake_minimum_required(VERSION 3.17)
project(ueransim)

set(CMAKE_CXX_STANDARD 17)

include_directories(src)

#################### SUB DIRECTORIES ####################

add_subdirectory(src/ext)
add_subdirectory(src/asn/asn1c)
add_subdirectory(src/asn/rrc)
add_subdirectory(src/asn/ngap)
add_subdirectory(src/asn/utils)
add_subdirectory(src/utils)
add_subdirectory(src/sctp)
add_subdirectory(src/gnb)
add_subdirectory(src/rlc)
add_subdirectory(src/nas)
add_subdirectory(src/rrc)
add_subdirectory(src/gtp)
add_subdirectory(src/udp)
add_subdirectory(src/app)
add_subdirectory(src/urs)
add_subdirectory(src/crypt)
add_subdirectory(src/ue)

#################### GNB EXECUTABLE ####################

add_executable(nr-gnb src/gnb.cpp)

target_link_libraries(nr-gnb pthread)

target_compile_options(nr-gnb PRIVATE -Wall -Wextra -pedantic)

target_link_libraries(nr-gnb asn-rrc)
target_link_libraries(nr-gnb asn-ngap)
target_link_libraries(nr-gnb utils)
target_link_libraries(nr-gnb gnb)
target_link_libraries(nr-gnb rlc)
target_link_libraries(nr-gnb rrc)
target_link_libraries(nr-gnb gtp)
target_link_libraries(nr-gnb udp)
target_link_libraries(nr-gnb app)
target_link_libraries(nr-gnb urs)
target_link_libraries(nr-gnb crypt)

#################### UE EXECUTABLE ####################

add_executable(nr-ue src/ue.cpp)
target_link_libraries(nr-ue pthread)
target_compile_options(nr-ue PRIVATE -Wall -Wextra -pedantic)

target_link_libraries(nr-ue utils)
target_link_libraries(nr-ue rlc)
target_link_libraries(nr-ue nas)
target_link_libraries(nr-ue rrc)
target_link_libraries(nr-ue udp)
target_link_libraries(nr-ue app)
target_link_libraries(nr-ue urs)
target_link_libraries(nr-ue crypt)
target_link_libraries(nr-ue ue)

###################### IF BINDER ######################
add_library(devbnd SHARED src/binder.cpp)
target_compile_options(devbnd PRIVATE -D_GNU_SOURCE -Wall -Wextra)
target_link_options(devbnd PRIVATE -nostartfiles)
target_link_libraries(devbnd dl)

#################### CLI EXECUTABLE ####################
add_executable(nr-cli src/cli.cpp)
target_link_libraries(nr-cli pthread)
target_compile_options(nr-cli PRIVATE -Wall -Wextra -pedantic)

target_link_libraries(nr-cli app)
target_link_libraries(nr-cli udp)
target_link_libraries(nr-cli utils)
