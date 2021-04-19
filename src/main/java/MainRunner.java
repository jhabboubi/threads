public class MainRunner {
    /*
    interference and solutions
    1. show two objects
    2. show synchronized keyword on method declaration
    3. show synchronized block for the loop with color or this object (can't use primitives type - they are stored in the stack )
     */
    // creating a race condition
    // no interference if using two objects of countdown, this is not a real solution e.g. (bank account object)
    public static void main(String[] args) throws InterruptedException {
        // one countdown passed to both threads
        Countdown countdown = new Countdown();
        // one solutions is to use two different objects
        //Countdown countdown2 = new Countdown();


        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        // pass countdown2 here
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();


    }


}


class Countdown {
    private int i;

    // add keyword synchronized
    public void doCountdown() {
        // color is a local variable
        String color;
        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = ThreadColor.ANSI_RED;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_CYAN;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }
       //  synchronized (color) {  // try "this" keyword here
            for (this.i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
      //  }
    }
}

class CountdownThread extends Thread {
    //field
    private Countdown threadCountdown;

    //constructor
    public CountdownThread(Countdown countdown) {

        this.threadCountdown = countdown;
    }

    @Override
    public void run() {
        threadCountdown.doCountdown();

    }
}
