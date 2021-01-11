package com.springboot.backend.chat.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.backend.chat.models.dao.ChatRepository;
import com.springboot.backend.chat.models.documents.Message;

@Service
public class ChatServiceImp implements ChatService {
	
	@Autowired
	private ChatRepository chatDao;

	@Override
	public List<Message> obtenerUltimos10Mensajes() {
		return chatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Message guardar(Message message) {
		return chatDao.save(message);
	}

}
