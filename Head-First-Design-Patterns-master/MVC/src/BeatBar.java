import javax.swing.*;

public class BeatBar extends JProgressBar implements Runnable {
    JProgressBar progressBar;
    Thread thread;

    public BeatBar() {
        thread = new Thread(this);
        setMaximum(100);    // JProgressBar
        thread.start();
    }

    @Override
    public void run() {
        for (; ; ) {
            int value = getValue();
            value = (int) (value * .75);
            setValue(value);
            //System.out.println("Get value : " + value);
            repaint();
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }
}
