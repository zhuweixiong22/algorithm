package day04;

import java.util.*;

/**
 * @author novo
 * @date 2022/1/28-20:59
 */
public class TestComparator {
    public static class IdAscendingComparator implements Comparator<Student> {
        // 返回负数的时候，第一个参数排在前面
        // 返回正数的时候，第二个参数排在前面
        // 返回0的时候，谁在前面无所谓
        @Override
        public int compare(Student o1, Student o2) {
            //return o1.getId() - o2.getId();
            //return o2.getAge() -o1.getAge();
            // id升 如果id相等 比较age 降序
            return !o1.getId().equals(o2.getId()) ? o1.getId() - o2.getId() : o2.getAge() - o1.getAge();
        }
    }

    public static void main(String[] args) {
        Student student1 = new Student(1, 42, "A");
        Student student2 = new Student(4, 21, "B");
        Student student3 = new Student(4, 12, "C");
        Student student4 = new Student(4, 62, "D");
        Student student5 = new Student(5, 42, "E");
        Student[] students = new Student[]{student1, student2, student3, student4, student5};
        Arrays.sort(students, new IdAscendingComparator());
        for (Student stu : students) {
            System.out.println(stu.getId() + " " + stu.getAge() + stu.getName());
        }
        System.out.println("===================");
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.sort(new IdAscendingComparator());
        list.forEach(student -> System.out.println(student.getId() + " " + student.getAge() + student.getName()));
        System.out.println("===================");
        TreeMap<Student, String> treeMap = new TreeMap<>(Comparator.comparingInt(Student::getAge));
        treeMap.put(student1, "学生1");
        treeMap.put(student2, "学生2");
        treeMap.put(student3, "学生3");
        treeMap.put(student4, "学生4");
        treeMap.put(student5, "学生5");
        System.out.println(treeMap.size());
        treeMap.forEach((stu, s) -> System.out.println(stu.getId() + " " + stu.getAge() + stu.getName()));
    }
}
