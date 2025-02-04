#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <time.h>

int numRepeticiones=0;
int durAlarma=0;

void manejador(int signal) {
    time_t hora;
    char *fecha ;
    
    time(&hora);
    fecha = ctime(&hora);
    
    printf("Señal de alarma recibida a las:%s \n", fecha);
    numRepeticiones--;
    if(numRepeticiones>0)
    {
        alarm(durAlarma);
    }
    else
    {
        printf("Alarma desactivada\n");
    }
}

int main() {
    
    // Configurar el manejador de la señal SIGALRM
    signal(SIGALRM, manejador);
        
    printf("¿Cuántas veces quieres que se repita la alarma?\n");
    scanf("%d",&numRepeticiones);
    printf("¿Cuántos segundos quieres que dure la alarma?\n");
    scanf("%d",&durAlarma);
    alarm(durAlarma);
    //Creo un bucle infinito para que se siga ejecutando y pongo pause() para esperar a que se ejecute una señal
    while (numRepeticiones>0) {
        pause();
    }

    return 0;
}