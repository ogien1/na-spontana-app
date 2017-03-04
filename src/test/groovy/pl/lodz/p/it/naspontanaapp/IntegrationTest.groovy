package pl.lodz.p.it.naspontanaapp

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
import pl.lodz.p.it.naspontanaapp.domain.CategoryOutputDto
import pl.lodz.p.it.naspontanaapp.entities.Activity
import pl.lodz.p.it.naspontanaapp.entities.Category
import pl.lodz.p.it.naspontanaapp.entities.User
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository
import pl.lodz.p.it.naspontanaapp.repository.UserRepository
import pl.lodz.p.it.naspontanaapp.utils.DateFormater
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
			def owner = userRepository.save(createUser())
			def activity1 = createActivity(owner:owner,category: category)
			def activity = activityRepository.save(activity1)
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
			def user = userRepository.save(createUser())
			def activity = activityRepository.save(createActivity(owner:user,category: category))
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
			def mActivity = createActivity(owner:mark,category: category)
			mActivity.setUsers([mark])
			def jActivity = createActivity(owner:john,category: category)
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
			def activity1 = activityRepository.save(createActivity(owner:user,category: category, users: [user]))
			def activity2 = activityRepository.save(createActivity(owner:user,category: category, users: [user]))
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

	def "should get all categories"() {
		given:
			def category1 = categoryRepository.save(createCategory(name: "cat1"))
			def category2 = categoryRepository.save(createCategory(name: "cat2"))
			def category3 = categoryRepository.save(createCategory(name: "cat3"))
			def expectedResult = [
			        new CategoryOutputDto(id:category1.id,name:category1.name,verb: category1.verb),
			        new CategoryOutputDto(id:category2.id,name:category2.name,verb: category2.verb),
			        new CategoryOutputDto(id:category3.id,name:category3.name,verb: category3.verb),
			]
		when:
			def response = mvc.perform(get("/activity/categories"))
					.andReturn().response
		then:
			response.status == 200
			println(response.contentAsString)
			expectedResult == objectMapper.readValue(response.contentAsString,CategoryOutputDto[]).toList()
	}

	def "Should get similar activities"() {
		given:
			def category = categoryRepository.save(createCategory())
			def me = userRepository.save(createUser(facebookId: 1))
			def mark = userRepository.save(createUser(facebookId: 2))
			def john = userRepository.save(createUser(facebookId: 3))
			def andrew = userRepository.save(createUser(facebookId: 4))
			def myActivity = activityRepository.save(createActivity(startDate: new LocalDateTime(100000),category:category,owner:me))
			def markActivity = activityRepository.save(createActivity(startDate: new LocalDateTime(100000).plusHours(20),category:category,owner:mark))
			def johnActivity = activityRepository.save(createActivity(startDate: new LocalDateTime(100000).plusHours(15),category:category,owner:john))
			def andrewActivity = activityRepository.save(createActivity(startDate: new LocalDateTime(100000).plusHours(25),category:category,owner:andrew))
		def dtoJson = """{
					"startDate": "${DateFormater.convert(new LocalDateTime(100000))}",
					"categoryId" : ${myActivity.category.id},
					"description" : "somedescr",
					"name" : "lalal",
					"facebookId" : ${me.facebookId},
					"minutesDiff" : ${24*60},
					"friends" : ["${john.facebookId}","${andrew.facebookId}"]
					}
			""".stripIndent()
		when:
			def response = mvc.perform(post("/activity/addSimilarActivity")
					.contentType(MediaType.APPLICATION_JSON)
					.content(dtoJson))
					.andReturn().response
		then:
			response.status == 200
			def expectedBody = [
					DtoUtils.fromActivity(johnActivity),
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
								 startDate      : LocalDateTime.parse("2015-06-14T15:25:33", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")),
								 publicationDate: LocalDateTime.parse("2015-06-13T15:25:33", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss")),
								 published      : true,
								 category       : createCategory(),
								 owner          : createUser(),
								 users          : []]
		new Activity(defaultProperties << properties)
	}

}

