package com.youcode.sudest_market.domain.enums;

public enum Permission {

    CAN_MANAGE_PROFILE("can_manage_profile"),
    CAN_MANAGE_CART("can_manage_cart"),
    CAN_MANAGE_FAVORITES("can_manage_favorites"),

    CAN_ADD_PRODUCT("can_add_product"),
    CAN_UPDATE_PRODUCT("can_update_product"),
    CAN_DELETE_PRODUCT("can_delete_product"),
    CAN_ORDER_PRODUCT("can_order_product"),


    CAN_MARK_ORDER_PENDING("can_mark_order_pending"),
    CAN_MARK_ORDER_PROCESSING("can_mark_order_processing"),
    CAN_MARK_ORDER_DELIVERED("can_mark_order_delivered"),
    CAN_MARK_ORDER_CANCELED("can_mark_order_canceled"),

    CAN_MANAGE_CATEGORY("can_manage_category"),
    CAN_MANAGE_USER("can_manage_user"),
    CAN_MANAGE_STORE("can_manage_store"),
    CAN_MANAGE_SELLER("can_manage_seller")
    ;

    public final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
