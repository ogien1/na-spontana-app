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
import pl.lodz.p.it.naspontanaapp.converting.ActivityDtoConverter
import pl.lodz.p.it.naspontanaapp.domain.ActivityOutputDto
import pl.lodz.p.it.naspontanaapp.domain.CategoryOutputDto
import pl.lodz.p.it.naspontanaapp.entities.Activity
import pl.lodz.p.it.naspontanaapp.entities.Category
import pl.lodz.p.it.naspontanaapp.entities.User
import pl.lodz.p.it.naspontanaapp.repository.ActivityRepository
import pl.lodz.p.it.naspontanaapp.repository.CategoryRepository
import pl.lodz.p.it.naspontanaapp.repository.UserRepository
import pl.lodz.p.it.naspontanaapp.utils.DateFormater
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
			def activity1 = activityBuilder().owner(owner).category(category).build()
			def activity = activityRepository.save(activity1)
			def expectedActivity = ActivityDtoConverter.toDto(activity)
		when:
			def response = mvc.perform(get("/activity/details/$activity.id")).andReturn().response
		then:
			response.status == 200
			def resultActivity = objectMapper.readValue(response.contentAsString, ActivityOutputDto)
			expectedActivity == resultActivity;
	}

	def "Should add activity"() {
		given:
			def owner = userRepository.save(createUser())
			def category = categoryRepository.save(createCategory())
			def activityJson = """{
					"categoryId": $category.id,
					"facebookId": $owner.facebookId,
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
            insertedActivity.owner == owner
            insertedActivity.users == [owner]
			insertedActivity.category == category
	}

	def "Should add user to activity"() {
		given:
			def category = categoryRepository.save(createCategory())
			def owner = userRepository.save(createUser("1"))
			def user = userRepository.save(createUser("2"))
			def activity = activityRepository.save(activityBuilder().owner(owner).category(category).build())
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
			def mark = userRepository.save(createUser("122","Mark"))
			def john = userRepository.save(createUser("123", "John"))
			def mActivity = activityBuilder().owner(mark).category(category).users([mark]).build()
			def jActivity = activityBuilder().owner(john).category(category).users([john]).build()
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
					ActivityDtoConverter.toDto(markActivity),
					ActivityDtoConverter.toDto(johnActivity)
			]
			expectedResult == objectMapper.readValue(response.contentAsString, ActivityOutputDto[]).toList()
	}

	def "Should get user's activities"() {
		given:
			def category = categoryRepository.save(createCategory())
			def user = userRepository.save(createUser("1","John",))
			def activity1 = activityRepository.save(activityBuilder().owner(user).category(category).users([user]).build())
			def activity2 = activityRepository.save(activityBuilder().owner(user).category(category).users([user]).build())
			def expectedBody = [
					ActivityDtoConverter.toDto(activity1),
					ActivityDtoConverter.toDto(activity2)
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
			def category1 = categoryRepository.save(createCategory("cat1"))
			def category2 = categoryRepository.save(createCategory("cat2"))
			def category3 = categoryRepository.save(createCategory("cat3"))
			def expectedResult = [
				new CategoryOutputDto(category1.id, category1.name, category1.verb),
				new CategoryOutputDto(category2.id, category2.name, category2.verb),
				new CategoryOutputDto(category3.id, category3.name, category3.verb),
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
			def me = userRepository.save(createUser("1"))
			def mark = userRepository.save(createUser("2"))
			def john = userRepository.save(createUser("3"))
			def andrew = userRepository.save(createUser("4"))
			def myActivity = activityRepository.save(activityBuilder().startDate(new LocalDateTime(100000)).category(category).owner(me).build())
			def markActivity = activityRepository.save(activityBuilder().startDate(new LocalDateTime(100000).plusHours(20)).category(category).owner(mark).build())
			def johnActivity = activityRepository.save(activityBuilder().startDate(new LocalDateTime(100000).plusHours(15)).category(category).owner(john).build())
			def andrewActivity = activityRepository.save(activityBuilder().startDate(new LocalDateTime(100000).plusHours(25)).category(category).owner(andrew).build())
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
					ActivityDtoConverter.toDto(johnActivity),
			]
			def actualBody = objectMapper.readValue(response.contentAsString, ActivityOutputDto[]).toList()
			actualBody == expectedBody
	}

	def createUser(String facebookId = "1234", String name = "John",activities = []) {
        User.builder()
                .name(name)
                .facebookId(facebookId)
                .lastname("Johnson")
                .activities(activities)
                .build()
	}

	def createCategory(name="Category") {
		Category.builder().name(name).verb("CategoryVerb").activities([]).build()
	}

    def activityBuilder() {
		Activity.builder()
			.name("Aktywnosc")
			.description("AktywnoscDesc")
            .startDate(LocalDateTime.parse("2015-06-14T15=25=33", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH=mm=ss")))
            .publicationDate(LocalDateTime.parse("2015-06-13T15=25=33", DateTimeFormat.forPattern("yyyy-MM-dd'T'HH=mm=ss")))
            .published(true)
			.category(createCategory())
			.owner(createUser())
            .users([])
	}

}

