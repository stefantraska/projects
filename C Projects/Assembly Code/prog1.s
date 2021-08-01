# Stefan Traska, Login ID: straska, UID: 117209587, Section: 0104

# This program is intended to take in a length, width, and height of a
# rectangular prism from the user, calculate its surface area, and
# then print it to the user.


# -*- mode: text -*-

        .data
length: .word 0                       # int length = 0;
width:	.word 0                       # int width = 0;
height:	.word 0                       # int height = 0;
ans:	.word 0                       # int ans = 0;
	
	.text
main:   li     $sp,    0x7ffffffc
	
        li     $t0,    -1             # ans = -1;
        sw     $t0,    ans

        li     $v0,    5              # scanf("%d", &length);
	syscall
	sw     $v0,    length

	li     $v0,    5              # scanf("%d", &width);
	syscall
	sw     $v0,    width

	li     $v0,    5              # scanf("%d", &height);
	syscall
	sw     $v0,    height

	lw     $t0,    length
	lw     $t1,    width
	lw     $t2,    height

	blez   $t0,    endif          # if (length < 0) go to endif
	blez   $t1,    endif          # if (width  < 0) go to endif
	blez   $t2,    endif          # if (height < 0) go to endif

	mul    $t3,    $t0,    $t1    # ans = 2*(w+l + l*h + w*h)
	mul    $t4,    $t1,    $t2
	mul    $t5,    $t0,    $t2
	add    $t0,    $t3,    $t4
	add    $t0,    $t0,    $t5
	mul    $t0,    $t0,    2
	sw     $t0,    ans

endif:	li     $v0,    1              # printf("%d", ans);
	lw     $a0,    ans
	syscall

	li     $v0,    11             # printf("\n");
	li     $a0,    10
	syscall

	li     $v0,    10             # return 0;
	syscall
	
	
	 
