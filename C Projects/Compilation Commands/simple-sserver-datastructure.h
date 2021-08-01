/* This file describes a Command data structure, which includes two linked
   lists of Command structures, one of compilation commands, and
   the other for testing commands. */

#if !defined(SIMPLE_SSERVER_DATASTRUCTURE)
#define SIMPLE_SSERVER_DATASTRUCTURE
#include "simple-sserver.h"

typedef struct Command {
  struct Command *next;
  char *command;
} Command;

typedef struct Commands {

  int compilation_size;
  int test_size;
  struct Command *compilation_head;
  struct Command *test_head;

} Commands;
#endif
