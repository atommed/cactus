#include <winsock2.h>
#include <windows.h>
#pragma comment(lib, "ws2_32.lib")

SOCKET new_Socket();
void Connect (SOCKADDR_IN recvSockAddr, SOCKET recvSocket);
void sendRequest(SOCKET recvSocket,const char * host_name);
void Recieve(SOCKET recvSocket, char * buffer );
SOCKADDR_IN setSocAddr(char * ip);
