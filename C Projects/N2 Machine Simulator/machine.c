#include "machine.h"
#include <stdio.h>

/* The purpose of this program is to replicate the 32768-byte
   N2 machine's instructions being printed and checked for validity of
   instructions. */

int check_validity(Wrd instruction);


/* This function's purpose is to print the instruction given, along with
   the extension used, registers used, and memory address used, if
   applicable. */
unsigned short print_instruction(Wrd instruction) {

  /* Break down the instruction into each piece of information */
  Wrd opcode = (instruction >> 27);
  Wrd extension = (instruction >> 24) & 7;
  Wrd reg1 = (instruction >> 21) & 7;
  Wrd reg2 = (instruction >> 18) & 7;
  Wrd reg3 = (instruction >> 15) & 7;
  Wrd address = (instruction & 0x7FFF);

  /* Check for validity of the program */
  int check_validity_result = check_validity(instruction);
  if (check_validity_result == 0)
    return 0;

  /* Print based on the opcode of the instruction */
  if (opcode == PLUS) {
    printf("plus R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == MINUS) {
    printf("minus R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == TIMES) {
    printf("times R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == DIV) {
    printf("div R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == MOD) {
    printf("mod R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == NEG) {
    printf("neg R%d R%d", reg1, reg2);

    return 1;
  }

  if (opcode == ABS) {
    printf("abs R%d R%d", reg1, reg2);

    return 1;
  }

  if (opcode == SHL) {
    printf("sh1 R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == SHR) {
    printf("shr R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == BAND) {
    printf("band R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == BOR) {
    printf("bor R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == BXOR) {
    printf("bxor R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == BNEG) {
    printf("bneg R%d R%d", reg1, reg2);

    return 1;
  }

  if (opcode == AND) {
    printf("and R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == OR) {
    printf("or R%d R%d R%d", reg1, reg2, reg3);

    return 1;
  }

  if (opcode == NOT) {
    printf("not R%d R%d", reg1, reg2);

    return 1;
  }

  if (opcode == EQ) {
    printf("eq R%d R%d %05d", reg1, reg2, address);

    return 1;
  }

  if (opcode == NEQ) {
    printf("neq R%d R%d %05d", reg1, reg2, address);

    return 1;
  }

  if (opcode == LE) {
    printf("le R%d R%d %05d", reg1, reg2, address);

    return 1;
  }

  if (opcode == LT) {
    printf("lt R%d R%d %05d", reg1, reg2, address);

    return 1;
  }

  if (opcode == GE) {
    printf("ge R%d R%d %05d", reg1, reg2, address);

    return 1;
  }

  if (opcode == GT) {
    printf("gt R%d R%d %05d", reg1, reg2, address);

    return 1;
  }

  if (opcode == MOVE) {
    printf("move R%d R%d", reg1, reg2);

    return 1;
  }

  if (opcode == LW) {
    printf("lw R%d %05d", reg1, address);

    return 1;
  }

  if (opcode == SW) {
    printf("sw R%d %05d", reg1, address);

    return 1;
  }

  if (opcode == LI) {
    printf("li R%d %d", reg1, address);

    return 1;
  }

  if (opcode == SYS) {
    if(extension == 4)
      printf("sys %d", extension);
    else printf("sys %d R%d", extension, reg1);
    return 1;
  }
  return 0;
}

/* This function's purpose is to print all instructions of a program
   if it is a valid program. Any intrusctions that are invalid are pointed
   to by bad_instr */
unsigned short disassemble(const Wrd program[], unsigned short num_instrs,
                           unsigned short *const bad_instr) {
  
  int memory_address = 0;
  int i;

  /* Check for invalid arguments */
  if (num_instrs > NUM_WORDS || bad_instr == NULL || program == NULL)
    return 0;

  /* Check for an invalid program */
  for (i = 0; i < num_instrs; i++) {
    if (check_validity(program[i]) == 0) {
      *bad_instr = i;
      return 0;
    }
  }

  /* Print the instructions of the program */
  for (i = 0; i < num_instrs; i++) {
    printf("%04x: ", memory_address);
    print_instruction(program[i]);
    printf("\n");
    memory_address += 4;
  }
  return 1;
}

/* This function is designed to return the first index of the program
   array where reg_nbr is given a value. */
short where_set(const Wrd program[], unsigned short num_words,
                unsigned short reg_nbr) {

  int i;

  /* Check for invalid input */
  if (program == NULL || num_words > NUM_WORDS || reg_nbr < 0 || reg_nbr > 5)
    return -1;

  /* Check where reg_nbr is given a value */
  for (i = 0; i < num_words; i++) {

    /* Break down instruction into information */
    Wrd opcode = (program[i] >> 27);
    Wrd reg1 = (program[i] >> 21) & 7;
    Wrd extension = (program[i] >> 24) & 7;

    /* If opcode gives a value to reg1 */
    if (opcode == PLUS || opcode == MINUS || opcode == TIMES || opcode == DIV
      || opcode == MOD ||opcode == SHL || opcode == SHR || opcode == BAND
      || opcode == BOR || opcode == BXOR || opcode == AND || opcode == OR
      || opcode == NEG || opcode == ABS || opcode == BNEG || opcode == NOT
      || opcode == MOVE || opcode == LW || opcode == LI) {

      if (reg1 == reg_nbr)
        return i;
    }

    /* If the opcode is sys */
    if (opcode == SYS) {
      if ((extension == 0 || extension == 2) && reg1 == reg_nbr)
          return i;
    }

  }

  return -1;
}

/* This function's purpose is to indicate whether the program is
   considered a safe program and follows all rules, using where_set()
   as help. If the program isn't safe, it points bad_instr to the index
   where the issue occured. */
unsigned short is_safe(const Wrd program[], unsigned short pgm_size,
                       unsigned short *const bad_instr) {
  int i;

  /* Check for invalid arguments */
  if (program == NULL || bad_instr == NULL || pgm_size > NUM_WORDS)
    return 0;

  /* Check for valid program instructions */
  for (i = 0; i < pgm_size; i++) {
    if (check_validity(program[i]) == 0)
      return 0;
  }

  /* Loops through all instructions and ensure they are safe instructions */
  for (i = 0; i < pgm_size; i++) {
    
    /* Breaks down instruction into information */
    Wrd instruction = program[i];
    Wrd opcode = (instruction >> 27);
    Wrd extension = (instruction >> 24) & 7;
    
    /* If opcode uses only reg1, reg2, reg3 */
    if (opcode == PLUS || opcode == MINUS || opcode == TIMES || opcode == DIV
      || opcode == MOD ||opcode == SHL || opcode == SHR || opcode == BAND
      || opcode == BOR || opcode == BXOR || opcode == AND || opcode == OR) {

      Wrd reg2 = (instruction >> 18) & 7;
      Wrd reg3 = (instruction >> 15) & 7;
      int result2 = where_set(program, pgm_size, reg2);
      int result3 = where_set(program, pgm_size, reg3);

      if (result2 == -1 || result3 == -1) {
        *bad_instr = i;
        return 0;
      } else {
        if (result2 >= i || result3 >= i) {
          *bad_instr = i;
          return 0;
        }
      }

    }

    /* If opcode uses only reg1, reg2 */
    if (opcode == NEG || opcode == ABS || opcode == BNEG || opcode == NOT 
        || opcode == MOVE) {

      Wrd reg2 = (instruction >> 18) & 7;
      int result2 = where_set(program, pgm_size, reg2);
      if (result2 == -1) {
        *bad_instr = i;
        return 0;
      } else {
        if (result2 >= i) {
          *bad_instr = i;
          return 0;
        }
      }
    }

    /* If opcode uses only reg1, reg2, address */
    if (opcode == EQ || opcode == NEQ || opcode == LE || opcode == LT 
        || opcode == GE || opcode == GT) {

      Wrd reg2 = (instruction >> 18) & 7;
      Wrd reg1 = (instruction >> 21) & 7;
      int result2 = where_set(program, pgm_size, reg2);
      int result1 = where_set(program, pgm_size, reg1);
      if (result2 == -1 || result1 == -1) {
        *bad_instr = i;
        return 0;
      } else {
        if (result2 >= i || result1 >= i) {
          *bad_instr = i;
          return 0;
        }
      }
    }

    /* If opcode is sw */
    if (opcode == SW) {
      Wrd reg1 = (instruction >> 21) & 7;
      int result1 = where_set(program, pgm_size, reg1);
      if (result1 == -1) {
        *bad_instr = i;
        return 0;
      } else {
        if (result1 >= i) {
          *bad_instr = i;
          return 0;
        }
      }
    }

    /* If the opcode is sys */
    if (opcode == SYS) {
      Wrd reg1 = (instruction >> 21) & 7;
      int result1 = where_set(program, pgm_size, reg1);
      if (extension == 1 || extension == 3 || extension == 4) {
        if (result1 == -1) {
          *bad_instr = i;
          return 0;
        } else {
          if (result1 >= i) {
            *bad_instr = i;
            return 0;
          }
        }
      }
    }
  }
  
  return 1;
}

/* Checks the validity of the instruction passed in, and ensures all the
   applicable information is valid. */
int check_validity (Wrd instruction) {

  /* Breaks down instruction into information */
  Wrd opcode = (instruction >> 27);
  Wrd extension = (instruction >> 24) & 7;
  Wrd reg1 = (instruction >> 21) & 7;
  Wrd reg2 = (instruction >> 18) & 7;
  Wrd reg3 = (instruction >> 15) & 7;
  Wrd address = (instruction & 0x7FFF);

  if (opcode < PLUS || opcode > SYS)
    return 0;

  /* If opcode uses only reg1, reg2, reg3 */
  if (opcode == PLUS || opcode == MINUS || opcode == TIMES || opcode == DIV
      || opcode == MOD ||opcode == SHL || opcode == SHR || opcode == BAND
      || opcode == BOR || opcode == BXOR || opcode == AND || opcode == OR) {

    /* Checks registers used are valid */
    if (reg1 < 0 || reg1 > 5 || reg2 < 0 || reg2 > 6 || reg3 < 0 
        || reg3 > 6)
      return 0;

  }

  /* If opcode uses only reg1, reg2 */
  if (opcode == NEG || opcode == ABS || opcode == BNEG || opcode == NOT
      || opcode == MOVE) {

    /* Checks registers used are valid */
    if (reg1 < 0 || reg1 > 5 || reg2 < 0 || reg2 > 6)
      return 0;
  }

  /* If opcode uses only reg1, reg2, address */
  if (opcode == EQ || opcode == NEQ || opcode == LE || opcode == LT
      || opcode == GE || opcode == GT) {

    /* Checks registers and address used are valid */
    if (reg1 < 0 || reg1 > 6 || reg2 < 0 || reg2 > 6 || address % 4 != 0)
      return 0;
  }

  /* If opcode is SW */
  if (opcode == SW) {
    
    /* Checks registers and address used are valid */
    if (reg1 < 0 || reg1 > 6 || address % 4 != 0)
      return 0;
  }

  /* If opcode is LW */
  if (opcode == LW) {

    /* Checks registers and addresses used are valid */
    if (reg1 < 0 || reg1 > 5 || address % 4 != 0)
      return 0;
  }

  /* If opcode is LI */
  if (opcode == LI) {

    /* Checks registers used are valid */
    if (reg1 < 0 || reg1 > 5)
      return 0;
  }

  /* If the opcode is sys */
  if (opcode == SYS) {
    
    /* Checks registers used are valid, when applicable */
    if (extension == 1 || extension == 3) {
      if (reg1 < 0 || reg1 > 6)
        return 0;
    } else if (extension == 0 || extension == 2) {
      if (reg1 < 0 || reg1 > 5)
        return 0;
    } else if (extension == 4) {

    } else {
      return 0;
    }
  }
  return 1;
}



