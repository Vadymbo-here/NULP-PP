package jab1;

/**
 * <h1>Hello, World!</h1>
 * <h2>java_hellow</h2>
 * <p>
 * fib function is generating N-th Fibo number
 * </p>
 * 
 * <p>
 * At main all other calculations are done
 * Like comparison values and etc.
 * </p>
 * 
 * @author Vadym_Bogorodetskyy KN-207
 */

class java_hellow {
    /**
     * The Fibonacci numbers function
     * 
     * @param k - is a index of Fibo number we are looking for
     * @return returns 1 or 0 in a base case, otherwise -> do recursion N-1 + N-2
     */
    static double fibo(int k) {
        if (k <= 1)
            return k;
        return fibo(k - 1) + fibo(k - 2);
    }

    /**
     * @param args[]
     */
    public static void main(String args[]) {
        System.out.println(fibo(1));
        System.out.println(fibo(2));
        int N = Integer.parseInt(args[0]), counter = 0;

        /**
         * Main loop in main
         * It's iterrates through the all Fibo number up to N
         */
        for (int i = 1; i <= N; i++) {
            Double temp = fibo(i);
            System.out.println(temp);
            /**
             * Second loop in a main
             * It takes single Fibo number and goes to check in a brute way possible cubes
             */
            for (double j = 1; j <= temp; j++) {
                if ((j * j * j) == temp) {
                    System.out.print("Found at ");
                    System.out.print(j);
                    System.out.print(" fibo ->  ");
                    System.out.println(temp);
                    counter++;
                }
            }
        }

        System.out.print("Found cubes in Fibo: ");
        System.out.print(counter);
    }
}
