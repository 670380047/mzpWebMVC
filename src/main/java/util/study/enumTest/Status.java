package util.study.enumTest;

/**
 * 枚举的构造方法
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/16 19:12
 * @File : City
 * @Software: IntelliJ IDEA 2019.3.15
 */
public enum Status {
    /**
     * 这里使用的是无参构造器
     *      定义了响应的构造器之后，才能使用对应的声明方式
     */
    NORTH,
    SOUTH,
    WEST,
    EAST,
    /**
     * 这里使用的是一个参数的构造器。属性值在下面有定义，是一个String类型的变量
     *      定义了响应的构造器之后，才能使用对应的声明方式
     */
    SPRING("春天"),
    SUMMER("夏天"),
    AUTUMN("秋天"),
    WINTER("冬天"),
    /**
     * 这里使用的是全参构造器。属性值在下面有定义，分别是一个int类型和一个String类型的变量
     *      定义了响应的构造器之后，才能使用对应的声明方式
     */
    FORBIDDEN(403,"权限不足"),
    NOT_FOUND(404,"路径找不到"),
    INNER_ERROR(500,"服务器内部错误");


    /**
     * 定义两个属性。然后就可以写构造方法，再然后就可以定义不同类型的枚举值了，
     *      比如：FORBIDDEN(403,"权限不足")。 根据构造方法可知，第一个参数，是code，第二个参数是value
     */
    private int code;
    private String value;

    /**
     * 构造参数默认是私有的。并且只允许是私有的
     */
    private Status() {
    }

    Status(String value) {
        this.value = value;
    }

    Status(int code, String value) {
        this.code = code;
        this.value = value;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }
}
