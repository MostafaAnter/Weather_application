package app.anter.feature_search_current_weather.ui.usecase

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by Mostafa Anter on 11/17/21.
 */
class SearchValidatorTest{
    private lateinit var searchValidator: SearchValidator

    @Before
    fun setUp() {
        searchValidator = SearchValidator()
    }

    @Test
    fun `Empty string return false`(){
        val isValid = searchValidator.isValidQuery("")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `String length less than 3 character return false`(){
        val isValid = searchValidator.isValidQuery("2")
        assertThat(isValid).isFalse()
    }

    @Test
    fun `String length equal 3 character return true`(){
        val isValid = searchValidator.isValidQuery("cat")
        assertThat(isValid).isTrue()
    }

    @Test
    fun `String length larger than 3 character return true`(){
        val isValid = searchValidator.isValidQuery("football")
        assertThat(isValid).isTrue()
    }
}