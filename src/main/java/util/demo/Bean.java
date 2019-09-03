package util.demo;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/8/15 18:20
 * @File : Bean
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:
 * @date: 2019/8/15 18:20
 */
class Bean {
    int a;

    public Bean() {
    }

    public Bean(int a) {
        this.a = a;
    }

    public void swap(Bean b){
        int tem;
        tem = b.getA();
//        b.setA(this.a);
        b.a = this.a;
        this.a = tem;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
