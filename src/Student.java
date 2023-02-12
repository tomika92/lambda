import java.util.ArrayList;
import java.util.List;

public class Student{
    public enum Plec{
        MALE, FEMALE
    }
    private String imie;
    private String nazwisko;
    private Plec plec;

    public Student(String imie, String nazwisko, Plec plec){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
    }

    public String getImie(){
        return imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public Plec getPlec(){
        return plec;
    }

    public void setImie(String imieN){
        imie = imieN;
    }

    public void setNazwisko(String nazwiskoN){
        nazwisko = nazwiskoN;
    }

    public void setPlec(Plec plecN){
        plec = plecN;
    }

    interface CheckStudent<Student> {
        boolean test(Student p);
    }

    interface Consumer<Student>{
        void accept(Student p);
    }

    public String toString(){
        return "Imie: " + this.imie + ", Nazwisko: " + this. nazwisko + ", plec: " + this.plec;
    }

    public static void doSth(List<Student> lista, CheckStudent<Student> testowanie, Consumer<Student> dzialanie){
        for (Student p : lista){
            if (testowanie.test(p)){
                dzialanie.accept(p);
            }
        }
    }

    public static void main(String[] args) {
        Student person = new Student("Anna", "Tomanek", Plec.FEMALE);
        Student person2 = new Student("Robert", "Lewandowski", Plec.MALE);
        Student person3 = new Student("Malgorzata", "Dudek", Plec.FEMALE);
        Student person4 = new Student("Lukasz", "Nowak", Plec.MALE);
        List<Student> lista = new ArrayList<>();
        lista.add(person);
        lista.add(person2);
        lista.add(person3);
        lista.add(person4);
        System.out.println("Rozmiar listy to: " + lista.size());
        for (int i = 0; i < lista.size(); i++){
            System.out.println(lista.get(i));
        }
        System.out.println();

        doSth(lista, p -> p.getPlec() == Student.Plec.MALE || "Anna".equals(p.getImie()), p -> System.out.println(p));
        System.out.println();
        doSth(
                lista,
                p -> p.getPlec() == Student.Plec.FEMALE,
                p -> {
                    p.setImie("Monika");
                    System.out.println(p);
                });
        System.out.println();
        doSth(
                lista,
                p -> "Dudek".equals(p.getNazwisko()) && p.getPlec() == Student.Plec.FEMALE,
                p -> {
                    p.setPlec(Plec.MALE);
                    System.out.println(p);
                });
    }
}