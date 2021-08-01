#include <stdio.h>

int number= 0;
int result= 0;

static int jacobsthal(int n);

int jacobsthal(int n) {
  int ans, temp1, temp2;

  ans= -1;

  if (n >= 0) {
    if (n == 0 || n == 1)
      ans= n;
    else {
      temp1= 2 * jacobsthal(n - 2);
      temp2= jacobsthal(n - 1);
      ans= temp1 + temp2;
    }
  }

  return ans;
}

int main(void) {
  scanf("%d", &number);

  result= jacobsthal(number);

  printf("%d\n", result);

  return 0;
}
