package com.dbdev.music.service;

import com.dbdev.music.domain.Comment;
import com.dbdev.music.repository.CommentRepository;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SysUserRepository sysUserRepository;
    public List<Comment> processComments(List<Comment> list,boolean useTree)
    {
        List<Comment> result = new ArrayList<>();

        for(Comment comment:list)
        {
            if(comment.getParentId()==null)
            {
                result.add(comment);
            };
        }
        return result;

    }

    public List<Comment> getComments(Long albumId, Pageable pageable)
    {
        List<Comment> content = commentRepository.findByAlbumId(albumId, pageable).getContent();

        return processComments(content,false);
    }

}
