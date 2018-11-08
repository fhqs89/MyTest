package com.example.demo.face.map;

import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.io.Serializable;

/**https://www.2cto.com/kf/201805/742233.html
 * 对象里面可以存放key-values，map里面不能重复key，一个key只能有一个value。
 * 
 * 这是一个所有map的接口类，定义了初始抽象方法。
 * 
 * 接口包含3个集合方法，一个包含所有key的set，一个包含所有value的collection，
 * 或者一个 key-value 键值对的集合（Set<entry>）
 * @author  Josh Bloch
 * @see HashMap
 * @see TreeMap
 * @see Hashtable
 * @see SortedMap
 * @see Collection
 * @see Set
 * @since 1.2
 */
public interface MyMap<K,V> {
    // Query Operations

    /**
     * 返回map中 key-value 映射数量，如果最大值超过Intefer最大值，就返回Integer的最大值
     * <tt>Integer.MAX_VALUE</tt>.
     * @return 返回map中 key-value 映射数量
     */
    int size();

    /**
     *  如果key-value键值对为空，返回true.
     * @return 如果key-value键值对为空，返回true.
     */
    boolean isEmpty();

    /**
     * 如果map包含这个key，返回ture，否则返回false
     * 如果key类型不匹配，将抛出 ClassCastException 
     * 如果该map不支持key=null，传入null,将抛出NullPointerException。
     */
    boolean containsKey(Object key);

    /**
     * 如果map中有一个或多个值等于value，返回true，否则返回false。
     * 对于大多数map多实现，需要根据map的大小花费相应的线性时间。
     * 如果value的类型不匹配，将抛出ClassCastException。
     */
    boolean containsValue(Object value);

    /**
     * 返回key映射的value对象，如果没有返回null。
     * 如果该map允许value为null，返回null，不一定表示没有key的映射关系。
     * containsKey（k）可以区分这种情况
     * 如果key的类型不符，将抛出ClassCastException。
     * 如果传入null，将抛出NullPointerException。
     */
    V get(Object key);

    // Modification Operations

    /**
     * 将key和value以键值对的形式放入map中。
     * 如果新键值对中，已经存在新key，将会覆盖旧的value。
     * 一个map是否已经包含这个key的依据是 containsKey（k）返回true
     *
     * 返回值是 覆盖前的value，如果没有旧的就返回null。
     * 如果map实现允许value=null，且返回null，那么表明，key旧值对应的可能是空，也可能key是新的
     * 如果该map不支持put操作，将抛出UnsupportedOperationException。
     * 如果key和value不是指定类型，将抛出ClassCastException。
     * 如果map不支持key或value为null，将抛出NullPointerException。
     * 如果map阻止保存指定的key或value的某些属性，将抛出IllegalArgumentException
     */
    V put(K key, V value);

    /**
     * 如果该map包含key的映射，则删除该映射。
     * 返回值是先前对应key的value，如果没有该key的映射，则返回null。
     * 如果map支持value的值为null，那么返回null也可能表明key在先前对应的value就是null。
     * 一旦调用返回，该map将不再包含指定key的映射。
     * 如果该map不支持remove操作，将抛出UnsupportedOperationException。
     * 如果key是非法类型，则抛出ClassCastException。
     * 如果该map不支持key=null，将抛出NullPointerException。
     */
    V remove(Object key);


    // Bulk Operations

    /**
     * 从指定map中拷贝所有映射到该map中。
     * 这个调用过程相当于，将指定map中所有映射都调用了一次put（key，value）方法，存入该map中。
     * 如果在此操作过程中，被指定map被修改了，那么这个操作的行为是未定义的，不能确定是新值还是旧值。
     * 如果该map不支持putAll操作将抛出 UnsupportedOperationException。
     * 如果key或value是非法类型，那么抛出ClassCastException。
     * 如果指定map为null，或者该map不支持key或value为null，且指定map中存在key或value为null，
     * 则将抛出NullPointerException。
     * 如果该map阻止保存指定key或value的某些属性，将抛出IllegalArgumentException。
     */
    void putAll(MyMap<? extends K, ? extends V> m);

    /**
     * 删除map中所有映射，删除后map为空。
     * 如果该map不支持clear，则抛出UnsupportedOperationException。
     */
    void clear();


    // Views

    /**
     * 返回map中所有key的Set视图
     * 该set是由map支持的，所以对map对更改，会影响到set，反之亦然。
     * 如果在迭代对过程中，map被修改了（除了迭代器自己对remove操作），那么迭代结果是不确定的。
     * 该Set支持删除map映射的操作，
     * 其中包括其中方法包括Iterator.remove、Set.remove、removeAll、retainAll及clear。
     * 但不支持add（）或addAll（）操作。
     */
    Set<K> keySet();

    /**
     * 返回该map中所有value但Collection视图。
     * 该Collection是由map支持的，所以对map修改，会影响到Collection，反之亦然。
     * 如果在迭代过程中，map被修改了(除了迭代器使用自己的remove操作)，那么迭代的结果是不确定的。
     * 该collection支持删除map中映射的操作，
     * 其中方法包括Iterator.remove、Collection.remove、removeAll、retainAll及clear。
     * 但不支持add（）或addAll（）操作。
     */
    Collection<V> values();

    /**
     * 返回该map中所有映射的set视图。
     * 该set是由map支持的，所以对map的更改将影响到set，反之亦然。
     * 如果在迭代set的过程中map被修改了(除了迭代器使用自己的remove操作，或者setValue操作)，
     * 那么迭代的结果是不确定的。
     * 该set支持删除map中映射的操作，
     * 其中方法包括Iterator.remove、Set.remove、removeAll、retainAll及clear。
     * 但不支持add（）或addAll（）操作。
     */
    Set<MyMap.Entry<K, V>> entrySet();

    /**
     * 一条map的记录（一个键值对）。
     * Map.entrySet方法返回的collection视图中的元素就是这个类（Entry）。
     * 唯一一个获得Entry引用的方式，是从collection视图的迭代器中获取。
     * 这些Map.Entry对象只在迭代期间有效。
     * 如果在entry的迭代器返回后，作为支持的map被修改了（除了entry的setValue操作），
     * 那么entry的行为是未知的。
     * @see Map#entrySet()
     * @since 1.2
     */
    interface Entry<K,V> {
        /**
         * 返回该entry对应的key
         * 一种实现方式，但是不做要求，如果entry已经被支持的map中删除了，抛出IllegalStateException。
         */
        K getKey();

        /**
         * 返回该entry对应的value。
         * 如果在作为支持的map中删除了该映射（用迭代器的remove操作），那么调用的结果是未知的。
         * 一种实现方式，但是不做要求，如果entry已经被从支持的map中删除了，抛出IllegalStateException。
         */
        V getValue();

        /**
         * 用指定value替换该entry中对应的value（可选的操作）。
         * 该操作会影响到map上。
         * 如果在map上的映射已经被删除了（使用迭代器的remove方法），那么这个行为的调用是未知的。
         * 返回值是先前entry对应的value值。
         * 如果作为支持的map不支持put操作，将抛出UnsupportedOperationException。
         * 如果指定value的类是被作为支持的map阻止的，那么抛出ClassCastException。
         * 如果作为支持map不支持value为null，传入null将抛出NullPointerException。
         * 一种实现方式，但是不做要求，如果entry已经被支持的map中删除了，抛出IllegalArgumentException。
         */
        V setValue(V value);

        /**
         * 比较指定object和该entry是否相等。
         * 如果指定的object也是一个entry，并且这两个entry表示同一个映射，那么返回true。
         * 按照如下方式判断两个entry是否相等：
         *   (e1.getKey()==null ? e2.getKey()==null : e1.getKey().equals(e2.getKey()))  
         *     &&
         *   (e1.getValue()==null ?e2.getValue()==null : e1.getValue().equals(e2.getValue()))
         * 要嘛都为空，要嘛值相等，只比较值，不比较对象。
         * 这样确保了equals方法可以在不同的Map.Entry实现类中正常工作。
         * 如果指定object和该entry相等，返回true。
         */
        boolean equals(Object o);

        /**
         * 返回该entry的哈希码值，
         * 哈希码是按如下定义的：
         *     (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^(异或)
         *     (e.getValue()==null ? 0 : e.getValue().hashCode())
         * 这样确保了对于任意两个entry，如e1和e2，
         * 如果e1.equals(e2)，那么意味着e1.hashCode() == e2.hashCode()
         */
        int hashCode();

        /**
         * 返回一个Map.Entry的比较容器，按key的自然排序进行比较
         * 返回的比较器是可序列化的，当entry与空值进行比较时抛出NullPointerException
         * key的泛型要求实现Comparable接口，因为返回的Comparator接口的compare方法，
         * 要使用泛型的compareTo接口进行比较。
         * 
         * 返回比较器对Map.Entry<k,v="">进行比较，比较的方式采用key的自然排序。
         * &表示返回比较器既要实现Comparator接口，也要实现Serializable接口。
         * return 后面的代码是采用Lambda表达式形式返回函数式接口。
         * 返回一个Map.Entry的比较器，按照value的自然排序进行比较。
         * 
         * @since 1.8
         */
        public static <K extends Comparable<? super K>, V> Comparator<MyMap.Entry<K,V>> comparingByKey() {
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getKey().compareTo(c2.getKey());
        }

        /**
         * 返回一个Map.Entry的比较器，按照value的自然排序进行比较。
         * 返回的比较器是可序列化的，当entry与空值进行比较时抛出NullPointerException。
         * value的泛型要求实现Comparable接口，因为返回的Comparator接口的compare方法，
         * 要使用value泛型的compareTo接口进行比较。
         * @since 1.8
         */
        public static <K, V extends Comparable<? super V>> Comparator<MyMap.Entry<K,V>> comparingByValue() {
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                (c1, c2) -> c1.getValue().compareTo(c2.getValue());
        }

        /**
         * 返回一个Map.Entry的比较器，使用给定key的比较器进行比较。
         * 如果指定的比较器是可序列化的，那么返回的比较器也要求是可序列化的。
         * 入参cmp是一个比较器
         * @since 1.8
         */
        public static <K, V> Comparator<MyMap.Entry<K, V>> comparingByKey(Comparator<? super K> cmp) {
            Objects.requireNonNull(cmp); //判断cmp是否为空，如果为空，抛出NullPointerException
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getKey(), c2.getKey());
        }

        /**
         * 返回一个Map.Entry的比较器，使用给定value的比较器进行比较。
         * 如果指定的比较器是可序列化的，那么返回的比较器也要求是可序列化的。
         * @since 1.8
         */
        public static <K, V> Comparator<MyMap.Entry<K, V>> comparingByValue(Comparator<? super V> cmp) {
            Objects.requireNonNull(cmp);
            return (Comparator<MyMap.Entry<K, V>> & Serializable)
                (c1, c2) -> cmp.compare(c1.getValue(), c2.getValue());
        }
    }

    // Comparison and hashing  比较和散列

    /**
     * 平等地比较指定object和该map是否相等。
     * 如果指定地object也是一个map，并且这两个map表示同一个映射，那么返回true。
     * 更正式的，对于两个map如m1和m2，如果m1.entrySet().equals(m2.entrySet())，
     * 那么他们代表相同的映射。
     * 这样确保了equals方法可以在不同的Map实现类中正常工作。
     */
    boolean equals(Object o);

    /**
     * 返回该map的哈希码。
     * 一个map的哈希码被定义为该map的entrySet视图中每个entry的哈希码的和。
     * 这样确保了对于任意两个map如m1和m2，
     * 如果m1.equals(m2)，那么意味着e1.hashCode()==e2.hashCode()。
     * 按照一般的要求规定Object.hashCode
     */
    int hashCode();

    // Defaultable methods

    /**
     * 返回指定的key对应的value，如果map不包含key的映射，那么返回传入的默认值。
     * 
     * 默认实现不保证该方法的同步性或原子属性。任何提供原子性保证的实现，
     * 都必须重写次方法并记录其并发性。
     * 如果key的类型不符合，抛出ClassCastException
     * 如果该map不支持key为null，传入null抛NullPointerException
     * @since 1.8
     */
    default V getOrDefault(Object key, V defaultValue) {
        V v;
        return (((v = get(key)) != null) || containsKey(key))
            ? v
            : defaultValue;
    }

    /**
     * 在此map中对每个entry执行给定的操作，直到所有的entry执行完成或者抛出异常。
     * 除非实现类另有规定，否则操作将按照entrySet迭代器的顺序执行（如果迭代器指定了顺序）。
     * 由操作引发的异常会传递给调用者。
     * 默认实现等价于：
     * for(Map.Entry<k, v> entry : map.entrySet()){
     * 		action.accept(entry.getKey(),entry.getValue());
     * }
     * 默认实现不保证该方法的同步性或原子性。
     * 任何提供原子性保证的实现都必须重写此方法并记录其并发性。
     * @since 1.8
     */
    default void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (MyMap.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    /**
     * 将每个entry的value替换为在该entry上调用给定函数的结果，
     * 直到所有的entry执行完成或者抛出异常。由操作引发的异常会传递给调用者。
     * 默认实现等价于：
     * for (Map.Entry<K, V> entry : map.entrySet())
     *     entry.setValue(function.apply(entry.getKey(), entry.getValue()));
     * }
     * 默认实现不保证该方法的同步性，
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param function the function to apply to each entry
     * @throws UnsupportedOperationException if the {@code set} operation
     * is not supported by this map's entry set iterator.
     * @throws ClassCastException if the class of a replacement value
     * prevents it from being stored in this map
     * @throws NullPointerException if the specified function is null, or the
     * specified replacement value is null, and this map does not permit null
     * values
     * @throws ClassCastException if a replacement value is of an inappropriate
     *         type for this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if function or a replacement value is null,
     *         and this map does not permit null keys or values
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws IllegalArgumentException if some property of a replacement value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ConcurrentModificationException if an entry is found to be
     * removed during iteration
     * @since 1.8
     */
    default void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Objects.requireNonNull(function);
        for (MyMap.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }

            // ise thrown from function is not a cme.
            v = function.apply(k, v);

            try {
                entry.setValue(v);
            } catch(IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
        }
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}) associates it with the given value and returns
     * {@code null}, else returns the current value.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code
     * map}:
     *
     * <pre> {@code
     * V v = map.get(key);
     * if (v == null)
     *     v = map.put(key, value);
     *
     * return v;
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     *         {@code null} if there was no mapping for the key.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with the key,
     *         if the implementation supports null values.)
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the key or value is of an inappropriate
     *         type for this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key or value is null,
     *         and this map does not permit null keys or values
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @since 1.8
     */
    default V putIfAbsent(K key, V value) {
        V v = get(key);
        if (v == null) {
            v = put(key, value);
        }

        return v;
    }

    /**
     * Removes the entry for the specified key only if it is currently
     * mapped to the specified value.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code map}:
     *
     * <pre> {@code
     * if (map.containsKey(key) && Objects.equals(map.get(key), value)) {
     *     map.remove(key);
     *     return true;
     * } else
     *     return false;
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param key key with which the specified value is associated
     * @param value value expected to be associated with the specified key
     * @return {@code true} if the value was removed
     * @throws UnsupportedOperationException if the {@code remove} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the key or value is of an inappropriate
     *         type for this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key or value is null,
     *         and this map does not permit null keys or values
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @since 1.8
     */
    default boolean remove(Object key, Object value) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, value) ||
            (curValue == null && !containsKey(key))) {
            return false;
        }
        remove(key);
        return true;
    }

    /**
     * Replaces the entry for the specified key only if currently
     * mapped to the specified value.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code map}:
     *
     * <pre> {@code
     * if (map.containsKey(key) && Objects.equals(map.get(key), value)) {
     *     map.put(key, newValue);
     *     return true;
     * } else
     *     return false;
     * }</pre>
     *
     * The default implementation does not throw NullPointerException
     * for maps that do not support null values if oldValue is null unless
     * newValue is also null.
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param key key with which the specified value is associated
     * @param oldValue value expected to be associated with the specified key
     * @param newValue value to be associated with the specified key
     * @return {@code true} if the value was replaced
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of a specified key or value
     *         prevents it from being stored in this map
     * @throws NullPointerException if a specified key or newValue is null,
     *         and this map does not permit null keys or values
     * @throws NullPointerException if oldValue is null and this map does not
     *         permit null values
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws IllegalArgumentException if some property of a specified key
     *         or value prevents it from being stored in this map
     * @since 1.8
     */
    default boolean replace(K key, V oldValue, V newValue) {
        Object curValue = get(key);
        if (!Objects.equals(curValue, oldValue) ||
            (curValue == null && !containsKey(key))) {
            return false;
        }
        put(key, newValue);
        return true;
    }

    /**
     * Replaces the entry for the specified key only if it is
     * currently mapped to some value.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code map}:
     *
     * <pre> {@code
     * if (map.containsKey(key)) {
     *     return map.put(key, value);
     * } else
     *     return null;
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
      *
     * @param key key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or
     *         {@code null} if there was no mapping for the key.
     *         (A {@code null} return can also indicate that the map
     *         previously associated {@code null} with the key,
     *         if the implementation supports null values.)
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key or value is null,
     *         and this map does not permit null keys or values
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this map
     * @since 1.8
     */
    default V replace(K key, V value) {
        V curValue;
        if (((curValue = get(key)) != null) || containsKey(key)) {
            curValue = put(key, value);
        }
        return curValue;
    }

    /**
     * If the specified key is not already associated with a value (or is mapped
     * to {@code null}), attempts to compute its value using the given mapping
     * function and enters it into this map unless {@code null}.
     *
     * <p>If the function returns {@code null} no mapping is recorded. If
     * the function itself throws an (unchecked) exception, the
     * exception is rethrown, and no mapping is recorded.  The most
     * common usage is to construct a new object serving as an initial
     * mapped value or memoized result, as in:
     *
     * <pre> {@code
     * map.computeIfAbsent(key, k -> new Value(f(k)));
     * }</pre>
     *
     * <p>Or to implement a multi-value map, {@code Map<K,Collection<V>>},
     * supporting multiple values per key:
     *
     * <pre> {@code
     * map.computeIfAbsent(key, k -> new HashSet<V>()).add(v);
     * }</pre>
     *
     *
     * @implSpec
     * The default implementation is equivalent to the following steps for this
     * {@code map}, then returning the current value or {@code null} if now
     * absent:
     *
     * <pre> {@code
     * if (map.get(key) == null) {
     *     V newValue = mappingFunction.apply(key);
     *     if (newValue != null)
     *         map.put(key, newValue);
     * }
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties. In particular, all implementations of
     * subinterface {@link java.util.concurrent.ConcurrentMap} must document
     * whether the function is applied once atomically only if the value is not
     * present.
     *
     * @param key key with which the specified value is to be associated
     * @param mappingFunction the function to compute a value
     * @return the current (existing or computed) value associated with
     *         the specified key, or null if the computed value is null
     * @throws NullPointerException if the specified key is null and
     *         this map does not support null keys, or the mappingFunction
     *         is null
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @since 1.8
     */
    default V computeIfAbsent(K key,
            Function<? super K, ? extends V> mappingFunction) {
        Objects.requireNonNull(mappingFunction);
        V v;
        if ((v = get(key)) == null) {
            V newValue;
            if ((newValue = mappingFunction.apply(key)) != null) {
                put(key, newValue);
                return newValue;
            }
        }

        return v;
    }

    /**
     * If the value for the specified key is present and non-null, attempts to
     * compute a new mapping given the key and its current mapped value.
     *
     * <p>If the function returns {@code null}, the mapping is removed.  If the
     * function itself throws an (unchecked) exception, the exception is
     * rethrown, and the current mapping is left unchanged.
    *
     * @implSpec
     * The default implementation is equivalent to performing the following
     * steps for this {@code map}, then returning the current value or
     * {@code null} if now absent:
     *
     * <pre> {@code
     * if (map.get(key) != null) {
     *     V oldValue = map.get(key);
     *     V newValue = remappingFunction.apply(key, oldValue);
     *     if (newValue != null)
     *         map.put(key, newValue);
     *     else
     *         map.remove(key);
     * }
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties. In particular, all implementations of
     * subinterface {@link java.util.concurrent.ConcurrentMap} must document
     * whether the function is applied once atomically only if the value is not
     * present.
     *
     * @param key key with which the specified value is to be associated
     * @param remappingFunction the function to compute a value
     * @return the new value associated with the specified key, or null if none
     * @throws NullPointerException if the specified key is null and
     *         this map does not support null keys, or the
     *         remappingFunction is null
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @since 1.8
     */
    default V computeIfPresent(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue;
        if ((oldValue = get(key)) != null) {
            V newValue = remappingFunction.apply(key, oldValue);
            if (newValue != null) {
                put(key, newValue);
                return newValue;
            } else {
                remove(key);
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Attempts to compute a mapping for the specified key and its current
     * mapped value (or {@code null} if there is no current mapping). For
     * example, to either create or append a {@code String} msg to a value
     * mapping:
     *
     * <pre> {@code
     * map.compute(key, (k, v) -> (v == null) ? msg : v.concat(msg))}</pre>
     * (Method {@link #merge merge()} is often simpler to use for such purposes.)
     *
     * <p>If the function returns {@code null}, the mapping is removed (or
     * remains absent if initially absent).  If the function itself throws an
     * (unchecked) exception, the exception is rethrown, and the current mapping
     * is left unchanged.
     *
     * @implSpec
     * The default implementation is equivalent to performing the following
     * steps for this {@code map}, then returning the current value or
     * {@code null} if absent:
     *
     * <pre> {@code
     * V oldValue = map.get(key);
     * V newValue = remappingFunction.apply(key, oldValue);
     * if (oldValue != null ) {
     *    if (newValue != null)
     *       map.put(key, newValue);
     *    else
     *       map.remove(key);
     * } else {
     *    if (newValue != null)
     *       map.put(key, newValue);
     *    else
     *       return null;
     * }
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties. In particular, all implementations of
     * subinterface {@link java.util.concurrent.ConcurrentMap} must document
     * whether the function is applied once atomically only if the value is not
     * present.
     *
     * @param key key with which the specified value is to be associated
     * @param remappingFunction the function to compute a value
     * @return the new value associated with the specified key, or null if none
     * @throws NullPointerException if the specified key is null and
     *         this map does not support null keys, or the
     *         remappingFunction is null
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @since 1.8
     */
    default V compute(K key,
            BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        V oldValue = get(key);

        V newValue = remappingFunction.apply(key, oldValue);
        if (newValue == null) {
            // delete mapping
            if (oldValue != null || containsKey(key)) {
                // something to remove
                remove(key);
                return null;
            } else {
                // nothing to do. Leave things as they were.
                return null;
            }
        } else {
            // add or replace old mapping
            put(key, newValue);
            return newValue;
        }
    }

    /**
     * 如果指定的key没有与一个value关联或者key的映射值是null，那么用给定的非空value和key进行关联。
     * 否则，使用给定的remapping函数计算结果替换之前关联的value。
     * 如果计算结果为null，移除之前关联的value。
     * 这个方法可能会用在，当将多个映射值组合在一起。
     * 例如，要创建或者追加一个String msg到一个value的映射：
     * 默认的实现等价于如下所示，如果没有默认值，则返回当前value或null：
     * V oldValue = map.get(key);
     * V newValue = (oldValue == null) ? value :
     *              remappingFunction.apply(oldValue, value);
     * 
     * 
     * If the specified key is not already associated with a value or is
     * associated with null, associates it with the given non-null value.
     * Otherwise, replaces the associated value with the results of the given
     * remapping function, or removes if the result is {@code null}. This
     * method may be of use when combining multiple mapped values for a key.
     * For example, to either create or append a {@code String msg} to a
     * value mapping:
     *
     * <pre> {@code
     * map.merge(key, msg, String::concat)
     * }</pre>
     *
     * <p>If the function returns {@code null} the mapping is removed.  If the
     * function itself throws an (unchecked) exception, the exception is
     * rethrown, and the current mapping is left unchanged.
     *
     * @implSpec
     * The default implementation is equivalent to performing the following
     * steps for this {@code map}, then returning the current value or
     * {@code null} if absent:
     *
     * <pre> {@code
     * V oldValue = map.get(key);
     * V newValue = (oldValue == null) ? value :
     *              remappingFunction.apply(oldValue, value);
     * if (newValue == null)
     *     map.remove(key);
     * else
     *     map.put(key, newValue);
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties. In particular, all implementations of
     * subinterface {@link java.util.concurrent.ConcurrentMap} must document
     * whether the function is applied once atomically only if the value is not
     * present.
     *
     * @param key key with which the resulting value is to be associated
     * @param value the non-null value to be merged with the existing value
     *        associated with the key or, if no existing value or a null value
     *        is associated with the key, to be associated with the key
     * @param remappingFunction the function to recompute a value if present
     * @return the new value associated with the specified key, or null if no
     *         value is associated with the key
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *         does not support null keys or the value or remappingFunction is
     *         null
     * @since 1.8
     */
    default V merge(K key, V value,
            BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        Objects.requireNonNull(remappingFunction);
        Objects.requireNonNull(value);
        V oldValue = get(key);
        V newValue = (oldValue == null) ? value :
                   remappingFunction.apply(oldValue, value);
        if(newValue == null) {
            remove(key);
        } else {
            put(key, newValue);
        }
        return newValue;
    }
}
