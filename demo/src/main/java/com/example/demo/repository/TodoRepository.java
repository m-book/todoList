package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select t from Todo t where t.content like %:keyword% and t.isFinish = false order by t.id desc")
    List<Todo> findTodos(@Param("keyword") String keyword);

    @Query("select t from Todo t where t.content = :keyword")
    List<Todo> findByContent(@Param("keyword") String keyword);
}