/**
    Copyright 2014-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.

    Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with the License. A copy of the License is located at

        http://aws.amazon.com/apache2.0/

    or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package com.unilever.guesswho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.unilever.guesswho.model.Person;
import com.unilever.guesswho.util.CommunityUtils;

/**
 * This sample shows how to create a simple speechlet for handling speechlet
 * requests.
 */
public class GuessWhoSpeechlet implements Speechlet {
	private static final Logger log = LoggerFactory.getLogger(GuessWhoSpeechlet.class);
	private static final String PERSON_ATTRIBUTE = "person";
	private static final HashMap<String, Person> community = CommunityUtils.buildCommunity();
	
	public void onSessionStarted(final SessionStartedRequest request, final Session session) throws SpeechletException {
		log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		
		//TODO Make this pick a random person
		session.setAttribute(PERSON_ATTRIBUTE, "Scarlet");		
	}

	public SpeechletResponse onLaunch(final LaunchRequest request, final Session session) throws SpeechletException {
		log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		return getWelcomeResponse();
	}

	public SpeechletResponse onIntent(final IntentRequest request, final Session session) throws SpeechletException {
		log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());

		Intent intent = request.getIntent();
		String intentName = (intent != null) ? intent.getName() : null;

		if ("AMAZON.HelpIntent".equals(intentName)) {
			return getHelpResponse();
		} else if ("CheatIntent".equals(intentName)) {
			return getCheatResponse(intent,session);
		} else {
			throw new SpeechletException("Invalid Intent");
		}
	}

	public void onSessionEnded(final SessionEndedRequest request, final Session session) throws SpeechletException {
		log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
		// any cleanup logic goes here
	}

	/**
	 * Creates and returns a {@code SpeechletResponse} with a welcome message.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getWelcomeResponse() {
		String speechText = "Welcome to guess who. Ask a question to start";

		return getSpeechletResponse(speechText, speechText, true);
	}

	/**
	 * Creates a {@code SpeechletResponse} for the help intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getHelpResponse() {
		String speechText = "Welcome to guess who. Ask a question to start";
		return getSpeechletResponse(speechText, speechText, true);
	}
	

	/**
	 * Creates a {@code SpeechletResponse} for the cheat intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getCheatResponse(final Intent intent, final Session session) {
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if(name != null){
			Person person = CommunityUtils.findPersonByName(name, community);
			if(person != null){
				log.info(person.toString());
				String speechText = "The person I am thinking of is " + name;
				return getSpeechletResponse(speechText, speechText, true);				
			}else{ 
				String speechText = "I can't remember anything about them, try start over";
				return getSpeechletResponse(speechText, speechText, false);
			}
			
		}else{
			String speechText = "I can't remember who I was thinking of, try start over.";
			return getSpeechletResponse(speechText, speechText, false);
		}
		
		
	}
	
	private SpeechletResponse getSpeechletResponse(String speechText, String repromptText, boolean isAskResponse) {
		// Create the Simple card content.
		SimpleCard card = new SimpleCard();
		card.setTitle("GuessWho");
		card.setContent(speechText);

		// Create the plain text output.
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
		speech.setText(speechText);

		if (isAskResponse) {
			// Create reprompt
			PlainTextOutputSpeech repromptSpeech = new PlainTextOutputSpeech();
			repromptSpeech.setText(repromptText);
			Reprompt reprompt = new Reprompt();
			reprompt.setOutputSpeech(repromptSpeech);

			return SpeechletResponse.newAskResponse(speech, reprompt, card);

		} else {
			return SpeechletResponse.newTellResponse(speech, card);
		}
	}
}
