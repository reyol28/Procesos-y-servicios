#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
 
void main()
{
    int fd[2];
    char buffer[30];
    pid_t varpid, pidhijo;

    time_t hora;
    char *fecha ;
    
    //creo el pipe
    pipe(fd);

    varpid=fork();
    if (varpid==0)
    {
        close(fd[1]); // Cierra el descriptor de escritura
        pidhijo=getpid();
        printf("Soy el proceso hijo con pid %d \n",pidhijo);
        read(fd[0], buffer, 10);
        printf("\t Fecha/hora: %s \n", buffer);
    }
    else
    {
        close(fd[0]); // Cierra el descriptor de lectura
        time(&hora);
        fecha = ctime(&hora) ;
        write(fd[1], fecha, 10);  
        wait(NULL);
    }
}