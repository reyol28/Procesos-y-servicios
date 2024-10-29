#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
void main()
{
    pid_t varpid,varpid2,varpid3,pid1,pid2,pid3,pid4,ppid1,ppid2,ppid3,ppid4;
    int suma1,suma2,suma3,suma4;
    varpid=fork();
    if(varpid==0)//HIJO 1
    {
        varpid2 = fork();
        if (varpid2 == 0) // HIJO 2
        {
            varpid3 = fork();
            if (varpid3 == 0) // HIJO 3
            {
                pid4 = getpid(); 
                ppid4=getppid();
                suma4=pid4+ppid4;
                printf("PID4: %d, PID PADRE4: %d, SUMA PIDS: %d\n", pid4,ppid4,suma4); 
            }
            else // HIJO 2 PADRE DE HIJO 3
            {
                wait(NULL); // Espera a que el HIJO 3 termine
                pid3 = getpid(); 
                ppid3=getppid();
                suma3=pid3+ppid3;
                printf("PID3: %d, PID PADRE3: %d, SUMA PIDS: %d\n", pid3,ppid3,suma3); 
            }
        
        }
        else
        {
            pid2=getpid();
            ppid2=getppid();
            suma2=pid2+ppid2;
            printf("PID2: %d, hijo de: %d, suma ambos pids: %d\n",pid2,ppid2,suma2);
        }
    }
    else // PADRE
    {
        wait(NULL); // Espera a que el HIJO 1 termine
        pid1 = getpid(); 
        ppid1=getppid();
        suma1=pid1+ppid1;
        printf("PID1: %d, PID PADRE1: %d, SUMA PIDS: %d\n", pid1,ppid1,suma1); 
    }        
    exit(0);
}