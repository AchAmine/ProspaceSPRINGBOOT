package com.prospace.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prospace.spring.entity.ChatRoom;
import com.prospace.spring.entity.Offer;
import com.prospace.spring.entity.User;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{

	@Query(value = "SELECT c.id FROM ChatRoom c where :sender member c.users AND :recipient member c.users")
	 Long ByUsers(@Param("sender")User sender,@Param("recipient") User recipient);
}
