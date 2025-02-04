#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
void main()
{
    pid_t varpid,varpid2,varpid3,varpid4,varpid5,pid1,pid2,pid3,pid4,ppid1,ppid2,ppid3,ppid4,pid5,ppid5,pid6,ppid6,abuelo;
    varpid=fork();
    if(varpid==0)//HIJO 1 (P2)
    {
        varpid2 = fork();
        if (varpid2 == 0) // HIJO 2 (P3)
        {
            varpid3 = fork();
            if (varpid3 == 0) // HIJO 3 (P5)
            {
                pid5 = getpid(); 
                ppid5=getppid();
                abuelo=obtener_pid_abuelo(ppid5);
                printf("PID5: %d, PID PADRE5: %d,PID ABUELO 5:%d\n", pid5,ppid5,abuelo);
            }
            else // HIJO 2 PADRE DE HIJO 3 (P3)
            {
                wait(NULL); // Espera a que el HIJO 3 termine
                pid3 = getpid(); 
                ppid3=getppid();
                abuelo=obtener_pid_abuelo(ppid3);
                printf("PID3: %d, PID PADRE3: %d,PID ABUELO 3:%d\n", pid3,ppid3,abuelo);
            }
        
        }
        else//HIJO 1 PADRE DE HIJO 2 (P2)
        {
            wait(NULL);
            varpid4=fork();
            if(varpid4==0)//HIJO 4 (P4)
            {
                varpid5=fork();
                if (varpid5==0)//HIJO 6 (P6)
                {
                    pid6=getpid();
                    ppid6=getppid();
                    abuelo=obtener_pid_abuelo(ppid6);
                    printf("PID6: %d, PID PADRE6: %d,PID ABUELO 6:%d\n", pid6,ppid6,abuelo);
                }
                else//HIJO 4 PADRE DE HIJO 6 (P4)
                {
                    wait(NULL);
                    pid4=getpid();
                    ppid4=getppid();
                    abuelo=obtener_pid_abuelo(ppid4);
                    printf("PID4: %d, PID PADRE4: %d,PID ABUELO 4:%d\n", pid4,ppid4,abuelo);
                }
            }
            else// HIJO 1 PADRE DE HIJO 4 (P2)
            {
                wait(NULL);
                pid2=getpid();
                ppid2=getppid();
                abuelo=obtener_pid_abuelo(ppid2);
                printf("PID2: %d, PID PADRE2: %d,PID ABUELO 2:%d\n", pid2,ppid2,abuelo);
            }
        }
    }
    else // PADRE (P1)
    {
        wait(NULL); // Espera a que el HIJO 1 termine
        pid1 = getpid(); 
        ppid1=getppid();
        abuelo=obtener_pid_abuelo(ppid1);
        printf("PID1: %d, PID PADRE1: %d,PID ABUELO 1:%d\n", pid1,ppid1,abuelo); 
    }        
    exit(0);
}
//PARA OBTENER EL PID DEL ABUELO HE TENIDO QUE BUSCAR INFORMACIÓN DE CÓMO SACARLO
// Función para obtener el PID del abuelo
pid_t obtener_pid_abuelo(pid_t ppid) {
    char path[40];
    snprintf(path, 40, "/proc/%d/stat", ppid);
    
    FILE *stat_file = fopen(path, "r");
    if (stat_file == NULL) {
        perror("Error al abrir el archivo /proc");
        return -1; // Retorna -1 si hay error
    }

    pid_t pid_padre, pid_abuelo;
    fscanf(stat_file, "%d %*s %*c %d", &pid_padre, &pid_abuelo);
    fclose(stat_file);
    
    return pid_abuelo; // Retorna el PID del abuelo
}