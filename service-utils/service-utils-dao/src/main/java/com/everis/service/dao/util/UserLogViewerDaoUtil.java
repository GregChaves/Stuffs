package com.everis.service.dao.util;

/**
 * The Class DaoUtil.
 */
public class UserLogViewerDaoUtil {

	/**
	 * Return int from boolean value.
	 *
	 * @param valor
	 *            the valor
	 * @return the int
	 */
	public static int returnIntFromBooleanValue(boolean valor) {
		if (valor) {
			return 1;
		} else {
			return 0;
		}

	}

}
