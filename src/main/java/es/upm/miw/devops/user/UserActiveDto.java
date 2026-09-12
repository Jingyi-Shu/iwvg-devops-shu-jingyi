package es.upm.miw.devops.user;

public class UserActiveDto {
    private Boolean active;

    public UserActiveDto() {}

    public UserActiveDto(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}