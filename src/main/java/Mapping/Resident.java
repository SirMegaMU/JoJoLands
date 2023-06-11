package Mapping;

public class Resident {

    public final String name, age, gender, residentialArea;
    public final String parents;
    public Stand stand = null;

    public Resident(String name, String age, String gender, String residentialArea, String parents) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.residentialArea = residentialArea;
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Residential Area: " + residentialArea + "\n" +
                "Parents: " + parents + "\n";
    }
}
