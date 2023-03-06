package Dto;

public class UserDto {
    private int id;
    private String name;
    private String username;
    private String email;
    private AddressDto addressDto;
    private String phone;
    private String website;
    private CompanyDto companyDto;

    public UserDto(int id, String name, String username, String email, AddressDto addressDto, String phone, String website, CompanyDto companyDto) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.addressDto = addressDto;
        this.phone = phone;
        this.website = website;
        this.companyDto = companyDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddress() {
        return addressDto;
    }

    public void setAddress(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CompanyDto getCompany() {
        return companyDto;
    }

    public void setCompany(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", addressDto=" + addressDto +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", companyDto=" + companyDto +
                '}';
    }
}
