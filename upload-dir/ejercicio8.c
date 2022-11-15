#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
void* writerA(void* param){
      int i;
      for (i = 0; i < 6; i++){
            printf("%d", (int)(long)param);
            fflush(NULL); // escribimos lo que haya en buffer si o si
}
      pthread_exit(NULL);
}
void* writerB(void* param){
      int i;
      for (i = 0; i < 6; i++){
            printf("%d", (int)(long)param);
            fflush(NULL);
}
      pthread_exit(NULL);
}
void main(){
      pthread_t threads[2];
      pthread_create(&threads[0], NULL, writerA, (void*) 1);
      pthread_create(&threads[1], NULL, writerB, (void*) 2);
      pthread_join(threads[0], NULL);
      pthread_join(threads[1], NULL);
}