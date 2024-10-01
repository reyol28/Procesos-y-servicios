/*Crear un programa en C mp3.c que simule un árbol de procesos como el de la figura:
i. El proceso P1 es padre de P2 y de P3
ii. El proceso P2 deberá dormir 10 segundos y mostrar el mensaje “Despierto” al finalizar
iii. El proceso P3 deberá mostrar por pantalla su pid y el de su padre, indicando que es el proceso P3
iv. El/los proceso/s padre/s deberá/n esperar a que sus hijo/s termine/n*/

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

void main()
{
    pid_t varpid,p1,p2,p3;

    varpid=fork();
    if(varpid==0)
    {
        printf("\nSoy P2\n");
        sleep(10);
        printf("\nDespierto\n");
    }
    else
    {
        wait(NULL);
        varpid=fork();
        if(varpid==0)
        {
            p3=getpid();
            p1=getppid();
            printf("\nSoy P3, mid pid es %d, y el pid de mi padre es %d.\n",p3,p1);
        }
        else
        {
            wait(NULL);
            printf("\nSoy P1\n");
        }

    }
}