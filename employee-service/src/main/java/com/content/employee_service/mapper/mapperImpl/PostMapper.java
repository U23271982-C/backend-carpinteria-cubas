package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.PostRequestDTO;
import com.content.employee_service.dto.response.PostResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper
        extends Convert<Post, PostRequestDTO, PostResponseDTO> {
    @Mapping(target = "post_name", source = "post_name")
    @Mapping(target = "description", source = "description")
    @Override
    PostResponseDTO toDTO(Post model);

    @Mapping(target = "post_name", source = "post_name")
    @Mapping(target = "description", source = "description")
    @Override
    Post toModel(PostRequestDTO dto);
}
