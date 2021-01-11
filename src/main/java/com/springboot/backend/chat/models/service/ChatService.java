package com.springboot.backend.chat.models.service;

import java.util.List;

import com.springboot.backend.chat.models.documents.Message;


public interface ChatService {
	
	public List<Message> obtenerUltimos10Mensajes();
	public Message guardar(Message message);

}
