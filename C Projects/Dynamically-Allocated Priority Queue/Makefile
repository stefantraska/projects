CC = gcc
CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror

all: public01.x public02.x public03.x public04.x public05.x public06.x\
        public07.x public08.x public09.x public10.x public11.x public12.x\
	public13.x public14.x public15.x

public01.x: public01.o prio-q.o
	$(CC) public01.o prio-q.o -o public01.x

public02.x: prio-q.o public02.o
	$(CC) public02.o prio-q.o -o public02.x

public03.x: prio-q.o public03.o
	$(CC) public03.o prio-q.o memory-checking.o -o public03.x

public04.x: prio-q.o public04.o
	$(CC) public04.o prio-q.o memory-checking.o -o public04.x

public05.x: prio-q.o public05.o
	$(CC) public05.o prio-q.o memory-checking.o -o public05.x

public06.x: prio-q.o public06.o
	$(CC) public06.o prio-q.o memory-checking.o -o public06.x

public07.x: prio-q.o public07.o
	$(CC) public07.o prio-q.o memory-checking.o -o public07.x

public08.x: prio-q.o public08.o
	$(CC) public08.o prio-q.o memory-checking.o \
	compare-name-lists.o -o public08.x

public09.x: prio-q.o public09.o
	$(CC) public09.o prio-q.o memory-checking.o -o public09.x

public10.x: prio-q.o public10.o
	$(CC) public10.o prio-q.o memory-checking.o \
	compare-name-lists.o -o public10.x

public11.x: prio-q.o public11.o
	$(CC) public11.o prio-q.o memory-checking.o -o public11.x

public12.x: prio-q.o public12.o
	$(CC) public12.o prio-q.o compare-name-lists.o \
	memory-checking.o -o public12.x

public13.x: prio-q.o public13.o
	$(CC) public13.o prio-q.o -o public13.x

public14.x: prio-q.o public14.o
	$(CC) public14.o prio-q.o compare-name-lists.o -o public14.x

public15.x: prio-q.o public15.o
	$(CC) public15.o prio-q.o compare-name-lists.o -o public15.x

student.x: prio-q.o student.o
	$(CC) student.o prio-q.o compare-name-lists.o \
	memory-checking.o -o student.x

prio-q.o: prio-q.c prio-q.h
	$(CC) $(CFLAGS) -c prio-q.c

public01.o: public01.c prio-q.h prio-q-datastructure.h
	$(CC) $(CFLAGS) -c public01.c

public02.o: public02.c prio-q.h prio-q-datastructure.h
	$(CC) $(CFLAGS) -c public02.c

public03.o: public03.c prio-q.h prio-q-datastructure.h memory-checking.h
	$(CC) $(CFLAGS) -c public03.c

public04.o: public04.c prio-q.h prio-q-datastructure.h memory-checking.h
	$(CC) $(CFLAGS) -c public04.c

public05.o: public05.c prio-q.h prio-q-datastructure.h memory-checking.h
	$(CC) $(CFLAGS) -c public05.c

public06.o: public06.c prio-q.h prio-q-datastructure.h memory-checking.h
	$(CC) $(CFLAGS) -c public06.c

public07.o: public07.c prio-q.h prio-q-datastructure.h memory-checking.h
	$(CC) $(CFLAGS) -c public07.c

public08.o: public08.c prio-q.h prio-q-datastructure.h memory-checking.h \
	compare-name-lists.h
	$(CC) $(CFLAGS) -c public08.c

public09.o: public09.c prio-q.h prio-q-datastructure.h memory-checking.h
	$(CC) $(CFLAGS) -c public09.c

public10.o: public10.c prio-q.h prio-q-datastructure.h memory-checking.h \
	compare-name-lists.h
	$(CC) $(CFLAGS) -c public10.c

public11.o: public11.c prio-q.h prio-q-datastructure.h memory-checking.h \
	compare-name-lists.h
	$(CC) $(CFLAGS) -c public11.c

public12.o: public12.c prio-q.h prio-q-datastructure.h memory-checking.h \
	compare-name-lists.h
	$(CC) $(CFLAGS) -c public12.c

public13.o: public13.c prio-q.h prio-q-datastructure.h
	$(CC) $(CFLAGS) -c public13.c

public14.o: public14.c prio-q.h prio-q-datastructure.h compare-name-lists.h
	$(CC) $(CFLAGS) -c public14.c

public15.o: public15.c prio-q.h prio-q-datastructure.h
	$(CC) $(CFLAGS) -c public15.c

student.o: student.c prio-q.h prio-q-datastructure.h memory-checking.h \
	compare-name-lists.h
	$(CC) $(CFLAGS) -c student.c

prio-q.h: prio-q-datastructure.h
	touch prio-q.h

clean:
	rm -f *.x p*.o
