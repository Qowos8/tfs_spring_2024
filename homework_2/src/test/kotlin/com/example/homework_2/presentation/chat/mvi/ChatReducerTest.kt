import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.chat.mvi.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.math.exp


@RunWith(JUnit4::class)
class ChatReducerTest {

    private val reducer = ChatReducer()

    @Test
    fun `when receive CacheSuccess event should return CacheSuccess state with expected messages`() {
        val initialState = ChatState.Init
        val expectedMessages = listOf(
            MessageItem(0, topicName = "testing", content = "0"),
            MessageItem(1, topicName = "testing", content = "1"),
        )
        val event = ChatEvent.Domain.CacheSuccess(expectedMessages)

        val expectedState = ChatState.CacheSuccess(expectedMessages)

        val result = reducer.reduce(event, initialState)

        assertEquals(expectedState.messages, (result.state as ChatState.CacheSuccess).messages)
    }
}