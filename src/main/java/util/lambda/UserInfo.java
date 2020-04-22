package util.lambda;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/4/22 23:21
 * @File : UserInfo
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * @Description:
 * @Author maozp3
 * @Date: 2020/4/22 23:21
 */
public class UserInfo {
    String username;
    String password;
    int age;

    public UserInfo(){};

    public UserInfo(int age) {
        this.age = age;
    }

    public UserInfo(String username) {
        this.username = username;
    }

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
