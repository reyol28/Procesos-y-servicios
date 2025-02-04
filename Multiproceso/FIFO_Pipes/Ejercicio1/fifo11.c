#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>


int main()
{
    int p1,fp1,fp2;
    
    time_t t;
    int numero,factorial;
    srand((unsigned) time(&t));
    p1=mkfifo("FIFO1",0666);
    fp1=open("FIFO1", O_WRONLY);
    
    numero=rand() % 10; 
    write(fp1,&numero,sizeof(numero));
    close(fp1);

    fp2=open("FIFO2",O_RDONLY);
    read(fp2,&factorial,sizeof(factorial));

    
    printf("Número: %d, Factorial: %d\n",numero,factorial);
    close(fp2);
    unlink("FIFO1");
    unlink("FIFO2");

    return 0;
}
