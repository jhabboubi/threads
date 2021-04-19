import java.util.Random;

// Two threads one to produce messages and one to consume messages
// Deadlock
// if uncomment wait and notify code will work else will get into deadlock

public class MainRunner {
    public static void main(String[] args) {
        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

class Message {
    //fields

    // a message
    private String message;
    // no message to read set to true
    private boolean empty = true;

    // read method to be used by the consumer
    // the empty variable is locked and can't be released
    // only one synchronized method can run at a time
    public synchronized String read() {
        // while true
        while (empty) {

//            try {
//                wait();
//            } catch(InterruptedException e) {
//
//            }

        }
        empty = true;
//        notifyAll();
        return message;
    }

    // write method to be used by the producer
    public synchronized void write(String message) {
        // while not true
        while (!empty) {
//            try {
//                 wait();
//            } catch(InterruptedException e) {
//
//            }

        }
        empty = false;
        this.message = message;
//         notifyAll();
    }
}

class Writer implements Runnable {
    // fields
    private Message message;

    //constructor
    public Writer(Message message) {

        this.message = message;
    }

    @Override
    public void run() {
        String messages[] = {
                "This is line 1",
                "This is line 2",
                "This is line 3",
                "This is line 4"
        };

        Random random = new Random();

        for (int i = 0; i < messages.length; i++) {
            message.write(messages[i]);
            try {
                // sleep up to 2 sec
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
        message.write("Finished");
    }
}


class Reader implements Runnable {
    //fields
    private Message message;

    //constructor
    public Reader(Message message) {

        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        // loop throughout the messages until latestMessage equals Finished then exit for loop
        for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
    }
}

