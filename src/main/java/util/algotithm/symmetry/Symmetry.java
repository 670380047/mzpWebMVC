package util.algotithm.symmetry;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2019/7/11 17:54
 * @File : Symmetry
 * @Software: IntelliJ IDEA 2019.3.15
 */

/**
 *
 * @author maozp3
 * @description:  求对称字符串的算法
 * @date: 2019/7/11 17:54
 */
public class Symmetry {
    public static void main(String[] args) {
        String str = "agooglenoooon";
        System.out.println("输入的字符串："+str);
        manacher(str);
    }

    /**
    * description: 求对称字符串. 马拉车求最大回文串，算法复杂度为线性：o(n)
    * @author maozp3
    * @date: 20:47 2019/7/11
    * @param: [str]
    * @return java.lang.String
    */
    public static String manacher(String str){
        //将字符串变为字符数组
        char strArrays[] = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder("$#");
        //预处理，每个字符左右都加上#(为了区分奇偶；这样处理得到的回文串（如果有）都是奇数。)，
        // 并且在字符串的第一个位置前加上$（为了求回文串的起始位置。加上$之后，总字符串为偶数，但回文串（如果有）还是奇数）
        for(int i = 0; i< strArrays.length;i++){
            stringBuilder.append(strArrays[i]);
            stringBuilder.append("#");
        }
        //再次把字符串转换为数组。因为下面操作时用的是字符串数组。
        strArrays = stringBuilder.toString().toCharArray();
        System.out.println("预处理后："+stringBuilder);
        //开始进行回文串查找
        int id=0;   //能达到最右端的回文串的中心点的下标
        int mx=0;   //回文串所能达到的最后端的位置的下标
        int radius=0;  //目标回文串的半径长度
        int center=0;      //目标回文串的中心位置坐标
        //len[]是一个长度和预处理之后的字符串长度相等的数组。
        //其中len[i]表示预处理之后的字符串 strArrays[i]所对应的的回文串的半径长度
        int len[] = new int[strArrays.length];
        for(int i=1;i<strArrays.length;i++){
            //2*id-i是下标i关于中心点下标id的对称点j。
            //len[i](半径)取len[j]和mx-i中较小的一个的原因是：先保证这一串回文串都在以id为中心点下标对应的大半径之内。至于大半径之外部分还有没有满足回文串的，就由下面的while循环来匹配了
            len[i] = mx > i? Math.min(len[2*id-i],mx-i):1;  //关键算法
            if(i+len[i]<strArrays.length){  //验证边界
                while(strArrays[i+len[i]] == strArrays[i-len[i]]){
                    ++len[i];
                    if(i+len[i]>=strArrays.length){   //验证边界
                        break;
                    }
                }
            }
            if(mx<i + len[i]) {
                mx = i + len[i];
                id = i;
            }
            if(radius<len[i]){
                radius = len[i];
                center = i;
            }
        }
        //最大回文串的起始位置：起始位置=（中心点下标-回文串半径）/2
        int beginIndex = (center-radius)/2;
        //最大回文串的长度：长度=最大回文串半径-1
        int maxLength = radius-1;

        //要截取的回文串的起始位置 = 最大回文串的起始位置
        int startIndex = beginIndex;
        //要截取的回文串的结束位置 = 要截取的回文串的起始位置 + 长度 -1；
        int endIndex = startIndex + maxLength;
        str = str.substring(startIndex,endIndex);
        System.out.println("结果："+str);
        return str;
    }
}
