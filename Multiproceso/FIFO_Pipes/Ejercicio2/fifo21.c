#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>


int main()
{
    int p,fp;
    
    time_t t;
    int numero,factorial;
    srand((unsigned) time(&t));
    p=mkfifo("FIFO02",0666);
    fp=open("FIFO02", O_WRONLY);
    
    numero=rand() % 10; 
    write(fp,&numero,sizeof(numero));
    close(fp);

    fp=open("FIFO02",O_RDONLY);
    read(fp,&factorial,sizeof(factorial));

    
    printf("Número: %d, Factorial: %d\n",numero,factorial);
    close(fp);
    unlink("FIFO02");
    //en caso de que algo falle antes del unlink hay que ejecutar rm -f FIFO02

    return 0;
}
