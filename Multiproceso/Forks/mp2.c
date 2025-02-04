#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

void main()
{
    /* Crear un programa en C mp2.c que simule un árbol de procesos como el de la figura:
i. El proceso P1 es padre de P2 y abuelo de P3
ii. El proceso P2 es padre de P3 e hijo de P1
iii. El proceso P3 es hijo de P2 y nieto de P1
iv. El proceso P3 deberá mostrar por pantalla su pid y el de su padre, indicando que es el proceso P3
v. El proceso P2 deberá mostrar por pantalla su pid y el de su padre, indicando que es el proceso P2
vi. El proceso P1 deberá mostrar por pantalla su pid y el de su hijo, indicando que es el proceso P1
vii. Los procesos padres deberán esperar a que sus hijos terminen
*/
    pid_t varpid,varpid2,pidhijo,pidhijo2,pidpadre,pidnieto;
    varpid=fork();


    //Si quieres hacer que un proceso padre tenga 2 hijos, el segundo fork lo tienes que hacer donde solo lo ejecute en el proceso padre
    if(varpid==0)
    {
        

        
        varpid2=fork();//he llamada proceso padre a p1, proceso hijo a p2 (aunque es padre de p3) y proceso nieto a p3
        if(varpid2==0)
        {
            pidnieto=getpid();
            pidhijo2=getppid();
            printf("\nEstoy en el proceso nieto.\n");
            printf("Proceso nieto: %d \n Proceso hijo: %d",pidnieto,pidhijo2);
        }
        else
        {
            wait(NULL);
            pidhijo=getpid();
            pidpadre=getppid();
            printf("Proceso hijo: %d \n Proceso padre: %d",pidhijo,pidpadre);
            printf("\nEstoy en el proceso hijo\n");
        }
    }
    else
    {
        wait(NULL);
        printf("\nEstoy en el proceso padre\n");
    }
}