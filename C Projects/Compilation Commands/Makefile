CC = gcc
CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror

all: public01.x public02.x public03.x public04.x public05.x public06.x\
	public07.x public08.x public09.x public10.x

public01.x: public01.o simple-sserver.o safe-fork.o split.o
	$(CC) public01.o simple-sserver.o safe-fork.o split.o -o public01.x

public01.o: public01.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public01.c

public02.x: public02.o simple-sserver.o safe-fork.o split.o
	$(CC) public02.o simple-sserver.o safe-fork.o split.o -o public02.x

public02.o: public02.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public02.c

public03.x: public03.o simple-sserver.o safe-fork.o split.o memory-checking.o
	$(CC) public03.o simple-sserver.o safe-fork.o split.o \
	memory-checking.o -o public03.x

public03.o: public03.c simple-sserver.h simple-sserver-datastructure.h split.h \
	memory-checking.h
	$(CC) $(CFLAGS) -c public03.c

public04.x: public04.o simple-sserver.o safe-fork.o split.o
	$(CC) public04.o simple-sserver.o safe-fork.o split.o -o public04.x

public04.o: public04.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public04.c

public05.x: public05.o simple-sserver.o safe-fork.o split.o
	$(CC) public05.o simple-sserver.o safe-fork.o split.o -o public05.x

public05.o: public05.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public05.c

public06.x: public06.o simple-sserver.o safe-fork.o split.o memory-checking.o
	$(CC) public06.o simple-sserver.o safe-fork.o split.o \
	memory-checking.o -o public06.x

public06.o: public06.c simple-sserver.h simple-sserver-datastructure.h split.h \
	memory-checking.h
	$(CC) $(CFLAGS) -c public06.c

public07.x: public07.o simple-sserver.o safe-fork.o split.o
	$(CC) public07.o simple-sserver.o safe-fork.o split.o -o public07.x

public07.o: public07.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public07.c

public08.x: public08.o simple-sserver.o safe-fork.o split.o
	$(CC) public08.o simple-sserver.o safe-fork.o split.o -o public08.x

public08.o: public08.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public08.c

public09.x: public09.o simple-sserver.o safe-fork.o split.o
	$(CC) public09.o simple-sserver.o safe-fork.o split.o -o public09.x

public09.o: public09.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public09.c

public10.x: public10.o simple-sserver.o safe-fork.o split.o
	$(CC) public10.o simple-sserver.o safe-fork.o split.o -o public10.x

public10.o: public10.c simple-sserver.h simple-sserver-datastructure.h split.h
	$(CC) $(CFLAGS) -c public10.c

clean:
	rm -f public*.x p*.o
