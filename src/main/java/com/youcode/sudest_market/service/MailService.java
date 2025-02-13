package com.youcode.sudest_market.service;

import com.youcode.sudest_market.domain.AppUser;

public interface MailService {

    boolean sellerRequestAccepted(AppUser appUser);
}
