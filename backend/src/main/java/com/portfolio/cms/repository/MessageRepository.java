package com.portfolio.cms.repository;
import com.portfolio.cms.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MessageRepository extends JpaRepository<Message, Long> {}
