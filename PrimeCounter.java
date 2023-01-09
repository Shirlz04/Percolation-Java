public class PrimeCounter {
    // Checks if x is prime
    private static boolean isPrime(int x) {
        // For each 2 <= i <= sqrt(x), if x is divisible by
        // i, then x is not a prime. If no such i exists,
        // x is a prime.
        int result = 0;
        int num = 0;
        double root = Math.sqrt(x);
        for (int a = 1; a <= root; a += 2) {
            if (x % a == 0) {
                result += 1;
            }
            if (result > 1) {
                break;
            }   
        }
        return result > 1;
    }

    // Returns the number of primes <= N.
    private static int primes(int N) {
        // For each 2 <= i <= N, use isPrime() to test if
        // i is prime, and if so increment a count. At the
        // end return count.
        int count = 1;
        for (int a = 3; a < N; a += 2) {
            if (!isPrime(a)) {
                count += 1;
            }
        }
        return count;
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdOut.println(primes(N));
    }
}
