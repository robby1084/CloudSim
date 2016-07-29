package org.iti.redis.main;

import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("RedisForHash")
@Scope("prototype")
public class RedisForHash extends AbstractHttpInterfaceAction {

	/**
	 * redis hash
	 */
	private static final long serialVersionUID = 3595690159210068342L;

	@Override
	public String defaultExecute() throws Throwable {

		
		return super.defaultExecute();
	}
	
	@Override
	public String getResponState() {
		return responState;
	}

	@Override
	public String getResponResult() {
		return responResult;
	}
}
