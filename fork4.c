#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    pid_t varpid, varpid2, varpid3, varpid4, pid1, pid2, pid3, pid4, pid5;
    
    int acumulado;
    pid1 = getpid();  
    acumulado = pid1; 
    printf("PID1: %d, Acumulado inicial: %d\n", pid1, acumulado);
    varpid = fork();
    if (varpid == 0) // HIJO 1 (P2)
    { 
        varpid2 = fork();
        if (varpid2 == 0) // HIJO 2 (P5)
        { 
            pid5 = getpid();
            
            if (pid5 % 2 == 0) {
                acumulado += 10; // Suma 10 si es par
            } else {
                acumulado -= 100; // Resta 100 si es impar
            }
            printf("PID5: %d, Acumulado: %d\n", pid5, acumulado);
        } else { // HIJO 1 PADRE DE HIJO 2 (P2)
            wait(NULL);
            pid2 = getpid();
            
            if (pid2 % 2 == 0) 
            {
                acumulado += 10; 
            } 
            else 
            {
                acumulado -= 100; 
            }
            printf("PID2: %d, Acumulado: %d\n", pid2, acumulado);
        }
    } 
    else 
    { // PADRE (P1)
        wait(NULL);
        varpid3 = fork();
        if (varpid3 == 0) // HIJO 3 (P3)
        { 
            varpid4 = fork();
            if (varpid4 == 0) // HIJO 4 (P4)
            { 
                pid4 = getpid();
                
                if (pid4 % 2 == 0) 
                {
                    acumulado += 10; 
                } else 
                {
                    acumulado -= 100; 
                }
                printf("PID4: %d, Acumulado: %d\n", pid4, acumulado);
            } 
            else // Hijo 3 PADRE DE HIJO 4 (P3)
            { 
                wait(NULL);
                pid3 = getpid();
                 
                if (pid3 % 2 == 0) 
                {
                    acumulado += 10; 
                } 
                else 
                {
                    acumulado -= 100; 
                }
                printf("PID3: %d, Acumulado: %d\n", pid3, acumulado);
            }
        }
         else// (P1)
        {
            wait(NULL);
        }

    exit(0);
    }
}