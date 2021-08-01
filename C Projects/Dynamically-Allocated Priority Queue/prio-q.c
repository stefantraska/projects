#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "prio-q.h"

/* This file describes the functions of a priority queue that uses a
   linked list. Each node in the structure is dynamically allocated.
   The priority queue can enqueue, dequeue, and return its 
   data values. */

/* This function initializes a priority queue with default values */
void init (Prio_que *const prio_q) {

  if (prio_q != NULL) {

    prio_q -> head = NULL;
    prio_q -> size = 0;
  }

}

/* This function adds a node to the queue, its location in
   the queue depending on its priority level */
unsigned int enqueue (Prio_que *const prio_q, const char new_element[],
                     unsigned int priority) {

  char *string;
  Node *new_node;
  Node *curr;
  Node *prev;

  if (prio_q == NULL || new_element == NULL)
    return 0;

  /* Dynamically allocate memory for string */
  string = malloc(sizeof(char)*(strlen(new_element) + 1));
  strcpy(string, new_element);

  /* Create the node */
  new_node = malloc(sizeof(Node));
  new_node -> data = string;
  new_node -> priority = priority;
  new_node -> next = NULL;


  /* Enqueue by traversing until it reaches its location */
  curr = prio_q -> head;
  prev = NULL;

  /* Traverse until it reaches its location in queue */
  while (curr != NULL && new_node -> priority <= curr -> priority) {

    /* Cannot be added if priority already exists*/
    if (new_node -> priority == curr -> priority) 
      return 0;

    /* Otherwise, keep traversing */
    prev = curr;
    curr = curr -> next;
  }
  
  /* Re-connect the linked list so new node is included */
  if (prev == NULL)
    prio_q -> head = new_node;
  else
    prev -> next = new_node;
  new_node -> next = curr;

  /* Update size of queue */
  prio_q -> size = size(*prio_q) + 1;

  return 1;

}
/* This function returns 1 if the priority queue is empty, and 0 if not */
unsigned int is_empty(const Prio_que prio_q) {
  return size(prio_q) == 0;
}

/* This function returns the szie of the queue */
unsigned int size(const Prio_que prio_q) {
  return prio_q.size;
}

/* This function returns the data of the node with highest priority */
char *peek(Prio_que prio_q) {

  char *string;

  if (size(prio_q) == 0)
    return NULL;

  /* Dynamically allocate memory for data */
  string = malloc((strlen(prio_q.head -> data) + 1) * sizeof(char));
  strcpy(string, prio_q.head -> data);

  return string;
}

/* This function removes the node with highest priority, returns its data*/
char *dequeue(Prio_que *const prio_q) {

  char *string = malloc(sizeof(char) * (strlen(prio_q -> head -> data) + 1));
  Node *new_head;

  if (prio_q == NULL || prio_q -> head == NULL)
    return NULL;

  /* Retrieve string of node with highest priority */
  strcpy(string, prio_q -> head -> data);

  /* Free memory of head */
  new_head = prio_q -> head -> next;
  free(prio_q -> head -> data);
  free(prio_q -> head);

  /* Remove node from queue*/
  prio_q -> head = new_head;
  
  /* Update size */
  prio_q -> size = (prio_q -> size) - 1;

  return string;
}

/* This function returns an array of pointers to copies of the
   data in the priority queue, in order of priority, with NULL as
   the last array element. */
char **get_all_elements(Prio_que prio_q) {

  /* Dynamically allocate memory for array */
  char **array = malloc((sizeof(prio_q) + 1) * (sizeof(char *)));

  /* Create pointer to node to traverse queue to start traversing 
     at highest priority*/
  Node *curr = prio_q.head;
  int i;

  for (i = 0; i <= size(prio_q); i++) {
    
    if (i != size(prio_q)) {

      /* Dynamically allocate memory for string to add to array 
         and add it*/
      char *string = malloc((strlen(curr -> data) + 1) * sizeof(char));
      strcpy(string, curr -> data);
      array[i] = string;

      /* Traverse queue */
      curr = curr -> next;

    }
    /* Last element is NULL */
    else
      array[i] = NULL;

  }

  /* Free memory used for traversal */
  curr = NULL;
  free(curr);

  return array;
}

/* This function frees the memory of the contents of the array argument
   and the array itself */
void free_name_list (char *name_list[]) {
  
  if (name_list != NULL) {
    int i = 0;

    /* Continue freeing the contents of array until reached end of array */
    while (name_list[i] != NULL) {
      free(name_list[i]);
      i++;
    }
    /* Free the array itself */
    free(name_list);
  }

}

/* This function deallocates all dynamically-allocated momeory 
   in the priority queue */
void clear (Prio_que *const prio_q) {
  
  if (prio_q != NULL) {

    /* Continue freeing memory of nodes and removing them from the queue
       until it is empty */
    while (!is_empty(*prio_q)) {
      char *element;
      element = dequeue(prio_q);
      free(element);
    }
    /* Avoids dangling pointer */
    prio_q -> head = NULL;
  }

}

/* This function returns the highest priority of the given element */
int get_priority (Prio_que prio_q, char element[]) {

  Node *curr;
  int i;
  int priority;

  if (element == NULL)
    return -1;

  priority = -1;
  
  /* Start traversing node at highest priority */
  curr = prio_q.head;

  for (i = 0; i < size(prio_q); i++) {
    
    /* Update priority if the node contains element and is higher than 
       previous priority */
    if (strcmp(element, curr -> data) == 0 && curr -> priority > priority)
      priority = curr -> priority;

    /* Traverse queue */
    curr = curr -> next;

  }

  return priority;

}

/* This function removes the elements with priorities between "low" 
   and "high", inclusive */
unsigned int remove_elements_between (Prio_que *const prio_q, 
                                      unsigned int low, unsigned int high) {

  /* Create nodes to traverse queue, start traversing at highest priority */
  Node *curr = prio_q -> head;
  Node *prev = NULL;

  int count = 0;

  /* Loop through all nodes */
  while (curr != NULL) {
    
    /* If node's priority is in range, remove it */
    if (curr -> priority >= low && curr -> priority <= high) {

      if (curr == prio_q -> head) {
        /* Remove node and free memory */
        Node *next_node = prio_q -> head -> next;
        free(prio_q -> head -> data);
        free(prio_q -> head);

        /* Traverse to next node */
        prio_q -> head = next_node;
        curr = prio_q -> head;
        prev = NULL;

      } else if (curr != NULL){
        /* Remove node and free memory */
        Node *next_node = curr -> next;
        free(curr -> data);
        free(curr);

        /* Traverse to next node */
        if (prev != NULL)
          prev -> next = next_node;
        curr = next_node;
      }
      /* Update priority queue size and number of removed elements */
      prio_q -> size = prio_q -> size - 1;
      count++;

    /* Otherwise traverse to next node */
    } else { 
      prev = curr;
      curr = curr -> next;
    }

  }

  return count;

}

unsigned int change_priority (Prio_que *prio_q, char element[], 
                              unsigned int new_priority) {

  /* Create node to traverse queue */
  Node *curr = prio_q -> head;
  /* Pointer to target node */
  Node *elem;
  int element_count;

  /* Check if priority already exists or element exists more than once */
  while (curr != NULL) {

    /* Check number of nodes with the element */
    if (strcmp(curr -> data, element) == 0) {
      elem = curr;
      element_count++;
    }

    /* Priority cannot exist more than once */
    if (curr -> priority == new_priority)
      return 0;

    /* Traverse to next node */
    curr = curr -> next;
  }

  /* If there isn't only 1 instance of element */
  if (element_count != 1)
    return 0;

  /* Otherwise chnage priority */
  elem -> priority = new_priority; 

  return 1;

}

