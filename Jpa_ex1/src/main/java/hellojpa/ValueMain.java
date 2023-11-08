package hellojpa;

class Person{
    private String name;
    public Person(String name){
        this.name =name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class ValueMain {
    public static void main(String[] args) {
        Person jemok = new Person("jemok");
        Person Seul = jemok;
        System.out.println("jemok = " + jemok.getName());
        System.out.println("Seul = " + Seul.getName());
        Seul.setName("Seult");
        System.out.println("jemok = " + jemok.getName());
        System.out.println("Seul = " + Seul.getName());
    }
}
