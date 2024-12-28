package com.youcode.sudest_market.domain;

import com.youcode.sudest_market.domain.enums.Role;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String address;

    // Relationships ************************************************
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "appUser")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "appUser")
    private List<Order> orders;

    @OneToMany(mappedBy = "appUser")
    private List<SellerRequest> sellerRequests;

    @OneToOne(mappedBy = "owner")
    private Store store;

    // **************************************************************

    public AppUser() {
    }

    public AppUser(String username, String password, String email, Role role, String firstName, String lastName, String phoneNumber, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public void addFavorite(Favorite favorite) {
        this.favorites.add(favorite);
    }

    public void removeFavorite(Favorite favorite) {
        this.favorites.remove(favorite);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public List<SellerRequest> getSellerRequests() {
        return sellerRequests;
    }

    public void setSellerRequests(List<SellerRequest> sellerRequests) {
        this.sellerRequests = sellerRequests;
    }

    public void addSellerRequest(SellerRequest sellerRequest) {
        this.sellerRequests.add(sellerRequest);
    }

    public void removeSellerRequest(SellerRequest sellerRequest) {
        this.sellerRequests.remove(sellerRequest);
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUser appUser)) return false;
        return Objects.equals(getId(), appUser.getId()) && Objects.equals(getUsername(), appUser.getUsername()) && Objects.equals(getPassword(), appUser.getPassword()) && Objects.equals(getEmail(), appUser.getEmail()) && getRole() == appUser.getRole() && Objects.equals(getFirstName(), appUser.getFirstName()) && Objects.equals(getLastName(), appUser.getLastName()) && Objects.equals(getPhoneNumber(), appUser.getPhoneNumber()) && Objects.equals(getAddress(), appUser.getAddress()) && Objects.equals(getCity(), appUser.getCity()) && Objects.equals(getFavorites(), appUser.getFavorites()) && Objects.equals(getOrders(), appUser.getOrders()) && Objects.equals(getSellerRequests(), appUser.getSellerRequests()) && Objects.equals(getStore(), appUser.getStore());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), getRole(), getFirstName(), getLastName(), getPhoneNumber(), getAddress(), getCity(), getFavorites(), getOrders(), getSellerRequests(), getStore());
    }
}
