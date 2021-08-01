#if !defined(PRIO_Q_H)
#define PRIO_Q_H

/* (c) Larry Herman, 2021.  You are allowed to use this code yourself, but
   not to provide it to anyone else. */

/* DO NOT MODIFY THIS FILE OR YOUR CODE WILL NOT COMPILE ON THE SUBMIT
 * SERVER. */

#include "prio-q-datastructure.h"

/* this macro ONLY works right in the scope where an array is DECLARED */
#define ARRSIZE(arr) (sizeof(arr) / sizeof((arr)[0]))

void init(Prio_que *const prio_q);
unsigned int enqueue(Prio_que *const prio_q, const char new_element[],
                     unsigned int priority);
unsigned int is_empty(const Prio_que prio_q);
unsigned int size(const Prio_que prio_q);
char *peek(Prio_que prio_q);
char *dequeue(Prio_que *const prio_q);
char **get_all_elements(Prio_que prio_q);
void free_name_list(char *name_list[]);
void clear(Prio_que *const prio_q);
int get_priority(Prio_que prio_q, char element[]);
unsigned int remove_elements_between(Prio_que *const prio_q, unsigned int low,
                                     unsigned int high);
unsigned int change_priority(Prio_que *prio_q, char element[],
                             unsigned int new_priority);

#endif
