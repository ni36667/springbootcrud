package com.awslambda.crud.springbootcrud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.awslambda.crud.springbootcrud.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
