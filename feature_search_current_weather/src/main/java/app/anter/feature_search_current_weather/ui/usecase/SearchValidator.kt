package app.anter.feature_search_current_weather.ui.usecase

/**
 * Created by Mostafa Anter on 6/30/21.
 */
class SearchValidator {

    /**
     * this method for check query validation
     * @param query is text entered by end user
     * return tru if valid
     * return false if not valid
     * it doesn't accept empty string or string length less than 3 character
     * */
    fun isValidQuery(query: String): Boolean {
        if (query.isBlank()||query.isEmpty()|| query.length < 3)
            return false
        return true
    }


}