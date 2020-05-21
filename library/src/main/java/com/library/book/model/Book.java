package com.library.book.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.library.book.model.BaseEntity;
import javax.persistence.*;

/**
 * Simple JavaBean domain object that represents Books.
 */

@Entity
@Getter
@Setter
@ToString
public class Book extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "published")
    private String published;

    @Column(name = "rating")
    private Integer rating;

}