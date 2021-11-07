

public class Work3 {


    public static void main(String[] args) {

        Run run = new Run();

        for (int i = 0; i < 10; i++) {
            new Thread(run, "zhangsan" + i).start();
        }

    }
}
