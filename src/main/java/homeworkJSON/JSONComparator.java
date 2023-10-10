package homeworkJSON;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONComparator {
    public static boolean compareJSONs(String expectedResult, String actualResult) {
        JSONObject expectedJson = new JSONObject(expectedResult);
        JSONObject actualJson = new JSONObject(actualResult);

        return compareJSONObjects(expectedJson, actualJson);
    }

    private static boolean compareJSONObjects(JSONObject expected, JSONObject actual) {
        for (String key : expected.keySet()) {
            if (!actual.has(key)) {
                System.out.println("Key not found in actual JSON: " + key);
                return false;
            }

            if (expected.get(key) instanceof JSONObject) {
                if (!compareJSONObjects(expected.getJSONObject(key), actual.getJSONObject(key))) {
                    return false;
                }
            } else if (expected.get(key) instanceof JSONArray) {
                if (!compareJSONArrays(expected.getJSONArray(key), actual.getJSONArray(key))) {
                    return false;
                }
            } else if (!"ignore".equals(expected.get(key))) {
                if (!expected.get(key).equals(actual.get(key))) {
                    System.out.println("Values don't match for key: " + key + " - Expected: " + expected.get(key) + ", Actual: " + actual.get(key));
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean compareJSONArrays(JSONArray expected, JSONArray actual) {
        if (expected.length() != actual.length()) {
            System.out.println("JSONArrays of different lengths - Expected: " + expected.length() + ", Actual: " + actual.length());
            return false;
        }

        for (int i = 0; i < expected.length(); i++) {
            Object expectedItem = expected.get(i);
            Object actualItem = actual.get(i);

            if (expectedItem instanceof JSONObject && actualItem instanceof JSONObject) {
                if (!compareJSONObjects((JSONObject) expectedItem, (JSONObject) actualItem)) {
                    return false;
                }
            } else if (expectedItem instanceof JSONArray && actualItem instanceof JSONArray) {
                if (!compareJSONArrays((JSONArray) expectedItem, (JSONArray) actualItem)) {
                    return false;
                }
            } else {
                if (!expectedItem.equals(actualItem)) {
                    System.out.println("Array items don't match at index " + i + " - Expected: " + expectedItem + ", Actual: " + actualItem);
                    return false;
                }
            }
        }

        return true;
    }
}
