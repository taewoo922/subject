package com.cod.market.article.entity;

import com.cod.market.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    private Member author;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    @ManyToMany
    Set<Member> voter;
}
