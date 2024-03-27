package com.project.music.utils;

import com.project.music.common.Globals;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Slf4j
public class ServletUtils {


	public static boolean checkIfLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute(Globals.USER_SESSION) != null;
    }
	
	public static String getCtxPath(HttpServletRequest request) {
        return request.getContextPath();
	}
	
}
