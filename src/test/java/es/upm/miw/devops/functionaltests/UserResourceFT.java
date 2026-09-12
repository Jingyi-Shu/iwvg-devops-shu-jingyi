package es.upm.miw.devops.functionaltests;

import es.upm.miw.devops.rest.UserResource;
import es.upm.miw.devops.user.DatabaseSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

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
}