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
	private static final String GENDER_SLOT = "GENDER";
	private static final String HAIRCOLOUR_SLOT = "HAIRCOLOUR";
	private static final String HAIRTYPE_SLOT = "HAIRTYPE";
	private static final String ETHNICITY_SLOT = "ETHNICITY";
	private static final String EYECOLOUR_SLOT = "EYECOLOUR";
	private static final String NAME_SLOT = "NAMES";
	private static final String ACCESSORIES_SLOT = "ACCESSORIES";
	
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
		} else if ("GetGenderIntent".equals(intentName)) {
			return getGenderResponse(intent,session);
		} else if ("GetHairColourIntent".equals(intentName)) {
			return getHairColourResponse(intent,session);
		} else if ("GetHairTypeIntent".equals(intentName)) {
			return getHairTypeResponse(intent,session);
		} else if ("GetEthnicityIntent".equals(intentName)) {
			return getEthnicityResponse(intent,session);
		} else if ("GetFacialHairIntent".equals(intentName)) {
			return getFacialHairResponse(intent,session);
		} else if ("GetEyeColourIntent".equals(intentName)) {
			return getEyeColourResponse(intent,session);
		} else if ("GetAccessoriesIntent".equals(intentName)) {
			return getAccessoriesResponse(intent,session);
		} else if ("GetNameIntent".equals(intentName)) {
			return getNameResponse(intent,session);
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
	
	/**
	 * Creates a {@code SpeechletResponse} for the gender intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getGenderResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot genderSlot = slots.get(GENDER_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (genderSlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(genderSlot.getValue());
					String speechText = "";
					if(person.getGender().equalsIgnoreCase(genderSlot.getValue())){
						speechText = "Yes, their gender is " + genderSlot.getValue();
					}else{
						speechText = "No, their gender is not " + genderSlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
		}
	}
	
	
	/**
	 * Creates a {@code SpeechletResponse} for the hair colour intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getHairColourResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot hairColourSlot = slots.get(HAIRCOLOUR_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (hairColourSlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(hairColourSlot.getValue());
					String speechText = "";
					if(person.getHairColour().equalsIgnoreCase(hairColourSlot.getValue())){
						speechText = "Yes, their hair colour is " + hairColourSlot.getValue();
					}else{
						speechText = "No, their hair colour is not " + hairColourSlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
		}
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the hair type intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getHairTypeResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot hairTypeSlot = slots.get(HAIRTYPE_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (hairTypeSlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(hairTypeSlot.getValue());
					String speechText = "";
					if(person.getHairType().equalsIgnoreCase(hairTypeSlot.getValue())){
						speechText = "Yes, their hair type is " + hairTypeSlot.getValue();
					}else{
						speechText = "No, their hair type is not " + hairTypeSlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
		}
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the ethnicity intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getEthnicityResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot ethnicitySlot = slots.get(ETHNICITY_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (ethnicitySlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(ethnicitySlot.getValue());
					String speechText = "";
					if(person.getEthnicity().equalsIgnoreCase(ethnicitySlot.getValue())){
						speechText = "Yes, their ethnicity is " + ethnicitySlot.getValue();
					}else{
						speechText = "No, their ethnicity is not " + ethnicitySlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
		}
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the facial hair intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getFacialHairResponse(final Intent intent, final Session session) {

		//TODO: Supports only beard right  now
		
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (name != null) {
			Person person = CommunityUtils.findPersonByName(name, community);
			if (person != null) {
				log.info(person.toString());
				String speechText = "";
				if (person.isBearded()) {
					speechText = "Yes, they have a beard";
				} else {
					speechText = "No, they do not have a beard";
				}

				return getSpeechletResponse(speechText, speechText, true);
			} else {
				String speechText = "I can't remember anything about them, try start over";
				return getSpeechletResponse(speechText, speechText, false);
			}

		} else {
			String speechText = "I can't remember who I was thinking of, try start over.";
			return getSpeechletResponse(speechText, speechText, false);
		}
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the eye colour intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getEyeColourResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot eyeColourSlot = slots.get(EYECOLOUR_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (eyeColourSlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(eyeColourSlot.getValue());
					String speechText = "";
					if(person.getEyeColour().equalsIgnoreCase(eyeColourSlot.getValue())){
						speechText = "Yes, their eye colour is " + eyeColourSlot.getValue();
					}else{
						speechText = "No, their eye colour is not " + eyeColourSlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
		}
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the name intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getNameResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot nameSlot = slots.get(NAME_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (nameSlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(nameSlot.getValue());
					String speechText = "";
					if(person.getName().equalsIgnoreCase(nameSlot.getValue())){
						speechText = "Yes, they are " + nameSlot.getValue();
					}else{
						speechText = "No, they are not " + nameSlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
		}
	}
	
	/**
	 * Creates a {@code SpeechletResponse} for the accessories intent.
	 *
	 * @return SpeechletResponse spoken and visual response for the given intent
	 */
	private SpeechletResponse getAccessoriesResponse(final Intent intent, final Session session) {

		Map<String, Slot> slots = intent.getSlots();

		// Get the slot from the list of slots.
		Slot accessoriesSlot = slots.get(ACCESSORIES_SLOT);
		String name = (String) session.getAttribute(PERSON_ATTRIBUTE);
		if (accessoriesSlot != null) {
			if (name != null) {
				Person person = CommunityUtils.findPersonByName(name, community);
				if (person != null) {
					log.info(person.toString());
					log.info(accessoriesSlot.getValue());
					String speechText = "";
					
					if(person.getAccessories() != null){
						List<String> accessoriesList = person.getAccessories();
						if(accessoriesList.contains(accessoriesSlot.getValue())){
							speechText = "Yes, they have a " + accessoriesSlot.getValue();	
						}else{
							speechText = "No, they don't have a " + accessoriesSlot.getValue();	
						}
						
					}				
					else{
						speechText = "No, they don't have a " + accessoriesSlot.getValue();
					}
					
					return getSpeechletResponse(speechText, speechText, true);
				} else {
					String speechText = "I can't remember anything about them, try start over";
					return getSpeechletResponse(speechText, speechText, false);
				}

			} else {
				String speechText = "I can't remember who I was thinking of, try start over.";
				return getSpeechletResponse(speechText, speechText, false);
			}
		} else {
			String speechText = "Something went wrong, try ask again";
			return getSpeechletResponse(speechText, speechText, true);
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
