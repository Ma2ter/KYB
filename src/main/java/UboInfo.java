public class UboInfo {

    private Boolean isShareholder;
    private Boolean isDirector;

    private String firstname;
    private String lastname;
    private String middlename;
    private String dob;
    private String phone;
    private String email;

    public static UboInfo defaultInstance() {
        return new UboInfo()
                .setShareholder(true)
                .setDirector(false)
                .setFirstname("Alexander")
                .setLastname("Alexandrov")
                .setMiddlename("Alexandrovich")
                .setDob("12/12/1993")
                .setPhone("7564736282")
                .setEmail("email@mail.ru");
    }

    public Boolean getShareholder() {
        return isShareholder;
    }

    public UboInfo setShareholder(Boolean shareholder) {
        isShareholder = shareholder;
        return this;
    }

    public Boolean getDirector() {
        return isDirector;
    }

    public UboInfo setDirector(Boolean director) {
        isDirector = director;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public UboInfo setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public UboInfo setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getMiddlename() {
        return middlename;
    }

    public UboInfo setMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public String getDob() {
        return dob;
    }

    public UboInfo setDob(String dob) {
        this.dob = dob;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UboInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UboInfo setEmail(String email) {
        this.email = email;
        return this;
    }
}
