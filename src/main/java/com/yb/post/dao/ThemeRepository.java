package com.yb.post.dao;

import com.yb.post.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by 杨波 on 2017/5/27.
 */
public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Modifying
    @Query("update Theme t set t.lasttime= :date where t.theme_id= :theme_id")
    void updateLastTime(@Param("date")Date date, @Param("theme_id")Long theme_id);
    @Modifying
    @Query("update Theme t set t.reply_num= t.reply_num + 1 where t.theme_id= :theme_id")
    void updateReplyNum(@Param("theme_id")Long theme_id);
}
