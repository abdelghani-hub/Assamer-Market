package com.youcode.sudest_market.web.vm.mapper;

import com.youcode.sudest_market.domain.Category;
import com.youcode.sudest_market.web.vm.category.CategoryVM;
import org.mapstruct.Mapper;


public interface CategoryVmMapper {
    CategoryVM toVM(Category Category);
    Category toEntity(CategoryVM categoryVM);
}
