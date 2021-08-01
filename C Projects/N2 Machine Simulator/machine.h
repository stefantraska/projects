/* (c) Larry Herman, 2021.  You are allowed to use this code yourself, but
   not to provide it to anyone else. */

/* DO NOT MODIFY THIS FILE OR YOUR CODE WILL NOT COMPILE ON THE SUBMIT
 * SERVER. */

#define NUM_BYTES 32768
#define BYTES_PER_WORD 4
#define NUM_WORDS (NUM_BYTES / BYTES_PER_WORD)
#define R0 0
#define R1 1
#define R2 2
#define R3 3
#define R4 4
#define R5 5
#define R6 6

typedef unsigned int Wrd;

typedef enum { PLUS, MINUS, TIMES, DIV, MOD, NEG, ABS, SHL, SHR, BAND, BOR,
               BXOR, BNEG, AND, OR, NOT, EQ, NEQ, LE, LT, GE, GT, MOVE, LW,
               SW, LI, SYS } Op_code;

unsigned short print_instruction(Wrd instruction);
unsigned short disassemble(const Wrd program[], unsigned short num_instrs,
                           unsigned short *const bad_instr);
short where_set(const Wrd program[], unsigned short num_words,
                unsigned short reg_nbr);
unsigned short is_safe(const Wrd program[], unsigned short pgm_size,
                       unsigned short *const bad_instr);
