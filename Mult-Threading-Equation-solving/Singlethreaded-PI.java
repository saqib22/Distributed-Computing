public class Singlethread {
    private int num_Steps;

    public Singlethread(int num_Steps){
        this.num_Steps = num_Steps;
    }

    public double calcPi(){
        double steps = 1/(double)num_Steps;
        double x, pi, sum = 0.0;

        for(int i = 0; i < num_Steps; i++) {
            x = ( i + 0.5) * steps;
            sum = sum + 4.0 / (1 + x*x);
        }
        pi = steps * sum;

        return pi;
    }

    public static void main(String[]args){

        Singlethread thread = new Singlethread(100000);
        double pi = 0.0;
        long start, end, duration = 0;

        start = System.nanoTime();

        pi = thread.calcPi();

        end = System.nanoTime();
        duration = end - start;

        System.out.println("Output : " + pi);
        System.out.println("\nTime Calculation : ");
        System.out.println("Current Time : " + (duration) + " nanoseconds");

        duration = 0;
        for(int i = 0; i < 100; i++){
            thread = new Singlethread(100000);
            start = System.nanoTime();

            pi = thread.calcPi();

            end = System.nanoTime();

            duration +=  end - start;
        }

        long average = duration/100;

        System.out.println("For 100 iterations Average Time : " + (average) + " nanoseconds");

    }
}