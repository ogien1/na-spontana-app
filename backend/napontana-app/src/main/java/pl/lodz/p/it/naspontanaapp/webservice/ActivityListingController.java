package pl.lodz.p.it.naspontanaapp.webservice;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.lodz.p.it.naspontanaapp.service.ActivityListingManager;

/**
 * Created by 'Jakub Dziworski' on 30.11.16
 */
@Transactional(value = TxType.REQUIRES_NEW)
@RestController
@RequestMapping("/list")
public class ActivityListingController {
	
	@Autowired
	ActivityListingManager activityListingManager;
	private static final Logger LOGGER = LoggerFactory.getLogger(ActivityListingController.class);

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(){
		LOGGER.info("test|START - wywolano z parametrami: " );
//		activityListingManager.doSth();
		LOGGER.info("test|STOP - zapisano: ");
		return "list test ok";
	}
}
