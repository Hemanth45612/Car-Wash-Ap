package com.cg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entity.WashRequest;

public interface WashRequestRepository extends JpaRepository<WashRequest, String> {

}
