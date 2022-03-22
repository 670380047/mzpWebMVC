//package util.study.gupao.thread;
//
//
//import util.listtest.binaryTree.Node;
//
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ThreadLocalRandom;
//
//import static com.sun.xml.internal.fastinfoset.util.ValueArray.MAXIMUM_CAPACITY;
//
///**
// * @version: java version 1.7+
// * @Author : mzp
// * @Time : 2021/3/8 19:58
// * @File : Demo
// * @Software: IntelliJ IDEA 2019.2.04
// */
//public class Demo {
//
//    final V putVal(K key, V value, boolean onlyIfAbsent) {
//        // 这里决定key和value都不能为null
//        if (key == null || value == null) throw new NullPointerException();
//        /**
//         * 根据key的hashCode来计算hash值。内部“&”了一个0x7fffffff值（二进制31个1），
//         *      保证了这个hash值的最高为一定为0。（因为&的这个值的最高为是0），
//         *      即这个hash值一定是个正数。
//         */
//        int hash = spread(key.hashCode());
//        //找个binCount的值使用来计算数组中节点的个数的，用来考虑是否构建红黑树。
//        int binCount = 0;
//        //for循环里的条件是，用一个本地变量Node数组tab来保存全局变量table。
//        // （在JVM中访问局部变量表中的变量，要比全局对象效率要高）
//        //接下来是自旋的插入操作。
//        for (Node<K,V>[] tab = table;;) {
//            //f是头结点。n是数组大小，i是数组下标，fh是头结点的hash值
//            Node<K,V> f; int n, i, fh;
//            /**
//             * 初始化操作：当数组为空是，就会触发数组的初始化操作，调用initTable()方法。
//             */
//            if (tab == null || (n = tab.length) == 0)
//                tab = initTable();
//            /**
//             * 走到这里，说明数据已经初始化过了。
//             * (n - 1) & hash 是用来计算数组下标的，然后把下标赋值给i。
//             * f = tabAt(tab,i)方法是根据偏移量从主存中直接获取tab数组中下标为 i 的元素，保证可见性。
//             *      即获取某个数组下标中的第一个元素，即头结点，然后赋值给变量 f
//             * 如果数组下标 i 这个位置没有元素的话，那就创建个新的Node节点，把新的key-value放到这个位置。
//             *      其中casTabAt(tab, i, null, Node)方法和tabAt()方法一样，都是直接操作主存的，
//             *      直接把数据更新到主存中，保证可见性。
//             */
//            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
//                if (casTabAt(tab, i, null,
//                        new Node<K,V>(hash, key, value, null)))
//                    break;                   // no lock when adding to empty bin
//            }
//            /**
//             * 如果走到这个条件里面的话，是说明当前有线程正在对map进行扩容。
//             *      然后helpTransfer()方法是协助扩容。
//             *
//             *  关于这个 hash = MOVED的节点。是因为在扩容的方法里面有个ForwardingNode节点对象，
//             *  他得 hash属性就是 MOVED。 这个节点是表示“在扩容数据迁移”的时候，
//             *      发现某个数组中没有节点的时候，会插入一个这个节点，表示这个数组“已经迁移过了，请跳过这个节点”
//             *      如果在这里遇到 这个节点的话，说明有线程正在进行扩容,请当前线程协助扩容，即进入helpTransfer()
//             */
//            else if ((fh = f.hash) == MOVED)
//                tab = helpTransfer(tab, f);
//            else {
//                /**
//                 * 这里就是发生冲突，要替换值或者是构建链表了。
//                 * 数组中第一个位置为null的情况在前面已经判断过了。走到这里说明数组的第一个元素不为空，即产生冲突了。
//                 *      那么接下来就是：要么替换元素，要么构建链表甚至是红黑树。
//                 */
//                V oldVal = null;
//                // 这里就是锁定数组下标为 i 的位置的第一个元素节点
//                synchronized (f) {
//                    //进入临界区第一件事也是判断数组下表为i的第一个元素有么有发生变化。
//                    //如果发生变化的话，即 不等于头结点f了，那就什么都不做，准备第二次自旋了。
//                    //如果没有变化的话，就进入if条件的的执行体中。
//                    if (tabAt(tab, i) == f) {
//                        //如果hash值是个正数，说明这是一个正常的节点。
//                        if (fh >= 0) {
//                            //走到这里说明下表为i这个位置，至少已经有一个元素了。
//                            binCount = 1;
//                            for (Node<K,V> e = f;; ++binCount) {
//                                K ek;
//                                //这里就是判断新来的key是不是已经在数组中了，
//                                //  如果已经在了，就用新值替换旧值，旧的值将来会被返回，然后结束循环
//                                if (e.hash == hash &&
//                                        ((ek = e.key) == key ||
//                                                (ek != null && key.equals(ek)))) {
//                                    oldVal = e.val;
//                                    if (!onlyIfAbsent)
//                                        e.val = value;
//                                    break;
//                                }
//                                //如果不在的话，就看当前节点的后继节点是不是null的，
//                                //  如果是null的，那就直接用新的key-value创建一个Node节点，
//                                //  然后插入到链表的尾部。然后结束循环
//                                Node<K,V> pred = e;
//                                if ((e = e.next) == null) {
//                                    pred.next = new Node<K,V>(hash, key,
//                                            value, null);
//                                    break;
//                                }
//                            }
//                        }
//                        //如果前面判断头结点的hash值不是正数的话，头结点有可能是一棵树
//                        //所以这里判断头结点f是不是一个树
//                        else if (f instanceof TreeBin) {
//                            Node<K,V> p;
//                            binCount = 2;
//                            //将新的节点放入到红黑树种。如果是新插入的话，会返回null
//                            //      如果是覆盖旧的节点的话，会返回旧的节点
//                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
//                                    value)) != null) {
//                                oldVal = p.val;
//                                if (!onlyIfAbsent)
//                                    p.val = value;
//                            }
//                        }
//                    }
//                }
//                /**
//                 * 执行完插入动作之后，就会判断数组节点 i 中元素的个数。
//                 * 如果元素个数大于0，就会判断是否要传唤为红黑树
//                 */
//                if (binCount != 0) {
//                    /**
//                     * 如果链表长度大于等于0，就要尝试转化为红黑树了。
//                     * 当然在treeifyBin()方法里面还判断了：如果当前数组的所有元素个数
//                     * 大于等于64的时候，才会进行红黑树的转换。
//                     *
//                     */
//                    if (binCount >= TREEIFY_THRESHOLD)
//                        treeifyBin(tab, i);
//                    //如果oldVal不为null的话，说明本次put进来的元素不是插入，而是发生了替换
//                    //  这里把被替换的旧值返回出去
//                    if (oldVal != null)
//                        return oldVal;
//                    break;
//                }
//            }
//        }
//        /**
//         * 最后这里是计算数组中所有元素的值。
//         *      第一个参数是要添加的值。
//         *      第二个参数binCount如果 <=1 的话，只需要检查是否有多线程在进行元素总量的增加操作
//         *          如果 >=0 的话， 就要检查是否需要扩容了。
//         *          因为两个范围有交叉。先判断的第一种情况，<=1,如果满足了就会直接return，不判断第二种。
//         */
//        addCount(1L, binCount);
//        return null;
//    }
//
//
//    private final Node<K,V>[] initTable() {
//        //tab表示的是整个数组。
//        // sc 表示的是 sizeCtl这个变量,在初始化的时候，默认是。当有线程正在初始化的时候，是-1
//        //      初始化完成后，就表示数组的阈值了。
//        Node<K,V>[] tab; int sc;
//        //如果数组为null，或者 数组的长度为0。都表示需要初始化，
//        //      比如 Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n]，数组长度就不是0了，是n
//        //这里用while是因为，CAS操作可能失败
//        while ((tab = table) == null || tab.length == 0) {
//            //把sizeCtl的值赋给sc。 同时如果sizeCtl的值<0,也就是-1。就说明有线程已经在进行初始化了
//            //      那就调用Thread.yield()让出CPU时间片。下次获得时间片之后再次循环，有可能发现还是<0，
//            //      那就一直在这里自旋，直到下面的初始化操作完成，此时数组不为null，且长度已经不为0了，
//            //      长度可能是默认16。
//            if ((sc = sizeCtl) < 0)
//                Thread.yield(); // lost initialization race; just spin
//            // 第一个线程就会用CAS把SIZECTL的值改为-1，表示当前有现成正在进行初始化。
//            //修改成功之后，就去进行初始化操作。
//            //保证线程安全。此时的SIZECTL就相当于一把锁。
//            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
//                try {
//                    if ((tab = table) == null || tab.length == 0) {
//                        //如果指定了想要初始化的大小sc的话，就初始化为我们指定的大小
//                        //      没有指定的话，就初始化为默认的16个，
//                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
//                        //初始化n个大小的数组
//                        @SuppressWarnings("unchecked")
//                        Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
//                        //然后把初始化的数组赋值给全局变量。
//                        table = tab = nt;
//                        //把sc的值更改为阈值： 16-(16/2/2)=16-4=12
//                        sc = n - (n >>> 2);
//                    }
//                } finally {
//                    //此时SIZECTL的值就是我们数组的阈值
//                    sizeCtl = sc;
//                }
//                //结束循环。
//                break;
//            }
//        }
//        //返回初始化好的数组
//        return tab;
//    }
//
//
//    private final void addCount(long x, int check) {
//        ConcurrentHashMap.CounterCell[] as; long b, s;
//        //在if条件里面的操作是，先判断counterCells是否为空，
//        //如果为counterCells为空，那就再尝试用CAS直接更新BASECOUNT的值，如果更新成功，那就说明
//        //      没有线程竞争，可以继续使用baseCount来保存元素的个数。
//        //      如果CAS更新失败了，说明存在竞争，那本次就不适合用baseCount，需要用到counterCells了。
//        if ((as = counterCells) != null ||
//                !U.compareAndSwapLong(this, BASECOUNT, b = baseCount, s = b + x)) {
//            ConcurrentHashMap.CounterCell a; long v; int m;
//            //判断是否有冲突的标识。默认是没有冲突
//            boolean uncontended = true;
//            /**
//             * 这里有几个判断
//             * 1. 计数表为空则直接调用fullAddCount()。这里面有初始化操作
//             * 2. 从计数表中随机取出一个数组的位置为空，如果为空，就调用fullAddCount()
//             * 3.通过CAS修改counterCells随机位置的值，如果修改失败，则说明存在并发竞争的情况，
//             *      那么就调用fullAddCount()。
//             *
//             * 关于产生随机数的问题。因为Random在线程并发的时候会有相同的随机数，所以使用
//             *      ThreadLocalRandom.getProbe()来解决这个问题，而且性能也比random高。
//             *
//             */
//            if (as == null || (m = as.length - 1) < 0 ||
//                    (a = as[ThreadLocalRandom.getProbe() & m]) == null ||
//                    !(uncontended =
//                            U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))) {
//                fullAddCount(x, uncontended);
//                return;
//            }
//            //check表示当前的链表长度。链表长度如果小于等于1的话，就退出。不需要扩容。
//            if (check <= 1)
//                return;
//            //这里可以获取当前CMH的元素个数。
//            s = sumCount();
//        }
//        /**
//         * 判断table是否需要扩容
//         * 这段逻辑是判断当前的元素节点是否需要扩容的。check表示当前节点的元素个数
//         */
//        if (check >= 0) {
//            ConcurrentHashMap.Node<K,V>[] tab, nt; int n, sc;
//            /**
//             * 1.如果当前table的总数量 s 大于 阈值 sizeCtl
//             * 2. 并且table不为null
//             * 3. 并且当前table的总长度还没有达到最大值 2^30
//             *
//             * 以上3条全部满足的时候，就会触发CHM的扩容操作。
//             */
//            while (s >= (long)(sc = sizeCtl) && (tab = table) != null &&
//                    (n = tab.length) < MAXIMUM_CAPACITY) {
//                //这里是获取一个扩容戳。下面讲解这个扩容戳rs。这里得知道这个扩容戳rs一定是一个负数。
//                //默认n=16时，得到 rs = 0000 0000 0000 0000 1000 0000 0001 1011
//                int rs = resizeStamp(n);
//                //如果rs小于0的话，说明线程正在扩容。
//                if (sc < 0) {
//                    /**
//                     * 1. 第一个条件。比较的是扩容戳。sc的扩容戳在高16位，现在无符号右移16位之后，
//                     *      就可以和计算出来的扩容戳rs做比较了。如果table的扩容还没有完成的话，
//                     *      那么sizeCtl高16位和计算出来的扩容戳是一样的。
//                     *      sc无符号右移16位，就是sc带着符号位一起右移16位，然后最高位补0。
//                     *      如果无符号右移之后不等于rs的话,那说明进行过扩容完成了。否则的话，两个值是一样的。
//                     *      因为rs是扩容戳（扩容完成后，新的扩容戳就会小）。这里只要还没有扩容完成，
//                     *      那么就是一样的 sc >>> RESIZE_STAMP_SHIFT 的结果就是 rs。
//                     *      所以第一个条件成了的话，说明扩容已经完成了。不需要协助了。
//                     * 2.第二个条件sc = rs + 1 。说明扩容结束。
//                     *      重点，第一个条件中的无符号右移，不会改变sc的值。所以sc的值还是rs的值。
//                     *      m-1=0,说明有0个线程。即扩容结束了。
//                     * 3.第三个条件：MAX_RESIZERS = 1111111111111111  （16个1）。表示的是最多可以允许2^16 -1 个线程参与扩容
//                     *      如果sizeCtl 的值，等于 rs+(2^16) -1。说明参与扩容的线程已经达到最大值了。不允许再协助扩容了
//                     *
//                     * 4.第四个条件：扩容完成后，会把nextTable设为null
//                     * 5.第五个条件: transferIndex <= 0。表示所有扩容任务都已经领取完毕了。
//                     */
//                    if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
//                            sc == rs + MAX_RESIZERS || (nt = nextTable) == null ||
//                            transferIndex <= 0)
//                        break;
//                    //这里尝试给sizeCtl增加1，表示又多了一个线程要去协助扩容
//                    if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1))
//                        transfer(tab, nt);
//                }
//                // 初次扩容。 把扩容戳右移16位（就变成负数了），然后+2
//                else if (U.compareAndSwapInt(this, SIZECTL, sc,
//                        (rs << RESIZE_STAMP_SHIFT) + 2))
//                    //调用扩容方法
//                    transfer(tab, null);
//                s = sumCount();
//            }
//        }
//    }
//
//
//
//    private final void fullAddCount(long x, boolean wasUncontended) {
//        // 这个h保存的是当前线程产生的随机数.用来计算counterCells数组的下标
//        //      计算的方法也是: (n-1)& h     其中 n是数组大小
//        //这个h会在下面“递增”，直到4操作成功
//        int h;
//        if ((h = ThreadLocalRandom.getProbe()) == 0) {
//            ThreadLocalRandom.localInit();      // force initialization
//            h = ThreadLocalRandom.getProbe();
//            //由于重新生成了Probe（随机数探测），将未冲突标记设置为true
//            wasUncontended = true;
//        }
//        //这个变量是控制是否可以扩容的。如果为false的话，会在下面扩容前的那个else if中被拦截下来，
//        //      即本次循环不可以扩容。然后会被改成true，表示下次循环可以进行扩容（当然，如果下次循环
//        //      在中途就更改size的值成功了，那就不需要扩容了）
//        boolean collide = false;                // True if last slot nonempty
//        // 自旋
//        for (;;) {
//            CounterCell[] as; CounterCell a; int n; long v;
//            /**
//             * 这个if说的是counterCells不为空时的情况，已经初始化过了。
//             * 这里是当存在竞争时，往counterCells数组里面存入值的
//             */
//            if ((as = counterCells) != null && (n = as.length) > 0) {
//                //这里是当前位置没有被占用的情况。
//                //通过随机数来获取counterCells的数组下标。 和hash表获取索引是一样的
//                //如果当前获取的元素下标位置没有元素，那就表示没有线程使用过这里，也就是没有竞争，
//                //      那就继续往下，准备创建个CounterCell节点放在这位置
//                if ((a = as[(n - 1) & h]) == null) {
//                    //这个cellsBusy就相当于是一把锁。等于0时，相当于没有别人使用
//                    //创建CounterCell元素、扩容时，都会被锁定为1，表示繁忙。
//                    if (cellsBusy == 0) {            // Try to attach new Cell
//                        //根据本次新增的节点数x，来创建一个CounterCell
//                        CounterCell r = new CounterCell(x); // Optimistic create
//                        //上一步创建完CounterCell元素之后，下一步会把CounterCell元素放入到CounterCells数组中去，
//                        //      在放入数组之前，需要先把锁更改为1，表示繁忙中，其他线程进不来
//                        if (cellsBusy == 0 &&
//                                U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
//                            //这个变量created表示是否已经完成了添加。
//                            boolean created = false;
//                            try {               // Recheck under lock
//                                CounterCell[] rs; int m, j;
//                                //再次判断一下当前下标位置有没有被占用，没有的话，我们就把新节点放进这个位置。
//                                if ((rs = counterCells) != null &&
//                                        (m = rs.length) > 0 &&
//                                        rs[j = (m - 1) & h] == null) {
//                                    rs[j] = r;
//                                    //表示添加完成。后面就可以break了。某则的话，就continue继续循环。
//                                    created = true;
//                                }
//                            } finally {
//                                cellsBusy = 0;
//                            }
//                            if (created)
//                                break;
//                            continue;           // Slot is now non-empty
//                        }
//                    }
//                    collide = false;
//                }
//                // 说明在addCount方法中，CAS失败了，并且获得的probe的值不为空
//                else if (!wasUncontended)       // CAS already known to fail
//                    wasUncontended = true;      // Continue after rehash
//                //这里是当发现某个数组已经有值了，那就尝试用CAS对这个值进行改变，比如增加1
//                //      如果成功了，就直接返回。
//                //      如果失败了，下面的逻辑就会考虑需不需要扩容了，
//                else if (U.compareAndSwapLong(a, CELLVALUE, v = a.value, v + x))
//                    break;
//                //如果其他线程已经给建立了新的counterCells数组（比如扩容），
//                //    或者counterCells的元素个数大于Cpu核心数（巧妙的设计，因为线程并发不会超过CPU核心数）
//                else if (counterCells != as || n >= NCPU)
//                    //设置当前线程的循环失败。不进行扩容
//                    collide = false;            // At max size or stale
//                //重置collide的状态，下次循环会跳过这一步，进行扩容了。
//                else if (!collide)
//                    collide = true;
//                /**
//                 * counterCells需要扩容了。
//                 * 前面的if和else if都没有满足的话，说明需要counterCells扩容了
//                 *
//                 * 我们尝试锁定cellsBusy这个值。cellsBusy=0，说明没有其他线程在使用（比如没有其他线程在扩容）
//                 *      如果失败的话。我们就取随机数的下一位，把上面的判断重新进行一轮。
//                 *      也许换一个下标位置就可以更改CMH的大小了。也有可能再来一轮别人就扩容成功了，我就可以直接往里面更新数据了。
//                 */
//                else if (cellsBusy == 0 &&
//                        U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
//                    //准备给counterCells扩容。
//                    try {
//                        //这个if是看一下counterCells和之前相比有没有变化（如果有变，说明别人已经扩容过了）
//                        if (counterCells == as) {// Expand table unless stale
//                            //重新创建一个数组大小是两倍的counterCells数组。
//                            CounterCell[] rs = new CounterCell[n << 1];
//                            //然后循环把元素迁移到新的counterCells数组里面
//                            for (int i = 0; i < n; ++i)
//                                rs[i] = as[i];
//                            //把新的rs数组复制给全局变量counterCells
//                            counterCells = rs;
//                        }
//                    } finally {
//                        //解除繁忙
//                        cellsBusy = 0;
//                    }
//                    collide = false;
//                    //扩容完之后，用当前的随机数从新来判断一轮，看看是否可以更新成功了。
//                    continue;                   // Retry with expanded table
//                }
//                // 把随机数“递增”一下，然后重新来一轮判断。看能否“插入/更新/扩容”成功，最后结果都是保证插入/更新成功
//                h = ThreadLocalRandom.advanceProbe(h);
//            }
//            /**
//             * 走到这里说明。counterCells没有初始化。
//             * 1. cellsBusy = 0表示空闲状态
//             * 2. counterCells == as 表示counterCells没有变化过（说明他还是没有初始化）
//             * 3. 要把cellsBusy改为 1，表示繁忙状态
//             *
//             * 三个条件都成功之后，就开始初始化了。
//             *
//             */
//            else if (cellsBusy == 0 && counterCells == as &&
//                    U.compareAndSwapInt(this, CELLSBUSY, 0, 1)) {
//                //标识是否初始成功了
//                boolean init = false;
//                try {                           // Initialize table
//                    //再判断一下：如果counterCells没有被更改过的话（即还是未初始化状态 ），那就准备初始化
//                    if (counterCells == as) {
//                        //初始化默认创建的是两个2元素的counterCells数组
//                        CounterCell[] rs = new CounterCell[2];
//                        //然后随机挑选一个下标的位置，把创建的counterCell给放进去
//                        rs[h & 1] = new CounterCell(x);
//                        //把新初始化的rs赋值给全局变量counterCells
//                        counterCells = rs;
//                        init = true;
//                    }
//                } finally {
//                    //解除繁忙状态
//                    cellsBusy = 0;
//                }
//                //退出循环
//                if (init)
//                    break;
//            }
//            /**
//             * 提升效率
//             *
//             * 如果counterCells数组为null，同时当前线程想要初始化的缺发现cellsBusy处于繁忙状态（可能是其线程
//             *      正在初始化）。那么当前线程就尝试直接在baseCount上来增加size的数量。
//             *      如果成功的话，就直接跳出循环返回。如果失败的话，就继续自旋。
//             */
//            else if (U.compareAndSwapLong(this, BASECOUNT, v = baseCount, v + x))
//                break;                          // Fall back on using base
//        }
//    }
//
//
//    private final void transfer(Node<K,V>[] tab, Node<K,V>[] nextTab) {
//        // n是就的table的大小。 stride是每个线程参与扩容负责的步长。
//        int n = tab.length, stride;
//        /**
//         * 确定单个线程扩容的步长
//         * NCPU是CPU核心数。
//         * 如果是单核CPU，那就选择当前容量作为扩容的步长。
//         * 如果是多核CPU，就是用“容量除以8，在除以CPU核心数”，如果结果小于16，那就取16的值。
//         */
//        if ((stride = (NCPU > 1) ? (n >>> 3) / NCPU : n) < MIN_TRANSFER_STRIDE)
//            stride = MIN_TRANSFER_STRIDE; // subdivide range
//        //nextTab仅在扩容的时候，不为null，表示下一张表。
//        //如果是第一个线程前来扩容的话，是null。
//        //如果nextTab不为空，说明是协助扩容。
//        if (nextTab == null) {            // initiating
//            try {
//                // 初始化一个新的数组。大小为原来的两倍
//                @SuppressWarnings("unchecked")
//                Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n << 1];
//                //把新表赋值给本地变量
//                nextTab = nt;
//            } catch (Throwable ex) {      // try to cope with OOME
//                sizeCtl = Integer.MAX_VALUE;
//                return;
//            }
//            //把新表赋值给全局变量nextTable
//            nextTable = nextTab;
//            //n是原数组的大小。这里假设是第二次扩容，原数组大小n=32。
//            //表示的是转移时的下标.也可以理解为“要有多少个数组下标没有处理”
//            transferIndex = n;
//        }
//        //这个是获取新数组的大小，这里假设是第二次扩容，新数组大小nextn=64
//        int nextn = nextTab.length;
//        /**
//         * 创建一个fwd节点，表示一个正在被迁移的Node，并且它的hash值为-1（MOVED），也就是前面在putCal()方法
//         *      中会遇到一个hash=MOVED的判断逻辑。它的作用是用来站位，表示原数组中位置i处的节点完成迁移后，
//         *      就会在 i 这个位置一个fwd来告诉其他线程，这个位置已经处理过了。
//         */
//        ForwardingNode<K,V> fwd = new ForwardingNode<K,V>(nextTab);
//        //首次推进为true。首次进来为true，是说明需要根据步长去计算本次扩容所负责的区间。
//        //      如果后面再次为true说明需要计算--i，即处理当前扩容区间的下一个节点，并转移数据。
//        //      如果为false，表示当前数组下标 i 的数据还没处理转移。当前下标 i 转移完之后，就又会
//        //      把advance设置为true，为的就是进行一次 --i，获取下一个需要处理的下标（从后往前遍历）。
//        boolean advance = true;
//        //判断是否已经扩容完成。true表示完成，直接return。
//        boolean finishing = false; // to ensure sweep before committing nextTab
//        /**
//         * 开始循环处理每个槽位中的链表元素。默认advace为真，通过CAS设置TransferIndex属性值，并初始化i和bound的值，
//         *      i指当前处理的槽位序号，bound指需要处理的槽位边界。先处理槽位15的节点。
//         *      [bound,i]是本线程本次要处理的扩容区间
//         */
//        for (int i = 0, bound = 0;;) {
//            Node<K,V> f; int fh;
//            while (advance) {
//                //nextIndex-1 当前线程扩容区间的上界，nextBound是下界。[nextBound,nextIndex)
//                int nextIndex, nextBound;
//                // 因为任务区间是[nextBound,nextIndex)也就是[bound,i]。因为bound是区间上限，i是区间下限
//                //      所以只要成功分配了任务，那么i一定会大于bound，就刻意跳出循环了，准备去转移数据，不需要
//                //      再划分区间了。
//                // 这里的自减操作是因为，i会作为数组的下标，开始遍历当前的扩容区间（因为--i，所以可以看出是从后往前开始遍历，）
//                //      当我们没转移完一个下标的时候，都会把advance重新设置为true，就会重新进入这个while循环，
//                //      目的就是为了进行一下 --i，级获得当前扩容区间内下一个需要扩容的下标。（可以看出是从后往前遍历的扩容区间）
//                if (--i >= bound || finishing)
//                    advance = false;
//                //每次循环，这个if里面会给 nextIndex 赋值。 transferIndex 表示的是当前数组中还未经过扩容处理的元素下标的个数。
//                //      当我们的 transferIndex 等于0的时候，说明所有数组元素都已经扩容转移完了，
//                //      那么本线程得不到协助扩容的任务了，因为已经没有任务了。
//                else if ((nextIndex = transferIndex) <= 0) {
//                    i = -1;
//                    advance = false;
//                }
//
//                /**
//                 * 通过CAS来修改transferIndex，为当前协助扩容的线程分配扩容的区间，
//                 *       当前线程处理的节点区间：[nextBound,nextIndex-1],比如说第一次扩容默认的就是[0,15]。
//                 *
//                 * 比如32扩容到64的时候： 此时第一个线程 nextIndex = transferIndex =32（表示当前扩容未处理的数组大小）。
//                 *      stride步长=16（表示每一个线程扩容负责处理的区间）。如果nextIndex > stride，即当前未处理的数组个数
//                 *      大于 当前的步长。所以可以把未处理的区间个数再次划分开来，划分出来一个步长来处理，剩下的留给下一个线程，
//                 *      或者是本线程的下一次循环。然后把剩余的未处理的个数通过CAS更新回 transferIndex 中去。
//                 *      当我们的 transferIndex 等于0的时候，说明所有数组元素都已经扩容转移完了，
//                 *      那么本线程得不到协助扩容的任务了，因为已经没有任务了。
//                 * 如果CAS更新成功的话，说明可以给当前线程分配扩容需要负责的区间了。
//                 *
//                 */
//                else if (U.compareAndSwapInt
//                        (this, TRANSFERINDEX, nextIndex,
//                                nextBound = (nextIndex > stride ?
//                                        nextIndex - stride : 0))) {
//                    // bound是本线程本次要处理的区间的上界。[bound,i]
//                    bound = nextBound;
//                    // i 是本线程本次要处理的区间的下界。[bound,i]
//                    i = nextIndex - 1;
//                    // advance = false。表示本次任务已经分配完毕，可以跳出循环，准备去处理了。
//                    advance = false;
//                }
//            }
//            // i<0 说明当前线程已经遍历完旧的数组中的所有元素了。准备结束扩容。
//            //  当一个扩容区间结束之后，就尝试进行下一个扩容区间的处理....直到把整个table都处理完。
//            //      当最后一个扩容区间处理挖之后，再尝试去获取下一个区间的，就会发现--i,已经<0了，
//            //      也就是table已经全部扩容完了。
//            if (i < 0 || i >= n || i + n >= nextn) {
//                int sc;
//                //表示扩容全部结束了，开始删除中间变量、更新table、更新阈值sizeCtl
//                if (finishing) {
//                    //如果完成了扩容，就删除成员变量
//                    nextTable = null;
//                    //更新为新的table数组
//                    table = nextTab;
//                    //更新阈值（32*0.75=24）
//                    sizeCtl = (n << 1) - (n >>> 1);
//                    return;
//                }
//                //这个CAS如果成立，就表示当前线程已经扩容完了，准备退出了
//                if (U.compareAndSwapInt(this, SIZECTL, sc = sizeCtl, sc - 1)) {
//                    /**
//                     * 走到这里说明当前线程扩容结束了。
//                     * 如果sc-2 不等于后面的值的话，说明只是当前线程扩容结束了，其他的线程还在处理
//                     *      因为协助扩容的时候，是 sc = (resizeStamp(n) << RESIZE_STAMP_SHIFT) +1
//                     * 如果sc-2 等于后面的值的话，说明是全部扩容任务都结束了，那么设置finishing和advance都为=true,
//                     *      准备下次循环的时候：删除中间变量、更新table、更新阈值sizeCtl。
//                     *      因为第一次扩容的时候，是 sc = (resizeStamp(n) << RESIZE_STAMP_SHIFT) +2，
//                     *      所以当 sc-2等于后面的值时，就是扩容全部结束了
//                     */
//                    if ((sc - 2) != resizeStamp(n) << RESIZE_STAMP_SHIFT)
//                        return;
//                    finishing = advance = true;
//                    i = n; // recheck before commit
//                }
//            }
//            //如果当前下标的第一个元素为null，说明当前节点没有要迁移的元素。
//            //      那么就给他添加一个 fwd节点。这个节点中有个nextTable，指向的是新的table数组。
//            else if ((f = tabAt(tab, i)) == null)
//                advance = casTabAt(tab, i, null, fwd);
//            // fwd节点的hash值是MOVED,进入这里说明这个节点已经被处理过了。那就 --i，换下一个节点。
//            // 同时这里把 头结点的hash值赋给了fh变量
//            else if ((fh = f.hash) == MOVED)
//                advance = true; // already processed
//            else {
//                //走到这里就是准备进行数据迁移了。依旧是锁定该数组下标的第一个元素。
//                synchronized (f) {
//                    //这个if是再次确认一下，当前元素节点有没有被改变。
//                    if (tabAt(tab, i) == f) {
//                        //ln表示低位元素节点（在新table中位置不变，不需要迁移的），
//                        //hn表示高位元素节点，迁移到新的table中时，位置要发生改变。
//                        Node<K,V> ln, hn;
//                        if (fh >= 0) {
//                            // 这个runBit = n & hash。 就是和“hashMap中判断元素节点迁移后位置是否发生变化的逻辑”一模一样
//                            //      如果runBit=0,表示低位节点。迁移后，位置不需要发生变化。即低位节点
//                            //      如果runBit=1，表示高位节点。迁移后，元素节点的位置需要发生变化了。即高位节点。
//                            //          新位置是： index = 原来的下标 i + 原来的数组大小 n
//                            int runBit = fh & n;
//                            /**
//                             * lastRun节点指向下标为 i 的第一个元素。
//                             * 这个 lastRun变量的作用是：加入第一个是高位元素（需要变换位置），然后第二位发现是低位元素的话，
//                             *      就会让lastRun指向这第二个元素，如果第三位又是高位元素，那就让lastRun再指向这个高位元素。
//                             *      意思就是每次发现高低位发生了变化，那么lastRun就会指向新的高位/低位元素节点。
//                             *      如果没有发生变化的话，lastRun指向的节点不用变化。
//                             * 这一波操作下来，最后lastRun指向的那个元素，以及他后面的所有元素（如果还有的话），
//                             *      就全部都是高位、或者全部都是低位的元素了，即都是同一类型。这样在遍历元素的时候，就可以少处理几个了，
//                             *      因为这几个类型都是一样的（要么都在高位，要么都在低位），因为我只要处理这个lastRun就可以了，
//                             *      后面的元素都会跟在lastRun节点的后面。
//                             */
//                            Node<K,V> lastRun = f;
//                            // 从这里开始，循环遍历数组下标为 i 的链表。遍历所有节点，就是为了最终定位lastRun的置位。
//                            for (Node<K,V> p = f.next; p != null; p = p.next) {
//                                //这两个b就是和runBit一样。这里的b就是计算当前节点是低位还是高位
//                                int b = p.hash & n;
//                                //如果发现当前节点的高低位和runBit对应的元素的高低位不一样，
//                                //      那就让runBit的值变为当前这个节点的高低位值（0或者1），同时把lastRun指向
//                                //      当前这个节点。
//                                if (b != runBit) {
//                                    runBit = b;
//                                    lastRun = p;
//                                }
//                            }
//                            //如果等于0，就说明是低位节点，即迁移后位置不变的节点。
//                            if (runBit == 0) {
//                                //就让低位指针，指向这个lastRun。同时清空高位指针原来指向的lastRun
//                                ln = lastRun;
//                                hn = null;
//                            }
//                            else {
//                                //如果runBit = 1的话，
//                                //      同理就让高位指针指向这个lastRun，同时清空低位指针原来指向的lastRun
//                                hn = lastRun;
//                                ln = null;
//                            }
//                            /**
//                             * 这里就开始循环遍历链表中lastRun之前的所有节点。因为lastRun之后的节点都跟lastRun一样，
//                             *      要么都是高位，要么都是低位，lastRun去哪，它们就去哪。
//                             * 第一次循环时，此时ln或者hn有一个指向的lastRun节点。第一次就先处理了这个lastRun节点，
//                             *      因为在创建Node节点的时候，就把lastRun节点作为了这个新的Node节点的后继节点，
//                             *      并且这个lastRun节点本身后面还连接着和lastRun同类型的节点呢。所以第一次循环就处理了
//                             *      一堆节点。
//                             * 后面循环就是根据高位节点或地位节点来创建Node节点了。做法是：就那低位来举例吧，创建一个新的
//                             *      Node节点，并且把ln作为Node节点的后继节点，同时又让ln指向这个新的Node节点（即指针前移），
//                             *      .....如此循环。
//                             * 遍历时从前往后遍历的，直到遇到lastRun节点就停止。
//                             * 构建高位和低位链表的时候，是“从后往前”，意思是每一个新创建的Node都是作为链表的第一个元素，
//                             *      即“头插法”。就是每次新创建一个新节点Node，都会把已经成型链表放在这个新Node节点的后即指针上。
//                             *
//                             */
//                            for (Node<K,V> p = f; p != lastRun; p = p.next) {
//                                int ph = p.hash; K pk = p.key; V pv = p.val;
//                                if ((ph & n) == 0)
//                                    ln = new Node<K,V>(ph, pk, pv, ln);
//                                else
//                                    hn = new Node<K,V>(ph, pk, pv, hn);
//                            }
//                            //这里是给新的table（即这里的nextTable）进行“低位”节点迁移。
//                            //      低位节点在旧表的哪个下标位置，那么迁移到新表后，还在那个下标处。
//                            setTabAt(nextTab, i, ln);
//                            //这里是这里是给新的table（即这里的nextTable）进行“高位”元素迁移。
//                            //      高位节点迁移到新表的位置后，他的新下标位置 index = “原来的下标i + 原table的大小n ”
//                            setTabAt(nextTab, i + n, hn);
//                            //然后对于旧表的当前位置i，就用一个fwd来占位一下。其他线程在进行put的时候，如果发现fwd节点，
//                            //      说明当前表正在进行扩容。 同时这个fwd节点里面有个nextTable指针，指向的是新表，当get的时候
//                            //      发现 fwd节点的话，就会通过nextTable指针来去新表里面获取元素。
//                            setTabAt(tab, i, fwd);
//                            //表示可以继续进上面的while循环里面了进行 --i 获取下一个下标，继续进行元素节点迁移了。
//                            advance = true;
//                        }
//                        /**
//                         * 下面就是红黑树的迁移了
//                         */
//                        else if (f instanceof TreeBin) {
//                            TreeBin<K,V> t = (TreeBin<K,V>)f;
//                            TreeNode<K,V> lo = null, loTail = null;
//                            TreeNode<K,V> hi = null, hiTail = null;
//                            int lc = 0, hc = 0;
//                            for (Node<K,V> e = t.first; e != null; e = e.next) {
//                                int h = e.hash;
//                                TreeNode<K,V> p = new TreeNode<K,V>
//                                        (h, e.key, e.val, null, null);
//                                if ((h & n) == 0) {
//                                    if ((p.prev = loTail) == null)
//                                        lo = p;
//                                    else
//                                        loTail.next = p;
//                                    loTail = p;
//                                    ++lc;
//                                }
//                                else {
//                                    if ((p.prev = hiTail) == null)
//                                        hi = p;
//                                    else
//                                        hiTail.next = p;
//                                    hiTail = p;
//                                    ++hc;
//                                }
//                            }
//                            ln = (lc <= UNTREEIFY_THRESHOLD) ? untreeify(lo) :
//                                    (hc != 0) ? new TreeBin<K,V>(lo) : t;
//                            hn = (hc <= UNTREEIFY_THRESHOLD) ? untreeify(hi) :
//                                    (lc != 0) ? new TreeBin<K,V>(hi) : t;
//                            setTabAt(nextTab, i, ln);
//                            setTabAt(nextTab, i + n, hn);
//                            setTabAt(tab, i, fwd);
//                            advance = true;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//
//    final Node<K,V>[] helpTransfer(Node<K,V>[] tab, Node<K,V> f) {
//        //nextTab是扩容后的新表。
//        // sc是sizeCtl。 当sc为负数的时候，表示正在扩容。并且假设此时sc的低16位表示的十进制值M，
//        //      就说明现在有 M-1 个线程正在参与扩容
//        Node<K,V>[] nextTab; int sc;
//        /**
//         * 条件1：如果当前旧表 tab不为空
//         * 条件2：当前节点f 是ForwardingNode，这个节点是扩容的时候才会放进去的。
//         *      表示这个节点已经处理完了。
//         * 条件3：当前节点 f （也就是ForwardingNode节点）的 nextTable属性不为null。
//         *      这个属性是指向扩容后的新表的。不为空说明扩容已经开始了，新表已经出现了，
//         *      此时新表还没有替换掉就掉table。等扩容全部结束，就会替换掉旧表table
//         *
//         * 3个条件全部满足，就说明可以准备参与协助扩容了。
//         */
//        if (tab != null && (f instanceof ForwardingNode) &&
//                (nextTab = ((ForwardingNode<K,V>)f).nextTable) != null) {
//            //这里是根据当前表的长度，来生成扩容戳。
//            int rs = resizeStamp(tab.length);
//            /**
//             * while循环的条件： 也是为了保证CAS能够成功
//             * 条件1：新表依旧存在。因为扩容结束后，nextTable就会变为 null
//             * 条件2：旧表也没有发生变化。因为扩容结束后，table就会变成新表nextTable了，
//             *            而此时就不会等于旧表 tab了
//             * 条件3：sizeCtl<0表示数组正在扩容。
//             */
//            while (nextTab == nextTable && table == tab &&
//                    (sc = sizeCtl) < 0) {
//                /**
//                 * 条件1：把sizeCtl无符号右移16位，得到就是关于扩容标记的数值（可以理解为纪元）
//                 *      如果不相等的话，说明不是从一个扩容的情况，比如一个是16扩到32，
//                 *      另一个表示的是 32扩到64。这俩做的根本不是一回事。
//                 *      如果相等的话，扩容还在继续。不相等的话，说明扩容已经完成，因为根据新的tab.length
//                 *      计算出来的扩容戳已经变了。
//                 * 条件2：可以看作：rs == sc-1 。 因为 rs 初始的最低为是0，如果层里的话，说明 sc -1 =0.
//                 *      那不就是 m-1 = 0吗（m是当sizeCtl为负数是，低16位表示的值）。 说明么有线程在扩容，即扩容结束了。
//                 * 条件3： MAX_RESIZERS = 1111111111111111 （16个1）。表示的是最多可以允许 2^16 -2个线程参与扩容（因为第一个是+2）。
//                 *          如果相等的话，说明参与扩容的线程已经满了。
//                 * 条件4：transferIndex <=0 表示当前table的扩容区间已经被分完了，没有任务了。不需要去协助扩容了。
//                 *
//                 *
//                 * 4个条件只要 1 个成立，就不允许协助扩容了，直接技术循环，返回
//                 */
//                if ((sc >>> RESIZE_STAMP_SHIFT) != rs || sc == rs + 1 ||
//                        sc == rs + MAX_RESIZERS || transferIndex <= 0)
//                    break;
//                /**
//                 * 修改sizeCtl的值，增加1 。 说明又有一个线程来参与扩容了。
//                 *      如果CAS修改成功，就进入 transfer来协助扩容。
//                 *      如果CAS修改失败的话，就继续循环，尝试再次修改。
//                 */
//                if (U.compareAndSwapInt(this, SIZECTL, sc, sc + 1)) {
//                    /**
//                     * 准备扩容。
//                     * 第一个参数tab：表示旧表，即旧的数组
//                     *
//                     * 第二个参数 nextTab：表示扩容过程中产生的新表，此时还没有替换旧表，扩容全部结束之后
//                     *      才会替换旧表。如果是首次扩容（不是协助扩容），那么这个参数是 null，因为还没有新表。
//                     */
//                    transfer(tab, nextTab);
//                    break;
//                }
//            }
//            return nextTab;
//        }
//        return table;
//    }
//
//}
