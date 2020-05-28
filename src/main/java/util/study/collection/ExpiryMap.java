package util.study.collection;/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2020/5/28 10:32
 * @File : ExpiryMap
 * @Software: IntelliJ IDEA 2019.3.15
 */

import java.util.*;

/**
 * 带有效期map。
 *      自声明的expiryMap用来存放key的过期时间。 key--expiryTime
 *      父类中的map，用来存放时间的数据内容。key--value
 * @Description:
 * @Author maozp3
 * @Date: 2020/5/28 10:32
 */
public class ExpiryMap<K,V> extends HashMap<K,V> {
    private static final long serialVersionUID = -7934194710817860100L;

    /**
     * 过期时间设置为3分钟，可以修改
     */
    private  long EXPIRY = 1000 * 60 * 3;

    /**
     * 自声明的一个HashMap,用来存放key的有效时间
     * 这个expiryMap用来存放数据内容。 key-value
     * 默认的容量大小16， 和加载因子 0.75.
     */
    private HashMap<K,Long> expiryMap = new HashMap<>();

    /**
     * 使用默认的容量大小16， 和加载因子 0.75.
     *   当实际容量超过 16*0.75时，哈希map就会扩容，使哈希表具有两倍的容量。
     */
    public ExpiryMap(){
        super();
    }

    /**
     * 定义带有过期时间的构造器
     *   定义容量左移4位：  10000（二进制） =  16 (十进制)   默认容量也是16位，加载因子为0.75
     * @param defaultExpiryTime
     */
    public ExpiryMap(long defaultExpiryTime) {
        this(1 << 4, defaultExpiryTime);
    }

    /**
     * 创建初始容量和过期时间
     * @param initialCapacity
     * @param defaultExpiryTime
     */
    public ExpiryMap(int initialCapacity ,long defaultExpiryTime){
        super(initialCapacity);
        this.EXPIRY = defaultExpiryTime;
    }

    /**
     * 自定义带有过期时间的put方法。
     * 本类中自声明的expiryMap的key-value，其中value用来存放这个key的过期时间。
     * 父类中的map的key-value，其中value用来存放key对应的真正的数据值。
     * @param key
     * @param value
     * @param expiryTime
     * @return  返回本次存入的数据值
     */
    public V put(K key,V value,long expiryTime){
        expiryMap.put(key,System.currentTimeMillis()+expiryTime);
        return super.put(key,value);
    }

    /**
     * 使用预定义的过期时间（EXPIRY）的put方法
     * @param key  key
     * @param value  value
     * @return  与key关联的value的值
     */
    public V put(K key,V value){
        expiryMap.put(key,System.currentTimeMillis()+EXPIRY);
        return super.put(key,value);
    }

    /**
     * 将指定映射中的所有映射复制到此映射。
     *      批量添加。过期时间应用的是默认的过期时间 EXPIRY。可通过创建带有过期时间的构造函数来修改EXPIRY
     *      如：new ExpiryMap<>(5000);
     * @param map
     */
    public void putAll(Map< ? extends K, ? extends V> map){
        for(Entry< ? extends K, ? extends V>entry : map.entrySet()){
            expiryMap.put(entry.getKey(),System.currentTimeMillis() + EXPIRY);
        }
        super.putAll(map);
    }

    /**
     * 获取父类中的值。
     * @param key  key
     * @return
     */
    public V get(Object key){
        if(key == null){
            return null;
        }
        if(checkExpiry(key,true)){
            return null;
        }
        return super.get(key);
    }


    /**
     * 检查key是否过期。
     * @param key  key
     * @param isRemoveSuper  是否移除该key
     * @return  true:过期   false:未过期
     */
    private boolean checkExpiry(Object key,boolean isRemoveSuper){
        /**
         * 如果不包含key，直接返回false
         */
        if(!expiryMap.containsKey(key)){
            return Boolean.FALSE;
        }

        /**
         * 获取自声明的 HashMap中key对应的value（这个value其实就是这个key的有效时间）
         */
        long expiryTime = expiryMap.get(key);
        /**
         * 与当前时间对比，看key是否过期.
         * true:已过期    false：未过期
         */
        boolean flag = System.currentTimeMillis() > expiryTime;

        if(flag){
            //是否移除父类中的key-value。  也就是实际的数据内容
            if(isRemoveSuper){
                super.remove(key);
            }
            // 移除过期时间
            expiryMap.remove(key);
        }
        return flag;
    }

    /**
     * 判断是否包含该key。
     * @param key   key
     * @return true：包含    false：不包含或已过期
     */
    public boolean containsKey(Object key){
        return !checkExpiry(key,true) && super.containsKey(key);
    }

    /**
     * 判断super.map中是否含有该值。
     * @param value
     * @return
     */
    public boolean containsValue(Object value){
        if(value == null){
            return Boolean.FALSE;
        }
        Set<Entry<K,V>> entrySet = super.entrySet();
        Iterator<Entry<K,V>> iterator = entrySet.iterator();
        /**
         * 遍历父类的map中，是否有指定的value。
         *  如果有的话，就检查该value对应的key是否过期，如果过期，就移除掉，并返回false
         */
        while (iterator.hasNext()){
            Entry<K,V> entry = iterator.next();
            if(value.equals(entry.getValue())){
                if(checkExpiry(entry.getKey(),false)){
                    iterator.remove();
                    return Boolean.FALSE;
                }else{
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 获取super.map中所有的value。
     * @return  values
     */
    public Collection<V> values(){
        Collection<V> values = super.values();
        if(values == null || values.size() < 1){
            return values;
        }

        Iterator<V> iterator = values.iterator();
        while(iterator.hasNext()){
            V next = iterator.next();
            /**
             * 判断super.map中是否含有该值。
             */
            if( !containsValue(next)){
                iterator.remove();
            }
        }
        return values;
    }

    /**
     * 获取entrySet。
     * @return  返回数据的entrySet。 是一个Set
     */
    public Set<Entry<K,V>> entrySet(){
        Set<Entry<K,V>> entrySet = super.entrySet();
        Iterator<Entry<K,V>> iterator = entrySet.iterator();
        while(iterator.hasNext()){
            Entry<K,V> entry = iterator.next();
            /**
             * 如果key过期（这里第二个入参是false，父类中map不移除），在迭代器中移除父类map中的元素
             */
            if(checkExpiry(entry.getKey(),false)){
                iterator.remove();
            }
        }
        return entrySet;
    }

    /**
     * 获取集合中的有效key-value。  （过期的或在这个过程中删除掉）
     * @return  有效的键值对的数量
     */
    public int size(){
        return entrySet().size();
    }

    /**
     * 判断map中元素是否为空。
     * @return  true:为空   false:不为空
     */
    public boolean isEmpty(){
        return entrySet().size() == 0;
    }

    /**
     * 移除指定的key
     * @param key   key
     * @return   如果成功移除，就返回移除的value。  否则返回null
     */
    public V remove(Object key){
        V value = null;
        if(key == null){
            return null;
        }
        if(containsKey(key)){
            value = super.get(key);
            this.expiryMap.remove(key);  // 移除过期时间
            super.remove(key);  //移除数据内容
        }
        return value;
    }

}
