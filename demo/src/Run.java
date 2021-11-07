
public class Run implements Runnable {

    private int count = 0;
    private Object obj = new Object();

    @Override
    public void run() {

        while (true){

            synchronized (obj) {



                if (count == 10){
                    break;
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName()+"第" + count + "个过山洞");
                count++;
            }
        }
    }
}
