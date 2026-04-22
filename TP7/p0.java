public class p0 {

    public static void main(String[] args) throws InterruptedException {


        Thread t4 = new Thread(() -> {
            try {
                p4.main(new String[] {});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t4.start();

        Thread.sleep(1000);

        p3 t3 = new p3();
        p2 t2 = new p2();
        p1 t1 = new p1();

        t3.start();
        t2.start();
        t1.start();
    }
}