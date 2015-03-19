/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.TheL0w3R.SkypePlus.Listeners;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import me.TheL0w3R.SkypePlus.MainForm;
import me.TheL0w3R.SkypePlus.Utils;
import ChatterBotApi.ChatterBot;
import ChatterBotApi.ChatterBotFactory;
import ChatterBotApi.ChatterBotSession;
import ChatterBotApi.ChatterBotType;

import com.skype.Chat;
import com.skype.ChatMessage;
import com.skype.ChatMessageListener;
import com.skype.SkypeException;

/**
 *
 * @author TheL0w3R
 */
public class SkypeMessageListener implements ChatMessageListener {

	private void SkypeMessageListener(String myMessage, Chat myChat) {
		try {
			ChatterBotFactory factory = new ChatterBotFactory();
			ChatterBot bot = factory.create(setBotType(MainForm.getBotCB.getSelectedIndex()));
			System.out.println("Using " + setBotType(MainForm.getBotCB().getSelectedIndex()).toString());
			// MainForm.getBotConsole().setText(MainForm.getBotConsole().getText()
			// + "<<<<<<<<<<<<Now Using " +
			// setBotType(MainForm.getBotCB().getSelectedIndex()).toString() +
			// ">>>>>>>>>>>>");
			ChatterBotSession session = bot.createSession();

			myMessage = session.think(myMessage);
			final Chat chatterup = myChat;
			if (MainForm.getIfEnabled() == true) {
				chatterup.send(myMessage);
				MainForm.getBotConsole().setText(MainForm.getBotConsole().getText() + "BOT> " + myMessage + "\n");
			} else {
				// do nothing
			}
		} catch (Exception ex) {
			Logger.getLogger(SkypeMessageListener.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void chatMessageReceived(ChatMessage recMessage) throws SkypeException {
		try {
			if (MainForm.getIfEnabled() == true) {
				if (!(MainForm.getGroupBotCB().getSelectedItem().equals("All Skype Contacts"))) {
					if (Arrays.asList(Utils.getGroup(MainForm.getGroupBotCB().getSelectedIndex() - 1, 1)).contains(recMessage.getSenderId())) {
						if (!(MainForm.getGroupBotCB().getSelectedItem().equals("All Skype Contacts"))) {
							MainForm.getBotConsole().setText(MainForm.getBotConsole().getText() + recMessage.getSenderDisplayName() + "> " + recMessage.getContent() + "\n");
							SkypeMessageListener(recMessage.getContent(), recMessage.getChat());
						}
					}
				} else {
					MainForm.getBotConsole().setText(MainForm.getBotConsole().getText() + recMessage.getSenderDisplayName() + "> " + recMessage.getContent() + "\n");
					SkypeMessageListener(recMessage.getContent(), recMessage.getChat());
				}
			}
		} catch (final SkypeException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			Logger.getLogger(SkypeMessageListener.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void chatMessageSent(ChatMessage sentMessage) throws SkypeException {
		try {
			MainForm.getBotConsole().setText(MainForm.getBotConsole().getText() + sentMessage.getSenderDisplayName() + "> " + sentMessage.getContent() + "\n");
			System.out.println(sentMessage.getSenderDisplayName() + "> " + sentMessage.getContent());
		} catch (final SkypeException ex) {
			ex.printStackTrace();
		}
	}

	public static ChatterBotType setBotType(int index) {
		if (index == 0) {
			return ChatterBotType.CLEVERBOT;
		} else if (index == 1) {
			return ChatterBotType.JABBERWACKY;
		} else {
			return null;
		}
	}
}
