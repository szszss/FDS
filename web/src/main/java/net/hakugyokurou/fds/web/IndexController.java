package net.hakugyokurou.fds.web;

import com.jfinal.core.Controller;

public class IndexController extends Controller {

	public void index() {
		renderJsp("/index.jsp");
	}
}