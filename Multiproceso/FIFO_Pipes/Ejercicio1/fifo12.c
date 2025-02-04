#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>

int main()
{
    int p2,fp1,fp2;
    int numero,factorial;
    
    fp1=open("FIFO1",O_RDONLY);

    read(fp1,&numero,sizeof(numero));
    close(fp1);
    //calculo el factorial
    
    factorial=calcFactorial(numero);


    p2=mkfifo("FIFO2",0666);
    fp2=open("FIFO2",O_WRONLY);
    write(fp2,&factorial,sizeof(factorial));
    close(fp2);

    return 0;
}
int calcFactorial(int num)
{
    int resultado = 1;
    if (num==0 || num==1)
    {
        return 1;
    }
    else
    {
        for(int i = num; i>=1; i--)
        {
            resultado *=i;
        }
    }
    return resultado;
}
