#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <signal.h> 

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>

// Variables globales para los procesos hijos
pid_t pid_p2, pid_p3;

// Manejador para finalizar los procesos hijos
void terminar_proceso(int sig) {
    printf("Proceso hijo con PID %d finalizando.\n", getpid());
    exit(0);
}

int main() {
    int pipefd[2]; // Pipe para la comunicación entre procesos
    int numero;

    // Crear el pipe
    if (pipe(pipefd) == -1) {
        perror("Error al crear el pipe");
        exit(EXIT_FAILURE);
    }

    // Crear el proceso P2
    pid_p2 = fork();
    if (pid_p2 == -1) {
        perror("Error al crear el proceso P2");
        exit(EXIT_FAILURE);
    }

    if (pid_p2 == 0) {
        // Código del proceso hijo P2
        signal(SIGUSR1, terminar_proceso); // Manejador para la señal de terminación
        close(pipefd[1]); // Cerrar el extremo de escritura del pipe

        while (1) {
            if (read(pipefd[0], &numero, sizeof(int)) > 0) { // Leer del pipe
                if (numero == 0) {
                    break; // Salir del bucle si recibe un 0
                }
                if (numero % 2 == 0) {
                    printf("Número par %d recibido por el proceso P2 con pid %d\n", numero, getpid());
                }
            }
        }

        close(pipefd[0]); // Cerrar el extremo de lectura del pipe
        exit(0); // Finalizar el proceso hijo
    }

    // Crear el proceso P3
    pid_p3 = fork();
    if (pid_p3 == -1) {
        perror("Error al crear el proceso P3");
        exit(EXIT_FAILURE);
    }

    if (pid_p3 == 0) {
        // Código del proceso hijo P3
        signal(SIGUSR1, terminar_proceso); // Manejador para la señal de terminación
        close(pipefd[1]); // Cerrar el extremo de escritura del pipe

        while (1) {
            if (read(pipefd[0], &numero, sizeof(int)) > 0) { // Leer del pipe
                if (numero == 0) {
                    break; // Salir del bucle si recibe un 0
                }
                if (numero % 2 != 0) {
                    printf("Número impar %d recibido por el proceso P3 con pid %d\n", numero, getpid());
                }
            }
        }

        close(pipefd[0]); // Cerrar el extremo de lectura del pipe
        exit(0); // Finalizar el proceso hijo
    }
    else{

        // Código del proceso padre P1
        close(pipefd[0]); // Cerrar el extremo de lectura del pipe

        while (1) {
            printf("Introduce número: ");
            scanf("%d", &numero);
            write(pipefd[1], &numero, sizeof(int)); // Escribir en el pipe

            if (numero == 0) {
                // Enviar señales de terminación a los hijos
                printf("Mandando señal de terminación al proceso hijo P2 con pid %d\n", pid_p2);
                kill(pid_p2, SIGUSR1);

                printf("Mandando señal de terminación al proceso hijo P3 con pid %d\n", pid_p3);
                kill(pid_p3, SIGUSR1);

                break; // Salir del bucle y finalizar el proceso padre
            }
        }

        // Esperar a que los hijos terminen
        wait(NULL);
        wait(NULL);

        printf("Fin proceso padre con pid %d\n", getpid());
        close(pipefd[1]); // Cerrar el extremo de escritura del pipe
    }
    return 0;
}