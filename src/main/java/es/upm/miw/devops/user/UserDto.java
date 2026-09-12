package es.upm.miw.devops.user;

public class UserDto {
    private String id;
    private String firstName;
    private String familyName;
    private String email;
    private Boolean active;

    public UserDto() {}

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.familyName = user.getFamilyName();
        this.email = user.getEmail();
        this.active = user.getActive();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}