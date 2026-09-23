package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.rest.UserResource;
import es.upm.miw.devops.user.DatabaseSeeder;
import es.upm.miw.devops.user.UserActiveDto;
import es.upm.miw.devops.user.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// ⚠️ 重点：必须添加 (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class UserResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void resetDatabase() {
        this.databaseSeeder.seed();
    }

    @Test
    void testDeleteUserEndpoint() {
        this.webTestClient.delete()
                .uri(UserResource.USERS + "/1")
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.get()
                .uri(UserResource.USERS + "/1")
                .exchange()
                .expectStatus().isNotFound();
    }

    // 5️⃣ Task: GET /user/{id} Endpoint Test
    @Test
    void testReadUserEndpoint() {
        this.webTestClient.get()
                .uri(UserResource.USERS + "/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo("1")
                .jsonPath("$.firstName").isEqualTo("John");
    }

    @Test
    void testReadUserEndpointNotFound() {
        this.webTestClient.get()
                .uri(UserResource.USERS + "/999")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateActiveEndpoint() {
        UserActiveDto activeDto = new UserActiveDto(true);
        this.webTestClient.put()
                .uri(UserResource.USERS + "/2/active")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(activeDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.active").isEqualTo(true);
    }

    // 8️⃣ Task: Search Billable Endpoint Test
    @Test
    void testSearchBillableEndpoint() {
        this.webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(UserResource.USERS + "/search")
                        .queryParam("billable", "true")
                        .build())
                .exchange()
                .expectStatus().isOk();
    }

    // 9 Task: PUT /user/{id} Endpoint Test
    @Test
    void testUpdateUserEndpoint() {
        UserDto userDto = new UserDto();
        userDto.setFirstName("JohnUpdated");
        userDto.setFamilyName("DoeUpdated");
        userDto.setEmail("john_updated@example.com");
        userDto.setActive(false);

        this.webTestClient.put()
                .uri(UserResource.USERS + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.firstName").isEqualTo("JohnUpdated")
                .jsonPath("$.familyName").isEqualTo("DoeUpdated")
                .jsonPath("$.email").isEqualTo("john_updated@example.com")
                .jsonPath("$.active").isEqualTo(false);
    }

    // 4️⃣ Task: PATCH /user Endpoint Test
    @Test
    void testUpdateActiveListEndpoint() {
        List<UserActiveDto> userActiveDtoList = List.of(
                new UserActiveDto("2", true),
                new UserActiveDto("3", false)
        );
        this.webTestClient.patch()
                .uri(UserResource.USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userActiveDtoList)
                .exchange()
                .expectStatus().isOk();

        assertTrue(this.databaseSeeder.getUsers().get(1).getActive());  // User 2
        assertFalse(this.databaseSeeder.getUsers().get(2).getActive()); // User 3
    }

    // 7. Bug fix Endpoint Test
    @Test
    void testUpdateActiveListPrimaryUserForbiddenEndpoint() {
        List<UserActiveDto> list = List.of(new UserActiveDto("1", false));
        this.webTestClient.patch()
                .uri(UserResource.USERS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(list)
                .exchange()
                .expectStatus().isForbidden();
    }
}