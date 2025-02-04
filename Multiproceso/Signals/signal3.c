#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <time.h>
#include <unistd.h>

#define OUTPUT_FILE "salidas.txt"

// Función para manejar la señal SIGINT
void handle_sigint(int sig) {
    // Abrir el fichero en modo de añadir
    FILE *file = fopen(OUTPUT_FILE, "a");
    if (file == NULL) {
        perror("Error al abrir el archivo");
        exit(EXIT_FAILURE);
    }

    // Obtener el tiempo actual
    time_t hora;
    char *fecha ;
    
    time(&hora);
    fecha = ctime(&hora) ;

    

    // Escribir en el fichero
    fprintf(file, "Señal SIGINT recibida a las %s\n", fecha);
    fclose(file);

    printf("Señal SIGINT capturada y guardada en %s\n", OUTPUT_FILE);
}

int main() {
    // Establecer el manejador para la señal SIGINT
    if (signal(SIGINT, handle_sigint) == SIG_ERR) {
        perror("Error al establecer el manejador de la señal");
        return EXIT_FAILURE;
    }

   
    printf("Programa en ejecución. Presiona CTRL+C para probar.\n");

    // Bucle infinito para mantener el programa corriendo
    while (1) {
        pause(); // Espera una señal
    }

    return 0;
}