#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <sys/stat.h>
#include <fcntl.h>

int main()
{
    int fp;
    int numero,factorial;
    
    fp=open("FIFO02",O_RDONLY);

    read(fp,&numero,sizeof(numero));
    close(fp);
    //calculo el factorial
    
    factorial=calcFactorial(numero);



    fp=open("FIFO02",O_WRONLY);
    write(fp,&factorial,sizeof(factorial));
    close(fp);

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