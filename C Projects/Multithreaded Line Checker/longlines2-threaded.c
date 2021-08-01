#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include "randomdelay.h"

/* This is a multithreaded program that opens each file, reads the file by character,
   determines the length of the lin e, and keeps track of the number of lines in each 
   file that are over 80 characters. */

static void *read_files (void *filename);

int main(int argc, char *argv[]) {
  long all_files_total = 0;
  int i;

  if (argc > 1) {  /* if there was at least one filename provided */
    pthread_t *tids = malloc(sizeof(pthread_t) * (argc - 1));
    long **count_list = malloc(sizeof(long *) * (argc - 1));
    
    for (i= 0; i < argc - 1; i++)
      pthread_create(&tids[i], NULL, read_files, (void *)argv[i+1]);

    for (i= 0; i < argc - 1; i++)
      pthread_join(tids[i], (void **) &count_list[i]);

    for (i= 0; i < argc - 1; i++) {
      all_files_total += *count_list[i];
      free(count_list[i]);
    }
    free(count_list);
    free(tids);
  }

  printf("%ld\n", all_files_total);

  return 0;
}

static void *read_files (void *filename) {

  long line_length;
  char ch;
  FILE *fp;
  long *retval = malloc(sizeof(long));
  
  fp = fopen(filename, "r");
  *retval= 0;

  if (fp != NULL) {
    line_length= 0;

    while ((ch= fgetc(fp)) != EOF) {
      if (ch != '\n')
        line_length++;
      else {
        if (line_length > 80)
          (*retval)++;

        line_length= 0;
      }
    }

    if (line_length > 80)
      (*retval)++;

    fclose(fp);
  }

  pthread_exit(retval);
}

