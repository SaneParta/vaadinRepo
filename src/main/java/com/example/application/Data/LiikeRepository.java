package com.example.application.Data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiikeRepository extends JpaRepository<Liike, Long> {
    List<Liike> id(long id);
}
