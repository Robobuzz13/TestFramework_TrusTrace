package com.trustrace.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map.Entry;

public class POJOreflection {
	/**
	 * To set values for set methods in POJO with respect to data available in
	 * testdata.xls
	 *
	 * @param obj
	 * @param testData
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static void setValuesInPOJO(Object obj, HashMap<String, String> testData)
			throws Exception, IllegalArgumentException, InvocationTargetException {

		java.lang.reflect.Method[] ArrayMethods = null;
		try {
			ArrayMethods = obj.getClass().getMethods();
			for (Entry<String, String> map_values_testdata : testData.entrySet()) {
				String ColumnName = map_values_testdata.getKey();
				String ColumnValue = map_values_testdata.getValue();
				for (java.lang.reflect.Method TmpMethod : ArrayMethods) {
					String methodName = TmpMethod.getName();
					if (methodName.toLowerCase().startsWith("set")
							&& methodName.toLowerCase().substring(3).equalsIgnoreCase(ColumnName)) {
						java.lang.reflect.Method PojoSetMethod = null;
						String parameterType = TmpMethod.getParameters()[0].getParameterizedType().getTypeName();
						switch (parameterType) {

						case "java.lang.String":
							PojoSetMethod = obj.getClass().getDeclaredMethod(methodName, String.class);
							PojoSetMethod.invoke(obj, ColumnValue);
							break;

						case "java.lang.String[]":
							String[] ArrayValue = ColumnValue.split(",");
							for (int count = 0; count < ArrayValue.length; count++) {
								if (ArrayValue[count].equalsIgnoreCase("null")) {
									ArrayValue[count] = "";
								}
							}
							PojoSetMethod = obj.getClass().getDeclaredMethod(methodName, String[].class);
							PojoSetMethod.invoke(obj, new Object[] { ArrayValue });
							break;
						default:
							throw new Exception();
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			Log.message("Exception thrown while setting values in POJO!" + e.getMessage());
		}
	}

}
