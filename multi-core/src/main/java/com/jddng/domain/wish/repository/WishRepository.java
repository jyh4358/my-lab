package com.jddng.domain.wish.repository;

import com.jddng.domain.wish.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {

}
