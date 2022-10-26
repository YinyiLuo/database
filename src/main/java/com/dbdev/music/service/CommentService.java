package com.dbdev.music.service;

import com.dbdev.music.domain.Comment;
import com.dbdev.music.repository.CommentRepository;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SysUserRepository sysUserRepository;
    public List<Comment> processComments(List<Comment> list,boolean useTree)
    {
        Map<Long,Comment> map = new HashMap<>();
        List<Comment> result = new ArrayList<>();

        //遍历一次，将所有comment 加入map中，方便查找
        for(Comment comment:list)
        {
            map.put(comment.getId(), comment);
        }

        //再遍历一次，把子评论放在父评论中
        for(Comment comment:list)
        {
//            if(comment.getParentId()!=null)
//            {
//                String parentName = map.get(comment.getParentId()).getName();
//                comment.setParentName(parentName);
//            }
            Long id;
            if(useTree)
            {
                id = comment.getParentId();//获取上一级的评论id，这样子是做成树的样子
            }
            else
            {
                id = comment.getRootParentId();//获取第一级评论的id
            }
            if(id!=null)
            {
                Comment p = map.get(id);//上面找root，现在找到了root，把这个评论加在root下
                if(p.getChild()==null)
                {
                    p.setChild(new ArrayList<>());
                }
                p.getChild().add(comment);
                System.out.println(p.getChild());
                System.out.println(map);
            }
        }

        for(Comment comment:list)
        {
            //这个就是第一级评论
            if(comment.getParentId()==null)
            {
                result.add(comment);
            }
//            map.put(comment.getId(),comment);
        }

        return result;
    }

    public List<Comment> getComments(Long albumId, Pageable pageable)
    {
        List<Comment> content = commentRepository.findByAlbumId(albumId, pageable).getContent();

        return processComments(content,false);
    }

}
