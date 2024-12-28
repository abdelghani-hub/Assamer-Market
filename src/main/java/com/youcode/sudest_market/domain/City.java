package com.youcode.sudest_market.domain;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity()
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column(name = "postal_code", unique = true)
    private String postalCode;

    // Relationships ************************************************
    @OneToMany(mappedBy = "city")
    private List<AppUser> users;

    // **************************************************************

    public City() {
    }

    public City(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public List<AppUser> getUsers() {
        return users;
    }

    public void setUsers(List<AppUser> users) {
        this.users = users;
    }

    public void addUser(AppUser user) {
        this.users.add(user);
    }

    public void removeUser(AppUser user) {
        this.users.remove(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City city)) return false;
        return Objects.equals(getId(), city.getId()) && Objects.equals(getName(), city.getName()) && Objects.equals(getPostalCode(), city.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPostalCode());
    }
}
