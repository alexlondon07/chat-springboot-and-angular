package com.springboot.backend.chat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.springboot.backend.chat.models.documents.Message;
import com.springboot.backend.chat.models.service.ChatService;

@Controller
public class ChatController {
	
	private String[] colores = { "red", "green", "blue", "magenta", "purple", "orange"};

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate websocket;
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Message receiveMessage( Message mensaje) {
		mensaje.setFecha( new Date().getTime());
		mensaje.setColor(colores[new Random().nextInt(colores.length)] );
		if( mensaje.getTipo().equals("NUEVO_USUARIO")){
			mensaje.setTexto("Nuevo usuario ");
		}else {
			chatService.guardar(mensaje);
		}
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String writingMessage( String username) {
		return username.concat( " está escribiendo ");
	}
	
	@MessageMapping("/historial")
	@SendTo("/chat/historial")
	public void history( String clientId) {
		websocket.convertAndSend("/chat/historial/" + clientId, chatService.obtenerUltimos10Mensajes());
	}
}
