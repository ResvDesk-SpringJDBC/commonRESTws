package com.telappoint.commonrestws.common.assembler;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.log4j.Logger;
import com.telappoint.commonrestws.common.masterdb.domain.Client;
import com.telappoint.commonrestws.common.model.ClientTO;

/**
 * The <tt>ClientAssembler</tt> class it will create which will assemble
 * Transfer Object to Business object and vice versa.
 * 
 * @author RajeevS
 */

public class ClientAssembler {
	private Logger logger = Logger.getLogger(ClientAssembler.class);

	private static ClientAssembler instance__ = new ClientAssembler();

	private ClientAssembler() {
	}

	public static ClientAssembler getInstance() {
		return instance__;
	}

	public ClientTO getClientTO(Client client) {

		ClientTO clientTO = null;
		try {
			clientTO = new ClientTO();
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			BeanUtils.copyProperties(clientTO, client);
		} catch (Exception e) {
			logger.error("Error:" + e, e);
		}
		return clientTO;
	}
}