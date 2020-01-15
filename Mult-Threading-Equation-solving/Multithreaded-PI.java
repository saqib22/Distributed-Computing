
class MyThread extends Thread {
    private int num_Steps;
    private int start;
    private int stop;
    private double result = 0;

    public MyThread(int num_Steps, int start, int stop) {
        this.num_Steps = num_Steps;
        this.start = start;
        this.stop = stop;
    }

    public double getResult(){
        return result;
    }

    public void run() {
        double steps = 1/(double)num_Steps;
        double x, sum = 0.0;

        for(int i = start; i < stop; i++) {
            x = ( i + 0.5) * steps;
            sum = sum + 4.0 / (1 + x*x);
        }
        result = steps * sum;
    }

}

public class Multithread {

    public static void main(String args[]) throws InterruptedException {
        int n = 100000;

        long start, end, duration = 0;

        MyThread T1 = new MyThread(n, 0, n/2);
        MyThread T2 = new MyThread(n, n/2, n);

        start = System.nanoTime();

        T1.start();
        T2.start();

        T1.join();
        T2.join();

        end = System.nanoTime();
        duration = end - start;

        System.out.println("Output : " + (T1.getResult() + T2.getResult()));
        System.out.println("\nTime Calculation :");
        System.out.println("Current Time : " + (duration) + " nanoseconds");

        duration = 0;
        for(int i = 0; i < 100; i++){
            T1 = new MyThread(n, 0, n/2);
            T2 = new MyThread(n, n/2, n);

            start = System.nanoTime();

            T1.start();
            T2.start();

            T1.join();
            T2.join();

            end = System.nanoTime();
            duration += (end - start);
        }

        long average = duration/100;

        System.out.println("For 100 iterations Average Time : " + (average) + " nanoseconds");

    }
}