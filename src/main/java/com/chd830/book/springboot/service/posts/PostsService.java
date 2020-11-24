package com.chd830.book.springboot.service.posts;

import com.chd830.book.springboot.domain.posts.Posts;
import com.chd830.book.springboot.domain.posts.PostsRepository;
import com.chd830.book.springboot.web.dto.PostsListResponseDto;
import com.chd830.book.springboot.web.dto.PostsResponseDto;
import com.chd830.book.springboot.web.dto.PostsSaveRequestDto;
import com.chd830.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    //JPA의 영속성 컨텍스트때문에 데이터베이스에 쿼리를 날리는 부분이 없다.
    //논리적 개념으로 엔티티가 영속성 컨텍스트에 포함되어있는지 여부로 갈린다.
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(posts -> new PostsListResponseDto(posts)).collect(Collectors.toList());
    }

}
