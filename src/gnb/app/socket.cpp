#include "socket.hpp"

#include <pthread.h>
#include <netinet/in.h>
#include <unistd.h>
#include <string>
#include <iostream>
#include <sys/un.h>

namespace nr::gnb
{

GNBSocket *gnb_socket;

void GNBSocket::startThread() 
{
    // set a seed for mutation
    srand(time(NULL));

    connfd = -1;
    pthread_t thread;
    pthread_create(&thread, NULL, start_socket, NULL);
    pthread_detach(thread);    
}

void GNBSocket::notify_response(std::string msg) 
{
    std::cout << msg << std::endl;
    msg.append("\n");
    if (connfd < 0) 
    {
        printf("No connection to statelearner\n");
        return;
    }
    
    if ((send(connfd, msg.c_str(), msg.length(), 0)) < 0) 
    {
        perror("Error in Send to Statelearner\n");
        assert(0);
        return;
    }
}

void* start_socket(void* arg)
{
    // unix socket
    // struct sockaddr_un addr;
    // int addrlen = sizeof(addr);
    // int fd = socket(AF_UNIX, SOCK_STREAM, 0);
    // if (fd < 0) 
    // {
    //     printf("Could not create socket: %s\n", strerror(errno));
    //     exit(1);
    // }
    // addr.sun_family = AF_UNIX;
    // strcpy(addr.sun_path, "./gNB.sock");
    // unlink(addr.sun_path);

    // internet socket
    struct sockaddr_in addr;
    int addrlen = sizeof(addr);
    int opt = 1;
    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd < 0) 
    {
        printf("Could not create socket: %s\n", strerror(errno));
        exit(1);
    }
    // internet socket
    if (setsockopt(fd, SOL_SOCKET, SO_REUSEADDR | SO_REUSEPORT, &opt, sizeof(opt))) 
    {
        printf("setsockopt failed: %s\n", strerror(errno));
        exit(1);
    }
    addr.sin_family = AF_INET;
    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_port = htons(56789);

    if (bind(fd, (struct sockaddr *)&addr, sizeof(addr)) < 0) 
    {
        printf("Could not bind socket: %s\n", strerror(errno));
        exit(1);
    }
    if (listen(fd, 1) < 0) 
    {
        printf("Could not listen on socket: %s\n", strerror(errno));
        exit(1);
    }
    printf("Waiting for connection\n");
    
    gnb_socket->connfd = accept(fd, (struct sockaddr*)&addr, (socklen_t*)&addrlen);
    if (gnb_socket->connfd < 0) 
    {
        printf("Could not accept connection: %s\n", strerror(errno));
        exit(1);
    }

    gnb_socket->notify_response("GNB connected");
    
    for(;;) 
    {
        char buffer[1024] = {0};
        int valread = read(gnb_socket->connfd, buffer, 1024); // read (block)
        if (valread < 0) 
        {
            printf("Could not read from socket: %s\n", strerror(errno));
            exit(1);
        }
        else if (valread == 0) 
        {
            printf("Connection closed\n");
            close(gnb_socket->connfd);
            gnb_socket->connfd = accept(fd, (struct sockaddr*)&addr, (socklen_t*)&addrlen);
            gnb_socket->notify_response("GNB connected");
            continue;
        }
        else
        {
            printf("Read %d bytes from socket\n", valread);
            std::string msg(buffer, valread);

            if (msg.compare("Hello\n") == 0) 
            {
                gnb_socket->notify_response("Hi");
                continue;
            }
        }
    }
    close(gnb_socket->connfd);
    close(fd);
    return NULL;
}

} // namespace nr::gnb
