package vn.bkdn.cntt.entity;

import javax.persistence.*;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class EducatingStaff {
    private Long id;
    private String firstName;
    private String lastName;

    private SystemAccount systemAccount;

    public EducatingStaff() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne
    @JoinColumn(name = "systemAccount_id")
    public SystemAccount getSystemAccount() {
        return systemAccount;
    }

    public void setSystemAccount(SystemAccount systemAccount) {
        this.systemAccount = systemAccount;
    }
}
