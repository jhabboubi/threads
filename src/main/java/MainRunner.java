public class MainRunner {
    public static void main(String[] args) throws InterruptedException {
        //create new object of Countdown here
        Countdown countdown = new Countdown();



        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();


    }


}

//class to countdown to 10 and change color depending on name of thread
class Countdown {
    private int i;
    public void doCountdown(){
        String color;
        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_RED;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_CYAN;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }
        // try this object
        // color here is local variable
        synchronized (color) {
            for (this.i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }
    }
}

//class to run on a thread
class CountdownThread extends Thread {
    private Countdown threadCountdown;
    public CountdownThread(Countdown countdown){
        this.threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountdown();

    }
}
