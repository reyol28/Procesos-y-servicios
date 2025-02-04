#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <time.h>
 

int main() {
    int fd1[2];
    int fd2[2];
    pid_t pid;
    int dni;
    char letras[] = "TRWAGMYFPDXBNJZSQVHLCKE";
    char letra;
    
    
      // Crear el pipe
    if (pipe(fd1) == -1) {
        printf("Error al crear el pipe");
        return 1;
    }
    if (pipe(fd2) == -1) {
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
        close(fd1[0]); //cierro lectura del pipe 1
        close(fd2[1]); //cierro escritura del pipe 2
        
        printf("Introduce los números de tu DNI:");
        scanf("%d", &dni);
        write(fd1[1], &dni, sizeof(dni));
        
        read(fd2[0], &letra ,sizeof(letra));
        printf("\nLa letra de ese DNI es: %c\n",letra);

        close(fd1[1]); //cierro escritura del pipe 1
        close(fd2[0]); //cierro lectura del pipe 2
    }
    
    else 
    {  
        // PADRE
        close(fd1[1]); //cierro escritura del pipe 1
        close(fd2[0]); //cierro lectura del pipe 2
        
        read(fd1[0], &dni, sizeof(dni));
        int indice= dni%23;
        letra=letras[indice];
        write(fd2[1],&letra, sizeof(letra));
        


        
        close(fd1[0]); //cierro lectura del pipe 1
        close(fd2[1]); //cierro escritura del pipe 2
    }

    return 0;
}