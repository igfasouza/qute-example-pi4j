package com.igor;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Path("hello")
public class HelloResource {

	@Inject
	Template hello;
	
	final GpioController gpio = GpioFactory.getInstance();
	GpioPinDigitalOutput pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_26, "1", PinState.HIGH);
	GpioPinDigitalOutput pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "2", PinState.HIGH);
	GpioPinDigitalOutput pin04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_07, "3", PinState.HIGH);
	GpioPinDigitalOutput pin05 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_09, "4", PinState.HIGH);
	GpioPinDigitalOutput pin06 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "5", PinState.HIGH);
	GpioPinDigitalOutput pin07 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "6", PinState.HIGH);
	GpioPinDigitalOutput pin09 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "7", PinState.HIGH);
	GpioPinDigitalOutput pin10 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_21, "8", PinState.HIGH);

	@GET
	@Produces(MediaType.TEXT_HTML) 
	public TemplateInstance get() {

		return hello.data("field1", "Test").data("field2", "Igor"); 
	}


	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Transactional
	@Path("/segment")
	public TemplateInstance segmentDisplay(@MultipartForm MessageForm messageForm) {
		
		if(messageForm.segment1 == null && messageForm.segment2 == null &&
				messageForm.segment3 == null && messageForm.segment4 == null &&
				messageForm.segment5 == null && messageForm.segment6 == null && 
				messageForm.segment7 == null && messageForm.segment8 == null) {
			pin01.setShutdownOptions(true, PinState.HIGH);
			pin02.setShutdownOptions(true, PinState.HIGH);
			pin04.setShutdownOptions(true, PinState.HIGH);
			pin05.setShutdownOptions(true, PinState.HIGH);
			pin06.setShutdownOptions(true, PinState.HIGH);
			pin07.setShutdownOptions(true, PinState.HIGH);
			pin09.setShutdownOptions(true, PinState.HIGH);
			pin10.setShutdownOptions(true, PinState.HIGH);
			pin01.high();
			pin02.high();
			pin04.high();
			pin05.high();
			pin06.high();
			pin07.high();
			pin09.high();
			pin10.high();
		}

		display(messageForm);

		return hello.data("field1", "Test").data("field2", "Igor")
				.data("segment1", messageForm.segment1)
				.data("segment2", messageForm.segment2)
				.data("segment3", messageForm.segment3)
				.data("segment4", messageForm.segment4)
				.data("segment5", messageForm.segment5)
				.data("segment6", messageForm.segment6)
				.data("segment7", messageForm.segment7)
				.data("segment8", messageForm.segment8);
	}

	public void display(MessageForm messageForm) {

		if(messageForm.segment1 != null && messageForm.segment1.equals("on")) {
			pin01.low();
		}else {
			pin01.high();
		}
		if(messageForm.segment2 != null && messageForm.segment2.equals("on")) {
			pin02.low();
		}else {
			pin02.high();
		}
		if(messageForm.segment3 != null && messageForm.segment3.equals("on")) {
			pin04.low();
		}else {
			pin04.high();
		}
		if(messageForm.segment4 != null && messageForm.segment4.equals("on")) {
			pin05.low();
		}else {
			pin05.high();
		}
		if(messageForm.segment5 != null && messageForm.segment5.equals("on")) {
			pin06.low();
		}else {
			pin06.high();
		}
		if(messageForm.segment6 != null && messageForm.segment6.equals("on")) {
			pin07.low();
		}else {
			pin07.high();
		}
		if(messageForm.segment7 != null && messageForm.segment7.equals("on")) {
			pin09.low();
		}else {
			pin09.high();
		}
		if(messageForm.segment8 != null && messageForm.segment8.equals("on")) {
			pin10.low();
		}else {
			pin10.high();
		}

	}

}