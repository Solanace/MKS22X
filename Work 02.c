#include <stdio.h>
#include <stdlib.h>
int main() {
    // Problem 1
    int sum = 0, i = 0;
    for (i; i < 1000; i ++) {
        // printf("Sum: %d\n", sum);
        if (i % 3 == 0 || i % 5 == 0) {
            // printf("Adding %d\n", i);
            sum += i;
        }
    }
    
    printf("Problem 1: %d\n", sum);
    
    // Problem 2
    int fib1 = 1, fib2 = 2, fibsum = 0;
    while (fib1 < 4000000 && fib2 < 4000000) {
        if (fib2 % 2 == 0) {
            fibsum += fib2;
        }
        fib2 = fib1 + fib2;
        fib1 = fib2 - fib1;
    }
    
    printf("Problem 2: %d\n", fibsum);
    
    return sum;
}
