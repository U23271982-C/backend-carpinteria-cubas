package com.content.employee_service.mapper.mapperImpl;

import com.content.employee_service.dto.request.PostRequestDTO;
import com.content.employee_service.dto.response.PostResponseDTO;
import com.content.employee_service.mapper.convert.Convert;
import com.content.employee_service.model.Post;

public interface PostMapper
        extends Convert<Post, PostRequestDTO, PostResponseDTO> {
    @Override
    PostResponseDTO toDTO(Post model);

    @Override
    Post toModel(PostRequestDTO dto);
}
