package mybatis.mock.demo.optial;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * <p> @description: 测试Optail的特性 </p>
 * <p> @date: 2022/4/19 </p>
 *
 * @author: yao
 * @version:
 */
@Slf4j
public class OptialTest {
    @Test
    public void testOptial() {
        User user = new User();
        user.setId("1");
        // 报错 指针异常
//        Optional<User> userOptional = Optional.of(user);
//        orElse会执行里面的方法，但是接受返回值
        User userCopy = Optional.ofNullable(user).orElse(createUser());

        User user1 = Optional.ofNullable(user).orElseGet(() -> createUser());
        log.info(userCopy.toString());
    }

    private User createUser() {
        User user = new User();
        user.setName("张三");
        user.setAge("22");
        return user;
    }

    class User {
        private String name;
        private String age;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age='" + age + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }
}
