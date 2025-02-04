#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

int main() {
    int fd[2];
    pid_t pid;
    char buffer[50];
    int sum = 0;

    // Crear el pipe
    if (pipe(fd) == -1) {
        printf("Error al crear el pipe");
        return 1;
    }

    // Crear el proceso hijo
    pid = fork();

    if (pid == -1) 
    {
        printf("Error al crear el proceso hijo");
        return 1;
    } 
    if (pid==0) {  // HIJO
        
        close(fd[1]);

        while (1) {
            // Leer datos desde el pipe
            read(fd[0], buffer, sizeof(buffer));

            //comparo el buffer con el símbolo +, cuando es igual sale del bucle y 
            if (strcmp(buffer, "+") == 0) {
                printf("Recibido carácter +\n");
                break;
            }

            int num = atoi(buffer);//atoi convierte una cadena de caracteres a entero
            printf("Número a sumar: %d\n", num);
            sum += num;
        }

        printf("La suma total es igual a: %d\n", sum);
        close(fd[0]);  
    }
    
    else 
    {  
        // PADRE
        close(fd[0]); 

        while (1) {//esto es un bucle infinito que se sigue ejecutando hasta que se den las condiciones necesarias para el break (que introduzca +)
            printf("Ingrese un número a sumar o '+' para terminar: ");
            
            scanf("%s",&buffer);
            write(fd[1], buffer, strlen(buffer) + 1);
            
            if (strcmp(buffer, "+") == 0) {//cuando introdcue + se hace un break y se pasa al proceso hijo
                break;
            }
        }

        close(fd[1]);  
        
    }

    return 0;
}