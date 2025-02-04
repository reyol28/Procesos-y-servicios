#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

int segundos=0;

void manejador(int signal) {
    
    segundos+=5;
    printf("Han transcurrido %d segundos\n", segundos);
    alarm(5);
}

int main() {
    // Configurar el manejador de la señal SIGALRM
    signal(SIGALRM, manejador);
        

    //los primeros 5 seg se ponen en el main y luego ya se maneja con el manejador
    alarm(5);

    //Creo un bucle infinito para que se siga ejecutando y pongo pause() para esperar a que se ejecute una señal
    while (1) {
        pause();
    }

    return 0;
}