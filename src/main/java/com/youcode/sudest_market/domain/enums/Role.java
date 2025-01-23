package com.youcode.sudest_market.domain.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(
            Permission.CAN_MANAGE_CATEGORY,
            Permission.CAN_MANAGE_USER,
            Permission.CAN_MANAGE_STORE,
            Permission.CAN_MANAGE_SELLER
    )),

    SELLER(Set.of(
            Permission.CAN_ADD_PRODUCT,
            Permission.CAN_UPDATE_PRODUCT,
            Permission.CAN_DELETE_PRODUCT
    )),

    CUSTOMER(Set.of(
            Permission.CAN_MANAGE_PROFILE,
            Permission.CAN_MANAGE_CART,
            Permission.CAN_MANAGE_FAVORITES,
            Permission.CAN_ORDER_PRODUCT
    )),

    DELIVERYAMAN(Set.of(
            Permission.CAN_MARK_ORDER_PENDING,
            Permission.CAN_MARK_ORDER_PROCESSING,
            Permission.CAN_MARK_ORDER_DELIVERED,
            Permission.CAN_MARK_ORDER_CANCELED
    ));

    public final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
