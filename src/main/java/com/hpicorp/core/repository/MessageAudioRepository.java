package com.hpicorp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.MessageAudio;

@Repository
public interface MessageAudioRepository extends JpaRepository<MessageAudio, Long> {

}
