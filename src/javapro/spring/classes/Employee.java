package javapro.spring.classes;

/**
 * @author:choumei
 * @date:2020/2/25 22:05
 * @Description:
 */
public class Employee {
    Integer id;
    String name;
    Dept dept;

    public Employee() {
    }

    public Employee(Integer id, String name, Dept dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dept=" + dept +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
