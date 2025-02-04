#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

void main()
{
    pid_t pid1,pidhijo,pidpadre;


    pid1=fork();
    if(pid1==0)
    {
        printf("Alberto");
        sleep(2);
    }
    else
    {
        wait(NULL);
        pidhijo=getpid();
        pidpadre=getppid();
    
        printf("PID padre: %d \nPID hijo: %d",pidpadre,pidhijo);
    }

/*Crear un programa en C mp1.c que realice las siguientes funciones:
i. El proceso padre deberá crear un proceso hijo que mostrará el nombre del alumno.
ii. El proceso padre deberá esperar a que su hijo termine, y mostrará por pantalla el pid del hijo y el
suyo propio*/
    
}