#include <stdio.h>
#include "randomdelay.h"

/* (c) Larry Herman, 2021.  You are allowed to use this code yourself, but
 * not to provide it to anyone else.
 */

/* This program processes a list of zero or more files whose names are given
 * on its command line, counting the number of lines that each file contains
 * that are longer than 80 characters.  It aggregates the number of lines in
 * all the files that are longer than 80 characters and prints the total as
 * its only output.  Files are read one character at a time, by character, *
 * because we don't have any way to know the maximum length of lines.  More
 * realistically, and unlike Project #2, we do not require that the last
 * line of a file end with a newline.
 */

int main(int argc, char *argv[]) {
  long one_file_count, all_files_total= 0, line_length;
  int i;
  char ch;
  FILE *fp;

  if (argc > 1) {  /* if there was at least one filename provided */
    /* iterate over all filenames on the command line */
    for (i= 1; i < argc; i++) {
      fp= fopen(argv[i], "r");  /* open file for reading */

      /* silently ignore nonexistent files, or ones that there was an error
         trying to open */
      if (fp != NULL) {
        one_file_count= 0;
        line_length= 0;

        /* see the explanation in the project assignment regarding what this
           is for */
        /* randomdelay(argv[i]); */

        while ((ch= fgetc(fp)) != EOF) {  /* read by character */
          if (ch != '\n')
            line_length++;
          else {
            if (line_length > 80)
              one_file_count++;

            line_length= 0;  /* reset for the next line */
          }
        }

        /* we have to handle it as a special case if the last line does not
           end with a newline and it is longer than 80 characters (of course
           only the last line can possibly not end with a newline) */
        if (line_length > 80)
          one_file_count++;

        all_files_total += one_file_count;
      }

      fclose(fp);
    }
  }

  printf("%ld\n", all_files_total);

  return 0;
}
