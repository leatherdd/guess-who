package com.unilever.jira;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.slu.Slot.Builder;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.unilever.guesswho.GuessWhoSpeechlet;

public class GuessWhoSpeechletTest {
	private static final Logger log = LoggerFactory.getLogger(GuessWhoSpeechletTest.class);

	private static IntentRequest request;
	private static Session session;
	private static Intent intent;
	private static GuessWhoSpeechlet speechlet;
	private static SessionStartedRequest startRequest;
	private static com.amazon.speech.slu.Intent.Builder intentBuilder;
	private static com.amazon.speech.speechlet.Session.Builder sessionBuilder;
	private static com.amazon.speech.speechlet.IntentRequest.Builder requestBuilder;
	private static com.amazon.speech.speechlet.SessionStartedRequest.Builder sessionStartBuilder;

	@Before
	public void setup() {
		intentBuilder = Intent.builder();
		sessionBuilder = Session.builder();
		requestBuilder = IntentRequest.builder();
		sessionStartBuilder = SessionStartedRequest.builder();
		session = sessionBuilder.withSessionId("session5678").build();
		startRequest = sessionStartBuilder.withRequestId("request1234").build();
		speechlet = new GuessWhoSpeechlet();

		try {
			speechlet.onSessionStarted(startRequest, session);
		} catch (SpeechletException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHelpIntent() throws Exception {
		intent = intentBuilder.withName("AMAZON.HelpIntent").build();
		request = requestBuilder.withRequestId("request1234").withTimestamp(new Timestamp(new Date().getTime()))
				.withIntent(intent).build();

		SpeechletResponse response = speechlet.onIntent(request, session);
		OutputSpeech speech = response.getOutputSpeech();
		String text = "";
		if (speech instanceof PlainTextOutputSpeech) {
			text = ((PlainTextOutputSpeech) speech).getText();
		}
		log.info("onSessionEnded requestId={}, outputSpeech={}", request.getRequestId(), text);
	}
}
