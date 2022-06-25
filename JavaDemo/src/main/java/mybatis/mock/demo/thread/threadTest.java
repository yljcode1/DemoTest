package mybatis.mock.demo.thread;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 优雅的停止线程
 *
 * @date: 2022/5/8
 * @author: yao
 */
@Component
public class threadTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("线程1接收中断消息，中断线程....中断标记" + Thread.currentThread().isInterrupted());
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "线程正在执行....");
            }
        }, "interrupt-1");
        thread.start();

        //创建2线程
        new Thread(() -> {
            int i = 0;
            while (i < 20) {
                System.out.println(Thread.currentThread().getName() + "线程正在执行...");
                if (i == 8) {
                    System.out.println("设置线程中断....");
                    //通知线程1 设置中断通知
                    thread.interrupt();

                }
                i++;
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "interrupt-2").start();
    }

}
