package util.study.gupao.thread;


/**
 * @version: java version 1.7+
 * @Author : mzp
 * @Time : 2021/3/8 19:58
 * @File : Demo
 * @Software: IntelliJ IDEA 2019.2.04
 */
public class Demo {

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
//        //如果数组为null，或者 数组数组没有元素（空间优化）。那么就准备初始化。
//        //      元素为0初始化的原因：比如原来有64个元素，后来全部移除了。此时数组还占据64个空间，
//        //      有些浪费了，需要重新初始化为16个。节省空间。
//        //这里用while是因为，CAS操作可能失败
//        while ((tab = table) == null || tab.length == 0) {
//            //把sizeCtl的值赋给sc。 同时如果sizeCtl的值<0,也就是-1。就说明有线程已经在进行初始化了
//            //      那就调用Thread.yield()让出CPU时间片。下次获得时间片之后再次循环，有可能发现还是<0，
//            //      那就一直在这里自旋，直到下面的初始化操作完成，sizeCtl>0
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
//    public void execute(Runnable command) {
//        // Runnable不能为 null。
//        if (command == null)
//            throw new NullPointerException();
//        /*
//         * Proceed in 3 steps:
//         *
//         * 1. If fewer than corePoolSize threads are running, try to
//         * start a new thread with the given command as its first
//         * task.  The call to addWorker atomically checks runState and
//         * workerCount, and so prevents false alarms that would add
//         * threads when it shouldn't, by returning false.
//         *
//         * 2. If a task can be successfully queued, then we still need
//         * to double-check whether we should have added a thread
//         * (because existing ones died since last checking) or that
//         * the pool shut down since entry into this method. So we
//         * recheck state and if necessary roll back the enqueuing if
//         * stopped, or start a new thread if there are none.
//         *
//         * 3. If we cannot queue task, then we try to add a new
//         * thread.  If it fails, we know we are shut down or saturated
//         * and so reject the task.
//         */
//        // 获取原子变量ctl。ctl的高三位保存的是线程池的状态。低29位保存的是线程池中的线程数量
//        int c = ctl.get();
//        //workerCountOf(c)方法，是获取ctl变量的低29位，也就是说获取线程池中的线程数量。
//        //如果当前线程的数量小于核心线程数corePoolSize的话，就回去创建线程。addWorker()
//        if (workerCountOf(c) < corePoolSize) {
//            //创建一个线程。线程池中的线程在这里才创建。addWorker,把线程比作worker工人
//            //执行完，就返回了。
//            if (addWorker(command, true))
//                return;
//            //如果添加新线程失败的话，就会继续执行下面的if
//            c = ctl.get();
//        }
//        //走到这里，说明核心线程已经满了。那么尝试把任务放到任务队列中去
//        //这里先判断
//        //      1.当前线程池的状态是不是运行状态runnable，
//        //      2.如果是的话，他还会把线程的当前任务command添加到工作队列workQueue中去。
//        //如果两个都成功地话，就会进入if执行体中
//        if (isRunning(c) && workQueue.offer(command)) {
//            //获取ctl变量，下面用来检查线程池的状态
//            int recheck = ctl.get();
//            //如果线程池的状态不是运行状态的话，那就尝试把任务移除。
//            //如果任务移除成功的话，就进入if执行体，来拒绝当前任务
//            if (! isRunning(recheck) && remove(command))
//                //那就拒绝当前这个任务。默认的拒绝策略是抛出异常。
//                reject(command);
//            //如果线程池状态是异常的，但是任务没有移除掉。说明队列中还有任务，那么就看一下当前线程池里面还有没有线程了。
//            //这种情况可能是：线程池是SHUTDOWN状态：不接收新任务，但是会处理已经在队列中的任务的。
//            // 如果没有的话，就创建一个。这里创建的是非核心线程来执行队列中的任务。
//            else if (workerCountOf(recheck) == 0)
//                addWorker(null, false);
//        }
//        //如果任务队列中的任务已经满了，那么我就去创建一些非核心线程（临时工）来处理任务
//        //如果创建失败的话，说明临时工也满了（此时线程池中的线程数量已经达到设定的最大值），
//        //      就进入if执行体里面，拒绝当前任务
//        else if (!addWorker(command, false))
//            //拒绝当前任务。默认的拒绝策略是抛出异常。
//            reject(command);
//    }
//
//
//    private boolean addWorker(Runnable firstTask, boolean core) {
//        //goto语句，避免死循环。
//        retry:
//        /**
//         * 这个循环的意思是，在创建新线程之前，用 CAS 先把线程池中线程的数量 +1
//         *      即ctl的值要加一。
//         */
//        for (;;) {
//            //获取ctl
//            int c = ctl.get();
//            //获取线程池的状态
//            int rs = runStateOf(c);
//
//            // Check if queue empty only if necessary.
//            /**
//             * 第一个条件：线程池是异常状态
//             * 第二个组合条件：
//             *      如果线程池的状态不是SHUTDOWN 或者  当前任务不为空  或者 任务队列为空的话
//             *      这个三个“反例”条件任意满足一个（也就是下面代码中的3个反例）   那么就返回。
//             * 返回到execute方法里面，最后还是拒绝当前任务。
//             *
//             * 总之就是当线程池是异常状态的时候。如果状态不是 SHUTDOWN 都会拒绝当前任务。因为线程池没了，
//             *      其他异常状态不会处理队列中的任务。
//             * 如果线程是异常状态，而且任务队列中没有任务了，此时也会拒绝当前任务。
//             *
//             * firstTask为null调用了addWorker方法，是说明线程队列中没有线程了，但是任务队列中还有任务，
//             *      需要创建线程来执行完队列中的任务。
//             */
//            if (rs >= SHUTDOWN &&
//                    ! (rs == SHUTDOWN && firstTask == null && ! workQueue.isEmpty()))
//                return false;
//
//            for (;;) {
//                //这里是获取线程池中的线程数量。
//                int wc = workerCountOf(c);
//                //如果线程数量达到了最大值（2^29-1 个）
//                //      或者大于“核心线程数”或者是“最大线程数”。如果core为true就表示要与核心线程数做对比，
//                //      如果core为false就表示与最大线程数做对比。
//                //意思就是线程数量超了。准备返回吧，不能再创建新的线程了
//                if (wc >= CAPACITY ||
//                        wc >= (core ? corePoolSize : maximumPoolSize))
//                    return false;
//                //如果线程数量没有达到最大值，那就把线程的数量+1 ，即ctl的数量+1
//                //      然后跳出所有循环。
//                if (compareAndIncrementWorkerCount(c))
//                    //跳出goto语句，这里就是结束循环。
//                    break retry;
//                //如果更改线程数量失败的话，就先检查线程池的状态是否变化了
//                //  如果没有变化的话，就继续循环。
//                //  此时的状态是正常状态，如果变化了，就变成了异常状态。
//                c = ctl.get();  // Re-read ctl
//                if (runStateOf(c) != rs)
//                    continue retry;
//                // else CAS failed due to workerCount change; retry inner loop
//            }
//        }
//        /**
//         * 下面的逻辑就是往线程池中添加线程和执行线程了
//         */
//        //workerStarted表示线程是否启动
//        boolean workerStarted = false;
//        //workerAdded表示线程是否添加到线程池
//        boolean workerAdded = false;
//        //Worker类是一个线程类，实现了Runnable接口
//        Worker w = null;
//        try {
//            /**
//             * 这里把任务传给了Worker对象。
//             * Worker类里面有个Thread类型的变量thread，里面这个动作
//             *      this.thread = getThreadFactory().newThread(this)；
//             *      这个操作实际就相当于：Thread thread = new Thread(Runnable)，
//             *      这个this就是Worker这个线程对象实例。
//             *  Worker里面还有一个Runnable类型的变量firstTask，保存这个当前的任务。表示的是
//             *      线程池中这个线程创建时首先需要执行的任务（因为这个任务，才创建的这个线程，
//             *      可以说是线程与生俱来的任务），而不是从任务队列中取出来的Runnable任务。
//             *
//             *
//             * 然后下面调用 t.start()方法，此时这个t就是由worker实例创建的线程。最后启动的也是Worker类里面的run()方法，
//             *      在run()方法里面第一次会执行这个Runnable类型的变量firstTask，
//             *      也就是与生俱来的任务。后面再循环的时候，才会常识从任务队列中获取任务。
//             *
//             */
//            w = new Worker(firstTask);
//            //获取Worker中的Thread类型的变量thread。
//            //      此时这个t就是由worker实例创建的线程
//            final Thread t = w.thread;
//            if (t != null) {
//                //加上一把重入锁
//                final ReentrantLock mainLock = this.mainLock;
//                mainLock.lock();
//                try {
//                    // Recheck while holding lock.
//                    // Back out on ThreadFactory failure or if
//                    // shut down before lock acquired.
//                    //获取当前线程池的状态
//                    int rs = runStateOf(ctl.get());
//                    /**
//                     * 只有当线程池是正常状态（RUNNABLE） 或者是  线程池虽然SHUTDOWN且当前任务firstTask为空时
//                     *      第二种情况发生在：线程池已经调用showDown()方法了，已经不接收新任务了，但是任务队列中
//                     *      还有任务没有处理完。
//                     *      那么需要创建非核心线程来继续执行里面的任务，此时外面是通过调用addWorker(null, false)
//                     *      方法进来的。
//                     *
//                     */
//                    if (rs < SHUTDOWN ||
//                            (rs == SHUTDOWN && firstTask == null)) {
//                        //查看线程 t 是否存活（已经start，但是还没有 死亡。这期间）
//                        //即如果发现这个线程正在运行着呢，那就抛出异常。
//                        if (t.isAlive()) // precheck that t is startable
//                            throw new IllegalThreadStateException();
//                        /**
//                         * 这里就是把新创建的线程worker添加到线程池中。
//                         * 线程池是一个HashSet。
//                         */
//                        workers.add(w);
//                        int s = workers.size();
//                        //largestPoolSize表示线程池HashSet中出现过的最大的线程数。
//                        //      相当于是一个“记录的保持者”
//                        //如果刷新记录的话，就把新纪录保存在largestPoolSize里面
//                        if (s > largestPoolSize)
//                            largestPoolSize = s;
//                        //workerAdded=true，表示新的线程已经进入线程池了。
//                        workerAdded = true;
//                    }
//                } finally {
//                    //释放重入锁。
//                    mainLock.unlock();
//                }
//                //如果线程Worker已经加入到了线程池workers中的话，就启动这个线程worker
//                if (workerAdded) {
//                    t.start();
//                    //workerStarted = true，表示线程池中这个线程已经启动。
//                    workerStarted = true;
//                }
//            }
//        } finally {
//            //最后这里，如果新建的线程没有成功加入到线程池，可能是出了异常，
//            //      此时workerStarted=false，那就实行回滚操作。
//            if (! workerStarted)
//                //回滚操作。里面有重入锁
//                // 把w线程从线程池中移除，同时线程池中的线程个数ctl变量要用 CAS 来减少1
//                //      同时去调用终止方法tryTerminate()来终止线程池。
//                addWorkerFailed(w);
//        }
//        //如果创建的线程成功加入队列并且启动了，这里就返回true
//        return workerStarted;
//    }
//
//
//    final void runWorker(Worker w) {
//        //获取当前线程
//        Thread wt = Thread.currentThread();
//        //如果线程池中这个线程创建的时候带有任务（这个任务是Worker实例创建的时候被赋予的），
//        //    那么就优先执行这个任务，而不是从任务队列中去获取等待的任务。
//        Runnable task = w.firstTask;
//        //然后就可以把这个变量清空了。
//        w.firstTask = null;
//        w.unlock(); // allow interrupts
//        //表示线程是否是异常结束：true表示异常结束，那么把当前线程从线程池中移除之后，要考虑是否需要再补充补充进来一个，
//        //      是根据实际情况考虑，而不是一定要补充，要看还有队列中没有任务需要执行，如果没有的话，就不用补充了
//        //      false表示不是异常结束，那就是因为没有抢到任务（或者超时）而结束了，
//        //      那么把当前线程从线程池中移除之后，就不需要再补充一个新的了。
//        boolean completedAbruptly = true;
//        try {
//            //这里就保证了线程池中的线程一直在循环，不会被回收
//            //如果有任务，就执行。如果没有任务（空闲），那就尝试从任务队列中获取任务。
//            //      如果获取的任务为null，那就结束循环，那么线程池中这个线程就要被回收了
//            //getTask()方法内部有阻塞机制，即阻塞队列workQueue.take()方法。
//            while (task != null || (task = getTask()) != null) {
//                //这里加锁不是为了线程安全。而是为了加锁之后，保证正在执行的任务不会被shutdown()被中断。
//                w.lock();
//                // If pool is stopping, ensure thread is interrupted;
//                // if not, ensure thread is not interrupted.  This
//                // requires a recheck in second case to deal with
//                // shutdownNow race while clearing interrupt
//                /**
//                 * 条件一：线程池的状态为stop即以上状态时，不接收新任务，也部助理线程池中的任务
//                 * 条件二：对于stop以上的状态，而且中断表示也是true的（调用完之后会中断复位成false）
//                 * 条件三：确保中断标志已经被复位成false了
//                 *
//                 * 三个条件都成立的话，就会设置当前线程的中断标志位为true。即要把这个中断传递给临界区
//                 *
//                 */
//                if ((runStateAtLeast(ctl.get(), STOP) ||
//                        (Thread.interrupted() && runStateAtLeast(ctl.get(), STOP))) &&
//                        !wt.isInterrupted())
//                    //把当前线程的中断标志设置为true
//                    wt.interrupt();
//                try {
//                    //这个方法默认是没有实现，我们可以继承 ThreadPoolExecutor，来重写这个方法
//                    //      做一些线程执行前的前驱操作
//                    beforeExecute(wt, task);
//                    Throwable thrown = null;
//                    try {
//                        /**
//                         * 关键来了。执行给定任务的run()方法。
//                         * 这里是在线程池的线程中直接调用任务类重写的的run()方法，而不是start了。
//                         *      因此这个任务就是由线程池中的线程来完成了。
//                         */
//                        task.run();
//                    } catch (RuntimeException x) {
//                        thrown = x; throw x;
//                    } catch (Error x) {
//                        thrown = x; throw x;
//                    } catch (Throwable x) {
//                        thrown = x; throw new Error(x);
//                    } finally {
//                        //这个方法默认是没有实现，我们可以继承 ThreadPoolExecutor，来重写这个方法
//                        //      做一些线程执行后的后继操作
//                        afterExecute(task, thrown);
//                    }
//                } finally {
//                    //最后这里每次执行完任务之后，就重置task=null，为的是如果下次抢不到任务时，
//                    //      task=null的话，就结束循环了，即线程池中这个线程结束了。
//                    task = null;
//                    //统计线程池中这个线程完成了几个任务
//                    w.completedTasks++;
//                    //释放锁。表示空闲状态，允许被shutdown()方法中断回收了
//                    w.unlock();
//                }
//            }
//            completedAbruptly = false;
//        } finally {
//            /**
//             * 这里是线程发生异常了（可能是因为用户任务），
//             *     或者是线程没有抢到任务，准备回收了。
//             * 然后就会进入这个方法中，把当前线程 w 从线程池中移除掉。
//             *
//             * 然后如果completedAbruptly=true，表示当前线程是异常结束的，那么移除之后要考虑是否需要再补充一个新的，
//             *      是根据实际情况考虑，而不是一定要补充，要看还有队列中没有任务需要执行，如果没有的话，就不用补充了，
//             *      如果需要补充的话，就调用addWorker(null, false)方法来补充一个新的线程。
//             * 然后如果completedAbruptly=false，表示当前线程是正常结束的，那么移除之后不需要补充了
//             */
//            processWorkerExit(w, completedAbruptly);
//        }
//    }
//
//
//    private Runnable getTask() {
//        /**
//         * timedOut表示当前线程从任务队列中获取任务时，有没有超时。
//         *      如果超时的话（timedOut=true），那么下一次就“有可能”会被回收掉。
//         *  这种“有可能”的情况是：当需要进行超时控制timed=true）的时候（
//         */
//        boolean timedOut = false; // Did the last poll() time out?
//        //线程池中的线程自旋尝试获取任务
//        for (;;) {
//            //获取ctl
//            int c = ctl.get();
//            //获取当前线程池的状态
//            int rs = runStateOf(c);
//
//            // Check if queue empty only if necessary.
//            /**
//             * 如果线程池的状态大于等于STOP（不接收新任务，不处理队列中已有的任务）
//             *
//             * 或者   线程池状态异常（大于等于SHUTDOWN）且任务队列已经是空的了
//             *      那么就把线程池中的任务数量减少1，同时把本次获取任务的结果返回为null。
//             *      表示任务获取失败，当前线程准备回收了。
//             */
//            if (rs >= SHUTDOWN && (rs >= STOP || workQueue.isEmpty())) {
//                decrementWorkerCount();
//                return null;
//            }
//            // 获取线程池中线程的数量
//            int wc = workerCountOf(c);
//
//            // Are workers subject to culling?
//            /**
//             * 标识是否需要超时控制。 timed = true表示需要。
//             *
//             * 判断线程池中的线程是否需要超时控制淘汰？，如果为true，那么线程空闲时间超时就会被淘汰
//             *      allowCoreThreadTimeOut默认是false，可以通过allowCoreThreadTimeOut()方法来设置为true。
//             *      或者   wc > corePoolSize 即当前线程的数量已经大于“核心线程数”了，
//             * 这两个条件只要满足一个，就需要“超时控制”来淘汰线程。当线程超时的时候，就会被淘汰
//             *
//             */
//            boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
//
//            /**
//             * 判断当前线程是否需要移除。
//             *
//             * 条件1. 如果线程数量大于线程池允许的最大线程数量。这种情况正常是不会发生的，
//             *          因为addWorker()方法里面在增加线程个数的时候有判断，不允许超过最大值。
//             *          超过最大值的情况是发生在：“人为修改了线程池的最大值数量，把这个参数调小了”
//             *    或者  开启超时控制，并且当前线程上一次获取任务时也超时了
//             * 条件2：线程池中还存在有至少2个线程（一个是当前线程自己，另一个是其他的。因为如果还有任务的话，
//             *          是需要线程来执行的。至少有2个的话，移除一个超时的，那么还剩一个也可以执行任务）
//             *    或者  任务队列已经没有任务了
//             *
//             * 两个条件都要满足。那么就会把这个“多于的线程”或者“超时的线程”给移除掉。
//             *      在这里先减少线程池的数量，然后return null，在外面移除HashSet中的线程。
//             */
//            if ((wc > maximumPoolSize || (timed && timedOut))
//                    && (wc > 1 || workQueue.isEmpty())) {
//                //如果修改线程池数量失败的话。那么就continue。自旋重新来尝试修改。
//                if (compareAndDecrementWorkerCount(c))
//                    return null;
//                continue;
//            }
//
//            /**
//             * 走到这里说明线程目前还没有问题，可以去任务队列里面获取任务了。
//             *
//             * 如果允许超时控制（timed=true），那就使用带有超时时间的poll()方法
//             *      来从任务队列中获取任务，这个超时时间和时间的单位都是我们通过构造方法来设置的
//             *
//             *  如果不允许超时控制（timed=false）,那么就是用take()阻塞的方式从任务队列中获取任务，
//             *      如果任务队列为空，就会阻塞。
//             */
//            try {
//                Runnable r = timed ?
//                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
//                        workQueue.take();
//                //如果获取到的任务不为空，那就正常返回这个任务，准备执行任务了。
//                if (r != null)
//                    return r;
//                /**
//                 * 能走到这里，说明是采用的“超时控制”（timed=true），而且是任务获取超时了。即上一步获取的r是null的。
//                 *      那么就让 timedOut=true，表示线程获取任务超时了，那么下次这个线程就就有可能被回收了。
//                 */
//                timedOut = true;
//            } catch (InterruptedException retry) {
//                //如果发生中断（比如阻塞中断），就把超时标记为false，没有超时
//                timedOut = false;
//            }
//        }
//    }
//
//
//    public boolean offer(E e) {
//        //如果添加的元素是null，就抛出空指针
//        if (e == null) throw new NullPointerException();
//        //获取队列中的元素总数
//        final AtomicInteger count = this.count;
//        //capacity是元素总数。默认是2^31-1
//        //如果元素已经满了的话，就返回false，表示添加失败了
//        if (count.get() == capacity)
//            return false;
//        //c的初始值是-1。添加一个元素，就会增加1。只要是大于等于0的，都表示添加成功了
//        int c = -1;
//        //用当前元素e来创建一个Node节点。
//        Node<E> node = new Node<E>(e);
//        final ReentrantLock putLock = this.putLock;
//        //加上重入锁
//        putLock.lock();
//        try {
//            //如果当前队列没有瞒的话，就把新元素添加到队列末尾。（尾插法）
//            if (count.get() < capacity) {
//                //将元素插入到队列中
//                enqueue(node);
//                //元素总个数+1
//                c = count.getAndIncrement();
//                //如果当前队列还没有满的话，可以通知其他写入的线程，继续写入。
//                if (c + 1 < capacity)
//                    notFull.signal();
//            }
//        } finally {
//            //释放锁
//            putLock.unlock();
//        }
//        //c的初始值是-1。如果等于0表示一个元素添加成功了
//        //      那么就可以唤醒“读条件队列”中的线程可以来获取元素了
//        if (c == 0)
//            signalNotEmpty();
//        // c>=0都表示元素添加成功了，为true
//        return c >= 0;
//    }
//
//
//    public void shutdown() {
//        final ReentrantLock mainLock = this.mainLock;
//        //加重入锁
//        mainLock.lock();
//        try {
//            //进行线程权限检查，看是否有关闭线程的权限。
//            checkShutdownAccess();
//            //把线程池的状态更改为 SHUTDOWN =0
//            advanceRunState(SHUTDOWN);
//            //遍历线程池中的线程。中断那些“空闲的、没有执行任务的线程”
//            //      空闲的线程就是没有获得锁的线程，即AQS的state=0。
//            //      将这些线程的中断标识设置为true。即在getTask()方法中让他们从阻塞中被唤醒，
//            //      然后他们就会响应中断、抛出异常，“结束自己罪恶的一生”，被回收掉。
//            interruptIdleWorkers();
//            onShutdown(); // hook for ScheduledThreadPoolExecutor
//        } finally {
//            mainLock.unlock();
//        }
//        //尝试终止线程池。清除所有线程，并且把状态改为 TERMINATED=3
//        tryTerminate();
//    }
//
//
//    final void tryTerminate() {
//        //自旋操作。
//        for (;;) {
//            int c = ctl.get();
//            /**
//             * 条件1：如果线程池状态正常，RUNNABLE
//             * 条件2：如果线程池的状态已经是TIDYING 或 TERMINATED （即c>=2）
//             * 条件3：如果状态是 SHUTDOWN 并且任务队列不为空
//             *
//             * 三个条件任意满足一个，都会直接return。不能终止。
//             *
//             * 只有当状态是SHUTDOWN并且任务队列为空的时候，  或者状态是 STOP=1
//             *      这两种情况，才会跳过这个 if，继续往下。
//             */
//            if (isRunning(c) ||
//                    runStateAtLeast(c, TIDYING) ||
//                    (runStateOf(c) == SHUTDOWN && ! workQueue.isEmpty()))
//                return;
//            /**
//             * 只有当状态是SHUTDOWN并且任务队列为空的时候，  或者状态是 STOP=1
//             *    这两种情况，才能走到这里。
//             *
//             * 因为在shutdown()中已经清理过一次空闲线程了，这里如果发现还有线程存在的话，
//             *       就再尝试清除一条。（当然也可能清除失败。因为剩余的线程都在运行中）
//             *       如果发现线程池中已经没有线程了，那就会跳过。
//             */
//            if (workerCountOf(c) != 0) { // Eligible to terminate
//                /**
//                 * 尝试再通过中断信号来中断一个等待状态线程来把中断信号传递下去。
//                 *      中断传递在addWorker()方法最后finally中的processWorkerExit()方法中，
//                 *      因为在processWorkerExit()方法中又调用了tryTerminate()这个方法来继续重复中断。
//                 *
//                 * 当然，如果这里没有成功中断一个线程的话，说明剩余的线程都是“获得了锁标志，正在执行任务呢，不能中断”，
//                 *      那么没关系。因为线程池的状态现在已经是SHUTDOWN或者是STOP了，那么在getTask()方法中有个判断逻辑，
//                 *      如果是这两种状态的话，只要等到任务队列中的任务全部结束之后，getTask()方法就会返回null，
//                 *      就会结束runWorker()的while循环，runWorker就会执行结束，在finally的processWorkerExit()方法中，
//                 *      又会调用到这个tryTerminate()方法（实现了中断的传递）。同时刚刚结束的runWorker()方法的这个线程就会执行结束，
//                 *      然后被JVM自然的回收。
//                 *
//                 */
//                interruptIdleWorkers(ONLY_ONE);
//                return;
//            }
//
//            /**
//             * 能走到这里，说明：下面两点都满足了。
//             *
//             * 1. 状态是 SHUTDOWN 或 stop（stop在shutdownNow()方法中产生） 且任务队列中没有任务了
//             * 2. 线程池中没有线程了
//             *
//             * 准备结束线程池。
//             */
//            final ReentrantLock mainLock = this.mainLock;
//            //获取锁
//            mainLock.lock();
//            try {
//                /**
//                 * 把线程池的状态改为 TIDYING=2，然后数量改为0。
//                 */
//                if (ctl.compareAndSet(c, ctlOf(TIDYING, 0))) {
//                    try {
//                        /**
//                         * 默认没有实现。可以自己重写这个方法。
//                         *      记得要在重写的子类里调用 super.terminated()方法
//                         */
//                        terminated();
//                    } finally {
//                        //最终把状态改为 TERMINATED =3
//                        ctl.set(ctlOf(TERMINATED, 0));
//                        //把所有等待的条件队列移动到同步队列中，然后等待unlock来唤醒。
//                        termination.signalAll();
//                    }
//                    return;
//                }
//            } finally {
//                //释放锁。
//                mainLock.unlock();
//            }
//            // else retry on failed CAS
//        }
//    }


}
