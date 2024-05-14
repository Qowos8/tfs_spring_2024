import com.example.homework_2.domain.entity.MessageItem
import com.example.homework_2.presentation.chat.mvi.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ChatReducerTest {

    private val reducer = ChatReducer()

//    @Before
//    fun setup() {
//        reducer = ChatReducer()
//    }

    @Test
    fun `when receive Loading event should return Loading state`() = runTest {
        // Arrange
        val initialState = ChatState.Init
        val expectedMessages = listOf(
            MessageItem(0, topicName = "testing", content = "0"),
            MessageItem(1, topicName = "testing", content = "1"),
            )
        val expectedState = ChatState.CacheSuccess(expectedMessages)

        // Act
        val event = ChatEvent.Ui.LoadMessages("testing", 1)
        val actual = reducer.reduce(event, initialState)

        // Assert
        assertEquals(expectedState, actual)
    }

//    @Test
//    fun `when receive CacheSuccess event should return CacheSuccess state`() = runTest {
//        // Arrange
//        val initialState = ChatState.Init
//        val expectedMessages = listOf(
//            MessageItem(1, "Hello"),
//            MessageItem(2, "World")
//        )
//        val expectedState = ChatState.CacheSuccess(expectedMessages)
//
//        // Act
//        val stateFlow = MutableStateFlow(initialState)
//        val event = ChatEvent.Domain.CacheSuccess(expectedMessages)
//        reducer.processEvents(event) { stateFlow.value = it }
//        val result = stateFlow.toList().last()
//
//        // Assert
//        assertEquals(expectedState, result)
//    }

    // Add other tests here for other events and states

}
