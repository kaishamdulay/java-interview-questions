package com.collective.backbase.datasource.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(
    name = "url_model",
    indexes = {
      @Index(name = "long_url_index", columnList = "long_url", unique = true),
      @Index(name = "short_url_index", columnList = "short_url", unique = true)
    })
public class URLModel implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "long_url", nullable = false, unique = true)
  private String longUrl;

  @Column(name = "short_url", nullable = false, unique = true)
  private String shortUrl;

  @Column(name = "created_on", nullable = false)
  private Date createdOn;

  @Column(name = "updated_on", nullable = false)
  private Date updatedOn;
}
