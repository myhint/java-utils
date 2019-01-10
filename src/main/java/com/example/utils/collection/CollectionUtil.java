package com.example.utils.collection;

import com.example.utils.datetime.DateUtil;
import com.example.utils.random.RandomUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Blake on 2018/10/18
 */
public class CollectionUtil {

    /**
     * 判断为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 判断非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 求交集
     *
     * @param masterList 主数据列表
     * @param slaveList  从数据列表
     * @param <T>
     * @return
     */
    public static <T> List<T> getIntersection(List<T> masterList, List<T> slaveList) {
        return masterList.stream().filter(slaveList::contains).collect(Collectors.toList());
    }

    /**
     * 求差集
     *
     * @param masterList 主数据列表
     * @param slaveList  从数据列表
     * @param <T>
     * @return
     */
    public static <T> List<T> getDifferenceSet(List<T> masterList, List<T> slaveList) {
        return masterList.stream().filter(t -> !slaveList.contains(t)).collect(Collectors.toList());
    }

    /**
     * 求并集
     *
     * @param masterList 主数据列表
     * @param slaveList  从数据列表
     * @param <T>
     * @return
     */
    public static <T> List<T> getUnionSet(List<T> masterList, List<T> slaveList) {
        List<T> list = new ArrayList<>();
        list.addAll(masterList);
        list.addAll(slaveList);
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(2);

        System.out.println(" ====== 求交集 ====== ");
        List<Integer> intersection = CollectionUtil.getIntersection(list1, list2);
        intersection.forEach(System.out::println);

        System.out.println(" ====== 求差集 ====== ");

        List<Integer> differenceSet = CollectionUtil.getDifferenceSet(list1, list2);
        differenceSet.forEach(System.out::println);

        System.out.println(" ====== 求并集 ====== ");
        List<Integer> unionSet = CollectionUtil.getUnionSet(list1, list2);
        unionSet.forEach(System.out::println);

        BigDecimal bigDecimal = BigDecimal.valueOf(10);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(20.0);

        double v = bigDecimal.doubleValue() * bigDecimal1.doubleValue();

        System.out.println(v);

        String replace = DateUtil.dateFormatWithyyyyMMddHHmmss(new Date());


        String fixLengthString = RandomUtils.getFixLengthString(12);

        String concat = replace.concat(fixLengthString);

        System.out.println(replace.length());
        System.out.println(fixLengthString.length());
        System.out.println(concat);

        /**
         * 20181213121212   32-14 = 18位
         */

    }

}
