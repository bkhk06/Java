package com.adcc.restresponse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseTimeRepository extends JpaRepository<ResponseTimeEntity, Long> {
}
