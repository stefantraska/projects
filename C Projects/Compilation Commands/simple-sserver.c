#include "simple-sserver.h"
#include "simple-sserver-datastructure.h"
#include "safe-fork.h"
#include "split.h"
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <err.h>
#include <sysexits.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>


/* This file deals with the loading, reading, and executing of compliation
   and testing commands, using a data structure named Commands. The
   data structure used is a linked list, where each node contains a 
   command. */


/* This function reads two lists of commands, one for compiling code,
   and the other for testing code, which will be stored in a data structure
   named Commands, which holds two linked lists.  */
Commands read_commands(const char compile_cmds[], const char test_cmds[]) {
  
  char buffer[256];
  Commands commands;
  Command *curr = NULL;
  FILE *compile_fp;
  FILE *test_fp;

  /* Open files  for reading */
  compile_fp = fopen(compile_cmds, "r");
  test_fp = fopen(test_cmds, "r");

  /* Check for file that doesn't exist or error for opening file */
  if (compile_cmds == NULL || test_cmds == NULL 
      || compile_fp == NULL || test_fp == NULL) 
    exit(1);

  /* Set default values of Commands */
  commands.compilation_size = 0;
  commands.test_size = 0;
  commands.compilation_head = NULL;
  commands.test_head = NULL;
  

  /* Read compilation commands */
  while (fgets(buffer, 256, compile_fp) != NULL) {

    /* Dynamically allocate memory and add data for
       new node in linked list */
    Command *new_node = malloc(sizeof(Command));
    char *string = malloc(strlen(buffer) + 1);
    strcpy(string, buffer);
    new_node -> command = string;
    new_node -> next = NULL;

    /* Traverse to new node and increase size */
    if (commands.compilation_size == 0) {
      commands.compilation_head = new_node;
      curr = commands.compilation_head;
    } else {
      curr -> next = new_node;
      curr = curr -> next;
    }
    commands.compilation_size++;
  }

  curr = NULL;

  /* Read testing commands */
  while (fgets(buffer, 256, test_fp) != NULL) {

    /* Dynamically allocate memory and add data for
       new node in linked list */
    Command *new_node = malloc(sizeof(Command));
    char *string = malloc(strlen(buffer) + 1);
    strcpy(string, buffer);
    new_node -> command = string;
    new_node -> next = NULL;

    /* Traverse to new node and increase size */
    if (commands.test_size == 0) {
      commands.test_head = new_node;
      curr = commands.test_head;
    } else {
      curr -> next = new_node;
      curr = curr -> next;
    }
    commands.test_size++;
  }  

  /* Close files */
  fclose(compile_fp);
  fclose(test_fp);

  return commands;
  
}

/* This function clears the dynamically-allocated memory from
   its parameter */
void clear_commands(Commands *const commands) {
  Command *curr = NULL;

  if (commands != NULL) {

    /* Free compilation linked list */
    curr = commands -> compilation_head;
    while (curr != NULL) {
      Command *next = curr -> next;

      free(curr -> command);
      free(curr);

      /* Traverse to next node */
      curr = next;
    }
    
    /* Free testing linked list */
    curr = commands -> test_head;
    while (curr != NULL) {
      Command *next = curr -> next;

      free(curr -> command);
      free(curr);

      /* Traverse to next node */
      curr = next;
    }

    /* Reset to default values */
    commands -> compilation_head = NULL;
    commands -> test_head = NULL;

  }
}

/* This function executes compliation commands from first to last,
   returning if they were all successful or not. */
int compile_program(Commands commands) {
  int i;
  pid_t pid;
  Command *curr = commands.compilation_head;

  /* Loop through all commands in order */
  for (i = 0; i < commands.compilation_size; i++) {

    /* Split command into arguments */
    char **cmd = split(curr -> command);    

    pid = safe_fork();

    /* Child */
    if (pid == 0) {
      
      /* Execute command */
      execvp(cmd[0], cmd);
      return FAILED_COMPILATION;

    /* Parent */
    } else {
      /* Wait for child to finish executing */
      int status;
      wait(&status);
      if (WIFEXITED(status)) {

        /* Free memory from created array of arguments */
        int j = 0;
        while (cmd[j] != NULL) {
          free(cmd[j]);
          j++;
        }
        free(cmd);

        /* Error if unsuccessful command execution */
        if (WEXITSTATUS(status) != 0)
          return FAILED_COMPILATION;
      }

      /* Traverse to next command */
      curr = curr -> next;
    }
  }
  return SUCCESSFUL_COMPILATION;
}


/* This function executes a list of test commands from first to last,
   returning the number of successful commands */
int test_program(Commands commands) {
  int successful_cmds = 0;
  int i;
  pid_t pid;
  Command *curr = commands.test_head;

  /* Loop through all commands */
  for (i = 0; i < commands.test_size; i++) {
    
    char **cmd = split(curr -> command);
    char *filename_input = NULL;
    char *filename_output = NULL;
    char **ptr = cmd;
    int lessthan_exists = 0;
    int fd1;
    int fd2;

    /* Traverse command arguments and look for input/output redirection */
    while (lessthan_exists == 0 && *ptr != NULL) {

      /* Case of input redirection occuring first */
      if (strcmp(*ptr, "<") == 0) {
        /* Save filename */
        filename_input = malloc(strlen(*(ptr + 1)) + 1);
        strcpy(filename_input, *(ptr + 1));

        /* If output redirection occurs after */
        if (*(ptr+2) != NULL) {

          /* Save filename and free output redirection arguments */
          filename_output = malloc(strlen(*(ptr + 3)) + 1);
          strcpy(filename_output, *(ptr + 3));
          free(*(ptr + 2));
          free(*(ptr + 3));
        }

        /* Free input redirection arguments */
        free(*ptr);
        free(*(ptr + 1));
        *ptr = NULL;
        lessthan_exists = 1; 

      /* Case of output redirection ocuring first */
      } else if (strcmp(*ptr, ">") == 0) {

        /* Save filename */
        filename_output = malloc(strlen(*(ptr + 1)) + 1);
        strcpy(filename_output, *(ptr + 1));

        /* If input redirection occurs after */ 
        if (*(ptr+2) != NULL) {

          /* save filename and free input redirection arguments */
          filename_input = malloc(strlen(*(ptr + 3)) + 1);
          strcpy(filename_input, *(ptr + 3));
          free(*(ptr + 2));
          free(*(ptr + 3));
        }

        /* Free output redirection arguments */
        free(*ptr);
        free(*(ptr + 1));
        *ptr = NULL;
        lessthan_exists = 1; 
      }
        
      ptr++;
    }

    /* Open files for input/output redirection */
    if (filename_input != NULL) {
      fd1 = open(filename_input, O_RDONLY);
      dup2(fd1, STDIN_FILENO);
    }
    
    if (filename_output != NULL) {
      fd2 = open(filename_input, FILE_FLAGS, FILE_MODE);
      dup2(fd2, STDIN_FILENO);
    }

    /* Fork to execute commands */
    pid = safe_fork();
    
    /* Child */
    if (pid == 0)
      execvp(cmd[0], cmd);

    /* Parent */
    else {
      int status;
      wait(&status);
      if (WIFEXITED(status)) {
      
        /* Free memory of arguments */
        int j = 0;
        if (WEXITSTATUS(status) == 0)
          successful_cmds++;
        while (cmd[j] != NULL) {
          free(cmd[j]);
          j++;
        }
        free(cmd);

        /* Free memory of filenames */
        if (filename_input != NULL)
          free(filename_input);
        if (filename_output != NULL)
          free(filename_output);
      }

      /* Traverse to next command */
      curr = curr -> next;
    }
  }
  return successful_cmds;
  
}

