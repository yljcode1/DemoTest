package com.yao.springtest.buildertest.entity;

/** builer模式test
 * @author xiao.K
 * @date 2021/10/28
 */
public class User {
    private String username;
    private String password;
    private Integer age;

    private static User.UserBuild builder() {
        return new User.UserBuild();
    }

    public static final class UserBuild {
        private String username;
        private String password;
        private Integer age;

        public UserBuild() {
        }

        public UserBuild(String username, String password, Integer age) {
            this.username = username;
            this.password = password;
            this.age = age;
        }

        public String getUsername() {
            return username;
        }

        public User.UserBuild username(String username) {
            this.username = username;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public User.UserBuild password(String password) {
            this.password = password;
            return this;
        }

        public Integer getAge() {
            return age;
        }

        public User.UserBuild age(Integer age) {
            this.age = age;
            return this;
        }

        public User.UserBuild build() {
            return new UserBuild(this.username,this.password,this.age);
        }
    }

    public static void main(String[] args) {
         User.builder().username("username").password("hello").age(12).build();
    }
}
