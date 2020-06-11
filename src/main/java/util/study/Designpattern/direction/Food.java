package util.study.Designpattern.direction;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/6/11 17:47
 * @File : Food
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 * 装饰者模式： 食物类
 * @Description:
 * @Author maozp3
 * @Date: 2020/6/11 17:47
 */
public class Food {
    private String foodName;

    public Food() {
    }

    public Food(String foodName) {
        this.foodName = foodName;
    }

    public String make(){
        return this.foodName;
    }

    public Food testFood(){
        return this;
    }
}
