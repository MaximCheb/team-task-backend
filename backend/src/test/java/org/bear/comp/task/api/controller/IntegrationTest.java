package org.bear.comp.task.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.bear.comp.task.api.model.dto.JwtAuthenticationResponseDto;
import org.bear.comp.task.api.model.dto.SignUpRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class IntegrationTest {
    public IntegrationTest(WebApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    private final WebApplicationContext webApplicationContext;
    private final JsonMapper jsonMapper = new JsonMapper();

    @Test
    @Order(1)
    public void testCreateUser() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        var dto = SignUpRequestDto.builder().username("user").password("user-pass").email("user@user.user").phone("67676").build();

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/sign-out")
                .header("Content-Type", "application/json")
                .content(jsonMapper.writeValueAsString( dto ))).andExpect(status().is(200)).andReturn().getResponse().getContentAsString();
        var token = jsonMapper.readValue(result, JwtAuthenticationResponseDto.class).getToken();
    }

    @Test
    @Order(2)
    public void testCreateProjectManager() throws Exception {}

    @Test
    @Order(3)
    public void testCreateAdmin() throws Exception {}

    @Test
    @Order(4)
    public void createProject() throws Exception {}

    @Test
    @Order(5)
    public void addUserToProject() throws Exception {}

    @Test
    @Order(6)
    public void deleteProject() throws Exception {}

    @Test
    @Order(7)
    public void watchDeleteProject() throws Exception {}

}
