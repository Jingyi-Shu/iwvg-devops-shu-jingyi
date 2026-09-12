package es.upm.miw.devops.user;

public class User {
    private String id;
    private String firstName;
    private String familyName;
    private String email;
    private String identity;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private Boolean active;

    public User() {}

    public User(String id, String firstName, String familyName, String email, String identity,
                String address, String city, String province, String postalCode, Boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.familyName = familyName;
        this.email = email;
        this.identity = identity;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.active = active;
    }

    // Task 2️⃣: 判断是否为可计费用户（8个字段均非空且非纯空格）
    public Boolean isBillable() {
        return isNotBlank(firstName) && isNotBlank(familyName) && isNotBlank(email) &&
                isNotBlank(identity) && isNotBlank(address) && isNotBlank(city) &&
                isNotBlank(province) && isNotBlank(postalCode);
    }

    private boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

    // Getters 和 Setters 保持不变
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getFamilyName() { return familyName; }
    public void setFamilyName(String familyName) { this.familyName = familyName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getIdentity() { return identity; }
    public void setIdentity(String identity) { this.identity = identity; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}