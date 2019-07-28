import java.util.EventObject;

public class FormEvent extends EventObject {

    private String name;
    private String occupation;
    private int ageCategory;
    private String empCat;
    private String niNum;
    private Boolean ukCitizen;
    private String gender;

    public FormEvent(Object source) {
        super(source);

    }

    public FormEvent(Object source, String name, String occupation, int ageCat, String empCat, String niNum, Boolean ukCitizen, String gender) {
        super(source);

        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCat;
        this.empCat = empCat;
        this.niNum = niNum;
        this.ukCitizen = ukCitizen;
        this.gender = gender;

    }

    public String getGender(){

        return gender;
    }



    public String getNiNum(){

        return niNum;
    }


    public Boolean isukCitizen(){
        return ukCitizen;
    }


    public String getEmploymentCategory(){
        return empCat;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
