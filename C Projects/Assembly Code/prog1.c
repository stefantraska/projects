#include <stdio.h>

int length= 0;
int width= 0;
int height= 0;
int ans= 0;

int main(void) {
  ans= -1;

  scanf("%d %d %d", &length, &width, &height);

  if (length > 0 && width > 0 && height > 0)
    ans= 2 * (width * length + length * height + width * height);

  printf("%d\n", ans);

  return 0;
}
