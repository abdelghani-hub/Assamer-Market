package com.youcode.sudest_market.web.vm.mapper;

import com.youcode.sudest_market.domain.AppUser;
import com.youcode.sudest_market.web.vm.user.UserVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserVmMapper {
    UserVM toUserVM(AppUser appUser);
    AppUser toUser(UserVM userVM);
}
