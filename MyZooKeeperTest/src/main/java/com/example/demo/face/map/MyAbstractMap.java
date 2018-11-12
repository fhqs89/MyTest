package com.example.demo.face.map;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 *
 * @author  Josh Bloch
 * @author  Neal Gafter
 * @see Map
 * @see Collection
 * @since 1.2
 */

public abstract class MyAbstractMap<K,V> implements MyMap<K,V> {
    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 用于由子类构造函数调用，protected
     */
    protected MyAbstractMap() {
    }

    // Query Operations

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 实现返回 entrySet().size()
     */
    public int size() {
        return entrySet().size();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 如果map没有任何key-value，返回true
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现遍历 entrySet() 查找指定的value，
     * 如果有返回true
     * 如果没有 false
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsValue(Object value) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (value==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getValue()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (value.equals(e.getValue()))
                    return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现遍历 entrySet()，查找指定key，
     * 如果有返回true
     * 没有返回 false
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsKey(Object key) {
        Iterator<MyMap.Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return true;
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现遍历 entrySet()，查找指定key的条目，
     * 如果没有返回null，这个实现是非线程安全的，线程安全需要覆盖重写。
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V get(Object key) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (key==null) {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    return e.getValue();
            }
        } else {
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    return e.getValue();
            }
        }
        return null;
    }


    // Modification Operations  修改操作

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public V put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现遍历 entrySet()，查找对应key的entry。
     * 如果找到，获取到value值，然后将这个entry从collection中移除，
     * 并返回获取到的值。
     * 如果没有找到返回null。
     * 这个实现是非线程安全的，线程安全需要覆盖重写。
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public V remove(Object key) {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        Entry<K,V> correctEntry = null;
        if (key==null) {
            while (correctEntry==null && i.hasNext()) {
                Entry<K,V> e = i.next();
                if (e.getKey()==null)
                    correctEntry = e;
            }
        } else {
            while (correctEntry==null && i.hasNext()) {
                Entry<K,V> e = i.next();
                if (key.equals(e.getKey()))
                    correctEntry = e;
            }
        }

        V oldValue = null;
        if (correctEntry !=null) {
            oldValue = correctEntry.getValue();
            i.remove();
        }
        return oldValue;
    }


    // Bulk Operations   批量操作

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现迭代指定map的 <tt>entrySet()</tt> 集合, 
     * 重复执行map的<tt>put</tt>操作。
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     */
    public void putAll(MyMap<? extends K, ? extends V> m) {
        for (MyMap.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * 调用<tt>entrySet().clear()</tt>.
     * @throws UnsupportedOperationException {@inheritDoc}
     */
    public void clear() {
        entrySet().clear();
    }


    // Views 视图

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 在第一次请求该map时，每个字段都被初始化为包含适当集合视图的实例。
     * 这些集合视图是无状态的，因此没有必要创建多个。
     * 因为访问这些字段时不执行同步，
     * 可以预期java.util.Map使用这些字段的视图类没有final字段(或者除了外部字段以外的任何字段)。
     * 遵守这些规则将使字段更安全。
     * ？ Adhering to this rule would make the races on these fields benign.
     * <p>It is also imperative that implementations read the field only once,
     * as in:
     * 实现只实例字段一次也是必须的，比如:
     * <pre> {@code
     * public Set<K> keySet() {
     *   Set<K> ks = keySet;  // single racy read   方法内部定义。
     *   if (ks == null) {
     *     ks = new KeySet();
     *     keySet = ks;
     *   }
     *   return ks;
     * }
     *}</pre>
     */
    transient Set<K>        keySet;
    transient Collection<V> values;

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现返回一个Set子类的实现。
     * 这个子集的迭代方法，返回一个map的<tt>entrySet()</tt>的迭代器的包装对象。
     * <tt>size</tt>方法执行map的<tt>size</tt>方法。
     * <tt>contains</tt> 方法执行的map的<tt>containsKey</tt> 方法.
     * 
     * <p>第一次调用该方法时就创建了集合,并且响应所有后续调用。
     * 是非线程安全的，因此多个线程调用可能不会返回相同结果。
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new AbstractSet<K>() {
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        private Iterator<Entry<K,V>> i = entrySet().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public K next() {
                            return i.next().getKey();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return MyAbstractMap.this.size();
                }

                public boolean isEmpty() {
                    return MyAbstractMap.this.isEmpty();
                }

                public void clear() {
                    MyAbstractMap.this.clear();
                }

                public boolean contains(Object k) {
                    return MyAbstractMap.this.containsKey(k);
                }
            };
            keySet = ks;
        }
        return ks;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec
     * 这个实现返回一个collection的子类实现集合。
     * 这个子集的迭代方法，返回一个map的<tt>entrySet()</tt>的迭代器的包装集合。
     * 
     * <tt>size</tt>方法执行map的<tt>size</tt>方法。
     * <tt>contains</tt> 方法执行的map的<tt>containsKey</tt> 方法.
     * 
     * <p>在第一次调用此方法时创建集合，并且响应所有后续调用。
     * 是非线程安全的，因此多个线程调用可能不会返回相同结果。
     */
    public Collection<V> values() {
        Collection<V> vals = values;
        if (vals == null) {
            vals = new AbstractCollection<V>() {
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        private Iterator<Entry<K,V>> i = entrySet().iterator();

                        public boolean hasNext() {
                            return i.hasNext();
                        }

                        public V next() {
                            return i.next().getValue();
                        }

                        public void remove() {
                            i.remove();
                        }
                    };
                }

                public int size() {
                    return MyAbstractMap.this.size();
                }

                public boolean isEmpty() {
                    return MyAbstractMap.this.isEmpty();
                }

                public void clear() {
                    MyAbstractMap.this.clear();
                }

                public boolean contains(Object v) {
                    return MyAbstractMap.this.containsValue(v);
                }
            };
            values = vals;
        }
        return vals;
    }

    public abstract Set<Entry<K,V>> entrySet();


    // Comparison and hashing  比较和哈希

    /**
     * 比较指定对象和该map是否相等
     * 如果这个对象继承Map接口，并且两个map表示相同的map 
     * <tt>true</tt> if the given object is also a map and the two maps represent the same mappings.
     * 更正式的讲，两个map对象，m1 和m2表示相同的map，
     * 那么有 <tt>m1.entrySet().equals(m2.entrySet())<tt>
     * 这样确保了<tt>equals</tt>方法在不同的<tt>map接口<tt>实现中正常工作。
     *
     * @implSpec
     * 这个实现首先检查指定的对象是否是该map对象，如果是，返回true。
     * 接着检测指定对象是否继承Map接口，而且map大小，是否与该map大小相同，如果不是返回false，
     * 如果是，迭代Map中所有<tt>entrySet</tt>集合，检查指定的map是否包含该map的所有映射，
     * 如果全部包含，则返回true，否则false。
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof MyMap))
            return false;
        MyMap<?,?> m = (MyMap<?,?>) o;
        if (m.size() != size())
            return false;

        try {
            Iterator<Entry<K,V>> i = entrySet().iterator();
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                if (value == null) {
                    if (!(m.get(key)==null && m.containsKey(key)))
                        return false;
                } else {
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }

    /**
     * 返回次map的hashCode值。
     * 映射的哈希码被定义为，该map所有的entrySet()视图的hashCode的总和。
     * Returns the hash code value for this map.  The hash code of a map is
     * defined to be the sum of the hash codes of each entry in the map's
     * <tt>entrySet()</tt> view.  
     * 这样确保了对于两个map，m1和m2，有<tt>m1.equals(m2)</tt>的同时，
     * <tt>m1.hashCode()==m2.hashCode()</tt>。
     */
    public int hashCode() {
        int h = 0;
        Iterator<Entry<K,V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
    }

    /**
     * 返回一个String表示的map。
     * Returns a string representation of this map.  
     * 这个String表示，该map所有<tt>entrySet</tt>视图包含的key-value映射，
     * 并用<tt>"{}"</tt>包起来，相邻的映射用<tt>", "</tt>隔开，
     * 每组key-value用<tt>"= "</tt>在中间，
     */
    public String toString() {
        Iterator<Entry<K,V>> i = entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append(key   == this ? "(this Map)" : key);
            sb.append('=');
            sb.append(value == this ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

    /**
     * 返回<tt>AbstractMap</tt>的一个前拷贝实例：key和value本身不会被拷贝。
     */
    protected Object clone() throws CloneNotSupportedException {
    	MyAbstractMap<?,?> result = (MyAbstractMap<?,?>)super.clone();
        result.keySet = null;
        result.values = null;
        return result;
    }

    /**
     * private方法
     * SimpleEntry和SimpleImmutableEntry的实用方法。
     * 用于测试检测null值的相等。
     *
     * NB: Do not replace with Object.equals until JDK-8015417 is resolved.
     */
    private static boolean eq(Object o1, Object o2) {
        return o1 == null ? o2 == null : o1.equals(o2);
    }

    // Implementation Note: SimpleEntry and SimpleImmutableEntry
    // are distinct unrelated classes, even though they share
    // some code. Since you can't add or subtract final-ness
    // of a field in a subclass, they can't share representations,
    // and the amount of duplicated code is too small to warrant
    // exposing a common abstract class.
    //实现注意：SimpleEntry和SimpleImmutableEntry是不同的不相关的类，即时他们共享一些代码。
    //由于用户无法在子类中添加或减去字段，因此它们无法功能性表示形式，并且重复的代码数量太小，
    //而法保证公开常见的抽象类。

    /**
     * Entry对象，保存key和value的条目。
     * value可以用<tt>setValue</tt>方法来改变。
     * 此类有助于构建自定义map的实现。
     * 例如，通过<tt>Map.entrySet().toArray</tt>方法，
     * 他可以很方便的返回<tt>SimpleEntry</tt>数组。
     * @since 1.6
     */
    public static class SimpleEntry<K,V>
        implements Entry<K,V>, java.io.Serializable
    {
        private static final long serialVersionUID = -8499721149061103585L;

        private final K key;
        private V value;

        /**
         * 根据指定的key和value创建一个entry代表一个映射。
         * @param key the key represented by this entry
         * @param value the value represented by this entry
         */
        public SimpleEntry(K key, V value) {
            this.key   = key;
            this.value = value;
        }

        /**
         * 根据指定的entry来创建一个相同的entry映射
         *
         * @param entry the entry to copy
         */
        public SimpleEntry(Entry<? extends K, ? extends V> entry) {
            this.key   = entry.getKey();
            this.value = entry.getValue();
        }

        /**
         * 返回该entry相应的key值。
         * @return the key corresponding to this entry
         */
        public K getKey() {
            return key;
        }

        /**
         * 返回该entry相应的value的值。
         * @return the value corresponding to this entry
         */
        public V getValue() {
            return value;
        }

        /**
         * 用指定value替换该entry的value
         * @param value new value to be stored in this entry
         * @return the old value corresponding to the entry
         */
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * 将指定对象与此条目进行比较以获得相等性。
         * Compares the specified object with this entry for equality.
         * 如果对象是于个map.entry，且两个entry的映射相同，就返回true。
         * 如果两个entry，e1和e2有如下关系，则视为相同entry：
         * <pre>
         *   (e1.getKey()==null ?
         *    e2.getKey()==null :
         *    e1.getKey().equals(e2.getKey()))
         *   &amp;&amp;
         *   (e1.getValue()==null ?
         *    e2.getValue()==null :
         *    e1.getValue().equals(e2.getValue()))</pre>
         * 这确保了不同Map.Entry实现都可以通过这个方法判断。
         * @param o object to be compared for equality with this map entry
         * @return {@code true} if the specified object is equal to this map
         *         entry
         * @see    #hashCode
         */
        public boolean equals(Object o) {
            if (!(o instanceof MyMap.Entry))
                return false;
            MyMap.Entry<?,?> e = (MyMap.Entry<?,?>)o;
            return eq(key, e.getKey()) && eq(value, e.getValue());
        }

        /**
         * 返回这个map entry的hashCode。
         * map entry的hashCode是这样定义的：
         * <pre>
         *   (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *   (e.getValue()==null ? 0 : e.getValue().hashCode())</pre>
         * 这样确保了任意两个entry的code
         * @return the hash code value for this map entry
         * @see    #equals
         */
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                   (value == null ? 0 : value.hashCode());
        }

        /**
         * 返回代表这个map entry的String。
         * 这个方法返回entry的key和value，中间用("<tt>=</tt>")隔开。
         * @return a String representation of this map entry
         */
        public String toString() {
            return key + "=" + value;
        }

    }

    /**
     * 保持不可变键和值的Entry。
     * 这个类不支持<tt>setValue</tt>方法。
     * 这个类方便做一个线程安全的key-value映射快照。
     * @since 1.6
     */
    public static class SimpleImmutableEntry<K,V>
        implements Entry<K,V>, java.io.Serializable
    {
        private static final long serialVersionUID = 7138329143949025153L;

        private final K key;
        private final V value;

        /**
         * 根据指定的key和value创建一个entry映射。
         * @param key the key represented by this entry
         * @param value the value represented by this entry
         */
        public SimpleImmutableEntry(K key, V value) {
            this.key   = key;
            this.value = value;
        }

        /**
         * 根据指定的entry来创建一个相同的entry映射
         * @param entry the entry to copy
         */
        public SimpleImmutableEntry(Entry<? extends K, ? extends V> entry) {
            this.key   = entry.getKey();
            this.value = entry.getValue();
        }

        /**
         * 返回相应entry的key。
         * @return the key corresponding to this entry
         */
        public K getKey() {
            return key;
        }

        /**
         * 返回相应entry映射的value。
         * @return the value corresponding to this entry
         */
        public V getValue() {
            return value;
        }

        /**
         * 用指定value替换相应的entry的value。
         * 这个方法实现抛出 <tt>UnsupportedOperationException</tt>异常。
         * @param value new value to be stored in this entry
         * @return (Does not return)
         * @throws UnsupportedOperationException always
         */
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }

        /**
         * 将指定对象与此条目进行比较以获得相等性。
         * 如果对象是于个map.entry，且两个entry的映射相同，就返回true。
         * 如果两个entry，e1和e2有如下关系，则视为相同entry：
         * <pre>
         *   (e1.getKey()==null ?
         *    e2.getKey()==null :
         *    e1.getKey().equals(e2.getKey()))
         *   &amp;&amp;
         *   (e1.getValue()==null ?
         *    e2.getValue()==null :
         *    e1.getValue().equals(e2.getValue()))</pre>
         * 
         * 这确保了不同Map.Entry实现都可以通过这个方法判断。
         *
         * @param o object to be compared for equality with this map entry
         * @return {@code true} if the specified object is equal to this map
         *         entry
         * @see    #hashCode
         */
        public boolean equals(Object o) {
            if (!(o instanceof MyMap.Entry))
                return false;
            MyMap.Entry<?,?> e = (MyMap.Entry<?,?>)o;
            return eq(key, e.getKey()) && eq(value, e.getValue());
        }

        /**
         * 返回这个map entry的hashCode。
         * map entry的hashCode是这样定义的：
         * <pre>
         *   (e.getKey()==null   ? 0 : e.getKey().hashCode()) ^
         *   (e.getValue()==null ? 0 : e.getValue().hashCode())</pre>
         * 这样确保了任意两个entry的code
         * @return the hash code value for this map entry
         * @see    #equals
         */
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                   (value == null ? 0 : value.hashCode());
        }

        /**
         * 返回代表这个map entry的String。
         * 这个方法返回entry的key和value，中间用("<tt>=</tt>")隔开。
         * @return a String representation of this map entry
         */
        public String toString() {
            return key + "=" + value;
        }

    }

}
