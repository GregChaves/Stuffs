package com.everis.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.everis.service.business.ServiceUtilsBusiness;
import com.everis.service.constants.ServiceUtilsControllerConstant;
import com.everis.service.domain.MessageTO;

/**
 * @author gbritoch
 *
 */
@RestController
public class ServiceUtilsController {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtilsController.class);

	@Autowired
	private ServiceUtilsBusiness userLogViewerBusiness;
	
	@RequestMapping(value = ServiceUtilsControllerConstant.GET_HELLO, method = RequestMethod.GET)
	public MessageTO getHelloMsg() throws Exception {
		
		LOGGER.info("[Service] Entering in getHelloMsg method ");
		
		String msg = "ok!";
		
		MessageTO to = new MessageTO();
		
		try {
		
			userLogViewerBusiness.shuffleAndSendEmail();
			to.setResult(msg);
		
		} catch (Exception e) {
			to.setResult("Error sending e-mail");
		}
		
		return to;
	}
	
	@RequestMapping(value = ServiceUtilsControllerConstant.POST_HELLO, method = RequestMethod.POST)
	public MessageTO getHelloMsgPost() throws Exception {
		
		LOGGER.info("[Service] Entering in getHelloMsgPost method ");
		
		String msg = "ok!";
		
		MessageTO to = new MessageTO();
		
		try {
		
			userLogViewerBusiness.shuffleAndSendEmail();
			to.setResult(msg);
		
		} catch (Exception e) {
			to.setResult("Error sending e-mail");
		}
		
		return to;
	}
	
	/**
	 * @return the userLogViewerBusiness
	 */
	public ServiceUtilsBusiness getUserLogViewerBusiness() {
		return userLogViewerBusiness;
	}

	/**
	 * @param userLogViewerBusiness the userLogViewerBusiness to set
	 */
	public void setUserLogViewerBusiness(ServiceUtilsBusiness userLogViewerBusiness) {
		this.userLogViewerBusiness = userLogViewerBusiness;
	}
	
}