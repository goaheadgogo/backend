package com.patientpal.backend.post.service;

import com.patientpal.backend.common.exception.EntityNotFoundException;
import com.patientpal.backend.common.exception.ErrorCode;
import com.patientpal.backend.post.domain.Post;
import com.patientpal.backend.post.domain.PostType;
import com.patientpal.backend.post.dto.PostCreateRequestDto;
import com.patientpal.backend.post.dto.PostUpdateRequestDto;
import com.patientpal.backend.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    // TODO: wjdwwidz paging 처리
    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<Post> getNotices() {
        return postRepository.findAllByPostType(PostType.NOTICE);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));
    }

    @Transactional
    public Post createPost(PostCreateRequestDto createRequest) {
        Post post = Post.builder()
                .title(createRequest.getTitle())
                .content(createRequest.getContent())
                .postType(PostType.NOTICE)
                .build();

        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostUpdateRequestDto updateRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.POST_NOT_FOUND));

        post.update(updateRequest.getTitle(), updateRequest.getContent());

        return post;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}