# Stefan Traska, Login ID: straska, UID: 117209587, Section: 0104

# This program is intended to take in a number n from the user, 
# and using loops it should return the value of n'th Jacobsthal number.

# -*- mode: text -*-

        .data
number: .word 0                       # int number = 0;
result: .word 0                       # int result = 0;
        
        .text
main:   li     $sp,    0x7ffffffc
        
        li     $v0,    5              # scanf("%d", &number);
        syscall
        sw     $v0,    number

        lw     $t0,    number         # call jacobsthal(number)
	sw     $t0,    ($sp)
	sub    $sp,    $sp,    4
	jal    jacobs
	add    $sp,    $sp,    4

	sw     $v0,    result         # result = jacobsthal(number);
	
	li     $v0,    1              # printf("%d", result);
	lw     $a0,    result
	syscall

	li     $v0,    11             # printf("\n");
	li     $a0,    10
	syscall

	li     $v0,    10             # return 0;
	syscall

jacobs: sub    $sp,    $sp,    24     # prologue
	sw     $ra,    24($sp)
	sw     $fp,    20($sp)
	add    $fp,    $sp,    24

	lw     $t0,    4($fp)         # store argument n

	li     $t1,    -1             # ans = -1;
	sw     $t1,    16($sp)

	bltz   $t0,    end            # if (n < 0) go to return statement

	beqz   $t0,    if             # if (n == 0 || n == 1) go to if body
	beq    $t0,    1,      if
                                      # else body
	li     $t1,    1              # ans = 1;
	sw     $t1,    16($sp)

	li     $t2,    0              # prev = 0;
	sw     $t2,    12($sp)

	li     $t4,    2              # int i = 2;
	sw     $t4,    4($sp)

loop:   bgt    $t4,    $t0,    end    # loop until i > n

	sw     $t2,    ($sp)          # call helper(prev, ans);
	sub    $sp,    $sp,    4
	sw     $t1,    ($sp)
	sub    $sp,    $sp,    4
	jal    help
	add    $sp,    $sp,    8

	lw     $t0,    4($fp)         # restore registers from memory
	lw     $t1,    16($sp)
	
	move   $t3,    $v0            # temp = helper(prev, ans);
	sw     $t3,    8($sp)

	move   $t2,    $t1            # prev = ans;
	sw     $t2,    12($sp)

	move   $t1,    $t3            # ans = temp;
	sw     $t1,    16($sp)
	
	
        add    $t4,    $t4,    1      # i++
	sw     $t4,    4($sp)
	j      loop                   # return to condition of loop

                                      # if body
if:     move   $t1,    $t0            # ans = n;
	sw     $t1,    16($sp)

end:	move   $v0,    $t1            # return ans;

	lw     $ra,    24($sp)        # epilogue
	lw     $fp,    20($sp)
	add    $sp,    $sp,    24
	jr     $ra
	
                                      # helper function
help:	sub    $sp,    $sp,    8      # prologue
	sw     $ra,    8($sp)
	sw     $fp,    4($sp)
	add    $fp,    $sp,    8

	lw     $t0,    8($fp)         # return 2 * x + y;
	lw     $t1,    4($fp)
	mul    $t0,    $t0,    2
	add    $t0,    $t0,    $t1
	move   $v0,    $t0

	lw     $ra,    8($sp)         # epilogue
	lw     $fp,    4($sp)
	add    $sp,    $sp,    8
	jr     $ra
