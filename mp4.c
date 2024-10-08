/*Dado el siguiente código de programa mp4.c

a) Dibuja un gráfico de la jerarquía de procesos que genera la ejecución de este código, suponiendo
que el pid del programa mp4 es el 1000 y los pids se generan de uno en uno en orden creciente.

(Padre)- PID:1000
|
|
-----(Hijo)- PID:1001

b) ¿Qué salida genera este código? ¿Podría producirse otra salida? Justifica la respuesta

Este codigo genera la salida CCC - AAA - CCC - BBB, podría producirse otra salida si al proceso padre le metemos un wait para que su hijo salga antes.

c) Modificar el código para que la salida por pantalla sea:
CCC
BBB
AAA    Para que el CCC solo salga una vez, necesito meterlo en el proceso padre o crear un hijo nuevo que ejecute el printf("CCC"); 
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
void main()
{
   if (fork()!=0)
   {
      wait(NULL);
      printf("AAA \n");
   } 
   else
   { 
      printf("CCC \n");
      printf("BBB \n");
   }
   exit(0);
}