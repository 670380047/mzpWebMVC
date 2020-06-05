package util.study.reflect.old;

/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/3/26 15:00
 * @File : MethodBeReflected
 * @Software: IntelliJ IDEA 2019.3.15
 */
public class MethodBeReflected {
    private static String mName;
    private static int mAge;
    private static float mWeight;
    private String mSex;

    private void setAllValues(String name, int age, float weight, String sex) {
        mName = name;
        mAge = age;
        mWeight = weight;
        mSex = sex;
    }


    private String getmSex() {
        return mSex;
    }

    private void setmSex(String mSex) {
        this.mSex = mSex;
    }

    private static String getmName() {
        return mName;
    }

    protected static void setmName(String mName) {
        MethodBeReflected.mName = mName;
    }

    private static int getmAge() {
        return mAge;
    }

    private static void setmAge(int mAge) {
        MethodBeReflected.mAge = mAge;
    }

    private static float getmWeight() {
        return mWeight;
    }

    protected static void setmWeight(float mWeight) {
        MethodBeReflected.mWeight = mWeight;
    }
}
