public class CompanyInfo {
    private String companyName;
    private String registrationNumber;
    private String country;
    private String legalAddress;
    private String typeOfEntity;
    private String incorporatedOn;
    private String controlScheme;
    private Boolean skipUbo;

    public static CompanyInfo defaultInstance() {
        return new CompanyInfo()
                .setCompanyName("QWERTY")
                .setRegistrationNumber("123456")
                .setCountry("Russia")
                .setLegalAddress("Address asdasd asdas")
                .setTypeOfEntity("qwe")
                .setIncorporatedOn("12/01/1996")
                .setControlScheme("qweaskdnsadn")
                .setSkipUbo(false);
    }

    public String getCompanyName() {
        return companyName;
    }

    public CompanyInfo setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public CompanyInfo setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CompanyInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public CompanyInfo setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
        return this;
    }

    public String getTypeOfEntity() {
        return typeOfEntity;
    }

    public CompanyInfo setTypeOfEntity(String typeOfEntity) {
        this.typeOfEntity = typeOfEntity;
        return this;
    }

    public String getIncorporatedOn() {
        return incorporatedOn;
    }

    public CompanyInfo setIncorporatedOn(String incorporatedOn) {
        this.incorporatedOn = incorporatedOn;
        return this;
    }

    public String getControlScheme() {
        return controlScheme;
    }

    public CompanyInfo setControlScheme(String controlScheme) {
        this.controlScheme = controlScheme;
        return this;
    }

    public Boolean getSkipUbo() {
        return skipUbo;
    }

    public CompanyInfo setSkipUbo(Boolean skipUbo) {
        this.skipUbo = skipUbo;
        return this;
    }
}

