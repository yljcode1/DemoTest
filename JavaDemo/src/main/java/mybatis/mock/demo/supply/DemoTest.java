package mybatis.mock.demo.supply;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lsx
 * @date 2022/4/15
 */
public class DemoTest {
    public static void main(String[] args) throws Exception {
        List<String> strings = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream("d://1.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {
            strings.add(str);
        }
        setObjectValue(strings);
    }

    private static void setObjectValue(final List<String> strings) {
        List<Emp> emps = new ArrayList<>();
        strings.stream().forEach(x -> {
            String[] split = x.split(",");
            Emp emp = new Emp();
            emp.setName(split[0]);
            emp.setId(split[1]);
            emp.setAge(split[2]);
            if (split.length > 3) {
                emp.setManageId(split[3]);
            }
            emps.add(emp);
        });
        List<Emp> newEmps = sortEmps(emps);
        printEmps(newEmps);
    }

    private static void printEmps(List<Emp> newEmps) {
        String str = "==";
        for (Emp newEmp : newEmps) {
            if (newEmp.getChildEmp() != null) {
                System.out.println(String.join("", Collections.nCopies(newEmp.getLevel(), "======")) + newEmp);
                printEmps(newEmp.getChildEmp());
            }
        }
    }

    private static List<Emp> sortEmps(final List<Emp> emps) {
        List<Emp> parentEmps = new ArrayList<>();
        List<Emp> newEmps = new ArrayList<>();

        // 1、获取到第一级节点
        for (Emp emp : emps) {
            if (null == emp.getManageId()) {
                emp.setLevel(1);
                parentEmps.add(emp);
            }
        }
        // 2、递归获取到子节点
        for (Emp parentEmp : parentEmps) {
            parentEmp = tree(parentEmp, emps);
            newEmps.add(parentEmp);
        }
        return newEmps;

    }

    private static Emp tree(Emp parentEmp, List<Emp> emps) {
        for (Emp emp : emps) {
            if (Objects.equals(parentEmp.getId(), emp.getManageId())) {
                emp.setLevel(parentEmp.getLevel() + 1);
                emp = tree(emp, emps);
                parentEmp.getChildEmp().add(emp);
            }
        }
        return parentEmp;
    }


    static class Emp {
        private String name;
        private String id;
        private String age;
        private Integer level;
        private String manageId;
        private List<Emp> childEmp = new ArrayList<>();


        public List<Emp> getChildEmp() {
            return childEmp;
        }

        public void setChildEmp(List<Emp> childEmp) {
            this.childEmp = childEmp;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(final String id) {
            this.id = id;
        }

        public String getAge() {
            return age;
        }

        public void setAge(final String age) {
            this.age = age;
        }

        public String getManageId() {
            return manageId;
        }

        public void setManageId(final String manageId) {
            this.manageId = manageId;
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "Emp{" + "name='" + name + '\'' + ", id='" + id + '\'' + ", age='" + age + '\'' + ", manageId='" + manageId + '\'' + '}';
        }
    }
}
