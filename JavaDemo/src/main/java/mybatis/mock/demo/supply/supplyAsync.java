package mybatis.mock.demo.supply;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p> @description: completableFuture使用 </p>
 * <p> @date: 2022/4/7 </p>
 *
 * @author: yao
 * @version: 1.0
 */
public class supplyAsync {

    /**
     * 需求
     * 进入餐厅--点餐--厨师炒菜--厨师打饭-小白开吃
     * --小白打王者--
     * 厨师炒菜与厨师打饭和小白打王者同时进行
     */
    @Test
    public void test01() {
        System.out.println(Thread.currentThread() + "我进入餐厅");
        System.out.println(Thread.currentThread() + "我点餐点了一个番茄炒蛋以及一个米饭");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + "厨师炒菜");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(Thread.currentThread() + "厨师打饭");
                TimeUnit.MILLISECONDS.sleep(100);
                return "番茄炒蛋和米饭做好了";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        });
        System.out.println(Thread.currentThread() + "小白打王者");
        System.out.println(Thread.currentThread() + cf1.join() + "小白开吃");
    }

    /**
     * 需求
     * 进入餐厅--点餐--厨师炒菜--服务员打饭-小白开吃
     * --小白打王者--
     * 服务员打饭是在厨师炒菜之后的
     */
    @Test
    public void test02() {
        System.out.println(Thread.currentThread() + "我进入餐厅");
        System.out.println(Thread.currentThread() + "我点餐点了一个番茄炒蛋以及一个米饭");
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + "厨师炒菜");
                TimeUnit.MILLISECONDS.sleep(200);
                return "番茄炒蛋做好了";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }).thenCompose(result -> CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread() + "服务员打饭");
                TimeUnit.MILLISECONDS.sleep(100);
                return result + "米饭做好了";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }));
        System.out.println(Thread.currentThread() + "小白打王者");
        System.out.println(Thread.currentThread() + cf1.join() + "小白开吃");
    }
}
