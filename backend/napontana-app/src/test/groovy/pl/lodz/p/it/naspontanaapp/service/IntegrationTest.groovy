package pl.lodz.p.it.naspontanaapp.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.joda.time.LocalDateTime
import org.joda.time.format.DateTimeFormat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto
import pl.lodz.p.it.naspontanaapp.entities.Activity
import pl.lodz.p.it.naspontanaapp.entities.Category
import pl.lodz.p.it.naspontanaapp.entities.User
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository
import pl.lodz.p.it.naspontanaapp.repository.UserRepository
import pl.lodz.p.it.naspontanaapp.utils.DtoUtils
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


/**
 * Created by 'Jakub Dziworski' on 08.12.16
 */
@ContextConfiguration
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
class IntegrationTest extends Specification {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ObjectMapper objectMapper;

	def "Should get activity"() {
		given:
			def category = categoryRepository.save(createCategory())
			def activity = activityRepository.save(createActivity(category: category))
			def expectedActivity = DtoUtils.fromActivity(activity)
		when:
			def response = mvc.perform(get("/activity/details/$activity.id")).andReturn().response
		then:
			response.status == 200
			def resultActivity = objectMapper.readValue(response.contentAsString, ActivityOutputDto)
			expectedActivity == resultActivity;
	}

	def "Should add activity"() {
		given:
			def user = userRepository.save(createUser())
			def category = categoryRepository.save(createCategory())
			def activityJson = """{
					"categoryId": $category.id,
					"facebookId": $user.facebookId,
					"name": "running",
					"description": "running for one hour",
					"startDate": "2015-06-13T15:25:33"
					}
			""".stripIndent()
		when:
			def response = mvc.perform(post("/activity/addActivity")
					.contentType(MediaType.APPLICATION_JSON)
					.content(activityJson)).andReturn().response
		then:
			response.status == 200
			def insertedActivity = activityRepository.findOne(response.contentAsString.toLong())
			insertedActivity.name == "running"
			insertedActivity.description == "running for one hour"
			insertedActivity.startDate == LocalDateTime.parse("2015-06-13T15:25:33")
			insertedActivity.users == []
			insertedActivity.category == category
	}

	def "Should add user to activity"() {
		given:
			def category = categoryRepository.save(createCategory())
			def activity = activityRepository.save(createActivity(category: category))
			def user = userRepository.save(createUser())
		when:
			def response = mvc.perform(post("/activity/addUserToActivity")
					.param("facebookId", user.facebookId)
					.param("activityId", activity.id.toString())).andReturn().response
		then:
			response.status == 200
			def fetchedActivity = activityRepository.findOne(activity.id)
			userRepository.findOne(user.id).activities == [fetchedActivity]
			fetchedActivity.users.first() == user
	}

	def "Should get friends' activities"() {
		given:
			def category = categoryRepository.save(createCategory())
			def mark = userRepository.save(createUser(facebookId: "122", name: "Mark"))
			def john = userRepository.save(createUser(facebookId: "123", name: "John"))
			def mActivity = createActivity(category: category)
			mActivity.setUsers([mark])
			def jActivity = createActivity(category: category)
			jActivity.setUsers([john])
			def markActivity = activityRepository.save(mActivity)
			def johnActivity = activityRepository.save(jActivity)
		when:
			def response = mvc.perform(get("/activity/friendsActivities")
					.param("friendId", mark.facebookId)
					.param("friendId", john.facebookId))
					.andReturn().response
		then:
			response.status == 200
			def expectedResult = [
					DtoUtils.fromActivity(markActivity),
					DtoUtils.fromActivity(johnActivity)
			]
			expectedResult == objectMapper.readValue(response.contentAsString, ActivityOutputDto[]).toList()
	}

	def "Should get user's activities"() {
		given:
			def category = categoryRepository.save(createCategory())
			def user = userRepository.save(createUser())
			def activity1 = activityRepository.save(createActivity(category: category, users: [user]))
			def activity2 = activityRepository.save(createActivity(category: category, users: [user]))
			user.setActivities([activity1,activity2])
			def expectedBody = [
					DtoUtils.fromActivity(activity1),
					DtoUtils.fromActivity(activity2)
			]
		when:
			def response = mvc.perform(get("/activity/userActivities")
					.param("facebookId", user.facebookId))
					.andReturn().response
		then:
			response.status == 200
			def actualBody = objectMapper.readValue(response.contentAsString, ActivityOutputDto[]).toList()
			actualBody == expectedBody
	}

	@Ignore("To jeszce niestety nie działa. Możesz piotrek odpalić i zobaczysz co jest nie tak na diffie")
	def "Should get similar activities"() {
		given:
			def category = categoryRepository.save(createCategory())
			def activity1 = activityRepository.save(createActivity(startDate: new LocalDateTime(100000),category:category))
			def activity2 = activityRepository.save(createActivity(startDate: new LocalDateTime(100000).minusHours(5),category:category))
			def activity3 = activityRepository.save(createActivity(startDate: new LocalDateTime(100000).plusHours(20),category:category))
			def activity4 = activityRepository.save(createActivity(startDate: new LocalDateTime(100000).plusHours(500),category:category))
			def dtoJson = """{
					"start": ${activity1.startDate.toDate().getTime()}
					}
			""".stripIndent()
		when:
			def response = mvc.perform(post("/activity/similarActivities")
					.param("minutes", (24 * 60).toString())
					.contentType(MediaType.APPLICATION_JSON)
					.content(dtoJson))
					.andReturn().response
		then:
			response.status == 200
			def expectedBody = [
					DtoUtils.fromActivity(activity2),
					DtoUtils.fromActivity(activity3),
			]
			def actualBody = objectMapper.readValue(response.contentAsString, ActivityOutputDto[]).toList()
			actualBody == expectedBody
	}

	def createUser(Map properties = [:]) {
		def defaultProperties = [facebookId: "1234",
								 name      : "John",
								 lastname  : "Johnson",
								 activities: []]
		new User(defaultProperties << properties)
	}

	def createCategory(Map properties = [:]) {
		def defaultProperties = [name      : "Category",
								 verb      : "CategoryVerb",
								 activities: []]
		new Category(defaultProperties << properties)
	}

	def createActivity(Map properties = [:]) {
		def defaultProperties = [name           : "Aktywnosc",
								 description    : "AktywnoscDesc",
								 startDate      : LocalDateTime.parse("2015-06-14T15:25:33",DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")),
								 publicationDate: LocalDateTime.parse("2015-06-13T15:25:33",DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")),
								 published      : true,
								 category       : createCategory(),
								 users          : []]
		new Activity(defaultProperties << properties)
	}

}

