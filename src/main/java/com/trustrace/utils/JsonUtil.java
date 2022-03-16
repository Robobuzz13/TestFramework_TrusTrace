package com.trustrace.utils;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Common method for JSON comparison
 */
public class JsonUtil {

	/**
	 * This Method is used to compare json when the order of elements within the
	 * json is not matching
	 *
	 * @param ExpectedJson as Json String
	 * @param ActualJson   as Json String
	 * @return as boolean
	 * @throws JSONException
	 */
	public static boolean jsonCompareOrderNotMatching(String ExpectedJson, String ActualJson) {
		boolean returnFlag = false;
		try {
			JSONAssert.assertEquals(ExpectedJson, ActualJson, JSONCompareMode.NON_EXTENSIBLE);
			returnFlag = true;

		} catch (JSONException e) {
			Log.fail("Json does not match");
		}
		return returnFlag;
	}

	/**
	 * This Method is used to compare json wen the order of elements within the json
	 * is not matching
	 *
	 * @param ExpectedJson as Json String
	 * @param ActualJson   as Json String
	 * @return as boolean
	 */
	public static boolean jsonCompare(String ExpectedJson, String ActualJson) {
		boolean returnFlag = false;
		try {
			JSONAssert.assertEquals(ExpectedJson, ActualJson, JSONCompareMode.STRICT);
			returnFlag = true;

		} catch (JSONException e) {
			Log.fail("Json does not match");
		}
		return returnFlag;
	}

}
