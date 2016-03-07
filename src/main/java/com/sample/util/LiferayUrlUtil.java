package com.sample.util;

import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletURLFactoryUtil;

/**
 * Util class for URL creation. (taglibs are not available from Thymeleaf templates) 
 * 
 * @author Vianney FAIVRE
 */
public class LiferayUrlUtil {

	private static final Log LOGGER = LogFactoryUtil.getLog(LiferayUrlUtil.class);

	/**
	 * Create an action URL for the current context (portlet id + plid)
	 */
	public static PortletURL createActionURL(PortletRequest portletRequest, ThemeDisplay themeDisplay, WindowState state, PortletMode mode) {
		PortletURL actionURL = PortletURLFactoryUtil.create(portletRequest, themeDisplay.getPortletDisplay().getId(), themeDisplay.getPlid(), PortletRequest.ACTION_PHASE);
		try {
			actionURL.setWindowState(state);
		} catch (WindowStateException e) {
			LOGGER.error(e);
		}
		try {
			actionURL.setPortletMode(mode);
		} catch (PortletModeException e) {
			LOGGER.error(e);
		}
		return actionURL;
	}

	/**
	 * Create a resource URL with a specific resource ID
	 */
	public static ResourceURL createResourceURL(String resourceId, PortletResponse portletResponse) {

		LiferayPortletResponse liferayPortletResponse =	PortalUtil.getLiferayPortletResponse(portletResponse);
		ResourceURL resourceURL = liferayPortletResponse.createResourceURL();
		resourceURL.setResourceID(resourceId);

		return resourceURL;
	}
}
