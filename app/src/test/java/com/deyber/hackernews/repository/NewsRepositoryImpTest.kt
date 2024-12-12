import android.content.Context
import com.deyber.database.data.DBResource
import com.deyber.database.repository.HitRepository
import com.deyber.hackernews.core.network.NetworkUtils
import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.data.repository.NewsRepositoryImp
import com.deyber.hackernews.data.response.NewsResponse
import com.deyber.hackernews.data.service.NewsService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class NewsRepositoryImpTest {

    @MockK
    private lateinit var service: NewsService

    @MockK
    private lateinit var hitRepository: HitRepository

    @MockK
    private lateinit var networkUtils: NetworkUtils

    private lateinit var newsRepository: NewsRepositoryImp

    @Before
    fun setUp() {
        service = mockk(relaxed = true)
        hitRepository = mockk(relaxed = true)
        newsRepository = mockk(relaxed = true)
        networkUtils = mockk(relaxed = true)
        val context : Context = mockk(relaxed = true)

        newsRepository = NewsRepositoryImp(context, service, hitRepository, networkUtils)
    }

    @Test
    fun `getHits returns data from network when network is available and execute Execute Api Network`() = runBlockingTest {

        every { networkUtils.isNetworkAvailable() } returns true
        val dataBaseResult = DBResource.Success(true)
        val mockResponse = mockk<Response<NewsResponse>>()
        coEvery { service.getNews() } returns mockResponse
        every { mockResponse.isSuccessful } returns true
        val mockBody = mockk<NewsResponse>()
        every { mockResponse.body() } returns mockBody
        every { mockBody.hits } returns emptyList()
        coEvery { hitRepository.addHits(any()) } returns dataBaseResult
        val result = newsRepository.getHits()
        coVerify(exactly = 1) { service.getNews() }
        assert(result is Resource.Success)
        result as Resource.Success

    }

    @Test
    fun `getHits returns data from network when network is available and execute Execute Room service`() = runBlockingTest {

        every { networkUtils.isNetworkAvailable() } returns false
        val dataBaseResult = DBResource.Success(true)
        val mockResponse = mockk<Response<NewsResponse>>()
        coEvery { service.getNews() } returns mockResponse
        every { mockResponse.isSuccessful } returns true
        val mockBody = mockk<NewsResponse>()
        every { mockResponse.body() } returns mockBody
        every { mockBody.hits } returns emptyList()
        coEvery { hitRepository.addHits(any()) } returns dataBaseResult
        val result = newsRepository.getHits()
        coVerify(exactly = 0) { service.getNews() }
        coVerify(exactly = 1) { hitRepository.getHits() }
        assert(result is Resource.Success)
        result as Resource.Success

    }



}