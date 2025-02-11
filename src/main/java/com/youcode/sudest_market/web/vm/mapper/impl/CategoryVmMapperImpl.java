package com.youcode.sudest_market.web.vm.mapper.impl;

import com.youcode.sudest_market.domain.Attachment;
import com.youcode.sudest_market.domain.Category;
import com.youcode.sudest_market.service.AttachmentService;
import com.youcode.sudest_market.web.vm.category.CategoryVM;

import com.youcode.sudest_market.web.vm.mapper.CategoryVmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryVmMapperImpl implements CategoryVmMapper {

    private final AttachmentService attachmentService;

    @Override
    public CategoryVM toVM(Category Category) {
        if ( Category == null ) {
            return null;
        }

        CategoryVM categoryVM = new CategoryVM();

        categoryVM.setName( Category.getName() );
        categoryVM.setId( Category.getId() );

        List<Attachment> attachments = attachmentService.findByEntityTypeAndEntityId("Category", Category.getId());
        if (attachments.isEmpty()) {
            return categoryVM;
        }

        categoryVM.setImageSrc("/api/v1/attachments/" + attachments.get(0).getId() + "/download");
        return categoryVM;
    }

    @Override
    public Category toEntity(CategoryVM categoryVM) {
        if ( categoryVM == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryVM.getId() );
        category.setName( categoryVM.getName() );

        return category;
    }
}
