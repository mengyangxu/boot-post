package com.yb.post.dao;

import com.yb.post.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 杨波 on 2017/5/27.
 */
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
