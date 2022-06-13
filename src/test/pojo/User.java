package test;

import java.io.Serializable;

/**
 * @author zwx
 * @date 2022-06-11 20:54
 */
public class User  implements Serializable {
    private static final long serialVersionUID = 7526472295622776147L;

    private String name;
    private Integer age;

    public User() {

    }
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
