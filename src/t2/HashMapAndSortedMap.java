package day02;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author novo
 * @date 2022/1/26-14:52
 */
public class HashMapAndSortedMap {
    public static class Student{
        int data;
        public Student(int data){
            this.data = data;
        }
    }
    public static void main(String[] args) {
        Integer a = 1111111111;
        Integer b = 1111111111;
        Student student1 = new Student(2);
        Student student2 = new Student(2);
        HashMap<Student,String> map1 = new HashMap<>();
        HashMap<Integer,String> map = new HashMap<>();

        // 基础数据类型的包装类且范围不在常量池，存放在哈希表中时，根据值匹配，属同一对象。
        map.put(a,"我是a");
        System.out.println(map.containsKey(b));

        // 非包装类的引用类型，存放在哈希表中时，根据引用值匹配，不属同一对象。
        map1.put(student2,"student2");
        System.out.println(map1.containsKey(student1));

        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(3,"3");
        treeMap.put(1,"3");
        treeMap.put(5,"3");
        treeMap.put(4,"3");
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.floorKey(2)); // 小于等于且离他最近的数
        System.out.println(treeMap.ceilingKey(3)); // 大于等于且离他最近的数

    }
}
