package io.github.gianpamx.pidoorman.domain

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter

class RingBellTest {
    private val timeGateway: TimeGateway = mock()
    private val notificationGateway: NotificationGateway = mock()
    private val formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
    private val testObserver = TestObserver<Any>()

    lateinit var ringBell: RingBell

    @Before
    fun setUp() {
        whenever(timeGateway.systemZoneId()).thenReturn(ZoneId.of("Z"))

        ringBell = RingBell(timeGateway, notificationGateway, formatter)
    }

    @Test
    fun `positive difference in milliseconds`() {
        val anyServerTime = 0L
        whenever(timeGateway.utcInMillis()).thenReturn(1L)
        whenever(notificationGateway.ringNotification(any(), any()))
            .thenReturn(Completable.complete())

        ringBell(anyServerTime).toObservable<Any>().subscribe(testObserver)

        testObserver.assertComplete()
    }

    @Test
    fun `negative difference in milliseconds`() {
        val anyServerTime = 2L
        whenever(timeGateway.utcInMillis()).thenReturn(1L)
        whenever(notificationGateway.ringNotification(any(), any()))
            .thenReturn(Completable.complete())

        ringBell(anyServerTime).toObservable<Any>().subscribe(testObserver)

        testObserver.assertComplete()
    }
}
