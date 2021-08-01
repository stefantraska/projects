/* This file defines a priority queue (which uses a linked list format)
   and the nodes in the queue. */

/* Each Node in the queue has a data value, priority, and a pointer to
   the next node */
typedef struct node {
  struct node *next;
  char *data;
  int priority;
} Node;

/* Priority queues store the amount of nodes present and a pointer to
   the node with highest priority */
typedef struct prio_que {
  int size;
  struct node *head;
} Prio_que;
