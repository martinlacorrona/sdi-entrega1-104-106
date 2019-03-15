package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Bid;
import com.uniovi.entities.Conversation;
import com.uniovi.entities.User;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

    List<Conversation> findAll();

    @Query("SELECT c FROM Conversation c WHERE c.bid = ?1 AND c.interestedUser = ?2 ")
    Conversation findConversationByBidAndUser(Bid bid, User interestedUser);

    @Query("SELECT c FROM Conversation c WHERE c.interestedUser = ?1 ")
    List<Conversation> findConversationsByBuyer(User user);
}