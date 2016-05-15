#include <stdio.h>
#include <stdlib.h>
#include <winsock2.h>
#include <windows.h>
#include <string.h>
#include "client.h"
#include "parce.h"

#define MAXBUFLEN 20480

int main()
{
    WSADATA Data;
    SOCKADDR_IN recvSockAddr;
    SOCKET recvSocket;
    int status;
    char string[50];
    struct hostent * remoteHost;
    char * ip;
    const char * host_name = "api.vk.com";
    char buffer[MAXBUFLEN];
    memset(buffer,0,MAXBUFLEN);
    status = WSAStartup(MAKEWORD(2, 2), &Data);
    if(status != 0)
    {
        printf("ERROR: WSAStartup unsuccessful\r\n");
        return 0;
    }
    remoteHost = gethostbyname(host_name);
    ip = inet_ntoa(*(struct in_addr *)*remoteHost->h_addr_list);

    recvSockAddr = setSocAddr(ip);

    recvSocket = new_Socket();

    Connect(recvSockAddr,recvSocket);

    sendRequest(recvSocket,host_name);
    Recieve(recvSocket,buffer);


char * buff = strstr(buffer,"{");

parse(buff);

    return 0;
}
