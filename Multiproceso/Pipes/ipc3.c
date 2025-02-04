#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <time.h>
 

int main() {
    int fd[2];
    pid_t pid;
    char buffer[50];
    int sum,resta,multiplicacion,division;
    time_t t;
    int num1,num2;
    srand((unsigned) time(&t));
    //Generamos numero aleatorio entre 1 y 50
    num1=rand() % 50; 

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
        
        
        close(fd[0]); 
        
        //Generamos numero aleatorio entre 1 y 50
        num1=rand() % 50;
        num2=rand() % 50;
        write(fd[1], &num1, sizeof(num1));
        write(fd[1], &num2, sizeof(num2));
        
        close(fd[1]);  
    }
    
    else 
    {  
        // PADRE
        close(fd[1]);

        read(fd[0], &num1, sizeof(num1));
        read(fd[0], &num2, sizeof(num2));

        printf("%d + %d = %d\n", num1, num2, num1 + num2);
        printf("%d - %d = %d\n", num1, num2, num1 - num2);
        printf("%d * %d = %d\n", num1, num2, num1 * num2);
        printf("%d / %d = %d\n", num1, num2, num1 / num2);

        
        close(fd[0]);  
        
    }

    return 0;
}