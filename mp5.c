/*Dado el siguiente código de programa mp5.c

a) Dibuja un gráfico de la jerarquía de procesos que genera la ejecución de este código, suponiendo
que el pid del programa mp5 es el 1000 y los pids se generan de uno en uno en orden creciente.

(P1)-PID:1000
|   \
|    \
|     \
(P2)    (P3)- PID:1002
  |
PID:1001

b) ¿Qué salida genera este código? ¿Podría producirse otra salida? Justifica la respuesta

Este código genera la salida AAA - CCC - AAA - CCC - AAA - BBB.
Podrían podrucirse otras salidas al hacer que el proceso padre se ejecute despues de sus dos hijos.

c) Añade el código necesario para que el orden de ejecución sea tal que los respectivos procesos
padre sean los últimos que se ejecuten*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
void main()
{
 pid_t pid1, pid2;
 printf("AAA \n");
 pid1 = fork();
 if (pid1==0)
 {
 printf("BBB \n");
 }
 else
 {
    wait(NULL);
    pid2 = fork();
    if(pid2==0)
    {
      
    }
    else{
      wait(NULL);
      printf("CCC \n");
    }
    
 }
 exit(0);
}