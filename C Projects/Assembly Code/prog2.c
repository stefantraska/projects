#include <stdio.h>

int number= 0;
int result= 0;

static int helper(int x, int y);
static int jacobsthal(int n);

int helper(int x, int y) {
  return 2 * x + y;
}

int jacobsthal(int n) {
  int ans, prev, temp, i;

  ans= -1;

  if (n >= 0) {
    if (n == 0 || n == 1)
      ans= n;
    else {
      ans= 1;
      prev= 0;

      for (i= 2; i <= n; i++) {
        temp= helper(prev, ans);
        prev= ans;
        ans= temp;
      }
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
