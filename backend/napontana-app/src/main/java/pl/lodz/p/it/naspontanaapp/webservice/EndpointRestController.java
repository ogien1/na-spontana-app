package pl.lodz.p.it.naspontanaapp.webservice;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Transactional(value = TxType.REQUIRES_NEW)
@RestController
@RequestMapping("/app")
public class EndpointRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EndpointRestController.class);

	@RequestMapping(value = "/addRoutes", method = RequestMethod.GET)
	public void addUser() {

	}
}
