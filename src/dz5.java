public class dz5 {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        method1();
        method2();
    }

    private static void method1(){
        float[] arr = new float[size];
        for (int i = 0; i < size; i++){
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println(b - a);

    }

    private static void method2(){
        float[] arr = new float[size];
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        for (int i = 0; i < size; i++){
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        Thread runnableThread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++){
                    a1[i] = (float)(a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        Thread runnableThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++){
                    a2[i] = (float)(a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });
        runnableThread1.start();
        runnableThread2.start();
        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }
}
