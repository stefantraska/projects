# Stefan Traska, Login ID: straska, UID: 117209587, Section: 0104

# This program is intended to take in a number n from the user, 
# and using recursion it should return the value of n'th Jacobsthal number.

# -*- mode: text -*-

        .data
number: .word 0                       # int number = 0;
result: .word 0                       # int result = 0;
        
        .text
main:   li     $sp,    0x7ffffffc
        
        li     $v0,    5              # scanf("%d", &number);
        syscall
        sw     $v0,    number

	lw     $t0,    number         # call jacobsthal(number);
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

jacobs: sub    $sp,    $sp,    20     # prologue
        sw     $ra,    20($sp)
        sw     $fp,    16($sp)
        add    $fp,    $sp,    20

        lw     $t0,    4($fp)         # store argument n

        li     $t1,    -1             # ans = -1;
        sw     $t1,    12($sp)

        bltz   $t0,    end            # if (n < 0) go to return statement
        beqz   $t0,    if             # if (n == 0 || n == 1 ) go to if body
        beq    $t0,    1,     if
                                      # else
        sub    $t0,    $t0,   2       # temp1 = 2 * jacobsthal(n - 2);
        sw     $t0,    ($sp)
        sub    $sp,    $sp,   4
        jal    jacobs
        add    $sp,    $sp,   4
        move   $t2,    $v0
        mul    $t2,    $t2,   2
        sw     $t2,    8($sp)

        lw     $t0,    4($fp)         # temp2 = jacobsthal(n - 1);
        sub    $t0,    $t0,   1
        sw     $t0,    ($sp)
        sub    $sp,    $sp,   4
        jal    jacobs
        add    $sp,    $sp,   4
        move   $t3,    $v0
        sw     $t3,    4($sp)

        lw     $t0,    4($fp)        # restore registers from memeory
        lw     $t1,    12($sp)
        lw     $t2,    8($sp)
        lw     $t3,    4($sp)

        add    $t1,    $t2,    $t3   # ans = temp1 + temp2;
        sw     $t1,    12($sp)
        j      end                   # jump to return statement

                                     # if body
if:     move   $t1,    $t0           # ans = n;
        sw     $t1,    12($sp)

end:    move   $v0,    $t1           # return ans;

	lw     $ra,    20($sp)       # epilogue
        lw     $fp,    16($sp)
        add    $sp,    $sp,   20
        jr     $ra
        
	
