package org.iti.cxf.client.action;

import org.apache.struts2.ServletActionContext;
import org.iti.application.context.bean.factory.BeanFactory;
import org.iti.common.util.JsonUtil;
import org.iti.cxf.client.service.IDownLoadImageService;
import org.iti.http.interfaces.abstracts.action.AbstractHttpInterfaceAction;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("DownloadImageCXFAction")
@Scope("prototype")
public class DownloadImageCXFAction extends AbstractHttpInterfaceAction {

	/**
	 * WebService封装图片下载测试Action
	 */
	private static final long serialVersionUID = 5085883589394860751L;

	private String pictureName;

	@Override
	public String defaultExecute() throws Throwable {

		String path = getIDownLoadImageService().downLoadImage(
				ServletActionContext.getResponse(), pictureName);
		this.responResult = JsonUtil.toJson(path);
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

	private IDownLoadImageService getIDownLoadImageService() {
		return (IDownLoadImageService) BeanFactory.getBean("cxfClient");
	}
}
