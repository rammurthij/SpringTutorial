package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.RegisterPage;

public interface RegisterRepository extends JpaRepository<RegisterPage, Long> {

}
