#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <signal.h> 

void manejador(int signal)
{
    time_t hora;
    char *fecha ;

    pid_t pid=getpid();
    
    time(&hora);
    fecha = ctime(&hora) ;
    
    printf("Fin del proceso %d: %s",pid,fecha);
}

int main()
{
    signal(SIGINT,manejador);
    pid_t pid;
    time_t hora;
    char *fecha ;

    time(&hora);
    fecha = ctime(&hora) ;
    
    pid=getpid();

    printf("Inicio del proceso %d: %s",pid,fecha);
    
    while(1)
    {
        sleep(1);
    }
    return 0;
}