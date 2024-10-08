#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
void main()
{
    pid_t varpid,varpid2,varpid3,pid1,pid2,pid3,pid4,spid1;
    int contador=0;
    varpid=fork();
    if(varpid==0)//HIJO 1
    {
        pid2=getpid();
        spid1=getppid();
        printf("PID2: %d, hijo de: %d\n",pid2,spid1);
    }
    else // PADRE
    {
        wait(NULL); // Espera a que el HIJO 1 termine

        varpid2 = fork();
        if (varpid2 == 0) // HIJO 2
        {
            varpid3 = fork();
            if (varpid3 == 0) // HIJO 3
            {
                pid4 = getpid();
                printf("PID4: %d\n", pid4);
            }
            else // HIJO 2 PADRE DE HIJO 3
            {
                wait(NULL); // Espera a que el HIJO 3 termine
                pid3 = getpid();
                printf("PID3: %d\n", pid3);
            }
        }
        else // PROCESO PADRE
        {
            wait(NULL); // Espera a que el HIJO 2 termine
            pid1 = getpid(); 
            printf("PID1: %d\n", pid1); 
        }
    }

  
   exit(0);
}