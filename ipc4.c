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
    char buffer[50];
    int sum,resta,multiplicacion,division;
    time_t t;
    int num,resultado;
    srand((unsigned) time(&t));
    //Generamos numero aleatorio entre 1 y 50
     

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
        close(fd1[1]); //cierro escritura del pipe 1
        close(fd2[0]); //cierro lectura del pipe 2
        
        read(fd1[0], &num, sizeof(num));
        if(num==0)
        {
            resultado=0;
        }
        else//si no inicializo el resultado a 1, el resultado siempre seria 0 pues se estaria multiplicando 0*n, que siempre es 0.
        {
            resultado=1;
        }
        for (int i = 1; i <= num; i++) 
        {
            resultado *= i;
        }
        write(fd2[1], &resultado , sizeof(resultado));


        close(fd1[0]); //cierro lectura del pipe 1
        close(fd2[1]); //cierro escritura del pipe 2
    }
    
    else 
    {  
        // PADRE
        close(fd1[0]); //cierro lectura del pipe 1
        close(fd2[1]); //cierro escritura del pipe 2
        num=rand() % 10;
        write(fd1[1], &num, sizeof(num));
        printf("El proceso padre crea el número %d y se lo pasa al hijo en el pipe 1",num);

        
        read(fd2[0], &resultado , sizeof(resultado));

        printf("\nEl resultado del factorial de %d es: %d\n",num,resultado);

        close(fd1[1]); //cierro escritura del pipe 1
        close(fd2[0]); //cierro lectura del pipe 2
    }

    return 0;
}