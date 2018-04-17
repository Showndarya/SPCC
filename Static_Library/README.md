# To execute (in Linux)

> gcc -c lib_numsys.c

> ar rcs lib_numsys.a lib_numsys.o

> gcc -c driver.c

> gcc -o d driver.o -L. -l_numsys

> ./d
