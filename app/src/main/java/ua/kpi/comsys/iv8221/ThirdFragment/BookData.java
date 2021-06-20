package ua.kpi.comsys.iv8221.ThirdFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public final class BookData {
    private static JSONObject data;

    public static JSONObject getData() {
        return data;
    }

    public static void setData(JSONObject data) {
        BookData.data = data;
    }

    public static int getBookDataSize() {
        try {

            JSONArray arr = data.getJSONArray("books");
            return arr.length();
        } catch (JSONException e) {
            return 0;
        }

    }

    public static JSONObject getBook(int index) throws JSONException {
        JSONArray arr = data.getJSONArray("books");
        return (JSONObject) arr.get(index);
    }

    public static String[] getTitleArray() throws JSONException {
        String[] bookList = new String[BookData.getBookDataSize()];
        for (int i = 0; i < BookData.getBookDataSize(); i++) {
            bookList[i] = BookData.getBook(i).getString("title");

        }
        return bookList;

    }
}
