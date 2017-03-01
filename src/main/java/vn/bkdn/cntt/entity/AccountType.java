package vn.bkdn.cntt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by XuanVinh on 3/2/2017.
 */

@Entity
public class AccountType {
    private Long id;
    private String name;

    private Set<SystemAccount> systemAccounts;

    public AccountType() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "accountType")
    @JsonIgnore
    public Set<SystemAccount> getSystemAccounts() {
        return systemAccounts;
    }

    public void setSystemAccounts(Set<SystemAccount> systemAccounts) {
        this.systemAccounts = systemAccounts;
    }


}
